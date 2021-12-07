package hw16.q1.presentation.viewer;

import hw16.q1.entity.City;
import hw16.q1.entity.Stadium;
import hw16.q1.manager.StadiumManager;
import hw16.q1.utility.EMFSigleton;
import hw16.q1.utility.Input;

import javax.persistence.EntityManager;
import java.util.List;

public class StadiumViewer {
    StadiumManager stadiumManager;
    EntityManager em = EMFSigleton.getEntityManager();

    public StadiumViewer() {
        stadiumManager = new StadiumManager();
    }

    public void saveStadium(){
        String name = Input.getStringInputValue("Enter name of the stadium");
        int cityId = Input.getInputValue("Enter the id of the city");
        City city = em.find(City.class, cityId);
        Long population = Input.getLongValue("Enter population of the stadium");
        Stadium stadium = new Stadium(name,city,population);
        stadiumManager.saveOrUpdate(stadium);

    }

    public void updateStadium(){

    }

    public void deleteStadium(){
        int id = Input.getInputValue("Enter the id of the stadium");
        stadiumManager.delete(id);
    }

    public void loadStadiumById(){
        int id = Input.getInputValue("Enter the id of the stadium");
        System.out.println(stadiumManager.loadById(id));
    }

    public void loadAllStadiums(){
        for (Stadium stadium : stadiumManager.loadAll()) {
            System.out.println(stadium);
        }
    }
}
