package mx.edu.ittepic.compilador.Automatas;

public class Valor_booleano {
int contador=0;
public String transiciones="";

public Valor_booleano(){}

    public boolean esBooleano(String palabra){
        contador=0;
        transiciones="";
        boolean resultado = false;
        if (esFinal(palabra)) {return false;}
        if (palabra.charAt(contador) == 'V') {
            contador++;
            transiciones = transiciones + "q0,V";

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'E') {
                contador++;
                transiciones = transiciones + "->q1,E";
            }

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'R') {
                contador++;
                transiciones = transiciones + "->q2,R";
            }

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'D') {
                contador++;
                transiciones = transiciones + "->q3,D";
            }

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'A') {
                contador++;
                transiciones = transiciones + "->q4,A";
            }

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'D') {
                contador++;
                transiciones = transiciones + "->q5,D";
            }

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'E') {
                contador++;
                transiciones = transiciones + "->q6,E";
            }

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'R') {
                contador++;
                transiciones = transiciones + "->q7,R";
            }

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'O') {
                contador++;
                transiciones = transiciones + "->q8,O";
            }

            if (esFinal(palabra)) {
                transiciones = transiciones + "->q9";
                return true;
            }

        }//verdadero
        else if (palabra.charAt(contador) == 'F') {
            contador++;
            transiciones = transiciones + "q0,F";

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'A') {
                contador++;
                transiciones = transiciones + "->q10,A";
            }

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'L') {
                contador++;
                transiciones = transiciones + "->q11,L";
            }

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'S') {
                contador++;
                transiciones = transiciones + "->q12,S";
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

        }//falso



        return resultado;
    }

    public boolean esFinal(String palabra){
        if(palabra.length()==contador){
            return true;
        }
        return false;
    }//esFinal
}
