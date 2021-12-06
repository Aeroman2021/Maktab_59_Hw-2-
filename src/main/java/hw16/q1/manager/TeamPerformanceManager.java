package hw16.q1.manager;

import hw16.q1.dao.BaseDao;
import hw16.q1.dao.TeamPerformanceDao;
import hw16.q1.entity.TeamPerformance;

public class TeamPerformanceManager extends AbstractCrudService<TeamPerformance,Integer> {

    public void TeamPerformanceManager() {
        setBaseDao(new TeamPerformanceDao());
    }

    @Override
    public TeamPerformanceDao getBaseDao() {
        return (TeamPerformanceDao) super.getBaseDao();
    }
}
