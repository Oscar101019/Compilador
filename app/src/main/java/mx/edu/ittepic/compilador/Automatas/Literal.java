package mx.edu.ittepic.compilador.Automatas;

/**
 * Created by carlo on 11/07/2018.
 */

public class Literal {
    int contador=0;
    String transiciones="";
    String cadena="";

    public Literal(){}

    public boolean esLiteral(String palabra){
        cadena=palabra;
        contador=0;
        transiciones="q0,";
        boolean resultado = false;

        if (esFinal(palabra)) {
            return false;
        }
        if (palabra.charAt(contador) == '"') {
            transiciones = transiciones + palabra.charAt(contador)+ "->q4,";
            contador++;
        }
        /*Aqui validar la cadena con recursividad*/
        if(!palabra()) {
            if (palabra.charAt(contador) == '"') {
                transiciones = transiciones + palabra.charAt(contador) + "->q6";
                contador++;

                if (esFinal(palabra)) {
                    return true;
                }
            }
        }
        return resultado;
    }//esLiteral

    public boolean palabra(){
        if (cadena.charAt(contador) == '"') {
            return false;
        }
        if (cadena.charAt(contador)>= 65 && cadena.charAt(contador)<=90 ||
                cadena.charAt(contador)>= 97 && cadena.charAt(contador)<=122  ||
                cadena.charAt(contador)>= 48 && cadena.charAt(contador)<=57) {
            transiciones = transiciones + cadena.charAt(contador)+ "->q5,";
            contador++;
            palabra();
        }else return false;
    return true;
    }//palabra

    public boolean esFinal(String palabra){
        if(palabra.length()==contador){
            return false;
        }
        return true;
    }//esFinal
}//clase
