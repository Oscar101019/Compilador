package mx.edu.ittepic.compilador.Automatas;

/**
 * Created by carlo on 15/07/2018.
 */

public class Numero {
    int contador=0;
    public String transiciones="";
    String cadena="";

    public Numero(){}


    public boolean esNumero(String palabra){
        cadena=palabra;
        contador=0;
        transiciones="q0,";
        boolean resultado=false;
        if(esFinal(palabra)){return false;}
        char i = palabra.charAt(contador);
        if(i>=48 && i<=57){
            transiciones=transiciones+i+"->q1";
            contador++;
            resultado = numero(palabra);/*revisar el resto de la cadena de manera recursiva*/
        }
        return resultado;
    }//esNumero

    public boolean numero(String palabra){
        boolean res=false;
        if(esFinal(palabra)){return true;}
        char i = palabra.charAt(contador);
        if(i>=48 && i<=57){
            transiciones=transiciones+","+i+"->q1";
            contador++;
            if(numero(palabra)){
                res = true;
            }else{res=false;}
        }
        return res;
    }//numero

    public boolean esFinal(String palabra){
        boolean r=false;
        if(palabra.length()==contador){
            r= true;
        }
        return r;
    }//esFinal
}
