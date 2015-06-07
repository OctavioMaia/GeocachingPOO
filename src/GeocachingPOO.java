package src;

import java.util.*;
import java.io.*;
import static java.lang.System.out;

/**
 * GeocachingPOO (exec)
 * 
 * @author Octavio Maia
 * @author Cecilia Marciel
 * @author Claudia Marques
 * @version 1.0
 */
public class GeocachingPOO implements Serializable{
    public Main main = new Main();
            
    /**
    * Este construtor apenas serve para uma simples execução do nosso projeto.
    */
    public GeocachingPOO(){
        main.run();
        out.println("Exit!");
    }
    
}