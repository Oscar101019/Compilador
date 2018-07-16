package mx.edu.ittepic.compilador.Automatas;

/**
 * Created by carlo on 10/07/2018.
 */

public class Relacional {
    int contador=0;
    String transiciones="";

    public Relacional(){}


    public boolean esRelacional(String palabra){
        contador=0;
        transiciones="";
        boolean resultado = false;

        if (esFinal(palabra)) {return false;}
        if (palabra.charAt(contador) == '<'||palabra.charAt(contador) == '>') {
            transiciones = transiciones + "q0,"+palabra.charAt(contador);
            contador++;
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q1";
                return true;
            }
            if (palabra.charAt(contador) == '=') {
                transiciones = transiciones + "->q1," + palabra.charAt(contador);
                contador++;
            }
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q2";
                return true;
            }
        }


        if (palabra.charAt(contador) == '!'||palabra.charAt(contador) == '=') {
            transiciones = transiciones + "q0," + palabra.charAt(contador);
            contador++;

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == '=') {
                transiciones = transiciones + "->q3," + palabra.charAt(contador);
                contador++;
            }
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q2";
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
