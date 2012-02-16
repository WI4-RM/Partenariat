/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author fingon
 */
public class InputValidatorTest {
    
    public InputValidatorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of checkEmail method, of class InputValidator.
     */
    @Test
    public void testCheckEmail() {
        System.out.println("checkEmail");
        String email = "hhcdazidkd";
        boolean expResult = false;
        boolean result = InputValidator.checkEmail(email);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckFalseEmail() {
        System.out.println("checkFalseEmail");
        String email = "lzjfb@hotmail.com";
        boolean expResult = false;
        boolean result = InputValidator.checkEmail(email);
        assertEquals(expResult, result);
    }  
   
    @Test
    public void testCheckGoodEmail() {
        System.out.println("checkFalseEmail");
        String email = "oiolo.satan@mines-st-etienne.org";
        boolean expResult = false;
        boolean result = InputValidator.checkEmail(email);
        assertEquals(expResult, result);
    }    

    /**
     * Test of checkPassword method, of class InputValidator.
     */
    @Test
    public void testCheckPassword() {
        System.out.println("checkPassword");
        String password = "";
        boolean expResult = false;
        boolean result = InputValidator.checkPassword(password);
        assertEquals(expResult, result);
    }
    
    
     @Test
    public void testCheckFalsePassword() {
        System.out.println("checkPassword");
        String password = "1234567";
        boolean expResult = false;
        boolean result = InputValidator.checkPassword(password);
        assertEquals(expResult, result);
    }   
     
      @Test
    public void testCheckGoodPassword() {
        System.out.println("checkPassword");
        String password = "00000000";
        boolean expResult = true;
        boolean result = InputValidator.checkPassword(password);
        assertEquals(expResult, result);
    }     

    /**
     * Test of checkNames method, of class InputValidator.
     */
    @Test
    public void testCheckNames() {
        System.out.println("checkNames");
        String input = "_|%$";
        boolean expResult = false;
        boolean result = InputValidator.checkNames(input);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckGoodNames() {
        System.out.println("checkNames");
        String input = "qmsldkvofoe$";
        boolean expResult = false;
        boolean result = InputValidator.checkNames(input);
        assertEquals(expResult, result);
    }    

    /**
     * Test of checkYear method, of class InputValidator.
     */
    @Test
    public void testCheckYear() {
        System.out.println("checkYear");
        String year = "20000";
        boolean expResult = false;
        boolean result = InputValidator.checkYear(year);
        assertEquals(expResult, result);
        year="245";
        expResult = false;
        result = InputValidator.checkYear(year);
        assertEquals(expResult, result);
        year="2000";
        expResult = true;
        result = InputValidator.checkYear(year);
        assertEquals(expResult, result);        
    }
}
