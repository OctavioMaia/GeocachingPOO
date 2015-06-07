package src;
import java.util.*;
import static java.lang.System.out;

/**
 * Subclasse Cache Virtual
 * 
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 * @version 1.0
 */
public class CacheVirtual extends Cache{
    /*
     * Construtores
     */
    /**
     * Construtor vazio. Como se trata de uma subclasse de Cache, utilizamos o construtor super para a reaproveitação de código e para utilizarmos herança.
     */
    public CacheVirtual(){
        super();
    }

    /**
     * Construtor parametrizado, ou seja, recebe um criador, as coordenadas, a descrição da Cache e uma lista de quem a visitou, e cria uma  com esses dados. Coloca-se "null", porque na Cache-Virtual não contém tesouros.
     * @param criador O criador da Cache.
     * @param coord As coordenadas para encontrar a Cache.
     * @param dificuldade De 1-5, descreve a dificuldade da Cache
     * @param desc A descrição da Cache, ou seja, o que contém lá dentro.
     * @param visitas A lista de quem visitou a Cache.
     */
    public CacheVirtual(String criador, Coordenadas coord, String desc, HashSet<String> visitas){
        super(criador,coord,0,desc,visitas);
    }

    /**
     * Construtor de cópia, ou seja, copia os dados de uma Cache-Virtual já existente.
     * @param c A Cache-Virtual a copiar.
     */
    public CacheVirtual(CacheVirtual c){
        super(c);
    }

    /*
     * Gets
     */
    /**
     * A função getTipo devolve o tipo da Cache.
     */
    public String getTipo(){ return "Cache Virtual";}

    /**
     * A função getPontuacao devolve a pontuação de cada tipo de Cache.
     */
    public int getPontuacao(){return 1;}

    /**
     * A função clone faz um clone de uma Cache Virtual, a partir do construtor por cópia.
     */
    public CacheVirtual clone(){
        return new CacheVirtual(this);
    }
    
    /**
     * A função toString imprime o Cache Virtual.
     * @param c A Cache Virtual que será imprimida.
     */
    public StringBuilder toString(CacheVirtual c){
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
     * A função equals recebe um Objeto genérico e verifica se é exatamente igual a uma Cache Virtual.
     * @param obj Objeto a comparar.
     */
    public boolean equals(Object obj){
        return super.equals(obj);
    }
}
