package Exceptions;

/**
 *
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 */
public class PedidoNaoExisteException extends Exception {
    
    /**
     * Construtor vazio, ou seja, apenas invoca o construtor da superclasse.
     */
    public PedidoNaoExisteException(){
        super();
    }
    
    /**
     * Construtor parametrizado, ou seja, recebe uma String como parâmetro para informação, que invoca igualmente o construtor da superclasse que aceita uma String por parâmetro.
     */
    public PedidoNaoExisteException(String message){
        super(message);
    }
    
    /**
     * A função getMessage imprime o texto de exceção.
     */
    public String getMessage() {
        return "Esse utilizador não lhe enviou um pedido de amizade!\n";
    }
}
