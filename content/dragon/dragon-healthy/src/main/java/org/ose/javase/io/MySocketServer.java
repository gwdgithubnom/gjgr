package org.ose.javase.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MySocketServer extends Thread {
    private int          port;
    private ServerSocket serverSocket;
    private boolean      running = false;

    public MySocketServer(int port) {
        this.port = port;
    }

    public void startServer() throws IOException {
        serverSocket = new ServerSocket(port);
        this.start();
    }

    @Override
    public void run() {
        running = true;
        try {
            while (running) {
                System.out.println("Listening for a connection");

                // Call accept() to receive the next connection (blocking call)
                Socket socket = serverSocket.accept();

                // Pass the socket to the RequestHandler thread for processing
                RequestHandler requestHandler = new RequestHandler(socket);
                requestHandler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanup();
        }
    }

    public void stopServer() {
        running = false;
        this.interrupt();
    }

    public void cleanup() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class RequestHandler extends Thread {
        private Socket socket;

        RequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                System.out.println("Received a connection");

                // Get input and output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream());

                // Write out our header to the client
                out.println("Echo Server 1.0");
                out.flush();

                // Echo lines back to the client until the client closes the connection or we receive an empty line
                String line = in.readLine();
                while (line != null && line.length() > 0) {
                    out.println("Echo: " + line);
                    out.flush();
                    line = in.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cleanup();
                System.out.println("Connection closed");
            }
        }

        private void cleanup() {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int port = 8888;

        System.out.println("Starting server on port: " + port);
        MySocketServer server = new MySocketServer(port);
        try {
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.stopServer();
        }
    }
}
