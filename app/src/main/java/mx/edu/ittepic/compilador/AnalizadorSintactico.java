package mx.edu.ittepic.compilador;

import java.util.ArrayList;
import java.util.Hashtable;

public class AnalizadorSintactico {
    //Apuntador de la tabla de simboloes
    //El array de array de los lineas de codigo sin modificar
    public String[] codSM;
    //Codigo transformado a tokens 
    public String[] codM;
    //Array de gramaticas para la sintaxis
    Gramatica gr;
    //Array de grafos para la sintaxis
    Grafo g;
    //Arreglo con erroresy secuencia de gramatica para donde se necesite
    String[] re;

    //Apuntador de la tabla de simboloes
    public Hashtable<String, Simbolo> tablaSimbolos;


    //Variables con los avances.
    public String avAutomata;
    public String avGramatica;

    public AnalizadorSintactico(ArrayList<LineaCod> ln) {
        codSM = new String[ln.size()];
        codM = new String[ln.size()];
        re = new String[2];
        re[0] = re[1] = avAutomata = avGramatica = "";
        cambiarCod(ln);
    }

    public AnalizadorSintactico(ArrayList<LineaCod> ln, Hashtable<String, Simbolo> tablaSimbolos) {
        codSM = new String[ln.size()];
        codM = new String[ln.size()];
        this.tablaSimbolos = tablaSimbolos;
        re = new String[2];
        re[0] = re[1] = avAutomata = avGramatica = "";
        cambiarCod(ln);
    }

    //Metodo para cmbiar los lexemas del codigo por tokens
    private void cambiarCod(ArrayList<LineaCod> ln) {
        String cadSM = "";
        String cadM = "";
        String temp;
        for (int x = 0; x < ln.size(); x++) {
            cadSM = "";
            cadM = "";
            if (ln.get(x).tokens.isEmpty()) {
                cadM += "i";
            }
            for (int y = 0; y < ln.get(x).tokens.size(); y++) {
                temp = ln.get(x).tokens.get(y);
                switch (temp.substring(1, temp.indexOf(","))) {
                    case "Tipo de Dato": {
                        cadM += "TD ";
                        break;
                    }
                    case "Variable del Sistema": {
                        cadM += "VS ";
                        break;
                    }
                    case "Operador Lógico": {
                        cadM += "OL ";
                        break;
                    }
                    case "Operador Relacional": {
                        cadM += "OR ";
                        break;
                    }
                    case "Operador de Asignación": {
                        cadM += "OA ";
                        break;
                    }
                    case "Identificador": {
                        cadM += "ID ";
                        break;
                    }
                    case "Cadena": {
                        cadM += "LT ";
                        break;
                    }
                    case "Número": {
                        cadM += "NU ";
                        break;
                    }
                        /*case "Operador Aritmetico":{
                            cadM += "OAR ";
                            break;
                        }*/
                    case "Valor booleano": {
                        cadM += "BOO ";
                        break;
                    }
                    default: {
                        cadM += temp.substring(temp.indexOf(",") + 2, temp.indexOf(">")).toUpperCase() + " ";
                    }
                }
                temp = ln.get(x).tokens.get(y);
                temp = temp.substring(temp.indexOf(",") + 2, temp.indexOf(">"));
                cadSM += temp + " ";
            }
            codM[x] = cadM;
            codSM[x] = cadSM;
        }
    }

    private Gramatica crearGramatica(String[] p, String pI, String[] n, String[] t) {
        Gramatica gramatica = new Gramatica();
        //Aignar la producciones
        gramatica.setP(p);
        //Asignar no terminal incial
        gramatica.setS(pI);
        //Asignar no terminales
        gramatica.setN(n);
        //Asignar terminales
        gramatica.setT(t);
        return gramatica;
    }

