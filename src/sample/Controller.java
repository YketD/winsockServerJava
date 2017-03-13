package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Scanner;

public class Controller {
    private Scanner scanner = new Scanner(System.in);
    private Server server = new Server(Config.SERVER_PORT);
    @FXML
    private Button sendButton;
    @FXML
    private void handleSendButton(ActionEvent event){
        System.out.print("Starting server...");
        server.start();

        String input;
        while(true)
        {
            input = scanner.nextLine();

            if (input.equals("stop"))
            {
                if (server != null) server.cleanup();
                System.out.print("Server stopped");
            }

            else if (input.equals("clients"))
            {
                if (server != null) server.listClients();
            }
        }

    }
}
