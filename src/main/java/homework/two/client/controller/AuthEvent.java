package homework.two.client.controller;

@FunctionalInterface
public interface AuthEvent {
   void authIsSuccessful(String userName);
}
