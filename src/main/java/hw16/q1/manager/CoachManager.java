package hw16.q1.manager;


import hw16.q1.dao.CoachDao;
import hw16.q1.entity.Coach;

import java.util.List;

public class CoachManager extends AbstractCrudService<Coach,Integer>{


    public CoachManager() {
        setBaseDao(new CoachDao());
    }

    @Override
    public CoachDao getBaseDao() {
        return (CoachDao) super.getBaseDao();
    }

    public List<Coach> printCoachListBasedOnContract(){
        return getBaseDao().SortCoachBasedOnContract();
    }

    public Coach PrintMostPaidCoach(){
        return getBaseDao().findMaxPaidCoach();
    }
}
