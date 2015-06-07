package src;
import java.util.*;
import static java.lang.System.out;

/**
 * Subclasse Cache Evento
 * 
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 * @version 1.0
 */
public class CacheEvento extends Cache{
    /*
     * Construtores
     */
    public CacheEvento(){
        super();
    }

    public CacheEvento(String criador, Coordenadas coord, String desc, HashSet<String> visitas){
        super(criador,coord,0,desc,visitas);
    }

    public CacheEvento(CacheEvento c){
        super(c);
    }

    /*
     * Gets
     */
    public String getTipo(){ return "Cache Evento";}

    /**
     * A função getPontuacao devolve a pontuação de cada tipo de Cache.
     */
    public int getPontuacao(){return 0;}

    /**
     * A função clone faz um clone de uma Cache Evento, a partir do construtor por cópia.
     */
    public CacheEvento clone(){
        return new CacheEvento(this);
    }

    /**
     * A função toString imprime a Cache Evento.
     * @param c A Cache Evento que será imprimida.
     */
    public StringBuilder toString(CacheEvento c){
        StringBuilder str = new StringBuilder();

        str.append("Criador: "             +this.getCriador()                       +"\n");
        str.append("Tipo: "                +this.getTipo()                          +"\n");
        str.append("Coordenadas: "         +this.getCoordenadas().toString(this.getCoordenadas())   +"\n");
        str.append("Descrição: "           +this.getDescricao()                     +"\n");
        str.append("Visitas: "             +this.getVisitas().toString()            +"\n");
        str.append("Código identificador: "+this.getCodigo()                        +"\n");
        
        return str;
    }

    /**
     * A função equals recebe um Objeto genérico e verifica se é exatamente igual a uma Cache Evento.
     * @param obj Objeto a comparar.
     */
    public boolean equals(Object obj){
        return super.equals(obj);
    }
}
