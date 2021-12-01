package hw15.q1.presentation.viewer;

import hw15.q1.entities.Customer;
import hw15.q1.exception.DataNotFound;
import hw15.q1.manager.CustomerManager;
import hw15.q1.utilities.Input;
import hw15.q1.utilities.NumberGenerator;

import java.util.List;

public class CustomerViewer {

    private final CustomerManager customerManager;
    private final TransactionViewer transactionViewer;
    private final AccountViewer accountViewer;
    private final BankClerkViewer bankClerkViewer;

    public CustomerViewer() {
        customerManager = new CustomerManager();
        transactionViewer = new TransactionViewer();
        accountViewer = new AccountViewer();
        bankClerkViewer = new BankClerkViewer();
    }


    public void registerNewCustomer() {
        System.out.println("Welcome to the Bank Application");
        String firstName = Input.getStringInputValue("Enter your firstName");
        String lastName = Input.getStringInputValue("Enter your lastName");
        String sex = Input.getStringInputValue("Enter your gender");
        int age = Input.getInputValue(" Enter your age");
        String username = Input.getStringInputValue("Enter username");
        String password = Input.getStringInputValue("Enter password");
        Customer customer = new Customer(firstName, lastName, sex, age, username, password);
        customerManager.save(customer);
        login();
    }

    public void login() {
        String username = Input.getStringInputValue("Enter your username");
        String password = Input.getStringInputValue("Enter your password");
        Customer customer = customerManager.login(username, password);
        if (customer != null) {
            AfterLoginMenu(customer);
        } else
            throw new DataNotFound("NoSuchUserExist");
    }

    public void AfterLoginMenu(Customer customer) {

        boolean continueSelect = false;
        while (!continueSelect) {
            System.out.println("=========================");
            System.out.println("""
                    1) Update your Information
                    2) Print your information
                    3) Create a new Account
                    4) Update the account information
                    5) Print account information
                    6) Apply for credit card
                    7) Update credit card information
                    8) Print credit card information
                    9) Create transaction
                    10) Print list of transaction
                    11) Back
                    """);
            int choice = Input.getInputValue("Select a Number");
            switch (choice) {
                case 1 -> updateCustomerInformation(customer);
                case 2 -> loadCustomerInformationById(customer);
                case 3 -> createNewAccount(customer);
//                case 4 -> updateAccountInformation(customer);
                case 5 -> printAccountInformation(customer);
                case 6 -> applyCreditCard(customer);
                case 7 -> updateCreditCardInformation(customer);
                case 8 -> PrintCreditCardInformation(customer);
                case 9 -> createTransaction(customer);
//                case 10 -> printListOfTransaction(customer);
                case 11 -> continueSelect = true;
            }
        }
    }


    private void updateCustomerInformation(Customer customer) {
        Integer id = customer.getId();
        customerManager.update(id, customer);
    }

    private Customer loadById() {
        int id = Input.getInputValue("Enter your id");
        return customerManager.loadById(id);
    }

    private void loadCustomerInformationById(Customer customer) {
        System.out.println(customer);
    }

    private void printAccountInformation(Customer customer) {
        customer.getAccounts().forEach(System.out::println);
    }


    private List<Customer> loadAll() {
        return customerManager.loadAll();
    }


    private void createNewAccount(Customer customer) {
        bankClerkViewer.createAccount(customer);
    }

    private void updateAccountInformation(Customer customer, Double amount) {
        bankClerkViewer.updateAccount(customer, amount);
    }

    private void applyCreditCard(Customer customer) {
        bankClerkViewer.createCreditCard(customer);
    }

    private void updateCreditCardInformation(Customer customer) {
        bankClerkViewer.updateCreditCard(customer);
    }

    private void createTransaction(Customer customer) {
        bankClerkViewer.createTransaction(customer);
    }

//    private void printListOfTransaction(Customer customer) {
//        bankClerkViewer.printTransactionsByDate(customer);
//    }

    private void PrintCreditCardInformation(Customer customer) {
        bankClerkViewer.printCardInformation(customer);
    }


}
