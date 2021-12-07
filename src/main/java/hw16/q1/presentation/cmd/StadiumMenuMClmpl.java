package hw16.q1.presentation.cmd;

import hw16.q1.presentation.viewer.StadiumViewer;
import hw16.q1.utility.Input;

public class StadiumMenuMClmpl implements MenuCommand{
    StadiumViewer stadiumViewer;

    public StadiumMenuMClmpl() {
        stadiumViewer = new StadiumViewer();
    }

    @Override
    public Integer select() {
        return 2;
    }

    @Override
    public void execute() {
        boolean continueSelect = true;

        while (continueSelect) {
            System.out.println("""
                         
                         1) Add a stadium
                         2) Update a stadium
                         3) Delete a stadium
                         4) Load a stadium by id  
                         5) Load all stadiums 
                         6) Back    
                    """);
            int selection = Input.getInputValue("Select a number");

            switch (selection) {
                case 1 -> stadiumViewer.saveStadium();
                case 2 -> stadiumViewer.updateStadium();
                case 3 -> stadiumViewer.deleteStadium();
                case 4 -> stadiumViewer.loadStadiumById();
                case 5 -> stadiumViewer.loadAllStadiums();
                case 6 -> continueSelect = false;
            }
        }
    }
}
