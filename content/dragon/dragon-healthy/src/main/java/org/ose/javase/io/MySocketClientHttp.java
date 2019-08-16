package org.ose.javase.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MySocketClientHttp {
    private String         serverAddr;
    private int            port;
    private Socket         socket;
    private BufferedReader in;
    private PrintStream    out;

    public MySocketClientHttp(String serverAddr, int port) {
        this.serverAddr = serverAddr;
        this.port = port;
    }

    public void connectServer() throws UnknownHostException, IOException {
        socket = new Socket(serverAddr, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream());
    }

    public String get(String path) throws IOException {
        // Follow the HTTP protocol of GET <path> HTTP/1.0 followed by an empty line
        StringBuilder request = new StringBuilder();
        request.append("GET ").append(path).append(" HTTP/1.0").append("\n\n");
        out.print(request.toString());

        // Read data from the server until we finish reading the document
        StringBuilder response = new StringBuilder();
        String line = null;
        while ((line = in.readLine()) != null) {
            response.append(line).append('\n');
        }

        return response.toString();
    }

    public void cleanup() {
        try {
            if (socket != null) {
                socket.close(); // also close input and output streams
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String serverAddr = "htyleo.com";
        int port = 80;
        String path = "/";

        MySocketClientHttp client = new MySocketClientHttp(serverAddr, port);
        try {
            client.connectServer();
            System.out.println(client.get(path));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.cleanup();
        }
    }
}
