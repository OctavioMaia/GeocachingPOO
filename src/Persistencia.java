package src;
import java.util.*;
import java.io.*;

/**
 * Escreva a descrição da classe Persistencia aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Persistencia implements Serializable{
   /**
    * A função guardarEstado permite guardar um estado em binario.
    * @param e O Core recebido.
    */
   public void guardarEstado(Core e) throws IOException{
       FileOutputStream fos = new FileOutputStream("GeocachingPOO.state");
       ObjectOutputStream oos = new ObjectOutputStream(fos);
        
       oos.writeObject(e);
       oos.close();
   }
    
   /**
    * A função carregarEstado permite carregar um estado.
    */
   public Core carregarEstado() throws IOException,ClassNotFoundException{
       ObjectInputStream ois = new ObjectInputStream(new FileInputStream("GeocachingPOO.state"));

       Core novoState = (Core) ois.readObject();      
       ois.close();
       
       return novoState;
   } 
}
