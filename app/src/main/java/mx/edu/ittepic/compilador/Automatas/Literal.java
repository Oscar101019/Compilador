package mx.edu.ittepic.compilador.Automatas;

/**
 * Created by carlo on 11/07/2018.
 */

public class Literal {
    int contador=0;
    public String transiciones="";
    String cadena="";

    public Literal(){}

    /*
        * empieza con "
        * seguido de minuscula, numero o mayuscula
        * termina con "
        * */
    public boolean esCadena(String palabra){
        cadena=palabra;
        contador=0;
        transiciones="q0,";
        boolean resultado=false;
        if(esFinal(palabra)){return false;}
        char i = palabra.charAt(contador);
        if(i==34){
            transiciones=transiciones+i+"->q1";
            contador++;
            resultado = cadena(palabra);/*revisar el resto de la cadena de manera recursiva*/
        }
        return resultado;
    }//esCadena

    public boolean cadena(String palabra){
        boolean res=false;
        if(esFinal(palabra)){
            char i = palabra.charAt(contador-1);
            if(i==34) {
                transiciones=transiciones+","+i+"->q3";
                return true;
            }
        }
        char i = palabra.charAt(contador);
        if(i>=97 && i<=122 ||
                i>=65 && i<=90 ||
                i>=48 && i<=57||i==34||i==32){
            transiciones=transiciones+","+i+"->q2";
            contador++;
            if(cadena(palabra)){
                res = true;
            }else{res=false;}
        }
        return res;
    }//identificador



    public boolean esFinal(String palabra){
        if(palabra.length()==contador){
            return true;
        }
        return false;
    }//esFinal
}//clase
