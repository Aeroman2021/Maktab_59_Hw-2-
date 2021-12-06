package hw16.q1.dao;

import hw16.q1.entity.City;
import hw16.q1.entity.Team;
import hw16.q1.utility.EMFSigleton;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class CityDao implements BaseDao<City, Integer> {

    EntityManager em = EMFSigleton.getEntityManager();

    @Override
    public void save(City entity) {

    }

    @Override
    public void update(City entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public City loadById(Integer integer) {
        return null;
    }

    @Override
    public List<City> loadAll() {
        return null;
    }

    @Override
    public boolean entityIsExist(City entity) {
        return false;
    }

    public void PrintListOfTeamsForInCity() {

//    "SELECT premire_league.city.name as 'city' ," +
//            "  count(city_id) as 'number_of_teams' " +
//            "FROM  premire_league.team  JOIN  premire_league.city  " +
//            " ON  premire_league.team.city_id = premire_league.city.id" +
//            " GROUP BY city_id " +
//            " ORDER BY count(city_id) DESC";

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> c = cb.createQuery(Object[].class);
        Root<Team> team = c.from(Team.class);
        Join<Team, City> city = team.join("city");
        CriteriaQuery<Object[]> criteriaQuery = c.multiselect(city.get("name"), cb.count(city.get("id")))
                .groupBy(city.get("id"))
                .orderBy(cb.desc(cb.count(city.get("id"))));
        List<Object[]> objects = em.createQuery(c).getResultList();
        System.out.print("City");
        System.out.println("\t Number of teams");
        System.out.println("-------------------");
        for (Object[] object : objects) {
            System.out.println(object[0] + "  " + object[1]);
        }



    }


}
