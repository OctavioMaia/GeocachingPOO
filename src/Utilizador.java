package src;
import java.util.*;
import static java.lang.System.out;
import Exceptions.*;
import java.io.*;

/**
 * Classe Utilizador
 * 
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 * @version 1.0
 */
public class Utilizador implements Serializable{
    //variaveis de instancia
    private String email;
    private String pw;
    private String nome;
    private char sexo;                                   //M ou F, apenas!
    private String morada;
    private GregorianCalendar data_nascimento;
    private TreeMap<GregorianCalendar,Cache> atividades; //lista atividades que realizou
    private TreeMap<String,Utilizador> amigos;           //lista amigos
    private TreeMap<String,Utilizador> pedidosAmizade;   //lista amigos pendente
    private int privilegios;                             //0 - user normal, 1 - admin
    private int pontuacao;
    private String codigo;
    
    /**
     * Construtor vazio, ou seja, inicializa um Utilizador a nulo.
     */
    public Utilizador(){
        this.email = "";
        this.pw = "";
        this.nome = "";
        this.sexo = ' ';
        this.morada = "";
        this.data_nascimento = new GregorianCalendar();
        this.atividades = new TreeMap<GregorianCalendar,Cache>();
        this.amigos = new TreeMap<String,Utilizador>();
        this.pedidosAmizade = new TreeMap<String,Utilizador>();
        this.privilegios=0;
        this.pontuacao=0;
        this.codigo = UUID.randomUUID().toString().replace("-","").substring(0,10);
    }
    
    /**
     * Construtor parametrizado, ou seja, recebe um email, uma password, um nome, o género, uma morada e uma data de nascimento, e cria um Utilizador com esses valores.
     * @param email Email de registo
     * @param pw Password de registo
     * @param nome Nome do Utilizador
     * @param sexo Género do Utilizador
     * @param morada Morada do Utilizador
     * @param d Data de nascimento do Utilizador
     * @param priv Privilégio do Utilizador, por defeito estão sempre a 0. (0 utilizador, 1 administrador)   
     */
    public Utilizador(String email, String pw, String nome, char sexo, String morada, GregorianCalendar d, int priv){
        this.email = email;
        this.pw = pw;
        this.nome = nome;
        this.sexo = sexo;
        this.morada = morada;
        this.data_nascimento = d;
        this.atividades = new TreeMap<GregorianCalendar,Cache>();
        this.amigos = new TreeMap<String,Utilizador>();
        this.pedidosAmizade = new TreeMap<String,Utilizador>();
        this.privilegios=priv;
        this.pontuacao=0;
        this.codigo = UUID.randomUUID().toString().replace("-","").substring(0,10);
    }
    
    /**
     * Construtor por cópia, ou seja, copia os dados de um Utilizador já existente.
     * @param user O Utilizador que vamos copiar.
     */
    public Utilizador(Utilizador user){
        this.email = user.getEmail();
        this.pw = user.getPw();
        this.nome = user.getNome();
        this.sexo = user.getSexo();
        this.morada = user.getMorada();
        this.data_nascimento = user.getDate();
        this.atividades = user.getAtividades();
        this.amigos = user.getAmigos();
        this.pedidosAmizade = user.getPedidos();
        this.pontuacao = user.getPontuacao();
        this.privilegios = user.getPrivilegios();
        this.codigo = UUID.randomUUID().toString().replace("-","").substring(0,10);
    }
    
    /*
     * Gets
     */
    /**
     * A função getEmail devolve o email do Ultilizador.
     */
    public String getEmail(){ return email; }
    
    /**
     * A função getPw devolve a Password do Utilizador.
     */
    public String getPw(){ return pw; }
    
    /**
     * A função getNome devolve o nome do Utilizador.
     */
    public String getNome(){ return nome; }
    
    /**
     * A função getSexo devolve o genero do Utilizador.
     */
    public char getSexo(){ return sexo; }
    
    /**
     * A função getMorada devolve a morada do Utilizador.
     */
    public String getMorada(){ return morada; }
    
    /**
     * A função getDate devolve a Data de Nascimento do Utilizador.
     */
    public GregorianCalendar getDate(){ return (GregorianCalendar)data_nascimento.clone(); }
    
    /**
     * A função getPrivilegios devolve o privilegio do Utilizador.
     */
    public int getPrivilegios(){ return privilegios; }
    
     /**
     * A função getPontuacao devolve a pontuação do Utilizador.
     */
    public int getPontuacao(){ return pontuacao; }
    
    /**
     * A função getCodigo devolve o código do Utilizador.
     */
    public String getCodigo(){ return codigo; }
    
