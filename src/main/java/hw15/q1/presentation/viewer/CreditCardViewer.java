package hw15.q1.presentation.viewer;

import hw15.q1.entities.CreditCard;
import hw15.q1.manager.CreditCardManager;
import hw15.q1.utilities.Input;
import hw15.q1.utilities.NumberGenerator;

import java.util.List;

public class CreditCardViewer {

    CreditCardManager creditCardManager;


    public void custExecuter() {
        System.out.println("""
                1) Apply for Credit card
                2) Update Credit card Information
                3) Delete Credit card
                4) Print Credit card Information
                5) Create A transaction by credit card
                              
                """);

        int choice = Input.getInputValue("Please select a number");
        switch (choice) {
            case 1 -> create();
            case 2 -> update();
            case 3 -> delete();
            case 4 -> printCreditCardInformation();
            case 5 -> createCardTransaction();
        }
    }

    public CreditCardViewer() {
        creditCardManager = new CreditCardManager();
    }

    private void create() {
        Long creditCardNumber = NumberGenerator.generateNumber();
        int password = Input.getInputValue("Enter four-digit Password");
        int CVV2 = Input.getInputValue("Enter four-digit CVV2");
        Long accNumber = Input.getLongValue("Enter your account number");
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(creditCardNumber);
        creditCard.setPassword(password);
        creditCard.setCVV2(CVV2);
        creditCardManager.createCreditCard(creditCard, accNumber);

    }

    private void update() {
        Long creditCardNumber = Input.getLongValue("Enter credit card number");
        int password = Input.getInputValue("Enter new four-digit Password");
        int CVV2 = Input.getInputValue("Enter new four-digit CVV2");
        CreditCard newCreditCard = new CreditCard();
        newCreditCard.setPassword(password);
        newCreditCard.setCVV2(CVV2);
        creditCardManager.update(creditCardNumber, newCreditCard);
    }

    private void delete() {
        Long creditCardNumber = Input.getLongValue("Enter credit card number");
        creditCardManager.delete(creditCardNumber);
    }


    private CreditCard loadById() {
        Long creditCardNumber = Input.getLongValue("Enter credit card number");
        return creditCardManager.loadById(creditCardNumber);
    }

    private void printCreditCardInformation() {
        Long creditCardNumber = Input.getLongValue("Enter credit card number");
        System.out.println(creditCardManager.loadById(creditCardNumber));
    }

    private List<CreditCard> loadAll() {
        return creditCardManager.loadAll();
    }

    private void printAllCardsInformation() {
        for (CreditCard creditCard : creditCardManager.loadAll()) {
            System.out.println(creditCard);
        }
    }

    private void createCardTransaction() {
        Long srcCardNumber = Input.getLongValue("Enter src card Number");
        Long destCardNumber = Input.getLongValue("Enter destination card Number");
        Double amount = Input.getDoubleValue("Enter the amount of money you want to transfer");

        creditCardManager.createTransactionByCard(srcCardNumber, destCardNumber, amount);

    }


}
