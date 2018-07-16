package mx.edu.ittepic.compilador;

import java.io.Serializable;

/**
 * Created by carlo on 07/07/2018.
 */

public class Token implements Serializable {
    String token;
    String cadena;
    String linea;
    String producciones;

    public Token(){}

    public Token(String t,String c, String l,String p){
        token=t;
        cadena=c;
        linea=l;
        producciones=p;
    }
}
