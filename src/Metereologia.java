package src;
import java.util.*;
import java.io.*;

/**
 * Classe Metereologia
 * 
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 * @version 1.0
 */
public class Metereologia implements Serializable{
    //Variaveis de instância
    private TreeMap<String, Integer> metereologia = new TreeMap<String, Integer>();
    
    public Metereologia () {
        metereologia.put("Limpo",1);
        metereologia.put("Neblina",2);
        metereologia.put("Nevoeiro",3);
        metereologia.put("Aguaceiros",4);
        metereologia.put("Chuva",5);
        metereologia.put("Ventos Fortes",6);
        metereologia.put("Trovoada",7);
        metereologia.put("Neve",8);
    }
    
    /*
     * GETS
     */
    public String getTempo (int v) {
        for(Map.Entry<String,Integer> entry : metereologia.entrySet()){
            if(entry.getValue() == v) return entry.getKey();
        }
        return null; 
    }
   
    public int getPontos (String s) {
        return metereologia.get(s);
    }
    
    public TreeMap<String, Integer> getMetereologia(){
        TreeMap<String, Integer> clone = new TreeMap<String, Integer>();
        
        for(Map.Entry<String,Integer> entry : metereologia.entrySet()){
            clone.put(entry.getKey(),entry.getValue());
        }
        
        return clone;
    }
    
    /**
     * A função equals recebe um Objeto genérico e verifica se é exatamente igual a uma Meteorologia.
     * @param obj Objeto a comparar.
     */
    public boolean equals(Object obj){
        if(this == obj) return true;
        if((obj == null) || (this.getClass() != obj.getClass())) return false;
        
        Metereologia m = (Metereologia) obj;
     
        return(this.metereologia.equals(m.getMetereologia()));
    }
}
