package Exceptions;

/**
 *
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 */
public class CacheNaoExisteException extends Exception {
    
    /**
     * Construtor vazio, ou seja, apenas invoca o construtor da superclasse.
     */
    public CacheNaoExisteException(){
        super();
    }
    
    /**
     * Construtor parametrizado, ou seja, recebe uma String como parâmetro para informação, que invoca igualmente o construtor da superclasse que aceita uma String por parâmetro.
     */
    public CacheNaoExisteException(String message){
        super(message);
    }
    
    /**
     * A função getMessage imprime o texto de exceção.
     */
    public String getMessage() {
        return "Não existe nenhuma cache com esse código!\n";
    }
}
