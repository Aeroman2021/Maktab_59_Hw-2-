package hw16.q2;

public class Main {

    public static void main(String[] args) {
        Car benz = new Car("Benz", "E", "Black");

        Car clonedCar1 = (Car) benz.clone();
        Car clonedCar2 = (Car) benz.clone();
        Car clonedCar3 = (Car) benz.clone();

        Car[] storedCars = {clonedCar1,clonedCar2,clonedCar3};
        for (int i = 0; i < storedCars.length; i++) {
            System.out.println(storedCars[i].equals(benz));
        }





    }
}
