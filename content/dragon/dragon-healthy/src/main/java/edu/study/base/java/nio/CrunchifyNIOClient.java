package edu.study.base.java.nio;

import org.apache.log4j.Logger;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * Created by gwd on 8/28/2016.
 */
public class CrunchifyNIOClient {

    private static Logger logger= Logger.getLogger(CrunchifyNIOClient.class);

    public static void getServices() throws Exception{
        InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", 1111);
        SocketChannel crunchifyClient = SocketChannel.open(crunchifyAddr);

        logger.debug("Connecting to Server on port 1111...");

        ArrayList<String> companyDetails = new ArrayList<String>();

        // create a ArrayList with companyName list
        companyDetails.add("Facebook");
        companyDetails.add("Twitter");
        companyDetails.add("IBM");
        companyDetails.add("Google");
        companyDetails.add("Crunchify");

        for (String companyName : companyDetails) {

            byte[] message = new String(companyName).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            crunchifyClient.write(buffer);

            logger.debug("sending: " + companyName);
            buffer.clear();

            // wait for 2 seconds before sending next message
            Thread.sleep(2000);
        }
        crunchifyClient.close();
    }

}
