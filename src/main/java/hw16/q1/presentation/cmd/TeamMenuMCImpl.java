package hw16.q1.presentation.cmd;

import hw16.q1.presentation.viewer.TeamViewer;
import hw16.q1.utility.Input;

public class TeamMenuMCImpl implements MenuCommand{

    TeamViewer teamViewer;

    public TeamMenuMCImpl() {
        teamViewer = new TeamViewer();
    }

    @Override
    public Integer select() {
        return 3;
    }

    @Override
    public void execute() {
        boolean continueSelect = true;

        while (continueSelect) {
            System.out.println("""
                         
                         1) Add a team
                         2) Update a team
                         3) Delete a team
                         4) Load a team by id  
                         5) Load all teams 
                         6) Back    
                    """);
            int selection = Input.getInputValue("Select a number");

            switch (selection) {
                case 1 -> teamViewer.saveTeam();
                case 2 -> teamViewer.updateTeam();
                case 3 -> teamViewer.deleteTeam();
                case 4 -> teamViewer.loadTeamById();
                case 5 -> teamViewer.loadAll();
                case 6 -> continueSelect = false;

            }
        }

    }
}
