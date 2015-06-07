package src;
import java.util.*;
import static java.lang.System.out;

/**
 * Subclasse Multi Cache
 * 
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 * @version 1.0
 */
public class CacheMulti extends Cache{
    private TreeMap<Integer,Coordenadas> cachesIntermedias = new TreeMap<Integer,Coordenadas>();  //caches intermedias
    private TreeMap<Integer,String> codigosCachesIntermedias= new TreeMap<Integer,String>();  //codigos verificacao caches intermedias

    /*
     * Construtores
     */
    /**
     * Construtor vazio. Como se trata de uma subclasse de Cache, utilizamos o construtor super para a reaproveitação de código e para utilizarmos herança.
     */
    public CacheMulti(){
        super();
    }

    /**
     * Construtor parametrizado, ou seja, recebe um criador, as coordenadas, a dificuldade da Cache, a descrição da Cache e uma lista de quem a visitou, e cria uma Multi-Cache com esses dados. Coloca-se "null", porque na Micro-Cache não contém tesouros.
     * @param criador O criador da Cache.
     * @param coord As coordenadas para encontrar a Cache.
     * @param dificuldade De 1-5, descreve a dificuldade da Cache
     * @param desc A descrição da Cache, ou seja, o que contém lá dentro.
     * @param visitas A lista de quem visitou a Cache.
     */
    public CacheMulti(String criador, Coordenadas coord,int dificuldade, String desc, HashSet<String> visitas){
        super(criador,coord,dificuldade,desc,visitas);
    }

    /**
     * Construtor de cópia, ou seja, copia os dados de uma Multi-Cache já existente.
     * @param c A Multi-Cache a copiar.
     */
    public CacheMulti(CacheMulti c){
        super(c);
    }

    /*
     * Gets
     */
    /**
     * A função getTipo devolve o tipo da Cache.
     */
    public String getTipo(){ return "Multi Cache";}

    /**
     * A função getCachesIntermedias devolve as Caches intermédias.
     */
    public TreeMap<Integer,Coordenadas> getCachesIntermedias(){ return cachesIntermedias;}

    /**
     * A função getCodigosIntermedios devolve o código das Caches intermédias.
     */
    public TreeMap<Integer,String> getCodigosIntermedios(){ return codigosCachesIntermedias;}

    /*
     * Sets
     */
    /**
     * A função setCachesIntermedias modifica as Caches intermédias.
     * @param cachesIntermedias As novas Caches intermédias que irão substituir as antigas.
     */
    public void setCachesIntermedias(TreeMap<Integer,Coordenadas> cachesIntermedias){ this.cachesIntermedias=cachesIntermedias;}

    /**
     * A função setCodigosIntermedios modifica os códigos de verificação das Caches intermédias.
     * @param codigosCachesIntermedias Os novos códigos das Caches intermédias que irão subsituir os antigos.
     */
    public void setCodigosIntermedios(TreeMap<Integer,String> codigosCachesIntermedias){ this.codigosCachesIntermedias=codigosCachesIntermedias;}

    /**
     * A função adicionarCodigosIntermedios adiciona um código intermédio.
     * @param i Cache intermédia.
     * @param codigo Código intermédio.
     */
    public void adicionarCodigosIntermedios(int i,String codigo){
        codigosCachesIntermedias.put(i,codigo);
    }

    /**
     * A função adicionarCachesIntermedias adiciona Caches intermédias.
     * @param i Cache intermédia.
     * @param c Coordenadas da Cache final.
     */
    public void adicionarCachesIntermedias(int i,Coordenadas c){
        cachesIntermedias.put(i,c);
    }

    /*
     * Funcoes aux
     */
    public StringBuilder printIntermedios(CacheMulti c){
        StringBuilder str = new StringBuilder();
        int i=1;
        for (Map.Entry<Integer,Coordenadas> entry : cachesIntermedias.entrySet()){
            str.append("\tCache intermédia nº"+i+": ");
            str.append(" Latitude: "+entry.getValue().getLatitude() +" Longitude: "+ entry.getValue().getLongitude()+"\n");
            i++;
        }

        return str;
    }

    /**
     * A função getPontuacao devolve a pontuação de cada tipo de Cache.
     */
    public int getPontuacao(){return 5;}

    /**
     * A função clone faz um clone de uma Multi Cache, a partir do construtor por cópia.
     */
    public CacheMulti clone(){
        return new CacheMulti(this);
    }

    /**
     * A função toString imprime a Multi Cache.
     * @param c A Multi Cache que será imprimida.
     */
    public StringBuilder toString(CacheMulti c){
        StringBuilder str = new StringBuilder();

        str.append("Criador: "                          +this.getCriador()                                +"\n");
        str.append("Tipo: "                             +this.getTipo()                                   +"\n");
        str.append("Coordenadas caches intermedias: \n" +this.printIntermedios(c));
        str.append("Coordenadas: "                      +this.getCoordenadas().toString(this.getCoordenadas())   +"\n");
        str.append("Descrição: "                        +this.getDescricao()                              +"\n");
        str.append("Visitas: "                          +this.getVisitas().toString()                     +"\n");
        str.append("Código identificador: "             +this.getCodigo()                                 +"\n");

        return str;
    }

    /**
     * A função equals recebe um Objeto genérico e verifica se é exatamente igual a uma Multi Cache.
     * @param obj Objeto a comparar.
     */
    public boolean equals(Object obj){
        return super.equals(obj);
    }
}
