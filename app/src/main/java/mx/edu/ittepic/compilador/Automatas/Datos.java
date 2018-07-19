package mx.edu.ittepic.compilador.Automatas;

/**
 * Created by carlo on 10/07/2018.
 */

public class Datos {
    int contador=0;
    public String transiciones="";

    public Datos(){}

    public boolean esDato(String palabra){
        contador=0;
        transiciones="";
        boolean resultado = false;

        if (esFinal(palabra)) {return false;}
        if (palabra.charAt(contador) == 'B') {
            contador++;
            transiciones = transiciones + "q0,B";

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'O') {
                contador++;
                transiciones = transiciones + "->q1,O";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'O') {
                contador++;
                transiciones = transiciones + "->q2,O";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'L') {
                contador++;
                transiciones = transiciones + "->q3,L";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'E') {
                contador++;
                transiciones = transiciones + "->q4,E";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'A') {
                contador++;
                transiciones = transiciones + "->q5,A";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'N') {
                contador++;
                transiciones = transiciones + "->q6,N";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'O') {
                contador++;
                transiciones = transiciones + "->q7,O";
            }
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q8";
                return true;
            }
        }//booleano

        if (palabra.charAt(contador) == 'E') {
            contador++;
            transiciones = transiciones + "q0,E";

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'N') {
                contador++;
                transiciones = transiciones + "->q9,N";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'T') {
                contador++;
                transiciones = transiciones + "->q10,T";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'E') {
                contador++;
                transiciones = transiciones + "->q11,E";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'R') {
                contador++;
                transiciones = transiciones + "->q12,R";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'O') {
                contador++;
                transiciones = transiciones + "->q13,O";
            }
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q14";
                return true;
            }
        }//ENTERO

        if (palabra.charAt(contador) == 'F') {
            contador++;
            transiciones = transiciones + "q0,F";
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'L') {
                contador++;
                transiciones = transiciones + "->q15,L";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'O') {
                contador++;
                transiciones = transiciones + "->q16,O";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'T') {
                contador++;
                transiciones = transiciones + "->q17,T";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'A') {
                contador++;
                transiciones = transiciones + "->q18,A";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'N') {
                contador++;
                transiciones = transiciones + "->q19,N";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'T') {
                contador++;
                transiciones = transiciones + "->q20,T";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'E') {
                contador++;
                transiciones = transiciones + "->q21,E";
            }
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q22";
                return true;
            }
        }//FLOTANTE
        if (palabra.charAt(contador) == 'C') {
            contador++;
            transiciones = transiciones + "q0,C";

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'A') {
                contador++;
                transiciones = transiciones + "->q23,A";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'D') {
                contador++;
                transiciones = transiciones + "->q24,D";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'E') {
                contador++;
                transiciones = transiciones + "->q25,E";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'N') {
                contador++;
                transiciones = transiciones + "->q26,N";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'A') {
                contador++;
                transiciones = transiciones + "->q27,A";
            }
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q28";
                return true;
            }
        }//CADENA
        return resultado;
    }//esDato

    public boolean esFinal(String palabra){
        if(palabra.length()==contador){
            return true;
        }
        return false;
    }//esFinal
}
