/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.exceptions;

/**
 *
 * @author angel
 */
public class InputsVaciosException extends Exception{
    
    public InputsVaciosException(){
        
    }
    
    public InputsVaciosException(String mensaje){
        super(mensaje);
    }
    
}
