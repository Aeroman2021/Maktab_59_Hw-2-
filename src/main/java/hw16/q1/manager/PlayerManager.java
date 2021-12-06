package hw16.q1.manager;

import hw16.q1.dao.PlayerDao;
import hw16.q1.entity.Player;

import java.util.List;

public class PlayerManager extends AbstractCrudService<Player,Integer>{
    public PlayerManager() {
        setBaseDao(new PlayerDao());
    }

    @Override
    public PlayerDao getBaseDao() {
        return (PlayerDao) super.getBaseDao();
    }

    public List<Player> printListOfPlayersBasedOnSalary(){
        return getBaseDao().SortPlayerBasedOnSalary();
    }

    public Player printTheMostPaidPlayer(){
        return getBaseDao().findMaxPaidPlayer();
    }
}