    public String[] compilar() {
        String sent = "";
        for (int nl = 0; nl < codM.length; nl++) {
            sent = codM[nl];
            while (!sent.equals("i") && !sent.equals("")) {
                switch (sent.substring(0, sent.indexOf(" "))) {
                    case "FUNCION": {
                        aSFUNCIONes();
                        sent = valAutomata(sent, nl);
                        if (!buscarLlave("FUNCION", nl)) {
                            String nomFunc = "";
                            String[] auxArr = codM[nl].split(" ");
                            for (int i = 0; i < auxArr.length; i++) {
                                if (auxArr[i].equals("ID")) {
                                    nomFunc = codSM[nl].split(" ")[i];
                                    break;
                                }
                            }
                            re[0] += "Error sintactico, en la linea " + (nl + 1) + ". Metodo " + nomFunc + " sin \"}\". Solucion: agregar \"}\".\n";
                        }
                        break;
                    }

                   /*
                    case "ELSE":{
                        if(!sent.equals("ELSE { ")){
                            re[0] += "Error sintactico, en la linea "+(nl+1)+". ELSE sin \"{\". Solucion: colocar \"{\" al final de la linea.\n";
                        }
                        if(!buscarIf(nl)){
                            re[0] += "Error sintactico, en la linea "+(nl+1)+". ELSE sin \"SI\". Solucion: eliminar \"ELSE\" o especificar \"SI\".\n";
                        }
                        if(!buscarLlave("ELSE", nl)){
                            re[0] += "Error sintactico, en la linea "+(nl+1)+". ELSE sin \"}\". Solucion: agregar \"}\".\n";
                        }
                        sent="";
                        break;
                    }*/
                    case "CICLO": {
                        aSCICLO();
                        sent = valAutomata(sent, nl);
                        if (!buscarLlave("CICLO", nl)) {
                            String nomFunc = "";
                            String[] auxArr = codM[nl].split(" ");
                            for (int i = 0; i < auxArr.length; i++) {
                                if (auxArr[i].equals("ID")) {
                                    nomFunc = codSM[nl].split(" ")[i];
                                    break;
                                }
                            }
                            re[0] += "Error sintactico, en la linea " + (nl + 1) + ". CICLO " + nomFunc + " sin \"}\". Solucion: agregar \"}\".\n";
                        }
                        break;
                    }


                    case "SI": {
                        aSGramatica();
                        valIf(nl, sent);
                        sent = "";
                        break;
                    }

                    case "TD": {
                        aSGramatica();
                        valIn(nl, sent);
                        sent = "";
                        break;
                    }
                    case "ID": {
                        aSGramatica();
                        valAs(nl, sent);
                        sent = "";
                        break;
                    }

                    case "INICIO": {
                        aSPow();
                        sent = valAutomata(sent, nl);
                        if (!buscarLlave("INICIO", nl)) {
                            String nomFunc = "";
                            String[] auxArr = codM[nl].split(" ");
                            for (int i = 0; i < auxArr.length; i++) {
                                if (auxArr[i].equals("ID")) {
                                    nomFunc = codSM[nl].split(" ")[i];
                                    break;
                                }
                            }
                            re[0] += "Error sintactico, en la linea " + (nl + 1) + ". INICIO " + nomFunc + " sin \"}\". Solucion: agregar \"}\".\n";
                        }
                        break;
                    }
                    case "RUTA": {
                        aSRuta();
                        sent = valAutomata(sent, nl);
                        if (!buscarLlave("RUTA", nl)) {
                            String nomFunc = "";
                            String[] auxArr = codM[nl].split(" ");
                            for (int i = 0; i < auxArr.length; i++) {
                                if (auxArr[i].equals("ID")) {
                                    nomFunc = codSM[nl].split(" ")[i];
                                    break;
                                }
                            }
                            re[0] += "Error sintactico, en la linea " + (nl + 1) + ". RUTA " + nomFunc + " sin \"}\". Solucion: agregar \"}\".\n";
                        }
                        break;
                    }
                    case "GARRA.ABRIR": {
                        aSGarra();
                        sent = valAutomata(sent, nl);
                        break;
                    }
                    case "GARRA.CERRAR": {
                        aSGarraCerrar();
                        sent = valAutomata(sent, nl);
                        break;
                    }
                    case "GARRA.IZQUIERDA": {
                        aSGarraIzquierda();
                        sent = valAutomata(sent, nl);
                        break;
                    }
                    case "GARRA.DERECHA": {
                        aSGarraDerecha();
                        sent = valAutomata(sent, nl);
                        break;
                    }
                    case "GARRA.ABAJO": {
                        aSGarraAbajo();
                        sent = valAutomata(sent, nl);
                        break;
                    }
                    case "GARRA.ARRIBA": {
                        aSGarraArriba();
                        sent = valAutomata(sent, nl);
                        break;
                    }
                    case "CARRO.ENCENDER": {
                        aSCarroEncender();
                        sent = valAutomata(sent, nl);
                        break;
                    }
                    case "CARRO.APAGAR": {
                        aSCarroApagar();
                        sent = valAutomata(sent, nl);
                        break;
                    }
                    case "CARRO.IZQUIERDA": {
                        aSCarroIzquierda();
                        sent = valAutomata(sent, nl);
                        break;
                    }
                    case "CARRO.DERECHA": {
                        aSCarroDerecha();
                        sent = valAutomata(sent, nl);
                        break;
                    }
                    case "CARRO.ATRAS": {
                        aSCarroAtras();
                        sent = valAutomata(sent, nl);
                        break;
                    }
                    case "CARRO.ADELANTE": {
                        aSCarroAdelante();
                        sent = valAutomata(sent, nl);
                        break;
                    }
                    default: {
                        re[0] += "Error sintactico, en la linea " + (nl + 1) + ". Sentencia sin especficar se obtuvo un " + auxErrMsj(sent.substring(0, sent.indexOf(" ")))
                                + " (" + lex(nl, 0) + "). Solucion: Eliminar el codigo \"" + codSM[nl] + "\" de la linea " + (nl + 1) + ".\n";
                        ;
                        sent = "";
                    }
                }
            }
        }
        return re;
    }


