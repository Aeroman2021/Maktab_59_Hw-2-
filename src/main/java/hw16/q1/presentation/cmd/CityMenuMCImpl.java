package hw16.q1.presentation.cmd;

import hw16.q1.presentation.viewer.CityViewer;
import hw16.q1.utility.Input;

public class CityMenuMCImpl implements MenuCommand {


    private CityViewer cityViewer;

    public CityMenuMCImpl() {
        cityViewer = new CityViewer();
    }

    @Override
    public Integer select() {
        return 1;
    }

    @Override
    public void execute() {
        boolean continueSelect = true;

        while (continueSelect) {
            System.out.println("""
                         
                         1) Add a city
                         2) Update a city
                         3) Delete a city
                         4) Load a city by id  
                         5) Load all cities 
                         6) print cities and the list of their teams in the league
                         7) Back    
                    """);
            int selection = Input.getInputValue("Select a number");

            switch (selection) {
                case 1 -> cityViewer.saveCity();
                case 2 -> cityViewer.updateCity();
                case 3 -> cityViewer.deleteCity();
                case 4 -> cityViewer.loadCityById();
                case 5 -> cityViewer.loadAll();
                case 6-> cityViewer.PrintListOfCitiesAndTheirTeams();
                case 7 -> continueSelect = false;

            }
        }
    }
}
