package hw15.q1.presentation;

import hw15.q1.dao.BankManagerDao;
import hw15.q1.dao.BranchDao;
import hw15.q1.entities.Account;
import hw15.q1.entities.BankManager;
import hw15.q1.entities.Branch;
import hw15.q1.manager.BankManagerManager;
import hw15.q1.manager.BranchManager;
import hw15.q1.presentation.cmd.*;
import hw15.q1.utilities.Input;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Menu {

    private final MenuCommandExecutor menuCommandExecutor;
    BranchManager branchManager;
    BankManagerManager bankManagerManager;

    public Menu() {
        this.menuCommandExecutor = new MenuCommandExecutor(initialCommand());
        this.branchManager = new BranchManager();
        this.bankManagerManager = new BankManagerManager();
    }

    public List<MenuCommand> initialCommand() {
        return List.of(
                new HelpCommand(),
                new CustomerCommandMCImp(),
                new ClerkCommandMCImp(),
                new BankManagerMCImp());
    }

    public void startApplication() {
//        initializer();
        boolean showMenu = true;
        while (showMenu) {
            menu();
            showMenu = chooseMenu();
        }
    }

    public boolean chooseMenu() {
        int inputValue = Input.getInputValue(" Please select a number");
        if (inputValue > 4)
            return false;
        menuCommandExecutor.execute(inputValue);
        return true;
    }

    public void menu() {
        System.out.println("""
                -----Select a rule----
                1) Help
                2) Customer
                3) Bank clerk
                4) Bank manager
                5) Exit
                """);
    }



    public void initializer(){
        Branch branch = new Branch();
//        branchManager.save(branch);
//        BankManager bankManager = new BankManager();
//        Branch branch1 = branchManager.loadById(1);
//        branch.setBankManager(bankManager);
//        bankManager.setBranch(branch);
//        bankManagerManager.save(bankManager);
//        Account account = new Account();
//        account.setBalance(45000);
//        account.setNumber(789123);
//        Account account1 = new Account();
//        account1.setBalance(50000);
//        account1.setNumber(123456);
//        Set<Account> accounts = new HashSet<>();
//        accounts.add(account);
//        accounts.add(account1);
//        branch.setAccounts(accounts);
//
//        branchManager.save(branch);

    }

}
