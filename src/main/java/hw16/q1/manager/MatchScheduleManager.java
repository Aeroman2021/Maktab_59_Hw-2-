package hw16.q1.manager;

import hw16.q1.dao.BaseDao;
import hw16.q1.dao.MatchScheduleDao;
import hw16.q1.entity.MatchSchedule;

public class MatchScheduleManager extends AbstractCrudService<MatchSchedule,Integer> {
    public MatchScheduleManager() {
        setBaseDao(new MatchScheduleDao());
    }

    @Override
    public MatchScheduleDao getBaseDao() {
        return (MatchScheduleDao) super.getBaseDao();
    }
}
