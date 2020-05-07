package homework.two.client.controller;

import homework.two.client.model.ClientService;
import homework.two.client.view.AuthForm;
import homework.two.client.view.ClientGUI;
import homework.two.client.view.RegistrationForm;
import homework.two.common.Command;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class ClientController {


    private static final String ALL_USERS_LIST_ITEM = "All";
    private static ClientService clientService;
    private final AuthForm authForm;
    private final RegistrationForm registrationForm;
    private final ClientGUI clientGUI;
    private String userName;

    public ClientController(String serverHost, int serverPort) {
        clientService = ClientService.getClientService(serverHost, serverPort, this);
        this.authForm = new AuthForm(this);
        this.registrationForm = new RegistrationForm(this);
        this.clientGUI = new ClientGUI(this);
    }

    public static void shutdown() {
        clientService.close();
    }


    public void initConnection() throws IOException {
        connectToServer();
        runAuthProcess();
    }

    private void runAuthProcess() {
        clientService.setSuccessfulAuthEvent(nickname -> {
            ClientController.this.setUserName(nickname);
            ClientController.this.openChat();
        });
        authForm.setVisible(true);
    }

    public void runRegRequestProcess() {
        authForm.dispose();
        registrationForm.setVisible(true);
    }

    private void openChat() {
        authForm.dispose();
        clientService.setMessageHandler(clientGUI::appendMessageIntoTextChatArea);
        clientGUI.setVisible(true);
    }

    private void setUserName(String userName) {
        SwingUtilities.invokeLater(() -> clientGUI.setTitle(userName));
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    private void connectToServer() throws IOException {
        try {
            clientService.connect();
        } catch (IOException e) {
            System.err.println("Failed to establish server connection");
            throw e;
        }
    }

    public void sendAuthMessage(String login, String password) {
        sendCommand(Command.authCommand(login, password));
    }

    public void sendRegRequestMessage() {
        sendCommand(Command.regRequestCommand());
    }

    private void sendCommand(Command command) {
        try {
            clientService.sendCommand(command);
        } catch (IOException e) {
            showErrorMessage(e.getMessage());
        }
    }

    public void sendMessage(String textMessage) {
        sendCommand(Command.broadcastMessageCommand(textMessage));
    }

    public void sendPrivateMessage(String selectedUserName, String textMessage) {
        sendCommand(Command.privateMessageCommand(selectedUserName, textMessage));
    }

    public void updateUsersList(List<String> users) {
        users.remove(userName);
        users.add(0, ALL_USERS_LIST_ITEM);
        clientGUI.updateUsers(users);
    }

    public void showErrorMessage(String errorMessage) {
        if (clientGUI.isActive()) {
            clientGUI.showError(errorMessage);
        } else if (authForm.isActive()) {
            authForm.showError(errorMessage);
        }
        System.err.println(errorMessage);
    }

    public void sendRegMessage(String firstName, String lastName, String nickName, String password) {
        sendCommand(Command.regCommand(firstName, lastName, nickName, password));
    }

    public void returnToAuth() {
        registrationForm.dispose();
        authForm.setVisible(true);
    }

    public void sendUpdateNickname(String newNickName) {
        String[] splitUserName = getUserName().split("%s");
        sendCommand(Command.updateNicknameCommand(splitUserName[0], splitUserName[1], newNickName));
    }
}
