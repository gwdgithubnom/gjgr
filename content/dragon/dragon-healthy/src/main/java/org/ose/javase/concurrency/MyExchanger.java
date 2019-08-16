package org.ose.javase.concurrency;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyExchanger {
    public static void main(String[] args) throws InterruptedException {
        final int BUFSIZE = 128;
        ByteBuffer buffer1 = ByteBuffer.allocate(BUFSIZE);
        ByteBuffer buffer2 = ByteBuffer.allocate(BUFSIZE);
        Exchanger<ByteBuffer> exchanger = new Exchanger<ByteBuffer>();

        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new ExchangerProducer(buffer1, exchanger));
        es.execute(new ExchangerConsumer(buffer2, exchanger));
        TimeUnit.SECONDS.sleep(10);
        es.shutdownNow();
    }
}

class ExchangerProducer implements Runnable {
    private ByteBuffer            buffer;
    private Exchanger<ByteBuffer> exchanger;

    public ExchangerProducer(ByteBuffer buffer, Exchanger<ByteBuffer> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // fill the buffer
                TimeUnit.SECONDS.sleep(3);
                buffer.put("data".getBytes(Charset.forName(System.getProperty("file.encoding"))));
                buffer.flip();
                System.out.println("[producer] buffer filled, size = " + buffer.remaining());

                buffer = exchanger.exchange(buffer); // exchange filled buffer with an empty one
                System.out.println("[producer] buffer exchanged, size = " + buffer.remaining());
            }
        } catch (InterruptedException e) {
            // acceptable way to terminate
        }
    }
}

class ExchangerConsumer implements Runnable {
    private ByteBuffer            buffer;
    private Exchanger<ByteBuffer> exchanger;

    public ExchangerConsumer(ByteBuffer buffer, Exchanger<ByteBuffer> exchanger) {
        this.buffer = buffer; // should be empty initially
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                buffer = exchanger.exchange(buffer); // exchange empty buffer with a filled one
                System.out.println("[consumer] buffer exchanged, size = " + buffer.remaining());

                // consume the buffer
                TimeUnit.SECONDS.sleep(2);
                System.out.println("[consumer] buffer consumed, content = "
                                   + Charset.forName(System.getProperty("file.encoding"))
                                       .decode(buffer).toString());
                buffer.clear();
                System.out.println("[consumer] buffer cleared, size = " + buffer.remaining());
            }
        } catch (InterruptedException e) {
            // acceptable way to terminate
        }
    }
}
