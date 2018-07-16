package mx.edu.ittepic.compilador.Automatas;

/**
 * Created by carlo on 08/07/2018.
 */

public class Palabras {
    int contador=0;
    public String transiciones="";

    public Palabras(){}

    public boolean esPalabra(String palabra){
        contador = 0;
        transiciones = "";
        boolean resultado = false;

        if (esFinal(palabra)) {return false;}
        if (palabra.charAt(contador) == 'C') {
            contador++;
            transiciones = transiciones + "q0,C";

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'I') {
                contador++;
                transiciones = transiciones + "->q1,I";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'C') {
                contador++;
                transiciones = transiciones + "->q2,C";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'L') {
                contador++;
                transiciones = transiciones + "->q3,L";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'O') {
                contador++;
                transiciones = transiciones + "->q4,O";
            }
            if (esFinal(palabra)) {
                transiciones = transiciones + "->Q5";
                return true;
            }
        }//ciclo
        if (palabra.charAt(contador) == 'S') {
            contador++;
            transiciones = transiciones + "q0,S";

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'S') {
                contador++;
                transiciones = transiciones + "->q6,I";
            }
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q7";
                return true;
            }else{
                if (palabra.charAt(contador) == 'N') {
                    contador++;
                    transiciones = transiciones + "->q7,N";
                }
                if (esFinal(palabra)) {return false;}
                if (palabra.charAt(contador) == 'O') {
                    contador++;
                    transiciones = transiciones + "->q10,O";
                }
                if (esFinal(palabra)) {
                    transiciones = transiciones + "->q11";
                    return true;
                }
            }//sino
        }//si, sino
        if (palabra.charAt(contador) == 'I') {
            contador++;
            transiciones = transiciones + "q0,I";

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'N') {
                contador++;
                transiciones = transiciones + "->q12,N";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'I') {
                contador++;
                transiciones = transiciones + "->q13,I";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'C') {
                contador++;
                transiciones = transiciones + "->q14,C";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'I') {
                contador++;
                transiciones = transiciones + "->q15,I";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'O') {
                contador++;
                transiciones = transiciones + "->q16,O";
            }

            if (esFinal(palabra)) {
                transiciones = transiciones + "->q19";
                return true;
            }
        }//inicio
        if (palabra.charAt(contador) == 'R') {
            contador++;
            transiciones = transiciones + "q0,R";

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'U') {
                contador++;
                transiciones = transiciones + "->q18,U";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'T') {
                contador++;
                transiciones = transiciones + "->q19,T";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'A') {
                contador++;
                transiciones = transiciones + "->q20,A";
            }
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q21";
                return true;
            }
        }//ruta
        if (palabra.charAt(contador) == 'F') {
            contador++;
            transiciones = transiciones + "q0,F";

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'U') {
                contador++;
                transiciones = transiciones + "->q22,U";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'N') {
                contador++;
                transiciones = transiciones + "->q23,N";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'C') {
                contador++;
                transiciones = transiciones + "->q24,C";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'I') {
                contador++;
                transiciones = transiciones + "->q25,I";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'O') {
                contador++;
                transiciones = transiciones + "->q26,O";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'N') {
                contador++;
                transiciones = transiciones + "->q27,N";
            }
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q28";
                return true;
            }
        }//funcion



        return resultado;
    }


    public boolean esFinal(String palabra){
        if(palabra.length()==contador){
            return true;
        }
        return false;
    }//esFinal
}//class
