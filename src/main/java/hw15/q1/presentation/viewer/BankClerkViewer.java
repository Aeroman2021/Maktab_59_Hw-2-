package hw15.q1.presentation.viewer;

import hw15.q1.entities.*;
import hw15.q1.exception.DataNotFound;
import hw15.q1.exception.NoSufficientMoney;
import hw15.q1.manager.*;
import hw15.q1.utilities.Input;

public class BankClerkViewer {

    AccountManager accountManager;
    CreditCardManager creditCardManager;
    TransactionManager transactionManager;
    BranchManager branchManager;
    CustomerManager customerManager;
    Customer customer;

    public BankClerkViewer() {
        this.accountManager = new AccountManager();
        this.creditCardManager = new CreditCardManager();
        this.transactionManager = new TransactionManager();
        this.branchManager = new BranchManager();
        this.customerManager = new CustomerManager();
        this.customer = new Customer();
    }

    public void createAccount(Customer customer) {
        Double balance = Input.getDoubleValue("Enter the balance for your account");
        Integer accNumber = Input.getInputValue("Enter account number");

        Integer branchId = Input.getInputValue("Enter Branch id");
        Branch branch = branchManager.loadById(branchId);
        Account newAccount = new Account(accNumber, balance);
        branch.addAccounts(newAccount);
        branchManager.save(branch);

        customer.addAccounts(newAccount);
        customerManager.getBaseDao().update(customer);

    }

    public void updateAccount(Customer customer, Double balance) {
        customer.getAccounts().forEach(System.out::println);

        int accountNumber = Input.getInputValue("Enter account Number");
        Account foundAccount = findAccountByAccNumber(customer, accountNumber);
        foundAccount.setBalance(balance);

        accountManager.update(accountNumber, foundAccount);
    }


    public void createCreditCard(Customer customer) {
        Long number = Input.getLongValue("Enter credit card number");
        int password = Input.getInputValue("Enter four-digit Password");
        int CVV2 = Input.getInputValue("Enter four-digit CVV2");

        System.out.println();
        System.out.println("Information of your account is: ");
        customer.getAccounts().forEach(System.out::println);

        int id = Input.getInputValue("Enter account id");
        Account foundAccount = findAccountById(customer, id);
        CreditCard creditCard = new CreditCard(number, password, CVV2, null, foundAccount);

        creditCard.setAccount(foundAccount);
        creditCardManager.getBaseDao().update(creditCard);
    }

    public void updateCreditCard(Customer customer) {
        int creditcardId = Input.getInputValue("Enter Creditcard id");
        int password = Input.getInputValue("Enter new four-digit Password");
        int CVV2 = Input.getInputValue("Enter new four-digit CVV2");

        int accountNumber = Input.getInputValue("Enter account Number");
        Account foundAccount = findAccountByAccNumber(customer, accountNumber);

        CreditCard newCreditCard = new CreditCard();
        newCreditCard.setPassword(password);
        newCreditCard.setCVV2(CVV2);
        foundAccount.setCreditCard(newCreditCard);
        creditCardManager.update(creditcardId, newCreditCard);
    }

    public void createTransaction(Customer customer) {
        boolean continueTransaction = true;
        while (continueTransaction) {
            System.out.println("""
                    1) Withdraw
                    2) Deposit
                    3) Transfer
                    4) Back
                                  
                    """);
            int choice = Input.getInputValue(" Select a number");

            switch (choice) {
                case 1 -> withdrawTransaction(customer);
                case 2 -> depositTransaction(customer);
                case 3 -> transferMoney(customer);
                case 4 -> continueTransaction = false;
            }
        }
    }


