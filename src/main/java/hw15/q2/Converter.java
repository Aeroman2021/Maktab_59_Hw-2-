package hw15.q2;

public class Converter {

    public static Integer stringToIntegerConverter(String str) {
        int inputNumber = 0;
        try {
            inputNumber = Integer.parseInt(str);
        } catch (Exception e) {
            throw new InvalidInputData("The number is not in range");
        }

        if ( !nullOrEmptyInputChecker(str) && rangeChecker(str) && characterChecker(str))
            return inputNumber;
        else
            throw new InvalidInputData("InvalidInputNumber");
    }

    public static Boolean nullOrEmptyInputChecker(String str){
        return str.length()==0 || str == null;
    }

    public static Boolean characterChecker(String str) {
        int counter = 0;
        if(str.charAt(0) == '-' || Character.isDigit(str.charAt(0))){
            for (int i = 1; i < str.length(); i++) {
                if (Character.isDigit(str.charAt(i)))
                    counter++;
            }
        }
        return counter == str.length()-1;
    }


    public static Boolean rangeChecker(String str) {
        return Integer.parseInt(str) >= -32767 &&
                Integer.parseInt(str) <= 32767;
    }
}
