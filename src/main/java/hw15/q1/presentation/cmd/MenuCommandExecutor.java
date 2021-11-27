package hw15.q1.presentation.cmd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuCommandExecutor {
   private final Map<Integer,MenuCommand> commandMap;

    public MenuCommandExecutor(List<MenuCommand> commandList) {
        Map<Integer,MenuCommand> commandMap = new HashMap<>();
        for(MenuCommand command : commandList)
            commandMap.put(command.select(),command);
        this.commandMap = commandMap;
    }

    public void execute(Integer select){
        commandMap.get(select).execute();
    }
}
