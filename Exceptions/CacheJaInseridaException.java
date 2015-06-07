package Exceptions;


/**
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 */
public class CacheJaInseridaException extends Exception{
    /**
     * Construtor vazio, ou seja, apenas invoca o construtor da superclasse.
     */
    public CacheJaInseridaException(){
        super();
    }
    
    /**
     * Construtor parametrizado, ou seja, recebe uma String como parâmetro para informação, que invoca igualmente o construtor da superclasse que aceita uma String por parâmetro.
     */
    public CacheJaInseridaException(String message){
        super(message);
    }
    
    /**
     * A função getMessage imprime o texto de exceção.
     */
    public String getMessage() {
        return "Não pode inserir uma cache já inserida!\n";
    }
}
