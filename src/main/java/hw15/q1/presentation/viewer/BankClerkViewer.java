package hw15.q1.presentation.viewer;

import hw15.q1.entities.*;
import hw15.q1.exception.NoSufficientMoney;
import hw15.q1.manager.AccountManager;
import hw15.q1.manager.CreditCardManager;
import hw15.q1.manager.TransactionManager;
import hw15.q1.utilities.Input;
import hw15.q1.utilities.NumberGenerator;

import java.util.List;
import java.util.Set;

public class BankClerkViewer {

    AccountManager accountManager;
    CreditCardManager creditCardManager;
    TransactionManager transactionManager;

    public BankClerkViewer() {
        this.accountManager = new AccountManager();
        this.creditCardManager = new CreditCardManager();
        this.transactionManager = new TransactionManager();
    }

    public void createAccount(Customer customer) {
        double balance = Input.getDoubleValue("Enter the balance for your account");
        Account newAccount = new Account();
        newAccount.setBalance(balance);
        customer.addAccounts(newAccount);
        accountManager.save(newAccount);
    }

    public void updateAccount(Customer customer, Double balance) {
        customer.getAccounts().forEach(System.out::println);

        int accountNumber = Input.getInputValue("Enter account Number");
        Account foundAccount = findAccountByAccNumber(customer, accountNumber);
        foundAccount.setBalance(balance);

        accountManager.update(accountNumber, foundAccount);
    }


    public void createCreditCard(Customer customer) {
        Long creditCardNumber = NumberGenerator.generateNumber();
        int password = Input.getInputValue("Enter four-digit Password");
        int CVV2 = Input.getInputValue("Enter four-digit CVV2");
        customer.getAccounts().forEach(System.out::println);

        int accountNumber = Input.getInputValue("Enter account Number");
        Account foundAccount = findAccountByAccNumber(customer, accountNumber);
        CreditCard creditCard = new CreditCard();

        creditCard.setNumber(creditCardNumber);
        creditCard.setPassword(password);
        creditCard.setCVV2(CVV2);
        foundAccount.setCreditCard(creditCard);
        creditCardManager.createCreditCard(creditCard, creditCardNumber);
    }

    public void updateCreditCard(Customer customer) {
        Long creditCardNumber = Input.getLongValue("Enter credit card number");
        int password = Input.getInputValue("Enter new four-digit Password");
        int CVV2 = Input.getInputValue("Enter new four-digit CVV2");

        int accountNumber = Input.getInputValue("Enter account Number");
        Account foundAccount = findAccountByAccNumber(customer, accountNumber);

        CreditCard newCreditCard = new CreditCard();
        newCreditCard.setPassword(password);
        newCreditCard.setCVV2(CVV2);
        foundAccount.setCreditCard(newCreditCard);
        creditCardManager.update(creditCardNumber, newCreditCard);
    }


    public void createTransaction(Customer customer) {
        int choice = 0;
        while (choice != 4) {
            System.out.println("""
                    1) Withdraw
                    2) Deposit
                    3) Transfer
                    4) Back
                                  
                    """);
            choice = Input.getInputValue(" Select a number");

            switch (choice) {
                case 1 -> withdrawTransaction(customer);
                case 2 -> depositTransaction(customer);
                case 3 -> transferMoney(customer);
            }
        }
    }


    private void withdrawTransaction(Customer customer) {

        Set<Account> accounts = customer.getAccounts();
        accounts.forEach(a -> System.out.println(a.getCreditCard().getNumber()));
        Long cardNumber = Input.getLongValue("Enter your card id");
        double amount = Input.getDoubleValue("Enter amount");

        CreditCard creditCard = creditCardManager.loadById(cardNumber);
        Account account = creditCard.getAccount();
        double balance = account.getBalance();
        if (balance > amount) {
            balance = -amount;
            account.setBalance(balance);
            Transaction newTransaction = new Transaction();
            newTransaction.setType(TransactionType.WITHDRAW);
            newTransaction.setAmount(amount);
            newTransaction.setDestAccNumber(cardNumber);
            newTransaction.setSrcAccNumber(cardNumber);
            transactionManager.save(newTransaction);
        } else
            throw new NoSufficientMoney("NoSufficientMoneyExistInAccount");
    }

    public void depositTransaction(Customer customer) {

        Set<Account> accounts = customer.getAccounts();
        accounts.forEach(a -> System.out.println(a.getCreditCard().getNumber()));
        Long cardNumber = Input.getLongValue("Enter your card id");
        double amount = Input.getDoubleValue("Enter amount");

        CreditCard creditCard = creditCardManager.loadById(cardNumber);
        Account account = creditCard.getAccount();
        double balance = account.getBalance();
        if (balance > amount) {
            balance = +amount;
            account.setBalance(balance);
            Transaction newTransaction = new Transaction();
            newTransaction.setType(TransactionType.DEPOSIT);
            newTransaction.setAmount(amount);
            newTransaction.setDestAccNumber(cardNumber);
            newTransaction.setSrcAccNumber(cardNumber);
            transactionManager.save(newTransaction);
        } else
            throw new NoSufficientMoney("NoSufficientMoneyExistInAccount");
    }

    public void transferMoney(Customer customer) {

        Set<Account> accounts = customer.getAccounts();
        accounts.forEach(a -> System.out.println(a.getCreditCard().getNumber()));
        Long sourceCardNumber = Input.getLongValue("Enter your source card id");
        CreditCard sourceCreditCard = creditCardManager.loadById(sourceCardNumber);
        Account sourceAccount = sourceCreditCard.getAccount();

        Long destinationCardNumber = Input.getLongValue("Enter your source card id");
        CreditCard destinationCreditCard = creditCardManager.loadById(destinationCardNumber);
        Account destAccount = destinationCreditCard.getAccount();

        double amount = Input.getDoubleValue("Enter amount of money you want to transfer");

        double sourceAccountBalance = sourceAccount.getBalance();
        double destAccountBalance = destAccount.getBalance();

        if (sourceAccountBalance > amount) {
            sourceAccountBalance = -(amount + 600);
            destAccountBalance = +amount;

            sourceAccount.setBalance(sourceAccountBalance);
            destAccount.setBalance(destAccountBalance);
            Transaction newTransaction = new Transaction();
            newTransaction.setType(TransactionType.TRANSFER);
            newTransaction.setAmount(amount);
            newTransaction.setDestAccNumber(destinationCardNumber);
            newTransaction.setSrcAccNumber(sourceCardNumber);
            transactionManager.save(newTransaction);
        } else
            throw new NoSufficientMoney("NoSufficientMoneyExistInAccount");
    }


    public void loadTransactionByCustomerID() {

    }

    public void printCardInformation(Customer customer) {
        int accountNumber = Input.getInputValue("Enter account Number");
        Account acc = findAccountByAccNumber(customer, accountNumber);
        CreditCard creditCard = acc.getCreditCard();
        System.out.println(creditCard);

    }

    public void printTransactionsByDate(Customer customer) {
        int accountNumber = Input.getInputValue("Enter account Number");
        Account acc = findAccountByAccNumber(customer, accountNumber);
        CreditCard creditCard = acc.getCreditCard();
        List<Transaction> transactions = creditCard.getTransactions();

    }


    private Account findAccountByAccNumber(Customer customer, int accNumber) {
        for (Account account : customer.getAccounts()) {
            if (account.getNumber() == accNumber)
                return account;
        }
        return null;
    }


}
