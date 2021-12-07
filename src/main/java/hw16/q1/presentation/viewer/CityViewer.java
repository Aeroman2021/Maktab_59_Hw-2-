package hw16.q1.presentation.viewer;

import hw16.q1.entity.City;
import hw16.q1.manager.CityManager;
import hw16.q1.utility.EMFSigleton;
import hw16.q1.utility.Input;

import javax.persistence.EntityManager;
import java.util.List;

public class CityViewer {
    CityManager cityManager;
    EntityManager em = EMFSigleton.getEntityManager();

    public CityViewer() {
        cityManager = new CityManager();
    }

    public void saveCity() {
        String name = Input.getStringInputValue("Enter name of the city");
        City city = new City();
        city.setName(name);
        cityManager.saveOrUpdate(city);


    }

    public void updateCity() {

    }

    public void deleteCity() {
        int cityId = Input.getInputValue("Enter city id");
        cityManager.delete(cityId);
    }

    public void loadCityById() {
        int cityId = Input.getInputValue("Enter city id");
        System.out.println(cityManager.loadById(cityId));
    }

    public void loadAll() {
        for (City city : cityManager.loadAll())
            System.out.println(city);
    }

    public void PrintListOfCitiesAndTheirTeams() {
        cityManager.getBaseDao().PrintListOfTeamsForInCity();
    }
}
