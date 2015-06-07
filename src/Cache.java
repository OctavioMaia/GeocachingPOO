package src;
import java.util.*;
import static java.lang.System.out;
import java.io.*;

/**
 * Superclasse Cache
 * 
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 * @version 1.0
 */
public class Cache implements Serializable{
    //variaveis de instancia
    private String criador;
    private Coordenadas coord;
    private int dificuldade;
    private String descricao;
    private HashSet<String> visitas;  //quem visitou a cache
    private HashSet<String> tesouros; //o que la tem -> usada apenas nesta classe! as subclasses nao usam isto
    private String codigo;            //codigo identifacador da cache, unico para cada cache, vai ser usado para concluir uma cache    
    private String motivo;            //apenas é usado se a cache foi reportada!

    /*
     * Construtores
     */
    /**
     * Construtor vazio, ou seja, inicializa uma cache.
     */
    public Cache(){
        this.criador="";
        this.coord=new Coordenadas();
        this.dificuldade=0;
        this.descricao="";
        this.visitas=new HashSet<String>();
        this.tesouros=new HashSet<String>();
        this.codigo=UUID.randomUUID().toString().replace("-","").substring(0,6);
    }

    /**
     * Construtor parametrizado, ou seja, rececbe um criador, umas coordenadas, um grau de dificuldade, uma descrição, uma lista com as visitas e uma lista com os 
     * tesouros, e cria uma nova Cache com estes parametros.
     * @ param criador Utilizador criadorda cache.
     * @param coord Coordenadas do local onde se encontra a cache.
     * @param dificuldade Grau de dificuldade da cache.
     * @param desc Descrição acerca da cache.
     * @param visitas Lista com os utilizadores que já visitaram a cache.
     */
    public Cache(String criador, Coordenadas coord, int dificuldade, String desc, HashSet<String> visitas){
        this.criador=criador;
        this.coord=coord;
        this.dificuldade=dificuldade;
        this.descricao=desc;
        this.visitas = new HashSet<String>();
        this.tesouros = new HashSet<String>();
        this.codigo = UUID.randomUUID().toString().replace("-","").substring(0,6);
    }

    /**
     * Construtor por cópia, ou seja, copia os dados de uma Cache já existente.
     * @param c Cache que vamos copiar.
     */
    public Cache(Cache c){
        this.criador=c.getCriador();
        this.coord=c.getCoordenadas();
        this.dificuldade=c.getDificuldade();
        this.descricao=c.getDescricao();
        this.visitas=c.getVisitas();
        this.tesouros=c.getTesouros();  
        this.codigo=c.getCodigo();
    }

    /*
     * Gets
     */
    /**
     * A função getCriador devolve o criador da cache.
     * @return criador Criador da cache.
     */
    public String getCriador(){ return criador; }

    /**
     * A função getCoordenadas devolve as coordenadas da cache.
     * @return coord Devolve um clone das coordenadas de uma cache.
     */
    public Coordenadas getCoordenadas(){return coord.clone(); }

    /**
     * A função getDificuldade devolve o grau de dificuldade da cache.
     * @return dificuldade Dificuldade de uma cache.
     */
    public int getDificuldade() { return dificuldade; }

    /**
     * A função getDescricao devolve uma descrição acerca da cache.
     * @return descricao Descrição da cache.
     */
    public String getDescricao(){ return descricao; }

    /**
     * A função getVisitas devolve a lista dos utilizadores que visitaram a cache.
     * @return clone Clone da lista dos visitantes da cache para respeitar o encapsulamento.
     */
    public HashSet<String> getVisitas(){
        HashSet<String> clone = new HashSet<String>();

        for(String visita: visitas){
            clone.add(visita);
        }

        return clone;
    }

    /**
     * A função getTesouros devolve a lista com os tesouros que a cache contém.
     */
    public HashSet<String> getTesouros(){ 
        HashSet<String> clone = new HashSet<String>();

        for(String tesouro: tesouros){
            clone.add(tesouro);
        }

        return clone;
    }

    /**
     * A função getCodigo devolve o código da cache.
     */
    public String getCodigo(){ return codigo; }

    /**
     * A função getReportMotive devolve o motivo de uma cache ter sido reportada, apenas é usado se cache tiver sido reportada.
     */
    public String getReportMotive(){ return motivo; }

    /**
     * A função getTipo devolve o tipo da cache.
     */
    public String getTipo(){ return "Cache Normal";}

    /*
     * Sets
     */
    /**
     * A função setCriador modifica o criador da cache.
     * @param criador O novo criador que ira substituir o antigo.
     */
    public void setCriador(String criador){ this.criador=criador; }

    /**
     * A função setCoordenadas modifica as coordenadas de uma cache.
     * @param coord As novas coordenadas que irão substituir as antigas.
     */
    public void setCoordenadas(Coordenadas coord){ this.coord=coord.clone(); }

