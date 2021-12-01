package hw15.q1.presentation.viewer;

import hw15.q1.entities.Account;
import hw15.q1.entities.Customer;
import hw15.q1.manager.AccountManager;
import hw15.q1.utilities.Input;

import java.util.Scanner;

public class AccountViewer extends AccountManager {


    Scanner scanner = new Scanner(System.in);
    AccountManager accountManager;

    public AccountViewer() {
        accountManager = new AccountManager();
    }



//    public void createAccount(Customer customer){
//        Long accNumber = Input.getLongValue("Enter The account Number");
//        double balance = Input.getDoubleValue("Enter the balance for your account");
//        Account newAccount = new Account();
//        newAccount.setNumber(accNumber);
//        newAccount.setBalance(balance);
//        customer.setAccounts(newAccount);
//        accountManager.save(newAccount);
//    }

    public void updateAccount(Customer customer){
        customer.getAccounts().forEach(System.out::println);
        int accNumber = Input.getInputValue("Enter account number");
        Account foundAccount = findAccountByAccNumber(customer, accNumber);

    }

    private Account findAccountByAccNumber(Customer customer, int accNumber) {
        for (Account account : customer.getAccounts()) {
            if(account.getNumber()== accNumber)
                return account;
        }
        return null;
    }


//    public void deleteAccount(){
//        Long accNumber = Input.getLongValue("Enter The account Number");
//        accountManager.delete(accNumber);
//    }

//    public Account loadAccountById(){
//        Long accNumber = Input.getLongValue("Enter The account Number");
//        return accountManager.loadById(accNumber);
//    }

//    public void printAccountInformation(){
//        Long accNumber = Input.getLongValue("Enter The account Number");
//        System.out.println(accountManager.loadById(accNumber));
//
//    }


}
