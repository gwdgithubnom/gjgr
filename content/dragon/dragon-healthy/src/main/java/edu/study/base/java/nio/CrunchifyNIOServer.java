package edu.study.base.java.nio;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by gwd on 2016/8/27.
 */
public class CrunchifyNIOServer {

    private static Logger logger=Logger.getLogger(CrunchifyNIOServer.class);

    @SuppressWarnings("unused")
    public static void services() throws IOException{
        // Selector: multiplexor of SelectableChannel objects
        Selector selector = Selector.open(); // selector is open here

        // ServerSocketChannel: selectable channel for stream-oriented listening sockets
        ServerSocketChannel crunchifySocket = ServerSocketChannel.open();
        InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", 1111);

        // Binds the channel's socket to a local address and configures the socket to listen for connections
        crunchifySocket.bind(crunchifyAddr);

        // Adjusts this channel's blocking mode.
        crunchifySocket.configureBlocking(false);

        int ops = crunchifySocket.validOps();

        SelectionKey selectKy = crunchifySocket.register(selector, ops, null);

        // Infinite loop..
        // Keep server running
        while (true) {

            logger.info("i'm a server and i'm waiting for new connection and buffer select...");
            // Selects a set of keys whose corresponding channels are ready for I/O operations
            selector.select();//waiting a remote client to send a quest

            // token representing the registration of a SelectableChannel with a Selector
            Set<SelectionKey> crunchifyKeys = selector.selectedKeys();
            Iterator<SelectionKey> crunchifyIterator = crunchifyKeys.iterator();

            while (crunchifyIterator.hasNext()) {

                SelectionKey myKey = crunchifyIterator.next();

                // Tests whether this key's channel is ready to accept a new socket connection
                if (myKey.isAcceptable()) {

                    logger.debug("Waiting to get...");
                    SocketChannel crunchifyClient = crunchifySocket.accept();

                    // Adjusts this channel's blocking mode to false
                    crunchifyClient.configureBlocking(false);

                    // Operation-set bit for read operations
                    crunchifyClient.register(selector, SelectionKey.OP_READ);
                    logger.info("Connection Accepted: " + crunchifyClient.getLocalAddress() + "\n");

                    // Tests whether this key's channel is ready for reading
                } else if (myKey.isReadable()) {

                    SocketChannel crunchifyClient = (SocketChannel) myKey.channel();
                    ByteBuffer crunchifyBuffer = ByteBuffer.allocate(256);
                    crunchifyClient.read(crunchifyBuffer);
                    String result = new String(crunchifyBuffer.array()).trim();

                    logger.info("Message received: " + result);

                    if (result.equals("Crunchify")) {
                        crunchifyClient.close();
                        logger.info("\nIt's time to close connection as we got last company name 'Crunchify'");
                        logger.info("\nServer will keep running. Try running client again to establish new connection");
                    }
                }
                crunchifyIterator.remove();
            }
        }
    }
}
