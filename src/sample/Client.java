package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by marce on 11/17/2016.
 */
public class Client extends Thread {
    private Server server;
    private boolean isConnected = false;
    private String username;
    private Socket socket;

    public Client(Server server, Socket socket) {

        this.server = server;
        this.socket = socket;
        System.out.println("client inet: " + socket.getInetAddress());
        BufferedReader in = null;

        isConnected = true;
        while (isConnected) {
            try {
                System.out.println("entered listening loop");

                String input = in.readLine();

                System.out.println("Received " + input);
            } catch (IOException e) {
                try {
                    in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                } catch (IOException xe) {
                    xe.printStackTrace();
                }

            }
        }
    }

    public void remove() {
        isConnected = false;
    }

    @Override
    public void run() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Socket getSocket() {
        return socket;
    }
}
