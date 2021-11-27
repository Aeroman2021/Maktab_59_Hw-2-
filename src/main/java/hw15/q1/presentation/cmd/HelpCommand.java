package hw15.q1.presentation.cmd;

public class HelpCommand implements MenuCommand{



    @Override
    public Integer select() {
        return 1;
    }

    @Override
    public void execute() {
        System.out.println("Welcome to the Bank application. Please select a number between 1 to 5");
    }
}
