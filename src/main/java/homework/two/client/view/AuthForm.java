package homework.two.client.view;

import homework.two.client.controller.ClientController;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AuthForm extends JFrame {

   private JPanel mainPanel;
   private JButton cancelButton;
   private JButton btnOk;
   private JTextField loginTextField;
   private JPasswordField passwordTextField;

   private final ClientController clientController;

   public AuthForm(ClientController clientController) {
      this.clientController = clientController;
      initAuthWindow();
   }

   private void initAuthWindow() {
      setTitle("Network chat::Authorization");
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      setSize(300, 175);
      setResizable(false);
      setLocationRelativeTo(null);
      btnOk.addActionListener(e -> onBtnOk());
      cancelButton.addActionListener(e -> onCancelButton());
      getRootPane().setDefaultButton(btnOk);
      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            onCancelButton();
         }
      });
      setContentPane(mainPanel);
   }

   private void onCancelButton() {
      System.exit(0);
   }

   private void onBtnOk() {
      String login = loginTextField.getText().trim();
      String pass = new String(passwordTextField.getPassword()).trim();
      clientController.sendAuthMessage(login, pass);
   }

   public void showError(String errorMessage) {
      JOptionPane.showMessageDialog(this, errorMessage);
   }
}
