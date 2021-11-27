package hw15.q1.presentation.viewer;

import hw15.q1.entities.*;
import hw15.q1.exception.NoSufficientMoney;
import hw15.q1.manager.CreditCardManager;
import hw15.q1.manager.TransactionManager;
import hw15.q1.utilities.Input;

import java.util.Set;

public class TransactionViewer {
    TransactionManager transationManager;
    CreditCardManager creditCardManager;

    public TransactionViewer() {
        transationManager = new TransactionManager();
        creditCardManager = new CreditCardManager();
    }

    public void custExecuter(){
        System.out.println("""
                1)Create A transaction
                2)Print All transaction
                
                """);
        int choice = Input.getInputValue("Please select a number");

        switch (choice){
//            case 1-> createTransaction(cus);
//            case 2->loadTransactionByCustomerID();
        }


    }



}
