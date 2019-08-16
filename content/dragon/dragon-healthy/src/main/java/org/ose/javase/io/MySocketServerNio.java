// http://www.javaworld.com/article/2853780/java-app-dev/socket-programming-for-scalable-systems.html?page=2

package org.ose.javase.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MySocketServerNio {
    private static final int                BUFF_SIZE       = 4096;
    private static final int                TIMEOUT_SECONDS = 5;

    private AsynchronousServerSocketChannel listener;
    private int                             port;

    public MySocketServerNio(int port) {
        this.port = port;
    }

    public void startServer() throws IOException {
        System.out.println("Starting server on port: " + port);

        listener = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));
        listener.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel ch, Void attachment) {
                // Accept the next connection
                listener.accept(null, this);

                // Greet the client
                ch.write(ByteBuffer
                    .wrap("Hello, I am Echo Server, let's have an engaging conversation!\n"
                        .getBytes()));

                // Allocate a byte buffer to read from the client
                ByteBuffer byteBuffer = ByteBuffer.allocate(BUFF_SIZE);
                try {
                    // Read the first line
                    int numBytesRead = ch.read(byteBuffer).get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
                    boolean running = true;
                    while (running) {
                        System.out.println("num of bytes read: " + numBytesRead);

                        // Make sure that we have data to read
                        // Empty line contains two bytes: a carriage return and a line feed
                        if (byteBuffer.position() > 2) {
                            // Make the buffer ready to read
                            byteBuffer.flip();

                            // Convert the buffer into a line
                            byte[] lineBytes = new byte[numBytesRead];
                            byteBuffer.get(lineBytes, 0, numBytesRead);
                            String line = new String(lineBytes);

                            // Debug
                            System.out.println("Message: " + line);

                            // Echo back to the caller
                            ch.write(ByteBuffer.wrap(line.getBytes()));

                            // Make the buffer ready to write
                            byteBuffer.clear();

                            // Read the next line
                            numBytesRead = ch.read(byteBuffer).get(TIMEOUT_SECONDS,
                                TimeUnit.SECONDS);
                        } else {
                            // An empty line signifies the end of the conversation in our protocol
                            running = false;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    // The user exceeded the TIMEOUT_SECONDS second timeout, so close the connection
                    System.out.println("Connection timed out, closing connection");
                }

                ch.write(ByteBuffer.wrap("Good Bye\n".getBytes()));
                System.out.println("End of conversation");
                try {
                    // Close the connection if we need to
                    if (ch.isOpen()) {
                        ch.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
            }
        });
    }

    public void cleanup() {
        try {
            if (listener != null) {
                listener.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 8888;

        MySocketServerNio server = new MySocketServerNio(port);
        try {
            server.startServer();
        } catch (IOException e) {
            server.cleanup();
        }

        try {
            TimeUnit.SECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