    public boolean buscarIf(int nl) {
        for (int i = nl; i >= 0; i--) {
            if (codM[i].contains("SI ")) {
                codM[i] = codM[i].replace("SI ", "");
                codSM[i] = codM[i].replace("SI ", "");
                return true;
            }
        }
        return false;
    }

    public boolean buscarLlave(String tipSent, int nl) {
        int contador = 1;
        codM[nl] = codM[nl].replaceFirst("\\{ ", "");
        codSM[nl] = codSM[nl].replaceFirst("\\{ ", "");
        for (int i = nl; i < codM.length; i++) {
            for (int j = 0; j < codM[i].length(); j++) {
                if (codM[i].charAt(j) == '{') {
                    contador++;
                }
                if (codM[i].charAt(j) == '}') {
                    contador--;
                }
                if (contador == 0) {
                    codM[i] = codM[i].replaceFirst("\\} ", "");
                    codSM[i] = codSM[i].replaceFirst("\\} ", "");
                    return true;
                }
            }
        }
        return false;
    }

    public String lex(int nl, int index) {
        String temp[] = codSM[nl].split(" ");
        return temp[index];
    }

    public void elmSentcodSM(int nl, int noCompLex) {
        String temp = "";
        String arrAux[] = codSM[nl].split(" ");
        for (int i = 0; i < noCompLex; i++) {
            temp += arrAux[i] + " ";
        }
        codSM[nl] = codSM[nl].substring(temp.length());
    }

    //Acepta punto y coma
    /*public boolean acepPyC(String cad){
        switch(cad){
            case"CALL":{return true;}
            case"PLAYER":{return true;}
            case"INC":{return true;}
            case"CONFIG":{return true;}
            case"COMBO":{return true;}
        }
        return false;
    }
    */
    //--------------------- Automatas ---------------------


    String nombre = "";
    public void aSFUNCIONes(){

        nombre="FUNCIONES";
        g = new Grafo();
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, false);
        g.insertar("q6", false, false);
        g.insertar("q7", false, false);
        g.insertar("q8", false, false);
        g.insertar("q9", false, false);
        g.insertar("q10", false, false);
        g.insertar("q11", false, true);
        g.insertar("q12", false, false);
        g.insertar("q13", false, true);

