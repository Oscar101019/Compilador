package mx.edu.ittepic.compilador.Automatas;

/**
 * Created by carlo on 10/07/2018.
 */

public class Aritmeticos {
    int contador=0;
    public String transiciones="";


    public Aritmeticos(){}


    public boolean esAritmetico(String palabra){
        contador=0;
        transiciones="";
        boolean resultado = false;

        if (esFinal(palabra)) {return false;}
        if (palabra.charAt(contador) == '+') {
            contador++;
            transiciones = transiciones + "q0,+";
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q1";
                return true;
            }
        }else
        if (esFinal(palabra)) {return false;}
        if (palabra.charAt(contador) == '-') {
            contador++;
            transiciones = transiciones + "q0,-";
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q1";
                return true;
            }
        }else
        if (esFinal(palabra)) {return false;}
        if (palabra.charAt(contador) == '*') {
            contador++;
            transiciones = transiciones + "q0,*";
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q1";
                return true;
            }
        }else
        if (esFinal(palabra)) {return false;}
        if (palabra.charAt(contador) == '/') {
            contador++;
            transiciones = transiciones + "q0,/";
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q1";
                return true;
            }
        }
        return resultado;
    }


    public boolean esFinal(String palabra){
        if(palabra.length()==contador){
            return true;
        }
        return false;
    }//esFinal
}
