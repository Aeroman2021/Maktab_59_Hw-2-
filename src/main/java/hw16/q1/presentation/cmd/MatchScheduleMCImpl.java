package hw16.q1.presentation.cmd;

import hw16.q1.presentation.viewer.MatchScheduleViewer;
import hw16.q1.utility.Input;

public class MatchScheduleMCImpl implements MenuCommand {

    MatchScheduleViewer matchScheduleViewer;

    public MatchScheduleMCImpl() {
        matchScheduleViewer = new MatchScheduleViewer();
    }

    @Override
    public Integer select() {
        return 6;
    }

    @Override
    public void execute() {
        boolean continueSelect = true;

        while (continueSelect) {
            System.out.println("""
                         
                         1) Add a MatchSchedule
                         2) Update a MatchSchedule
                         3) Delete a MatchSchedule
                         4) Load a MatchSchedule by id  
                         5) Load all MatchSchedule 
                         6) Back    
                    """);
            int selection = Input.getInputValue("Select a number");

            switch (selection) {
                case 1 -> matchScheduleViewer.saveMatchSchedule();
                case 2 -> matchScheduleViewer.updateMatchSchedule();
                case 3 -> matchScheduleViewer.deleteMatchSchedule();
                case 4 -> matchScheduleViewer.loadMatchScheduleById();
                case 5 -> matchScheduleViewer.loadAllMatchSchedule();
                case 6 -> continueSelect = false;
            }
        }


    }
}
