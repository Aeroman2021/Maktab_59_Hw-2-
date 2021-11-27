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


        String firstName = Input.getStringInputValue("Enter your firstName");
        String lastName = Input.getStringInputValue("Enter your lastName");
        String sex = Input.getStringInputValue("Enter your gender");
        int age = Input.getInputValue(" Enter your age");
        String username = Input.getStringInputValue("Enter username");
        String password = Input.getStringInputValue("Enter password");
        Customer customer = new Customer(null, null, null, null, null);
        customer.setFirstname(firstName);
        customer.setLastName(lastName);
        customer.setSex(sex);
        customer.setAge(age);
        customer.setUsername(username);
        customer.setPassword(password);
        customerManager.save(customer);
    }

    public void login() {
        String username = Input.getStringInputValue("Enter username");
        String password = Input.getStringInputValue("Enter password");
        Customer customer = customerManager.login(username, password);
        if (customer != null) {
            AfterLoginMenu(customer);
        } else
            throw new DataNotFound("NoSuchUserExist");
    }

    public void AfterLoginMenu(Customer customer) {

        int choice = 0;
        while (choice != 9) {
            System.out.println("""
                    Please select an action
                    1) Update your Information
                    2) Print your information
                    3) Print your account information
                    4) Update the account information
                    5) Print account information
                    6) Apply for credit card
                    7) Update credit card information
                    8) Create transaction
                    9) Back
                                  
                    """);
            choice = Input.getInputValue("Select a Number");
            switch (choice) {
                case 1 -> updateCustomerInformation(customer);
                case 2 -> loadCustomerInformationById(customer);
                case 3 -> createNewAccount(customer);
//                case 4 -> updateAccountInformation(customer);
                case 5 -> printAccountInformation(customer);
                case 6 -> applyCreditCard(customer);
                case 7 -> updateCreditCardInformation(customer);
                case 8 -> getPrintCreditCardInformation(customer);
                case 9 -> createTransaction(customer);
                case 10 -> printListOfTransaction(customer);

            }
        }
    }


    private void updateCustomerInformation(Customer customer) {
        Integer id = customer.getNumber();
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

    private void createTransaction(Customer customer) {
        bankClerkViewer.createTransaction(customer);
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

    private void printListOfTransaction(Customer customer) {
        bankClerkViewer.printTransactionsByDate(customer);
    }


    private void getPrintCreditCardInformation(Customer customer) {
        bankClerkViewer.printCardInformation(customer);
    }


}
