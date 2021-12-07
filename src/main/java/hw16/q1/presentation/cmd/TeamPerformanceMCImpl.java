package hw16.q1.presentation.cmd;

import hw16.q1.presentation.viewer.TeamPerformanceViewer;
import hw16.q1.utility.Input;

public class TeamPerformanceMCImpl implements MenuCommand {

    TeamPerformanceViewer teamPerformanceViewer;

    public TeamPerformanceMCImpl() {
        teamPerformanceViewer = new TeamPerformanceViewer();
    }

    @Override
    public Integer select() {
        return 7;
    }

    @Override
    public void execute() {
        boolean continueSelect = true;

        while (continueSelect) {
            System.out.println("""
                         
                         1) Add a TeamPerformance
                         2) Update a TeamPerformance
                         3) Delete a TeamPerformance
                         4) Load a TeamPerformance by id  
                         5) Load all TeamPerformance 
                         6) Load Ranking Table
                         7) Back    
                    """);
            int selection = Input.getInputValue("Select a number");

            switch (selection) {
                case 1 -> teamPerformanceViewer.save();
                case 2 -> teamPerformanceViewer.update();
                case 3 -> teamPerformanceViewer.delete();
                case 4 -> teamPerformanceViewer.loadById();
                case 5 -> teamPerformanceViewer.loadAll();
                case 6 -> teamPerformanceViewer.printRankingTableList();
                case 7 -> continueSelect = false;
            }
        }

    }
}