    private void withdrawTransaction(Customer customer) {

        Long cardNumber = Input.getLongValue("Enter your card number");
        Integer password = Input.getInputValue("Enter your password");
        CreditCard foundCard = creditCardManager.getBaseDao().findCardByCardNumberAndPass(cardNumber, password);

        if (foundCard != null) {
            double amount = Input.getDoubleValue("Enter amount");
            Account account = foundCard.getAccount();
            double balance = account.getBalance();
            if (balance > amount) {
                balance -= amount;
                account.setBalance(balance);
                accountManager.getBaseDao().update(account);

                Transaction newTransaction = new Transaction();
                newTransaction.setType("WITHDRAW");
                newTransaction.setAmount(amount);
                newTransaction.setDestAccNumber(account.getNumber());
                newTransaction.setSrcAccNumber(account.getNumber());
                newTransaction.setCreditCard(foundCard);
                transactionManager.save(newTransaction);
            } else
                throw new NoSufficientMoney("NoSufficientMoneyExistInAccount");
        } else
            throw new DataNotFound("NoSuchCardExist");
    }

    public void depositTransaction(Customer customer) {
        Long cardNumber = Input.getLongValue("Enter your card number");
        Integer password = Input.getInputValue("Enter your password");
        CreditCard foundCard = creditCardManager.getBaseDao().findCardByCardNumberAndPass(cardNumber, password);

        if (foundCard != null) {
            double amount = Input.getDoubleValue("Enter amount");
            Account account = foundCard.getAccount();
            double balance = account.getBalance();

            balance += amount;
            account.setBalance(balance);
            accountManager.getBaseDao().update(account);

            Transaction newTransaction = new Transaction();
            newTransaction.setType("DEPOSIT");
            newTransaction.setAmount(amount);
            newTransaction.setDestAccNumber(account.getNumber());
            newTransaction.setSrcAccNumber(account.getNumber());
            newTransaction.setCreditCard(foundCard);
            transactionManager.save(newTransaction);
        } else
            throw new DataNotFound("NoSuchCardExist");
    }

    public void transferMoney(Customer customer) {

        Long cardNumber = Input.getLongValue("Enter your card number");
        Integer password = Input.getInputValue("Enter your password");
        CreditCard srcCard = creditCardManager.getBaseDao().findCardByCardNumberAndPass(cardNumber, password);

        if (srcCard != null) {
            Long destCardNumber = Input.getLongValue("Enter destination card number");
            CreditCard destCard = creditCardManager.getBaseDao().findCardByCardNumber(destCardNumber);
            if (destCard != null) {
                double amount = Input.getDoubleValue("Enter amount");
                Account srcAccount = srcCard.getAccount();
                double srcAccBalance = srcAccount.getBalance();
                Account destAccount = destCard.getAccount();
                double destAccBalance = destAccount.getBalance();

                if (srcAccBalance > amount) {
                    srcAccBalance -= amount + 600;
                    srcAccount.setBalance(srcAccBalance);
                    accountManager.getBaseDao().update(srcAccount);
                    destAccBalance += amount;
                    destAccount.setBalance(destAccBalance);
                    accountManager.getBaseDao().update(destAccount);

                    Transaction newTransaction = new Transaction();
                    newTransaction.setType("TRANSFER");
                    newTransaction.setAmount(amount);
                    newTransaction.setSrcAccNumber(srcAccount.getNumber());
                    newTransaction.setDestAccNumber(destAccount.getNumber());
                    newTransaction.setCreditCard(srcCard);
                    transactionManager.save(newTransaction);
                } else
                    throw new NoSufficientMoney("NoSufficientMoneyExistInAccount");
            } else
                throw new DataNotFound("NoSuchCardExist");
        } else
            throw new DataNotFound("NoSuchCardExist");
    }

    public void printCardInformation(Customer customer) {
        int id = Input.getInputValue("Enter account id");
        CreditCard card = creditCardManager.findCardByAccId(id);
        System.out.println(card);
    }

//    public void printTransactionsByDate(Customer customer) {
//        int accountNumber = Input.getInputValue("Enter account Number");
//        Account acc = findAccountByAccNumber(customer, accountNumber);
//        CreditCard creditCard = acc.getCreditCard();
//        List<Transaction> transactions = creditCard.getTransactions();
//
//    }


    private Account findAccountByAccNumber(Customer customer, int accNumber) {
        for (Account account : customer.getAccounts()) {
            if (account.getNumber() == accNumber)
                return account;
        }
        return null;
    }

    private Account findAccountById(Customer customer, Integer id) {
        for (Account account : customer.getAccounts()) {
            if (account.getId().equals(id))
                return account;
        }
        return null;
    }


}
