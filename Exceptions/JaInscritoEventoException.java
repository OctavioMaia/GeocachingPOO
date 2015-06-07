package Exceptions;


/**
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 */
public class JaInscritoEventoException extends Exception{
    
    /**
     * Construtor vazio, ou seja, apenas invoca o construtor da superclasse.
     */
    public JaInscritoEventoException(){
        super();
    }
    
    /**
     * Construtor parametrizado, ou seja, recebe uma String como parâmetro para informação, que invoca igualmente o construtor da superclasse que aceita uma String por parâmetro.
     */
    public JaInscritoEventoException(String message){
        super(message);
    }
    
    /**
     * A função getMessage imprime o texto de exceção.
     */
    public String getMessage() {
        return "Já está inscrito nesse evento!\n";
    }
}
