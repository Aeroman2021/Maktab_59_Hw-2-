package hw15.q2;

import org.junit.jupiter.api.Test;

import static hw15.q2.Converter.*;
import static org.junit.jupiter.api.Assertions.*;

public class Main {


    @Test
    public void testOutputNotNull() {
        String str = "12345";
        assertNotNull(nullOrEmptyInputChecker(str));
    }

    @Test
    public void testCharacters() {
        String str = "-45000";
        assertTrue(characterChecker(str));
    }

    @Test
    public void testRange() {
        String str = "4500";
        assertTrue(rangeChecker(str));
    }

    @Test
    public void mainMethodEmptyInput(){
        String str = "";
        assertThrows(InvalidInputData.class,()->stringToIntegerConverter(str));
    }

    @Test
    public void mainMethodNullInput(){
        String str = null;
        assertThrows(InvalidInputData.class,()->stringToIntegerConverter(str));
    }


    @Test
    public void mainMethodLargeInput(){
        String str = "9999999999999999999";
        assertThrows(InvalidInputData.class,()->stringToIntegerConverter(str));
    }

    @Test
    public void mainMethodSmallInput(){
        String str = "-9999999999999999999";
        assertThrows(InvalidInputData.class,()->stringToIntegerConverter(str));
    }

    @Test
    public void mainMethodAcceptableInputTest(){
        String str = "4500";
        assertEquals(4500,stringToIntegerConverter(str));
    }

}
