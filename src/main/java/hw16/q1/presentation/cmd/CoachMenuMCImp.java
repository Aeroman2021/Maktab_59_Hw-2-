package hw16.q1.presentation.cmd;

import hw16.q1.presentation.viewer.CoachViewer;
import hw16.q1.utility.Input;

public class CoachMenuMCImp implements MenuCommand {

    CoachViewer coachViewer;

    public CoachMenuMCImp() {
        coachViewer = new CoachViewer();
    }

    @Override
    public Integer select() {
        return 4;
    }

    @Override
    public void execute() {
        boolean continueSelect = true;

        while (continueSelect) {
            System.out.println("""
                         
                         1) Add a Coach
                         2) Update a coach information
                         3) Delete a coach record
                         4) Load a coach by id  
                         5) Load all coaches
                         6) print list of the coaches base on their contract
                         7) print the most paid coach   
                         8) Back  
                    """);
            int selection = Input.getInputValue("Select a number");

            switch (selection) {
                case 1 -> coachViewer.saveCoach();
                case 2 -> coachViewer.updateCoach();
                case 3 -> coachViewer.deleteCoach();
                case 4 -> coachViewer.loadCoachById();
                case 5 -> coachViewer.loadAll();
                case 6 -> coachViewer.printCoachListBaseOnContract();
                case 7 -> coachViewer.printTheMostPaidCoach();
                case 8 -> continueSelect = false;

            }
        }
    }
}
