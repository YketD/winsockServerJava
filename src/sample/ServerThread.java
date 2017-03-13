package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    Socket client = null;
    PrintWriter output;
    BufferedReader input;

    public ServerThread(Socket client) {
        this.client = client;
    }

    public void run() {
        System.out.print("Accepted connection. ");

        try {
            // open a new PrintWriter and BufferedReader on the socket
            output = new PrintWriter(client.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.print("Reader and writer created. ");

            String inString;
            // read the command from the client
            while  ((inString = input.readLine()) == null) {
                System.out.println("Read command " + inString);

                // run the command using CommandExecutor and get its output
                String outString = inString;
                // send the result of the command to the client
                System.out.println(outString);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
         finally {
            // close the connection to the client
            try {
                client.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Output closed.");
        }

    }
}
