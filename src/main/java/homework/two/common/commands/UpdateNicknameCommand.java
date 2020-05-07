package homework.two.common.commands;

public class UpdateNicknameCommand {
    private String firstName;
    private String lastName;
    private String newNickName;

    public UpdateNicknameCommand(String firstName, String lastName, String newNickName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.newNickName = newNickName;
    }
}