    /**
     * A função setDificuldade altera a dificuldade de uma cache.
     * @param dificuldade O novo grau de dificuldade da cache.
     */
    public void setDificuldade(int dificuldade){ this.dificuldade=dificuldade; }

    /**
     * A função setDescricao altera a descrição de uma cache.
     * @param descricao A nova descrição acerca da cache.
     */
    public void setDescricao(String descricao){ this.descricao=descricao; }

    /**
     * A função setVisistas modifica a lista que utilizadores que visitaram a cache.
     * @param novas_visitas Nova lista de utilizadores que visitaram a cache.
     */
    public void setVisitas(HashSet<String> novas_visitas){
        visitas.clear(); //apaga tudo no hashset
        for(String s: novas_visitas){
            visitas.add(s);
        }
    }

    /**
     * A função setTesouros modifica a lista dos tesouros  que uma cache contêm.
     * @param novos_tesouros Lista com os tesouros que ira substituir a antiga.
     */
    public void setTesouros(HashSet<String> novos_tesouros){
        tesouros.clear(); //apaga tudo no hashset
        for(String s: novos_tesouros){
            tesouros.add(s);
        }
    }

    /**
     * A função setReportMotive altera o motivo de uma cache ter sido reportada.
     * @param motivo Novo motivo de a cache ter sido reportada.
     */
    public void setReportMotive(String motivo){ this.motivo=motivo;}

    /*
     * Funcoes aux
     */
    /**
     * A função addTesouro adiciona um tesouro a uma cache.
     * @param tesouro Novo objeto que será adicionado a lista de tesouros.
     */
    public void addTesouro(String tesouro){ tesouros.add(tesouro);}

    /**
     * A função addVisita adiciona um utilizador a lista de visitas de uma cache.
     * @param visitante Utilizador que será inserido na lista de visitas da cache.
     */
    public void addVisita(String visitante){ visitas.add(visitante);}

    /**
     * A função removeTesouro remove um tesouro da lista dos tesouros.
     * @param tesouro Tesouro que é retirado da lista dos tesouros.
     */
    public void removeTesouro(String tesouro){ tesouros.remove(tesouro);}

    /*
     * 
     * Funcoes aux
     */
    /**
     * A função clone duplica uma cache com o auxilio de um construtor por cópia.
     */
    public Cache clone(){ return new Cache(this);}

    /**
     * A função toString imprime o Cache.
     * @param c A Cache que será imprimida.
     */
    public StringBuilder toString(Cache c){
        StringBuilder str = new StringBuilder();
        String tipo = c.getTipo();
        
        switch(tipo){
                case "Cache Normal":
                    str.append("Criador: "             +this.getCriador()                      +"\n");
                    str.append("Tipo: "                +this.getTipo()                         +"\n");
                    str.append("Coordenadas: "         +coord.toString(this.getCoordenadas())  +"\n");
                    str.append("Dificuldade: "         +this.getDificuldade()                  +"\n");
                    str.append("Descrição: "           +this.getDescricao()                    +"\n");
                    str.append("Visitas: "             +this.getVisitas().toString()           +"\n");
                    str.append("Tesouros: "            +this.getTesouros()                     +"\n");
                    str.append("Código identificador: "+this.getCodigo()                       +"\n");
                    break;
                
                case "Micro Cache":
                    str = ((CacheMicro) c).toString((CacheMicro) c);
                    break;
                
                case "Multi Cache":
                    str = ((CacheMulti) c).toString((CacheMulti) c);
                    break;
                
                case "Cache Evento":
                    str = ((CacheEvento) c).toString((CacheEvento) c);
                    break;
                
                case "Cache Virtual":
                    str = ((CacheVirtual) c).toString((CacheVirtual) c);
                    break;
                
                case "Cache Misterio":
                    str = ((CacheMisterio) c).toString((CacheMisterio) c);
                    break;
                
            }
        return str;
    }

    /**
     * A função equals recebe um Objeto genérico e verifica se é exatamente igual a um Utilizador.
     * @param obj Objeto a comparar.
     */
    public boolean equals(Object obj){
        if(this == obj) return true;
        if((obj == null) || (this.getClass() != obj.getClass())) return false;

        Cache c = (Cache) obj;

        return(this.criador.equals(c.getCriador()) && this.coord.equals(c.getCoordenadas()) 
            && this.descricao.equals(c.getDescricao()) && this.getDificuldade()==c.getDificuldade()
            && this.visitas.equals(c.getVisitas()) && this.tesouros.equals(c.getTesouros()) 
            && this.codigo.equals(c.getCodigo()));
    }

    /**
     * A função getPontuacao devolve a pontuação de cada tipo de Cache.
     */
    public int getPontuacao(){return 3;}
}