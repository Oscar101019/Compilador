package mx.edu.ittepic.compilador.Automatas;

/**
 * Created by carlo on 09/07/2018.
 */

public class Garra {
    public String transiciones="";
    int contador=0;

    public Garra(){

    }

    public boolean esGarra(String palabra){
        contador = 0;
        transiciones = "";
        boolean estado = false;
        if(esFinal(palabra)){return false;}
        if (palabra.charAt(contador) == 'G') {
            contador++;
            transiciones = transiciones + "q0,G";
        }
        if(esFinal(palabra)){return false;}
        if (palabra.charAt(contador) == 'A') {
            contador++;
            transiciones = transiciones + "->q1,A";
        }
        if(esFinal(palabra)){return false;}
        if (palabra.charAt(contador) == 'R') {
            contador++;
            transiciones = transiciones + "->q2,R";
        }
        if(esFinal(palabra)){return false;}
        if (palabra.charAt(contador) == 'R') {
            contador++;
            transiciones = transiciones + "->q3,R";
        }
        if(esFinal(palabra)){return false;}
        if (palabra.charAt(contador) == 'A') {
            contador++;
            transiciones = transiciones + "->q4,A";
        }
        if(esFinal(palabra)){return false;}
        if (palabra.charAt(contador) == '.') {
            contador++;
            transiciones = transiciones + "->q5,.";
        }
        if(esFinal(palabra)){return false;}
        if (palabra.charAt(contador) == 'A') {
            contador++;
            transiciones = transiciones + "->q6,A";
            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'R') {
                contador++;
                transiciones = transiciones + "->q7,R";

                if(esFinal(palabra)){return false;}
                if (palabra.charAt(contador) == 'R') {
                    contador++;
                    transiciones = transiciones + "->q8,R";
                }
                if(esFinal(palabra)){return false;}
                if (palabra.charAt(contador) == 'I') {
                    contador++;
                    transiciones = transiciones + "->q9,I";
                }
                if(esFinal(palabra)){return false;}
                if (palabra.charAt(contador) == 'B') {
                    contador++;
                    transiciones = transiciones + "->q10,B";
                }
                if(esFinal(palabra)){return false;}
                if (palabra.charAt(contador) == 'A') {
                    contador++;
                    transiciones = transiciones + "->q11,A";
                }
                if(esFinal(palabra)){
                    transiciones = transiciones + "->q12";
                    return true;
                }
            }//ARRIBA
            if (palabra.charAt(contador) == 'B') {
                contador++;
                transiciones = transiciones + "->q7,B";

                if(esFinal(palabra)){return false;}
                if (palabra.charAt(contador) == 'A') {
                    contador++;
                    transiciones = transiciones + "->q35,A";

                    if(esFinal(palabra)){return false;}
                    if (palabra.charAt(contador) == 'J') {
                        contador++;
                        transiciones = transiciones + "->q15,J";
                    }
                    if(esFinal(palabra)){return false;}
                    if (palabra.charAt(contador) == 'O') {
                        contador++;
                        transiciones = transiciones + "->q16,O";
                    }
                    if(esFinal(palabra)){
                        transiciones = transiciones + "->q17";
                        return true;
                    }
                }//ABAJO
                if (palabra.charAt(contador) == 'R') {
                    contador++;
                    transiciones = transiciones + "->q35,R";

                    if(esFinal(palabra)){return false;}
                    if (palabra.charAt(contador) == 'I') {
                        contador++;
                        transiciones = transiciones + "->q36,I";
                    }
                    if(esFinal(palabra)){return false;}
                    if (palabra.charAt(contador) == 'R') {
                        contador++;
                        transiciones = transiciones + "->q37,R";
                    }
                    if(esFinal(palabra)){
                        transiciones = transiciones + "->q38";
                        return true;
                    }
                }//ABRIR
            }//B
        }//A
        if (palabra.charAt(contador) == 'I') {
            contador++;
            transiciones = transiciones + "->q6,I";

            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'Z') {
                contador++;
                transiciones = transiciones + "->q18,Z";
            }
            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'Q') {
                contador++;
                transiciones = transiciones + "->q19,Q";
            }
            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'U') {
                contador++;
                transiciones = transiciones + "->q20,U";
            }
            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'I') {
                contador++;
                transiciones = transiciones + "->q21,I";
            }
            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'E') {
                contador++;
                transiciones = transiciones + "->q22,E";
            }
            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'R') {
                contador++;
                transiciones = transiciones + "->q23,R";
            }
            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'D') {
                contador++;
                transiciones = transiciones + "->q24,D";
            }
            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'A') {
                contador++;
                transiciones = transiciones + "->q25,A";
            }
            if(esFinal(palabra)){
                transiciones = transiciones + "->q26";
                return true;
            }
        }//IZQUIERDA
        if (palabra.charAt(contador) == 'D') {
            contador++;
            transiciones = transiciones + "->q6,D";

            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'E') {
                contador++;
                transiciones = transiciones + "->q27,E";
            }
            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'R') {
                contador++;
                transiciones = transiciones + "->q28,R";
            }
            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'E') {
                contador++;
                transiciones = transiciones + "->q29,E";
            }
            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'C') {
                contador++;
                transiciones = transiciones + "->q30,C";
            }
            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'H') {
                contador++;
                transiciones = transiciones + "->q31,H";
            }
            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'A') {
                contador++;
                transiciones = transiciones + "->q32,A";
            }
            if(esFinal(palabra)){
                transiciones = transiciones + "->q33";
                return true;}
        }//DERECHA

        if (palabra.charAt(contador) == 'C') {
            contador++;
            transiciones = transiciones + "->q6,C";
            if(esFinal(palabra)){return false;}
            if (palabra.charAt(contador) == 'E') {
                contador++;
                transiciones = transiciones + "->q39,E";
                if(esFinal(palabra)){return false;}
                if (palabra.charAt(contador) == 'R') {
                    contador++;
                    transiciones = transiciones + "->q40,R";
                    if(esFinal(palabra)){return false;}
                    if (palabra.charAt(contador) == 'R') {
                        contador++;
                        transiciones = transiciones + "->q41,R";
                        if(esFinal(palabra)){return false;}
                        if (palabra.charAt(contador) == 'A') {
                            contador++;
                            transiciones = transiciones + "->q42,A";
                            if(esFinal(palabra)){return false;}
                            if (palabra.charAt(contador) == 'R') {
                                contador++;
                                transiciones = transiciones + "->q43,R";
                                if(esFinal(palabra)){
                                    transiciones = transiciones + "->q44";
                                    return true;}
                            }//r
                        }//a
                    }//r
                }//r
            }//e
        }//CERRAR


        return estado;
    }//esGarra

    public boolean esFinal(String palabra){
        if(palabra.length()==contador){
            return true;
        }
        return false;
    }//esFinal

}//clase
