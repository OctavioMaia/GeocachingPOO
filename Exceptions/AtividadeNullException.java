package Exceptions;


/**
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 */
public class AtividadeNullException extends Exception{
    
    /**
     * Construtor vazio, ou seja, apenas invoca o construtor da superclasse.
     */
    public AtividadeNullException(){
        super();
    }
    
    /**
     * Construtor parametrizado, ou seja, recebe uma String como parâmetro para informação, que invoca igualmente o construtor da superclasse que aceita uma String por parâmetro.
     */
    public AtividadeNullException(String message){
        super(message);
    }
    
    /**
     * A função getMessage imprime o texto de exceção.
     */
    public String getMessage() {
        return "O utilizador ainda não fez nenhuma atividade!\n";
    }
}
