package hw16.q1.presentation.cmd;

import hw16.q1.presentation.viewer.PlayerViewer;
import hw16.q1.utility.Input;

public class PlayerMenuMCImpl implements  MenuCommand {

    PlayerViewer playerViewer;

    public PlayerMenuMCImpl() {
        playerViewer = new PlayerViewer();
    }

    @Override
    public Integer select() {
        return 5;
    }

    @Override
    public void execute() {
        boolean continueSelect = true;

        while (continueSelect) {
            System.out.println("""
                         
                         1) Add a player
                         2) Update a player information
                         3) Delete a player record
                         4) Load a player by id  
                         5) Load all players
                         6) print list of the players base on their contract
                         7) print the most paid players   
                         8) Back  
                    """);
            int selection = Input.getInputValue("Select a number");

            switch (selection) {
                case 1 -> playerViewer.savePlayer();
                case 2 -> playerViewer.updatePlayer();
                case 3 -> playerViewer.deletePlayer();
                case 4 -> playerViewer.loadPlayerById();
                case 5 -> playerViewer.loadAllPlayers();
                case 6 -> playerViewer.printPlayersListBaseOnContract();
                case 7 -> playerViewer.printTheMostPaidPlayer();
                case 8 -> continueSelect = false;

            }
        }

    }
}