    /**
     * A função getAtividades devolve as caches descobertas pelo Utilizador.
     */
    public TreeMap<GregorianCalendar,Cache> getAtividades(){
        TreeMap<GregorianCalendar,Cache> clone = new  TreeMap<GregorianCalendar,Cache>();
        
        for(GregorianCalendar c: atividades.keySet()){
            clone.put(c,atividades.get(c).clone());
        }
        
        return clone;
    }
    
    /**
     * A função getAmigos devolve os amigos do Utilizador.
     */
    public TreeMap<String, Utilizador> getAmigos() { 
        TreeMap<String, Utilizador> clone = new TreeMap<String,Utilizador>();
        
        for(String user: amigos.keySet()){
            clone.put(user,amigos.get(user).clone());
        }
        
        return clone;
    }
    
    /**
     * A função getPedidos devolve os pedidos de amizade realizados pelo Utilizador.
     */
    public TreeMap<String, Utilizador> getPedidos(){
        TreeMap<String, Utilizador> clone = new TreeMap<String,Utilizador>();
        
        for(String user: pedidosAmizade.keySet()){
            clone.put(user,pedidosAmizade.get(user).clone());
        }
        
        return clone;
    }
       
    /*
     * Sets
     */
    /**
     * A função setEmail modifica o email do Utilizador.
     * @param email O novo email que irá substituir o antigo.
     */
    public void setEmail(String email){ this.email=email; }
    
    /**
     * A função setPw modifica a password do Utilizador.
     * @param pw A nova password que irá substituir a antiga.
     */
    public void setPw(String pw){ this.pw=pw; }
    
    /**
     * A função setNome modifica o nome do Utilizador.
     * @param nome O novo nome que irá substituir o antigo.
     */
    public void setNome(String nome){ this.nome=nome; }
    
    /**
     * A função setSexo modifica o género do Utilizador.
     * @param sexo O novo género que irá substituir o antigo.
     */
    public void setSexo(char sexo){ this.sexo=sexo; }
    
    /**
     * A função setMorada modifica a morada do Utilizador.
     * @param morada A nova morada que irá substituir a antiga.
     */
    public void setMorada(String morada){ this.morada=morada; }
    
    /**
     * A função setDate modifica a data de nascimento do Utilizador.
     * @param d A nova data de nascimento que irá substituir a antiga.
     */
    public void setDate(GregorianCalendar d){this.data_nascimento=(GregorianCalendar)d.clone(); }
    
    /**
     * A função setPrivilegios modifica o privilégio do Utilizador.
     * @param priv O novo privilégio que irá ser atibuído a um Uilizador.
     */
    public void setPrivilegios(int privilegios){ this.privilegios=privilegios; }
    
    /**
     * A função setPontuacao modifica a pontuação do Utilizador.
     * @param priv A nova pontuação que irá ser atibuído a um Uilizador.
     */
    public void setPontuacao(int pontuacao){ this.pontuacao=pontuacao; }
    
    /*
     * Funcoes lista de amigos
     */
    
    /**
     * A função adicionarAmigo adiciona um Utilizador à lista de amigos.
     * @param user Utilizador que se pretende adicionar à lista de amigos.
     */
    public void adicionarAmigo(Utilizador user) throws UtilizadorNaoExisteException{
        String email = user.getEmail();
        if(user==null){
            throw new UtilizadorNaoExisteException();
        }else{
            amigos.put(email,user.clone());
        }
    }
    
    /**
     * A função removerAmigo remove um Utilizador da lista de amigos.
     * @param user Utilizador que se pretende remover da lista de amigos.
     */
    
    public void removerAmigo(Utilizador user) throws UtilizadorNaoExisteException{
        String email = user.getEmail();
        
        if(amigos.containsKey(email)){
            amigos.remove(user);
        }else{ 
            throw new UtilizadorNaoExisteException();
        }
    }
    
    /*
     * Enviar pedido de amizade
     */
    /**
     * A função adicionarPedidoAmizade adiciona um Utilizador à lista de pedidos de amizade.
     * @param user Utilizador que se pretende adicionar à lista de pedidos de amizade.
     */
    public void adicionarPedidoAmizade(Utilizador user) throws UtilizadorNaoExisteException,AmigoJaAdicionadoException{
        if(user == null){
            throw new UtilizadorNaoExisteException();
        }else if(amigos.containsValue(user)){
            throw new AmigoJaAdicionadoException();
        }else{
            String mail = user.getEmail();
            pedidosAmizade.put(mail,user);
        }
    }
    
