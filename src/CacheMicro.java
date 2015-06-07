package src;
import java.util.*;
import static java.lang.System.out;

/**
 * Subclasse Micro Cache
 * 
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 * @version 1.0
 */
public class CacheMicro extends Cache{

    /**
     * Construtor vazio. Como se trata de uma subclasse de Cache, utilizamos o construtor super para a reaproveitação de código e para utilizarmos herança.
     */
    public CacheMicro(){
        super();
    }

    /**
     * Construtor parametrizado, ou seja, recebe um criador, as coordenadas, a dificuldade da Cache, a descrição da Cache e uma lista de quem a visitou, e cria uma Micro-Cache com esses dados. Coloca-se "null", porque na Micro-Cache não contém tesouros.
     * @param criador O criador da Cache.
     * @param coord As coordenadas para encontrar a Cache.
     * @param dificuldade De 1-5, descreve a dificuldade da Cache
     * @param desc A descrição da Cache, ou seja, o que contém lá dentro.
     * @param visitas A lista de quem visitou a Cache.
     */
    public CacheMicro(String criador, Coordenadas coord, int dificuldade, String desc, HashSet<String> visitas){
        super(criador,coord,dificuldade,desc,visitas);
    }

    /**
     * Construtor de cópia, ou seja, copia os dados de uma Micro-Cache já existente.
     * @param c A Micro-Cache a copiar.
     */
    public CacheMicro(CacheMicro c){
        super(c);
    }

    /**
     * A função getTipo devolve o tipo da Cache.
     */
    public String getTipo(){return "Micro Cache";}

    /**
     * A função getPontuacao devolve a pontuação de cada tipo de Cache.
     */
    public int getPontuacao(){return 2;}

    /**
     * A função clone faz um clone de uma Micro Cache, a partir do construtor por cópia.
     */
    public CacheMicro clone(){
        return new CacheMicro(this);
    }

    /**
     * A função toString imprime a Micro Cache.
     * @param c A Micro Cache que será imprimida.
     */
    public StringBuilder toString(CacheMicro c){
        StringBuilder str = new StringBuilder();

        str.append("Criador: "             +this.getCriador()                       +"\n");
        str.append("Tipo: "                +this.getTipo()                          +"\n");
        str.append("Coordenadas: "         +this.getCoordenadas().toString(this.getCoordenadas())   +"\n");
        str.append("Dificuldade: "         +this.getDificuldade()                   +"\n");
        str.append("Descrição: "           +this.getDescricao()                     +"\n");
        str.append("Visitas: "             +this.getVisitas().toString()            +"\n");
        str.append("Código identificador: "+this.getCodigo()                        +"\n");

        return str;
    }
    
    /**
     * A função equals recebe um Objeto genérico e verifica se é exatamente igual a uma Micro Cache.
     * @param obj Objeto a comparar.
     */
    public boolean equals(Object obj){
        return super.equals(obj);
    }
}   
