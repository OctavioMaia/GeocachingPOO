package Exceptions;

/**
 *
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 */
public class DificuldadeInvalidaException extends Exception {
    
    /**
     * Construtor vazio, ou seja, apenas invoca o construtor da superclasse.
     */
    public DificuldadeInvalidaException(){
        super();
    }
    
    /**
     * Construtor parametrizado, ou seja, recebe uma String como parâmetro para informação, que invoca igualmente o construtor da superclasse que aceita uma String por parâmetro.
     */
    public DificuldadeInvalidaException(String message){
        super(message);
    }
    
    /**
     * A função getMessage imprime o texto de exceção.
     */
    public String getMessage() {
        return "Introduziu uma dificuldade inválida!\n";
    }
}
