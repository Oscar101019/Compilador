package mx.edu.ittepic.compilador.Automatas;

/**
 * Created by carlo on 10/07/2018.
 */

public class Relacional {
    int contador=0;
    public String transiciones="";

    public Relacional(){}


    public boolean esRelacional(String palabra) {
        contador = 0;
        transiciones = "q0,";
        boolean resultado = false;
        if(esFinal(palabra)){return false;}
            char i=palabra.charAt(contador);
            if(i==60||i==61||i==61){//<, >, = pueden ser finales
                transiciones=transiciones+i+"->q1";
                contador++;
                if(esFinal(palabra)){
                    return true;
                }
            }if(i==33){//!
                transiciones=transiciones+i+"->q3";
                contador++;
            }
        i=palabra.charAt(contador);
        if(i==60||i==61||i==61){//<, >, = finales
            transiciones=transiciones+i+"->q2";
            contador++;
            if(esFinal(palabra)){
                return true;
            }
        }
        return resultado;
    }//esRelacional

    public boolean esFinal(String palabra){
        if(palabra.length()==contador){
            return true;
        }
        return false;
    }//esFinal
}
