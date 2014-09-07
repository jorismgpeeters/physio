
package data;

/**
 * Exceptionklasse voor het signaleren van fouten bij communicatie met de Physio-database
 * @author lievenp
 */
public class PhysioException extends Exception {
    
    public PhysioException(){
        super();
    }
    
    public PhysioException(String s){
        super(s);
    }
    
}
