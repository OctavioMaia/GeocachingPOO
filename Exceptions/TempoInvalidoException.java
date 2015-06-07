package Exceptions;

/**
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 */
public class TempoInvalidoException extends Exception{
   /**
     * Construtor vazio, ou seja, apenas invoca o construtor da superclasse.
     */
    public TempoInvalidoException(){
        super();
    }
    
    /**
     * Construtor parametrizado, ou seja, recebe uma String como parâmetro para informação, que invoca igualmente o construtor da superclasse que aceita uma String por parâmetro.
     */
    public TempoInvalidoException(String message){
        super(message);
    }
    
    /**
     * A função getMessage imprime o texto de exceção.
     */
    public String getMessage() {
        return "Verifique se introduziu um estado de tempo valido!\ni.e: Limpo, Neblina, Nevoeiro, Aguaceiros, Chuva, Ventos_Fortes, Trovoada, Neve \n";
    }
}
