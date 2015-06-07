package src;
import java.util.*;
import static java.lang.System.out;
import Exceptions.*;
import java.io.*;

/**
 * Classe Evento
 * 
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 * @version 1.0
 */
public class Evento implements Serializable{
    // variáveis de instância
    private String criador;  //nome do administrador que criou o evento
    private String nome_evento;
    private String codigo_evento;
    private String estado_tempo;
    private int numero_inscricoes; 
    private int maximo_inscricoes; // valor maximo
    private GregorianCalendar data_limite; //data fim das inscricões
    private TreeMap<String,Utilizador> participantes; //lista dos participantes
    private TreeMap<String,Cache> listaCaches;
    private Metereologia tempo = new Metereologia();
    
    /**
     * Construtor vazio, ou seja, inicializa um Evento a nulo.
     */
    public Evento(){ //substituir por ""
        this.criador = "";
        this.nome_evento = "";
        this.codigo_evento = UUID.randomUUID().toString().replace("-","").substring(0,10);
        this.estado_tempo = "";
        this.numero_inscricoes = 0;
        this.data_limite = new GregorianCalendar();
        this.participantes = new TreeMap<String,Utilizador>();
        this.listaCaches = new TreeMap<String,Cache>();
    }
    
    /**
     * Construtor parametrizado, ou seja, recebe um criador, o nome do evento, o estado do tempo, o número máximo de inscrições, e a data de limite de inscrição de um evento.
     * @param criador Nome do administrador que criou o Evento
     * @param nome_evento O nome do Evento
     * @param estado_tempo O estado de tempo
     * @param maximo_inscricoes O valor máximo de inscrições num Evento
     * @param data_limite A data limite para a inscrição de um Evento
     */
    public Evento(String criador, String nome_evento, String estado_tempo, int maximo_inscricoes, GregorianCalendar data_limite){
        this.criador = criador;
        this.nome_evento = nome_evento;
        this.codigo_evento = UUID.randomUUID().toString().replace("-","").substring(0,10);
        this.estado_tempo = estado_tempo;
        this.numero_inscricoes = 0;
        this.maximo_inscricoes = maximo_inscricoes;
        this.data_limite = data_limite;
        this.participantes = new TreeMap<String,Utilizador>();
        this.listaCaches = new TreeMap<String,Cache>();
    }
    
    /**
     * Construtor por cópia, ou seja, copia os dados de um Evento já existente.
     * @param e O Evento que vamos copiar.
     */
    public Evento(Evento e){
        this.criador = e.getCriador();
        this.nome_evento = e.getNomeEvento();
        this.estado_tempo = e.getEstadoTempo();
        this.numero_inscricoes = e.getNumInscricoes();
        this.maximo_inscricoes = e.getMaximoInscricoes();
        this.data_limite = e.getData();
        this.participantes = e.getParticipantes();
        this.listaCaches = e.getCaches();
        this.codigo_evento = UUID.randomUUID().toString().replace("-","").substring(0,10);
    }
    
    /*
     * Gets
     */
    /**
     * A função getCriador devolve o nome do administrador que criou o Evento.
     */
    public String getCriador(){return this.criador;}  
    
    /**
     * A função getNomeEvento devolve o nome do Evento.
     */
    public String getNomeEvento(){return this.nome_evento;}   
    
    /**
     * A função getCodigoEvento devolve o código do Evento.
     */
    public String getCodigoEvento(){return this.codigo_evento;}
    
    /**
     * A função getEstadoTempo devolve o estado de tempo.
     */
    public String getEstadoTempo () {return this.estado_tempo;}
    
    /**
     * A função getNumInscricoes devolve o número de inscrições do Evento.
     */
    public int getNumInscricoes(){return participantes.size();}  
    
    /**
     * A função getMaximoInscricoes devolve o número máximo de inscrições do Evento.
     */
    public int getMaximoInscricoes(){ return this.maximo_inscricoes;}
    
    /**
     * A função getData devolve a data limite de inscrições do Evento.
     */
    public GregorianCalendar getData(){return (GregorianCalendar) this.data_limite.clone();}   
    
    /**
     * A função getParticipantes devolve os participantes do Evento.
     */
    public TreeMap<String, Utilizador> getParticipantes() { 
        TreeMap<String, Utilizador> clone = new TreeMap<String,Utilizador>();
        
        for(String user: participantes.keySet()){
            clone.put(user,participantes.get(user).clone());
        }
        
        return clone;
    }
    
    /**
     * A função getCaches devolve as caches do Evento.
     */
    public TreeMap<String,Cache> getCaches(){
        TreeMap<String,Cache> clone = new  TreeMap<String,Cache>();
        
        for(String c: listaCaches.keySet()){
            clone.put(c,listaCaches.get(c).clone());
        }
        
        return clone;
    }
    