    /*
     * Handler pedidos de amizade
     */
    /**
     * A função aceitarPedidoAmizade aceita um pedido de amizade, caso este exista na lista de pedidos de amizade.
     * @param mail O email do Utilizador que iremos aceitar.
     */
    public void aceitarPedidoAmizade(String mail) throws PedidoNaoExisteException,AmigoJaAdicionadoException{
        if(!pedidosAmizade.containsKey(mail)){
            throw new PedidoNaoExisteException();
        }else if(amigos.containsKey(mail)){
            throw new AmigoJaAdicionadoException();
        }else{
            amigos.put(mail,pedidosAmizade.get(mail));
            pedidosAmizade.remove(mail);
        }
    }
    
    
    /**
     * A função rejeitarPedidoAmizade rejeita um pedido de amizade, caso este exita na lista de pedidos de amizade.
     * @param mail O email do Utilizador que iremos rejeitar.
     */
    public void rejeitarPedidoAmizade(String mail) throws UtilizadorNaoExisteException, PedidoNaoExisteException{
        if(mail == null){
            throw new UtilizadorNaoExisteException();
        }else if(!pedidosAmizade.containsKey(mail)){
            throw new PedidoNaoExisteException();
        }else{
            pedidosAmizade.remove(mail);
        }
    }
    
    /*
     * Atividades
     */
    /**
     * A função adicionarAtividade adiciona a descoberta de uma Cache, numa dada data, à lista de descobertos do Utilizador.
     * @param d A data da descoberta da Cache.
     * @param c A Cache descoberta.
     */
    public void adicionarAtividade(GregorianCalendar d, Cache c) throws DataInvalidaException,CacheNaoExisteException{
        if(d == null){
            throw new DataInvalidaException();
        }else if( c==null){
            throw new CacheNaoExisteException();
        }else{
            atividades.put((GregorianCalendar)d.clone(),c.clone());
        }
    }
    
    /**
     * A função removerAtividade remove a descoberta de uma Cavhe, numa dada data, da lista de descobertas do Utilizador.
     * @param d A data de remoção da descoberta da Cache.
     * @param c A Cache removida.
     */
    public boolean removerAtividade(String codigo) throws AtividadeNullException{
        if(getAtividades().size()==0){
            throw new AtividadeNullException();
        }else if(getAtividades().size()!=0){
            for(GregorianCalendar entry : atividades.keySet()) {
                if(atividades.get(entry).getCodigo().equals(codigo)){
                    atividades.remove(entry);
                    return true;
                }
            }
        }
        return false;
   }
   
   /**
    * A função addPontuacao adiciona a pontuação de um Utilizador.
    * @param p Pontos obtidos pelo Utilizador.
    */
   public void addPontuacao(int p){ this.pontuacao+=p;}
   
    /*
     * Funcoes aux
     */
    /**
     * A função toString imprime o Utilizador.
     * @param user O Utilizador que será imprimido.
     */
    public StringBuilder toString(Utilizador user){
       StringBuilder str = new StringBuilder();
       str.append("-------Dados do utilizador------\n");
       str.append("Nome : "       + this.getNome()         + "\n");
       str.append("Pontuação : "  + this.getPontuacao()    + "\n");
       str.append("Privilegio : " + this.getPrivilegios()  + "\n");
       str.append("Email : "      + this.getEmail()        + "\n");
       str.append("Sexo : "       + this.getSexo()         + "\n");
       str.append("Morada : "     + this.getMorada()       + "\n");
       str.append("Data de nascimento : " + this.getDate().get(Calendar.DAY_OF_MONTH)+"/"
                                          + this.getDate().get(Calendar.MONTH)+"/"
                                          + this.getDate().get(Calendar.YEAR)+"\n");
       return str;
    }
   
    /**
     * A função equals recebe um Objeto genérico e verifica se é exatamente igual a um Utilizador.
     * @param obj Objeto a comparar.
     */
    public boolean equals(Object obj){
        if(this == obj) return true;
        if((obj == null) || (this.getClass() != obj.getClass())) return false;
        
        Utilizador user = (Utilizador) obj;
        
        return(this.nome.equals(user.getNome()) && this.email.equals(user.getEmail()) 
            && this.pw.equals(user.getPw()) && this.sexo == user.getSexo() 
            && this.data_nascimento.equals(user.getDate()) && this.amigos.equals(user.getAmigos()) 
            && this.pontuacao == user.getPontuacao() && this.atividades.equals(user.getAtividades()));
    }
    
    /**
     * A função clone faz um clone de um Utilizador, a partir do construtor por cópia.
     */
    public Utilizador clone(){
        return new Utilizador(this);
    }
}    