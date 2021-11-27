package hw15.q1.presentation;

import hw15.q1.presentation.cmd.*;
import hw15.q1.utilities.Input;

import java.util.List;

public class Menu {

    private final MenuCommandExecutor menuCommandExecutor;

    public Menu() {
        this.menuCommandExecutor = new MenuCommandExecutor(initialCommand());
    }

    public List<MenuCommand> initialCommand() {
        return List.of(
                new HelpCommand(),
                new CustomerCommandMCImp(),
                new ClerkCommandMCImp(),
                new BankManagerMCImp());
    }

    public void startApplication() {
        boolean showMenu = true;
        while (showMenu) {
            menu();
            showMenu = chooseMenu();
        }
    }

    public boolean chooseMenu() {
        int inputValue = Input.getInputValue(" Please select a number");
        if (inputValue > 4)
            return false;
        menuCommandExecutor.execute(inputValue);
        return true;
    }

    public void menu() {
        System.out.println("""
                -----Select a rule----
                1) Help
                2) Customer
                3) Bank clerk
                4) Bank manager
                5) Exit
                """);
    }

}