    /**
     * A função adicionarCache adiciona uma cache a um Evento.
     * @param s O nome da cache.
     * @param c Cache que se pretende adicionar ao Evento.
     */
    public void adicionarCache(String s, Cache c) throws CacheNaoExisteException, CacheJaInseridaException{
        if(c==null){
            throw new CacheNaoExisteException();
        }else if(listaCaches.containsKey(s)){
            throw new CacheJaInseridaException();
        }else{
            listaCaches.put(s,c);
        }
    }
    
    /**
     * A função getTipo devolve o tipo da cache.
     * @param key Cache que se pretende verificar.
     */
    public String getTipo(String key){
        return this.listaCaches.get(key).getTipo();
    }
    
    /*
     * Sets
     */
     /**
     * A função setCriador modifica o nome do administrador que criou o Evento.
     * @param criador O novo nome que irá substituir o antigo.
     */
    public void setCriador(String criador){this.criador = criador;}
    
      /**
     * A função setNomeEvento modifica o nome do Evento.
     * @param nome_evento O novo nome do Evento que irá substituir o antigo.
     */
    public void setNomeEvento(String nome_evento){this.nome_evento = nome_evento;} 
    
      /**
     * A função setEstadoTempoS modifica o estado de tempo.
     * @param estado_tempo O novo estado de tempo que irá substituir o antigo.
     */
    public void setEstadoTempoS (String estado_tempo) {this.estado_tempo = estado_tempo;}
    
    /**
     * A função setMaxInscricoes modifica o número máximo de inscrições do Evento.
     * @param num O novo número máximo de inscrições do Evento que irá substituir o antigo.
     */
    public void setMaxInscricoes(int num){this.maximo_inscricoes = num;}  
    
    /**
     * A função setData modifica a data limite de inscrição no Evento.
     * @param data A nova data limite de inscrição irá substituir a antiga.
     */
    public void setData(GregorianCalendar data){this.data_limite=data;}
    
    /*
     * Funcoes aux
     */
    /**
     * A função addParticipante adiciona um participante à lista de participantes do Evento.
     * @param user Participante que se pretende adicionar à lista de participantes do Evento.
     */
    public void addParticipante(Utilizador user){
        participantes.put(user.getEmail(),user);
        this.numero_inscricoes++;
    }
    
    /**
     * A função removeParticipante remove um participante da lista de participantes do Evento.
     * @param email O email correspondente ao participante que se pretende remover.
     */
    public void removeParticipante(String email){participantes.remove(email);}
    
    /**
     * A função clone faz um clone de um Evento, a partir do construtor por cópia.
     */
    public Evento clone(){
        return new Evento(this);
    }
    
    /**
     * A função equals recebe um Objeto genérico e verifica se é exatamente igual a um Evento.
     * @param obj Objeto a comparar.
     */
    public boolean equals(Object obj){
        if(this == obj) return true;
        if((obj == null) || (this.getClass() != obj.getClass())) return false;
        
        Evento ev = (Evento) obj;
     
        return(this.criador.equals(ev.getCriador()) && this.nome_evento.equals(ev.getNomeEvento()) 
            && this.codigo_evento.equals(ev.getCodigoEvento()) && this.estado_tempo.equals(ev.getEstadoTempo()) && this.listaCaches.equals(ev.getCaches()) && this.numero_inscricoes == ev.getNumInscricoes() 
            && this.data_limite.equals(ev.getData()) && this.participantes.equals(ev.getParticipantes()));
            
    }
    
    /**
     * A função printCachesParticipantes imprime a cache do participante do Evento.
     * @param c O Evento que será imprimido.
     */
    public StringBuilder printCachesParticipantes(Evento c){
        StringBuilder str = new StringBuilder();
        
        for (Map.Entry<String,Cache> entry : listaCaches.entrySet()){
            str.append("\n   Cache do tipo: "+ entry.getValue().getTipo());
            str.append(" Código: "           + entry.getValue().getCodigo());
        }
        
        return str;
    }
    
    /**
     * A função toString imprime o Evento.
     * @param e O Evento que será imprimido.
     */
    public StringBuilder toString(Evento e){
        StringBuilder str = new StringBuilder();
        str.append("-------Dados do Evento------\n");
        str.append("Criador do evento: "       + this.getCriador()      +"\n");
        str.append("Nome do evento: "          + this.getNomeEvento()   +"\n");
        str.append("Código do evento: "        + this.getCodigoEvento() +"\n");
        str.append("Numero de inscrições: "    + this.getNumInscricoes()+"\n");
        str.append("Data limite de inscrição: "+ this.getData().get(Calendar.DAY_OF_MONTH)+"/"
                                               + this.getData().get(Calendar.MONTH)+"/"
                                               + this.getData().get(Calendar.YEAR)+"\n");
        str.append("Lista de caches: "         + this.printCachesParticipantes(e) +"\n");                                      
        return str;
    }
}