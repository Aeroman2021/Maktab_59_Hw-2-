package hw16.q1.manager;

import hw16.q1.dao.BaseDao;
import hw16.q1.dao.TeamDao;
import hw16.q1.entity.Team;

public class TeamManager extends AbstractCrudService<Team,Integer> {
    public TeamManager() {
        setBaseDao(new TeamDao());
    }

    @Override
    public TeamDao getBaseDao() {
        return (TeamDao) super.getBaseDao();
    }
}
