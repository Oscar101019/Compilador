package mx.edu.ittepic.compilador.Automatas;

/**
 * Created by carlo on 11/07/2018.
 */

public class Identificador {
    int contador=0;
    public String transiciones="";
    String cadena="";

    public Identificador(){}

    /*
    * empieza con minuscula
    * seguido de minuscula, numero o mayuscula
    * */
    public boolean esIdentificador(String palabra){
        cadena=palabra;
        contador=0;
        transiciones="q0,";
        boolean resultado=false;
            if(esFinal(palabra)){return false;}
            char i = palabra.charAt(contador);
            if(i>=97 && i<=122){
                transiciones=transiciones+i+"->q1";
                contador++;
                //if(esFinal(palabra)){return true;}
                resultado = identificador(palabra);/*revisar el resto de la cadena de manera recursiva*/
            }
        return resultado;
    }//esIdentificador

        public boolean identificador(String palabra){
        boolean res=false;
            if(esFinal(palabra)){return true;}
            char i = palabra.charAt(contador);
            if(i>=97 && i<=122 ||
                    i>=65 && i<=90 ||
                      i>=48 && i<=57){
                transiciones=transiciones+","+i+"->q1";
                contador++;
                if(identificador(palabra)){
                    res = true;
                }else{res=false;}

            }
            return res;
        }//identificador




    public boolean esFinal(String palabra){
        boolean r=false;
        if(palabra.length()==contador){
            r= true;
        }
        return r;
    }//esFinal

}//clase
