package hw15.q1.presentation.cmd;

import hw15.q1.presentation.viewer.AccountViewer;
import hw15.q1.presentation.viewer.CustomerViewer;
import hw15.q1.presentation.viewer.TransactionViewer;
import hw15.q1.utilities.Input;

public class CustomerCommandMCImp implements MenuCommand {


    CustomerViewer customerViewer;
    AccountViewer accountViewer;
    TransactionViewer transactionViewer;

    public CustomerCommandMCImp() {
        customerViewer = new CustomerViewer();
        accountViewer = new AccountViewer();
        transactionViewer = new TransactionViewer();
    }

    @Override
    public Integer select() {
        return 2;
    }

    @Override
    public void execute() {
        System.out.println("""
                select an action
                1) Register
                2) Login
                3) Back
                """);
        int choice = 0;
        while (choice!=3) {
            choice = Input.getInputValue("Select a Number");
            switch (choice) {
                case 1 -> customerViewer.registerNewCustomer();
                case 2 -> customerViewer.login();
            }
        }
    }
}
