package src;
import java.util.*;
import static java.lang.System.out;

/**
 * Subclasse Cache Misterio
 * 
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 * @version 1.0
 */
public class CacheMisterio extends Cache{
    private String enigmaLatitude;
    private String enigmaLongitude;
    private TreeMap<String,Double> puzzle = new TreeMap<String,Double>(); //puzzle, ao resolver a key (string) da o Double(uma das coord)

    /**
     * Construtor vazio. Como se trata de uma subclasse de Cache, utilizamos o construtor super para a reaproveitação de código e para utilizarmos herança.
     */
    public CacheMisterio(){
        super();
    }

    /**
     * Construtor parametrizado, ou seja, recebe um criador, as coordenadas, a dificuldade da Cache, a descrição da Cache, uma lista de quem a visitou e uma lista dos tesouros, e cria uma Cache-Mistério com esses dados.
     * @param criador O criador da Cache.
     * @param coord As coordenadas para encontrar a Cache.
     * @param dificuldade De 1-5, descreve a dificuldade da Cache
     * @param desc A descrição da Cache, ou seja, o que contém lá dentro.
     * @param visitas A lista de quem visitou a Cache.
     */
    public CacheMisterio(String criador, Coordenadas coord, int dificuldade, String desc, HashSet<String> visitas, HashSet<String> tesouros){
        super(criador,coord,dificuldade,desc,visitas);
    }

    /**
     * Construtor de cópia, ou seja, copia os dados de uma Cache-Mistério já existente.
     * @param c A Cache-Mistério a copiar.
     */
    public CacheMisterio(CacheMisterio c){
        super(c);
    }

    /*
     * Gets
     */
    /**
     * A função getPuzzle devolve um puzzle, que o Utilizador irá resolver, para encontrar a Cache.
     */
    public TreeMap<String,Double> getPuzzle(){
        TreeMap<String,Double> clone = new TreeMap<String,Double>();

        for(String s: puzzle.keySet()){
            clone.put(s,puzzle.get(s));
        }

        return clone;
    }

    /**
     * A função getEnigmaLatitude devolve o enigma para saber a latitude da Cache.
     */
    public String getEnigmaLatitude(){ return enigmaLatitude;}

    /**
     * A função getEnigmaLongitude devolve o enigma para saber a longitude da Cache.
     */
    public String getEnigmaLongitude(){ return enigmaLongitude;}

    /**
     * A função getTipo devolve o tipo da Cache.
     */
    public String getTipo(){ return "Cache Misterio";}

    /*
     * Sets
     */
    /**
     * A função setPuzzle modifica o puzzle inicial.
     * @param puzzle O novo puzzle que irá substituir o antigo.
     */
    public void setPuzzle(TreeMap<String,Double> puzzle){ this.puzzle=puzzle;}

    /**
     * A função setEnigmaLatitude modifica o enigma para descobrir a latitude da Cache.
     * @param enigma O novo enigma para saber a latitude da Cache, que irá substituir o antigo.
     */
    public void setEnigmaLatitude(String enigma){ this.enigmaLatitude=enigma;}

    /**
     * A função setEnigmaLongitude modifica o enigma para descobrir a longitude da Cache.
     * @param enigma O novo enigma para saber a longitude da Cache, que irá substituir o antigo.
     */
    public void setEnigmaLongitude(String enigma){ this.enigmaLongitude=enigma;}

    /*
     * Aux
     */
    /**
     * A função adicionarPuzzle adiciona um puzzle.
     * @param s Pergunta.
     * @param d Latitude ou Longitude.
     */
    public void adicionarPuzzle(String s ,Double d){
        puzzle.put(s,d);
    }

    /**
     * A função getPontuacao devolve a pontuação de cada tipo de Cache.
     */
    public int getPontuacao(){return 4;}

    /**
     * A função clone faz um clone de uma Cache Misterio, a partir do construtor por cópia.
     */
    public CacheMisterio clone(){
        return new CacheMisterio(this);
    }

    /**
     * A função toString imprime a Cache Misterio.
     * @param c A Cache Misterio que será imprimida.
     */
    public StringBuilder toString(CacheMisterio c){
        StringBuilder str = new StringBuilder();

        str.append("Criador: "             +this.getCriador()                       +"\n");
        str.append("Tipo: "                +this.getTipo()                          +"\n");
        str.append("Dificuldade: "         +this.getDificuldade()                   +"\n");
        str.append("Descrição: "           +this.getDescricao()                     +"\n");
        str.append("Visitas: "             +this.getVisitas().toString()            +"\n");
        str.append("Tesouros: "            +this.getTesouros()                      +"\n");
        str.append("Código identificador: "+this.getCodigo()                        +"\n");

        return str;
    }

    /**
     * A função equals recebe um Objeto genérico e verifica se é exatamente igual a uma Cache Misterio.
     * @param obj Objeto a comparar.
     */
    public boolean equals(Object obj){
        return super.equals(obj);
    }
}