        g.crearArista("q0", "q1", "FUNCION");
        g.crearArista("q1", "q2", "TD");
        g.crearArista("q1", "q2", "");
        g.crearArista("q2", "q3", "ID");
        g.crearArista("q3", "q4", "(");
        g.crearArista("q4", "q10", ")");
        g.crearArista("q10", "q11", "{");
        g.crearArista("q4", "q5", "TD");
        g.crearArista("q5", "q6", "ID");
        g.crearArista("q6", "q12", ")");
        g.crearArista("q12", "q13", "{");
        g.crearArista("q6", "q7", ",");
        g.crearArista("q7", "q8", "TD");
        g.crearArista("q8", "q9", "ID");
        g.crearArista("q9", "q6", "");
    }









    public void aSCICLO(){

        nombre="CICLO";

        g = new Grafo();
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, true);

        g.crearArista("q0", "q1", "CICLO");
        g.crearArista("q1", "q2", "(");
        g.crearArista("q2", "q3", "NU");
        g.crearArista("q3", "q4", ")");
        g.crearArista("q4", "q5", "{");


    }

    public void aSGarra(){
        nombre="GARRA.ABRIR";


        g = new Grafo();
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, true);

        g.crearArista("q0", "q1", "GARRA.ABRIR");
        g.crearArista("q1", "q2", "(");
        g.crearArista("q2", "q3", "NU");
        g.crearArista("q3", "q4", ")");
        g.crearArista("q4", "q5", ";");



    }

    public void aSGarraCerrar(){
        nombre="GARRA.CERRAR";
        g = new Grafo();
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, true);

        g.crearArista("q0", "q1", "GARRA.CERRAR");
        g.crearArista("q1", "q2", "(");
        g.crearArista("q2", "q3", "NU");
        g.crearArista("q3", "q4", ")");
        g.crearArista("q4", "q5", ";");
    }


    public void aSGarraDerecha(){
        nombre="GARRA.DERECHA";
        g = new Grafo();
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, true);

        g.crearArista("q0", "q1", "GARRA.DERECHA");
        g.crearArista("q1", "q2", "(");
        g.crearArista("q2", "q3", "NU");
        g.crearArista("q3", "q4", ")");
        g.crearArista("q4", "q5", ";");
    }

    public void aSGarraIzquierda(){
        nombre="GARRA.IZQUIERDA";

        g = new Grafo();
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, true);

        g.crearArista("q0", "q1", "GARRA.IZQUIERDA");
        g.crearArista("q1", "q2", "(");
        g.crearArista("q2", "q3", "NU");
        g.crearArista("q3", "q4", ")");
        g.crearArista("q4", "q5", ";");
    }

    public void aSGarraArriba(){
        g = new Grafo();
        nombre="GARRA.ARRIBA";
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, true);

        g.crearArista("q0", "q1", "GARRA.ARRIBA");
        g.crearArista("q1", "q2", "(");
        g.crearArista("q2", "q3", "NU");
        g.crearArista("q3", "q4", ")");
        g.crearArista("q4", "q5", ";");
    }

    public void aSGarraAbajo(){
        nombre="GARRA.ABAJO";
        g = new Grafo();
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, true);

        g.crearArista("q0", "q1", "GARRA.ABAJO");
        g.crearArista("q1", "q2", "(");
        g.crearArista("q2", "q3", "NU");
        g.crearArista("q3", "q4", ")");
        g.crearArista("q4", "q5", ";");
    }



    public void aSCarroEncender(){
        nombre="CARRO.ENCENDER";
        g = new Grafo();
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, true);


        g.crearArista("q0", "q1", "CARRO.ENCENDER");
        g.crearArista("q1", "q2", "(");
        g.crearArista("q2", "q3", ")");
        g.crearArista("q3", "q4", ";");

    }


    public void aSCarroApagar(){
        nombre="CARRO.APAGAR";
        g = new Grafo();
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, true);


        g.crearArista("q0", "q1", "CARRO.APAGAR");
        g.crearArista("q1", "q2", "(");
        g.crearArista("q2", "q3", ")");
        g.crearArista("q3", "q4", ";");

    }


    public void aSCarroIzquierda(){
        g = new Grafo();
        nombre="CARRO.IZQUIERDA";
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, true);

        g.crearArista("q0", "q1", "CARRO.IZQUIERDA");
        g.crearArista("q1", "q2", "(");
        g.crearArista("q2", "q3", "NU");
        g.crearArista("q3", "q4", ")");
        g.crearArista("q4", "q5", ";");
    }

    public void aSCarroDerecha(){
        nombre="CARRO.DERECHA";
        g = new Grafo();
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, true);

        g.crearArista("q0", "q1", "CARRO.DERECHA");
        g.crearArista("q1", "q2", "(");
        g.crearArista("q2", "q3", "NU");
        g.crearArista("q3", "q4", ")");
        g.crearArista("q4", "q5", ";");
    }

    public void aSCarroAtras(){
        nombre="CARRO.ATRAS";
        g = new Grafo();
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, true);

        g.crearArista("q0", "q1", "CARRO.ATRAS");
        g.crearArista("q1", "q2", "(");
        g.crearArista("q2", "q3", "NU");
        g.crearArista("q3", "q4", ")");
        g.crearArista("q4", "q5", ";");
    }


    public void aSCarroAdelante(){
        nombre="CARRO.ADELANTE";
        g = new Grafo();
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, true);

        g.crearArista("q0", "q1", "CARRO.ADELANTE");
        g.crearArista("q1", "q2", "(");
        g.crearArista("q2", "q3", "NU");
        g.crearArista("q3", "q4", ")");
        g.crearArista("q4", "q5", ";");
    }





    public void aSIn(){
        g = new Grafo();

        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, true);

        g.crearArista("q0", "q1", "TD");
        g.crearArista("q1", "q2", "ID");
    }

    public void aSPow(){
        nombre="INICIO";
        g = new Grafo();
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, true);

        g.crearArista("q0", "q1", "INICIO");
        g.crearArista("q1", "q2", "ID");
        g.crearArista("q2", "q3", "{");
    }

    public void aSRuta(){
        nombre="RUTA";
        g = new Grafo();
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, true);

        g.crearArista("q0", "q1", "RUTA");
        g.crearArista("q1", "q2", "{");
    }


    //El numero de linea se tiene que calcular con noLinea+1, ya que noLinea es del for que empieza en 0 y en 1
    public String valAutomata(String sent, int nl){
        avAutomata += "\nAutomata de la linea "+(nl+1)+" , "+nombre+" . ";
        //comLx es una variable que tiene los componente lexico de la sentencia
        String[]compLx = sent.split(" ");
        g.t = g.ini;
        //Variable auxiliar que almacena toda la sentencia que se va a eliminar
        String auxSent = "";
        String text = "";
        for(int i = 0; i < compLx.length; i++){
            //Si es estado final significa que ya se termina de leer la sentencia, por si hay mas de una sentencia por linea
            if(g.t.esEstFinal()){
                elmSentcodSM(nl, i);
                auxSem(compLx[0], codSM[nl], sent, nl, "");
             /*   if(compLx[0].equals("RUTA")){
                    buscarLlave("RUTA", nl);
                }else if(compLx[0].equals("INICIO")){
                    buscarLlave("INICIO", nl);
                }

                */
                return sent.substring(auxSent.length());
            }
            //Verifica si se puede avanzar con el lexema actual de la sentencia, si es asi entonces avanza y sigue en la siguiente iteracion
            if(g.mover(compLx[i])){
                //Aguarda estados...
                String f,nf;

                avAutomata += "\n"+"->"+g.t.valor ;

                if(g.t.efin==true){nf= "Estado Final";
                    avAutomata += "\n"+"El automata "+nombre+" Se encuentra en el estado -> "+g.t.valor + "( "+ nf+") " + "\n";

                    continue;
                }


                auxSent += compLx[i]+" ";
                continue;




            }else if (g.mover("")){
                continue;
            }
            //Si no se puede mover, significa que no puede que el lexema actual no es valido para la sintaxis de la sentencia
            for(String j:g.sigArista()){
                text += ", un "+auxErrMsj(j);
            }
            text = text.substring(2);
            re[0]+= "Error sintactico, en la linea "+(nl+1)+". Sentencia incorrecta para "+auxErrMsj(compLx[0])+", se obtuvo un "+auxErrMsj(compLx[i])
                    +"(\""+lex(nl, i)+"\"). Solucion: colocar despues del \""+auxErrMsj(compLx[i-1])+"\" \""+text+"\".\n";
            String f;
            if(g.t.efin==false){f="Estado NO Final";
                avAutomata += "\n"+"El automata "+nombre+" Se encuentra en el estado -> "+g.t.valor + "( "+ f+") " + "\n";


            }

            return "";
        }
        //Se pregunta si es estado final ya que solo hubo una sentencia por linea
        if(g.t.esEstFinal()){
            auxSem(compLx[0], codSM[nl], sent, nl, "");
           /* if(compLx[0].equals("RUTA")){
                buscarLlave("RUTA", nl);
            }else
            if(compLx[0].equals("INICIO")){
                buscarLlave("INICIO", nl);
            }

            */
            return "";
        }
        //Si no se puede mover, significa que no puede que el lexema actual no es valido para la sintaxis de la sentencia
        for(String j:g.sigArista()){
            if(j.equals(";")){
                continue;
            }
            text += ", un "+auxErrMsj(j);
        }
        //Si solo es un punto y coma entonces text lo maneja asi
        if(!text.equals("")){
            text = text.substring(2);
            re[0]+= "Error sintactico, en la linea "+(nl+1)+". Sentencia incompleta para "+auxErrMsj(compLx[0])+". "
                    + "Solucion: colocar despues del \""+auxErrMsj(compLx[compLx.length-1])+"\" \""+text+"\"\n";

            /*
            String f;
            if(g.t.efin==false){f="Estado NO Final";
                avAutomata += "\n"+"Se encuentra en ->"+g.t.valor + "( "+ f+") " + "\n";


            }*/

        }
        if(!sent.contains(";") /*&& acepPyC(compLx[0])*/){
            re[0]+= "Error sintactico, en la linea "+(nl+1)+". Te falto colocar un \';\':0. Solucion: Colocar el \';\' al final de la línea (:.\n";
            /*String f;
            if(g.t.efin==false){f="Estado NO Final";
                avAutomata += "\n"+"Se encuentra en ->"+g.t.valor + "( "+ f+") " + "\n";


            }*/
        }
        if(!text.equals("") || !sent.contains(";" )){  String f;
            if(g.t.efin==false){f="Estado NO Final";
                avAutomata += "\n"+"El automata "+nombre+" Se encuentra en el estado -> "+g.t.valor + "( "+ f+") " + "\n";


            }}
        return "";
    }
    //-----------------------------------------------------

    //Metodo auxiliar para los errores sintaticos, le mando como parametro el componente lexico y me retorna una cadena legible para personas
    public String auxErrMsj(String compLx){
        switch(compLx){
            case "FUNCION":{return "función";}

            case "CICLO":{return "CICLO()";}

            case "INICIO":{return "metodo para INICIAR";}
            case "NU":{return "número";}
            case "ID":{return "identificador";}
            case "TD":{return "tipo de dato";}
            case "(":{return "parentesis";}
            case ")":{return "parentesis";}
            case "OR":{return "operador relacional";}
            case "OL":{return "operador logico";}
            case "+":{return "suma";}
            case "-":{return "resta";}
            case "*":{return "multiplocacion";}
            case "/":{return "division";}
        }
        return compLx;
    }

    //--------------------- Gramaticas ---------------------
    public void aSGramatica(){
        //Creamos el objeto gramatica.
        gr = new Gramatica();
        //Aignar la producciones
        String[]p = {"e=e+t","e=e-t","e=t","t=t*f","t=t/f","t=f","f=fRg","f=g","g=gLh","g=h","h=B","h=I","h=N","h=T","h=(e)"};
        gr.setP(p);
        //Asignar terminal incial
        gr.setS("e");
        //Asignar no terminales
        String[]n = {"e","t","f","g","h"};
        gr.setN(n);
        //Asignar terminales I = ID, N = NUMERAO
        String[]t = {"I","N","L","R","T","B"};
        gr.setT(t);
    }

    public String compLxTerm(String cod){
        String[]arr = cod.split(" ");
        String sent = "";
        for(String i:arr){
            switch(i){
                case "ID":{sent += "I";break;}
                case "NU":{sent += "N";break;}
                case "BOO":{sent += "B";break;}
                case "OL":{sent += "L";break;}
                case "OR":{sent += "R";break;}
                case "LT":{sent += "T";break;}
                default:{sent += i;break;}
            }
        }
        return sent;
    }

    public void valGramatica(String tipSent, String cod, String codSM, int nl){
        //Variable que contiene la secuencia de la derivacion de la gramatica.
        String text = compLxTerm(cod);
        String recorrido = gr.validar(text);

        avGramatica += recorrido != null?"\n"+recorrido:"";

        if(recorrido != null){
            auxSem(tipSent, this.codSM[nl], this.codM[nl], nl, recorrido);
            return;
        }
        int index = gr.indxError(text);
        String[]auxSM = codSM.split(" ");
        String[]aux = cod.split(" ");
        String sigLx = "";
        if(index >= aux.length){
            index = aux.length-1;
            sigLx = aux[index];
        }else{
            if(aux.length <= 1){
                String st="";
                switch(tipSent){
                    case "ID":{st+="asignacion de variable incompleta. Solucion: La sintaxis correcta es ID Operador_Asignacion Valor.\n";break;}
                    case "SI":{st+="estructura selectiva SI incompleta. Solucion: La sintaxis correcta es SI ( CONDICION ) {...}.\n";break;}

                }
                re[0] +="Error semantico, en la linea "+(nl+1)+". Sentencia "+st+"\n";
                return;
            }
            sigLx = aux[index+1];
        }
        re[0] +="Error sintactico, en la linea "+(nl+1)+". Sentencia "+auxErrMsj(tipSent)+" incorrecta despues del "+auxErrMsj(aux[index])+ " ("+auxSM[index]+")." + " Solucion:";
        switch(sigLx){
            case"(":{re[0]+=" Cerrar el parentesis \'(\'.\n";break;}
            case")":{re[0]+=" Eliminar el parentesis \')\'.\n";break;}
            case"OR":{re[0]+=" Completar la sintaxis del operador relacional ("+auxSM[index]+")\n";break;}
            case"OL":{re[0]+=" Completar la sintaxis del operador logico ("+auxSM[index]+")\n";break;}
            case"+":{re[0]+=" Completar la sintaxis de la operacion aritmetica de la suma.\n";break;}
            case"-":{re[0]+=" Completar la sintaxis de la operacion aritmetica de la resta.\n";break;}
            case"/":{re[0]+=" Completar la sintaxis de la operacion aritmetica de la division.\n";break;}
            case"*":{re[0]+=" Completar la sintaxis de la operacion aritmetica de la multiplicacion.\n";break;}
            case"ID":{re[0]+=" Completar la operacion con un operador relacional o un operador logico.\n";break;}
            case"NU":{re[0]+=" Completar la operacion con un operador relacional.\n";break;}
            case"LT":{re[0]+=" Completar la operacion con un operador relacional.\n";break;}
            case"BOO":{re[0]+=" Completar la operacion con un operador logico.\n";break;}
            default:{re[0]+=" Eliminar "+auxErrMsj(sigLx)+"("+auxSM[index]+").";break;}
        }
    }

    //------------------------------------------------------
    public void valIf(int nl, String sent){
        if(sent.indexOf("(") == -1){
            re[0]+= "Error sintactico, en la linea "+(nl+1)+". Sentencia incompleta para estructura selectiva SI, la sentencia completa es \"SI(condicion)\"."
                    + " Solucion: colocar el parentesis \'(\' al principio de la condicion.\n";
            return;
        }
        if(sent.lastIndexOf(")") == -1){
            re[0]+= "Error sintactico, en la linea "+(nl+1)+". Sentencia incompleta para estructura selectiva SI, la sentencia completa es \"SI(condicion)\"."
                    + " Solucion: colocar el parentesis \')\' al final de la condicion.\n";
            return;
        }
        String cond = sent.substring(sent.indexOf("(")+1, sent.lastIndexOf(")"));
        String condSM = codSM[nl].substring(codSM[nl].indexOf("(")+1, codSM[nl].lastIndexOf(")"));
        valGramatica(sent.substring(0, sent.indexOf(" ")), cond, condSM, nl);
        if(!sent.contains("{")){
            re[0]+= "Error sintactico, en la linea "+(nl+1)+". Te falto colocar un \'{\':0. Solucion: Colocar el \'{\' al final de la línea (:.\n";
        }
        if(!buscarLlave("SI", nl)){
            re[0]+= "Error sintactico, en la linea "+(nl+1)+". Estructura SI sin \'}\' de cierre.\n";
        }
    }

    public void valIn(int nl, String sent){
        aSIn();
        String[]compLx = sent.split(" ");
        g.t = g.ini;
        //Variable auxiliar que almacena toda la sentencia que se va a eliminar
        String text = "";
        for(int i = 0; i < compLx.length; i++){
            //Si es estado final significa que ya se termina de leer la sentencia, por si hay mas de una sentencia por linea
            if(g.t.esEstFinal()){
                break;
            }
            //Verifica si se puede avanzar con el lexema actual de la sentencia, si es asi entonces avanza y sigue en la siguiente iteracion
            if(g.mover(compLx[i])){
                continue;
            }else if (g.mover("")){
                continue;
            }
            //Si no se puede mover, significa que no puede que el lexema actual no es valido para la sintaxis de la sentencia
            for(String j:g.sigArista()){
                text += ", un "+auxErrMsj(j);
            }
            text = text.substring(2);
            re[0]+= "Error sintactico, en la linea "+(nl+1)+". Sentencia incorrecta para "+auxErrMsj(compLx[0])+", se obtuvo un "+auxErrMsj(compLx[i])
                    +"(\""+lex(nl, i)+"\"). Solucion: colocar despues del \""+auxErrMsj(compLx[i-1])+"\" \""+text+"\".\n";
            return;
        }
        if(!sent.contains(";")){
            re[0]+= "Error sintactico, en la linea "+(nl+1)+". Te falto colocar un \';\':0. Solucion: Colocar el \';\' al final de la línea (:.\n";
            return;
        }
        if(sent.equals("TD ID ; ")){
            return;
        }

        if(!sent.contains("OA")){
            re[0]+= "Error sintactico, en la linea "+(nl+1)+". Sentencia de inicializacion aun necesita de un operador de asigmacion. Solucion colocar un operador de asignacion.\n";
            return;
        }

        String cond = sent.substring(sent.indexOf("OA")+3, sent.lastIndexOf(";"));
        String condSM = codSM[nl].substring(codSM[nl].indexOf("=")+1, codSM[nl].lastIndexOf(";"));
        valGramatica(sent.substring(0, sent.indexOf(" ")), cond, condSM, nl);
    }

    public void valAs(int nl, String sent){
        if(sent.indexOf("OA") == -1){
            re[0]+= "Error sintactico, en la linea "+(nl+1)+". Sentencia incompleta para asignacion del identificador "+codSM[nl].substring(0, codSM[nl].indexOf(" "))+"."
                    + " Solucion: colocar la asignacion \'=\' despues del identificador.\n";
            return;
        }
        if(!sent.contains(";")){
            re[0]+= "Error sintactico, en la linea "+(nl+1)+". Te falto colocar un \';\'. Solucion: Colocar el \';\' al final de la línea (:.\n";
            return;
        }
        String cond = sent.substring(sent.indexOf("OA")+3, sent.lastIndexOf(";"));
        String condSM = codSM[nl].substring(codSM[nl].indexOf("=")+1, codSM[nl].lastIndexOf(";"));
        valGramatica(sent.substring(0, sent.indexOf(" ")), cond, condSM, nl);
    }



    public void auxSem(String tipSent, String codSM, String codM, int nl, String sec){
        switch(tipSent){
            case "SI":{re[1]+=nl+","+tipSent+","+codM+","+codSM+","+sec+"\n";break;}
            case "TD":{re[1]+=nl+","+tipSent+","+codM+","+codSM+","+sec+"\n";break;}
            case "ID":{re[1]+=nl+","+tipSent+","+codM+","+codSM+","+sec+"\n";break;}


        }
    }
}