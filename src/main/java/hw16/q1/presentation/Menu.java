package hw16.q1.presentation;

import hw16.q1.presentation.cmd.*;
import hw16.q1.utility.Input;

import java.util.List;

public class Menu {
    MenuCommandExecutor menuCommandExecutor;

    public Menu() {
        menuCommandExecutor = new MenuCommandExecutor(initialCommands());
    }


    private List<MenuCommand> initialCommands() {
        return List.of(
                new CityMenuMCImpl(),
                new StadiumMenuMClmpl(),
                new TeamMenuMCImpl(),
                new CoachMenuMCImp(),
                new PlayerMenuMCImpl(),
                new MatchScheduleMCImpl(),
                new TeamPerformanceMCImpl());
    }

    public void startApplication() {
        boolean showMenu = true;
        while (showMenu) {
            menu();
            chooseMenu();
        }
    }

    public boolean chooseMenu() {
        int selection = Input.getInputValue("please select a number");
        if (selection > 8)
            return false;
        menuCommandExecutor.execute(selection);
        return true;
    }

    public void menu() {
        System.out.println("""
                                
                1) City
                2) Stadium
                3) Team
                4) Coach
                5) Player
                6) MatchSchedule
                7) Teams performances
                8) Exit
                               """);
    }
}
