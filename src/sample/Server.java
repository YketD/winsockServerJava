package sample;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by marce on 11/17/2016.
 */
public class Server extends Thread
{
    private int port;
    private ServerSocket serverSocket;
    private boolean isStarted = false;
    private HashMap<String, Client> clients = new HashMap<>();

    public Server(int port)
    {
        this.port = port;
    }

    public void listClients()
    {
        System.out.println("-----------------------------");
        System.out.println("List of connected clients:");
        for(Map.Entry<String, Client> client : clients.entrySet()) {
            System.out.println(client.getValue().getUsername());
        }
        System.out.println("-----------------------------");
    }

    public void cleanup()
    {
        isStarted = false;
    }

    public Client getClient(String username)
    {
        return clients.get(username);
    }

    @Override
    public void run()
    {
        try
        {
            serverSocket = new ServerSocket(this.port);
            isStarted = true;

            System.out.println(" started");
            System.out.println("listening on: "+ serverSocket.getInetAddress());

            while(isStarted)
            {
                Client client = new Client(this, serverSocket.accept());
                client.start();
                clients.put(client.getUsername(), client);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            for(Map.Entry<String, Client> client : clients.entrySet()) {
                client.getValue().remove();
            }

            serverSocket.close();
            clients.clear();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
