package mx.edu.ittepic.compilador.Automatas;

/**
 * Created by carlo on 08/07/2018.
 */

public class Carro {
    public String transiciones="";
    int contador=0;

    public Carro(){

    }

    public boolean  esCarro (String palabra) {
        contador = 0;
        transiciones = "";
        boolean estado = false;
        if (esFinal(palabra)) {
            return false;
        }
        if (palabra.charAt(contador) == 'C') {
            contador++;
            transiciones = transiciones + "q0,C";
            if (esFinal(palabra)) {
                return false;
            }
            if (palabra.charAt(contador) == 'A') {
                contador++;
                transiciones = transiciones + "->q1,A";

                if (esFinal(palabra)) {
                    return false;
                }
                if (palabra.charAt(contador) == 'R') {
                    contador++;
                    transiciones = transiciones + "->q2,R";
                    if (esFinal(palabra)) {
                        return false;
                    }
                    if (palabra.charAt(contador) == 'R') {
                        contador++;
                        transiciones = transiciones + "->q3,R";
                        if (esFinal(palabra)) {
                            return false;
                        }
                        if (palabra.charAt(contador) == 'O') {
                            contador++;
                            transiciones = transiciones + "->q4,O";

                            if (esFinal(palabra)) {
                                return false;
                            }
                            if (palabra.charAt(contador) == '.') {
                                contador++;
                                transiciones = transiciones + "->q5,.";



                            }
                        }
                    }
                }
            }
        }

        if (esFinal(palabra)) {return false;}
        if (palabra.charAt(contador) == 'A') {
            contador++;
            transiciones = transiciones + "->q6,A";

            if (esFinal(palabra)) {
                return false;
            }
            if (palabra.charAt(contador) == 'D') {
                contador++;
                transiciones = transiciones + "->q7,D";
                if (esFinal(palabra)) {
                    return false;
                }
                if (palabra.charAt(contador) == 'E') {
                    contador++;
                    transiciones = transiciones + "->q8,E";
                }
                if (esFinal(palabra)) {
                    return false;
                }
                if (palabra.charAt(contador) == 'L') {
                    contador++;
                    transiciones = transiciones + "->q9,L";
                }
                if (esFinal(palabra)) {
                    return false;
                }
                if (palabra.charAt(contador) == 'A') {
                    contador++;
                    transiciones = transiciones + "->q10,A";
                }
                if (esFinal(palabra)) {
                    return false;
                }
                if (palabra.charAt(contador) == 'N') {
                    contador++;
                    transiciones = transiciones + "->q11,N";
                }
                if (esFinal(palabra)) {
                    return false;
                }
                if (palabra.charAt(contador) == 'T') {
                    contador++;
                    transiciones = transiciones + "->q12,T";
                }
                if (esFinal(palabra)) {
                    return false;
                }
                if (palabra.charAt(contador) == 'E') {
                    contador++;
                    transiciones = transiciones + "->q13,E";
                }

                if (esFinal(palabra)) {
                    transiciones = transiciones + "->q14";
                    return true;
                }
                else return false;
            }
            if (palabra.charAt(contador) == 'T') {
                contador++;
                transiciones = transiciones + "->q7,T";

                if (esFinal(palabra)) {
                    return false;
                }
                if (palabra.charAt(contador) == 'R') {
                    contador++;
                    transiciones = transiciones + "->q16,R";
                }
                if (esFinal(palabra)) {
                    return false;
                }
                if (palabra.charAt(contador) == 'A') {
                    contador++;
                    transiciones = transiciones + "->q17,A";
                }
                if (esFinal(palabra)) {
                    return false;
                }
                if (palabra.charAt(contador) == 'S') {
                    contador++;
                    transiciones = transiciones + "->q18,S";
                }
                if (esFinal(palabra)) {
                    transiciones = transiciones + "->q19";
                    return true;
                }
                else {return false;}
            }if (esFinal(palabra)) {
                return false;
            }
            if (palabra.charAt(contador) == 'P') {
                contador++;
                transiciones = transiciones + "->q7,P";

            }if (esFinal(palabra)) {
                return false;
            }
            if (palabra.charAt(contador) == 'A') {
                contador++;
                transiciones = transiciones + "->q37,A";
            }if (esFinal(palabra)) {
                return false;
            }
            if (palabra.charAt(contador) == 'G') {
                contador++;
                transiciones = transiciones + "->q38,G";
            }if (esFinal(palabra)) {
                return false;
            }
            if (palabra.charAt(contador) == 'A') {
                contador++;
                transiciones = transiciones + "->q39,A";
            }if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'R') {
                contador++;
                transiciones = transiciones + "->q40,R";
            }
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q41";
                return true;
            }
            else {return false;}
        }//A


        if(palabra.charAt(contador)=='I'){
            contador++;
            transiciones=transiciones+"->q6,I";

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'Z') {
                contador++;
                transiciones = transiciones + "->q20,Z";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'Q') {
                contador++;
                transiciones = transiciones + "->q21,Q";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'U') {
                contador++;
                transiciones = transiciones + "->q22,U";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'I') {
                contador++;
                transiciones = transiciones + "->q23,I";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'E') {
                contador++;
                transiciones = transiciones + "->q24,E";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'R') {
                contador++;
                transiciones = transiciones + "->q25,R";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'D') {
                contador++;
                transiciones = transiciones + "->q26,D";
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
        }

        if(palabra.charAt(contador)=='D'){
            contador++;
            transiciones=transiciones+"->q6,D";

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'E') {
                contador++;
                transiciones = transiciones + "->q29,E";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'R') {
                contador++;
                transiciones = transiciones + "->q30,R";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'E') {
                contador++;
                transiciones = transiciones + "->q31,E";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'C') {
                contador++;
                transiciones = transiciones + "->q32,C";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'H') {
                contador++;
                transiciones = transiciones + "->q33,H";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'A') {
                contador++;
                transiciones = transiciones + "->q34,A";
            }
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q35";
                return true;
            }
        }
        if(palabra.charAt(contador)=='E'){
            contador++;
            transiciones=transiciones+"->q6,E";

            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'N') {
                contador++;
                transiciones = transiciones + "->q42,N";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'C') {
                contador++;
                transiciones = transiciones + "->q43,C";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'E') {
                contador++;
                transiciones = transiciones + "->q44,E";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'N') {
                contador++;
                transiciones = transiciones + "->q45,N";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'D') {
                contador++;
                transiciones = transiciones + "->q46,D";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'E') {
                contador++;
                transiciones = transiciones + "->q47,E";
            }
            if (esFinal(palabra)) {return false;}
            if (palabra.charAt(contador) == 'R') {
                contador++;
                transiciones = transiciones + "->q48,R";
            }
            if (esFinal(palabra)) {
                transiciones = transiciones + "->q49";
                return true;
            }


        }



        return estado;
    }

    public boolean esFinal(String palabra){
        if(palabra.length()==contador){
            return true;
        }
        return false;
    }//esFinal
}
