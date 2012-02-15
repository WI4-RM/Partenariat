/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

/**
 * class which check user input
 * 
 * @author fingon
 */
public class InputValidator {
    
    /**
     * 
     * @param email 
     * @return true if email is something@mines-saint-etienne.org
     */
    public static boolean checkEmail(String email){
        return email.matches("[a-zA-Z0-9\\.\\-]+@mines-saint-etienne\\.org");
    }
    
    /**
     * 
     * @param password
     * @return true if password has more than 8 caracteres
     */
    public static boolean checkPassword(String password){
        return  (password.length() >= 8);
    }
    
    /**
     * 
     * @param input
     * @return true if name is a word \w
     */
    public static boolean checkNames(String input){
        return input.matches("[a-zA-Z0-9-]+");
    }
    
    /**
     * 
     * @param year
     * @return true if year has 4 digits
     */
    public static boolean checkYear(String year ){
        return (year.matches("[0-9]{4}"));
    }
    
}
