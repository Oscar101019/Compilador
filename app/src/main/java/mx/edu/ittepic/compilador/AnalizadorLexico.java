package mx.edu.ittepic.compilador;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Oscar
 */
public class AnalizadorLexico {
    String t = "";
    public ArrayList<LineaCod> secCod;
    LineaCod auxLC;
    public static Hashtable<String, Simbolo> tablaSimbolos;

    String Alfabeto2 = ("[\\w]*[\\x09]*[\\x20]*[\\x3C]*[\\x3E]*[\\x3D]*[\\x2B]*[\\x2D]*[\\x2F]*[\\x2A]*[\\x3A]*[\\x2C]*[\\x28]*[\\x29]*[\\x5B]*[\\x5D]*[\\x7B]*[\\x7D]*[ñ]*[Ñ]*[á]*[Á]*[é]*[É]*[í]*[Í]*[ó]*[Ó]*[ú]*[Ú]*[\\x7C]*[\\x26]*[\\x25]*[\\x5F]*[\\x5E]*[\\x3F]*[\\xF9]*[\\x2E]*[\\x40]*");
    String letras = "[Q|A|Z|W|S|X|E|D|C|R|F|V|T|G|B|Y|H|N|Ñ|U|J|M|I|K|O|L|P|q|a|z|w|s|x|e|d|c|r|f|v|t|g|b|y|h|n|u|j|m|ñ|i|k|o|l|p]";
    String letrasMin = "[a|z|w|s|x|e|d|c|r|f|v|t|g|b|y|h|n|u|j|m|ñ|i|k|o|l|p]";
    String dig = "[0|1|2|3|4|5|6|7|8|9]";
    String carEsp = "[[\\x7E]|[\\x40]|[\\x23]|[\\x24]|[\\x25]|[\\x5E]|[\\x26]|[\\xB3]|[\\x3F]|[\\x5C][x  \\b0]|[\\xA1]|[\\xA8]|[\\xB4]|[\\x40]]";

    String reglas = ("(CARRO.ADELANTE\\b|CARRO.ATRAS\\b|CARRO.IZQUIERDA\\b|CARRO.DERECHA\\b|GARRA.ARRIBA\\b|GARRA.ABAJO\\b|GARRA.ABRIR\\b|GARRA.CERRAR\\b|GARRA.IZQUIERDA\\b|GARRA.DERECHA\\b|CARRO.ENCENDER\\b|CARRO.APAGAR\\b|CICLO\\b|SI\\b|SINO\\b|INICIO\\b|RUTA\\b|FUNCION\\b)|"//PALABRAS RESERVADAS
            + "(BOOLEANO\\b|ENTERO\\b|FLOTANTE\\b|CADENA\\b)|"//TIPO_DATO
            + "([\\x40])|"
            + "(FALSO\\b|VERDADERO\\b)|"
            + "([|]|[&])|"
            + "(<=|>=|<|>|!=)|"
            + "([==]|=)|"
            + "("+letrasMin+"+"+letras+"*["+dig+"|"+letras+"|\\x5F]*\\b)|" //identificador
            + "([#])|"
            + "([\\x22][\\W|\\w]*[\\x22]|[\\x27][\\W|\\w]*[\\x27])|" //Literales de texto St
            + "([{]|[}])|" //signos especiales
            + "(\\x28|\\x29|\\x5B|\\x5D)|" //signos de agrupacion
            + "([+|-]{0,1}"+dig+"*[.]{0,1}"+dig+"+\\b)|"//numero
            + "([-|/|+|*])|"//operador aritmetico
            + "([,|.|;])|"
            + "([\\x22][" + Alfabeto2 + "]*)|"//Error sin cerrar comillas doble
            + "([\\x27][" + Alfabeto2 + "]*)|"//Error sin cerrar comillas simple
            + "(["+letras+"|"+dig+"]+\\b)");//Error de token);//Error de token


    final int GRUPOS = 18;

    String[] tokens = {"<Desconocido, %s>\n", "<Palabra Reservada, %s>\n", "<Tipo de Dato, %s>\n",
            "<Declarador de variables, %s>\n", "<Valor booleano, %s>\n", "<Operador Lógico, %s>\n",
            "<Operador Relacional, %s>\n", "<Operador de Asignación, %s>\n",
            "<Identificador, %s>\n", "<Comentario Simple, %s>\n", "<Cadena, %s>\n",
            "<Signo Especial, %s>\n", "<Signo de Agrupación, %s>\n", "<Número, %s>\n",
            "<Operador Aritmetico, %s>\n", "<Signo de Puntuación, %s>\n"};

    public String[] re;

    //Metodo para compilar y mostrar error
    public String[] compilar(String codigo) {
        secCod = new ArrayList();
        t = "";
        tablaSimbolos = new Hashtable<>();
        re = new String[2];
        re[0] = re[1] = "";
        codigo = codigo.replace("\t", "");
        String[] texto = codigo.split("\n");
        String temp, tempCadDesc;

        int index;
        Pattern p = Pattern.compile(reglas);
        Matcher matcher;
        int nl = 0;
        for (String x : texto) {
            nl++;
            auxLC = new LineaCod();
            while (!x.equals("")) {
                index = 0;
                temp = "";
                x = borrEspInic(x);
                matcher = p.matcher(x);
                if (matcher.find() == false) {

                    re[1] += "Error Lexico, Linea " + nl + ". " + x + ", no se encuentra en ningun componente lexico. \n";
                    break;
                }
                for (int i = 1; i <= GRUPOS; i++) {
                    if (matcher.group(i) == null) {
                        continue;
                    }
                    if (temp.length() < matcher.group(i).length()) {
                        temp = matcher.group(i);
                        index = i;
                    }
                }
                //Comentarios
                if (index == 9) {
                    break;
                }
                if (x.indexOf(temp) == 0) {
                    x = x.substring(temp.length(), x.length());
                    if (auxError(index, temp, nl)) {
                        continue;
                    }
                    tabla(index, temp, nl);
                    re[0] += tokens[index].replace("%s", temp);
                    auxLC.tokens.add(tokens[index].replace("%s", temp));
                    continue;
                }
                tabla(index, temp, nl);
                tempCadDesc = x.substring(0, x.indexOf(temp));
                auxError(0, tempCadDesc, nl);
                x = x.substring(tempCadDesc.length(), x.length());
                x = x.substring(temp.length(), x.length());
                if (auxError(index, temp, nl)) {
                    continue;
                }
                re[0] += tokens[index].replace("%s", temp);
                auxLC.tokens.add(tokens[index].replace("%s", temp));

            }
            secCod.add(auxLC);
        }
        return re;
    }

    //Este metodo quita los espacio iniciales de una cadena y deja solo uno
    private String borrEspInic(String cad) {
        while (cad.indexOf(' ') == 0) {
            cad = cad.replaceFirst(" ", "");
        }
        return cad;
    }

    private boolean auxError(int index, String temp, int nl) {
        switch (index) {

            case 13:{
                return auxNumSig(index, temp, nl);
            }

            case 16: {
                re[1] += "Error lexico, en la linea " + nl + ". Lexema " + temp + ", comilla dobles sin cerrar. Solución colocar"
                        + " la correspondiente comilla doble\n";
                return true;
            }
            case 17: {
                re[1] += "Error lexico, en la linea " + nl + ". Lexema " + temp + ", comilla simples sin cerrar. Solución colocar la correspondiente"
                        + " comilla simple\n";
                return true;
            }
            case 18: {
                re[1] += "Error lexico, en la linea " + nl + ". Lexema " + temp + ", Inicio de identificador no valido\n";
                return true;
            }

        }
        return false;
    }


    public boolean auxNumSig(int index, String temp, int nl){
        if(re[0].equals("")){
            return false;
        }
        String tokenAnt = "";
        if(temp.charAt(0) == '+' || temp.charAt(0) == '-'){
            String aux[] = re[0].substring(0, re[0].length()-1).split("\n");
            tokenAnt = aux[aux.length-1].substring(1, aux[aux.length-1].indexOf(", "));
            if(tokenAnt.equals("Número") || tokenAnt.equals("Identificador")){
                tabla(index, temp.charAt(0)+"", nl);
                re[0] += tokens[14].replace("%s", temp.charAt(0)+"");
                auxLC.tokens.add(tokens[14].replace("%s", temp.charAt(0)+""));

                tabla(index, temp.substring(1,temp.length()), nl);
                re[0] += tokens[13].replace("%s", temp.substring(1,temp.length()));
                auxLC.tokens.add(tokens[13].replace("%s", temp.substring(1,temp.length())));
                return true;
            }
        }
        return false;
    }

    Simbolo simbolo = new Simbolo();
    String nombre = null;
    boolean bandera = false;

    private void tabla(int i, String temp, int nl){

        if(nl != simbolo.fila){
            simbolo = new Simbolo();
            nombre = null;
            simbolo.fila = nl;
        }
        if(simbolo.tipo != null && nombre != null){
            if(!tablaSimbolos.containsKey(nombre)){
                tablaSimbolos.put(nombre, simbolo);
            }
        }
        String tokenAnt = "";
        /*if(re[0].equals("")){
            return;
        }*/
        switch(i){
            case 2:{
                simbolo.tipo = temp;
                break;
            }
            case 8:{
                if(!re[0].equals("")){
                    String aux[] = re[0].substring(0, re[0].length()-1).split("\n");
                    tokenAnt = aux[aux.length-1].substring(1, aux[aux.length-1].indexOf(", "));
                    if(tokenAnt.equals("Tipo de Dato")){
                        nombre = temp;
                    }
                }
                break;
            }
        }
    }

    private void tabla(int i, String temp){
        int l = 0;//Variable para que no de error
        if (i == 2) {// verificar si el grupo es de los tipos de datos
            simbolo.tipo= temp;//Le asignamos el tipo de dato
        }
        if (i == 7) {//Verificar si el grupo corresponde a un identificador
            if (bandera) {
                simbolo.valor=temp;
                simbolo.fila=l;
            }else{
                nombre = temp;//Asignamos el identificador a nombre
                simbolo.fila = l;//Asignamos la fila en la que fue declarado el identificador
            }
        }
        if (i == 6) {//Verificar si el grupo corresponde al de un operador de asignación
            if (temp.equals("=")) {//Verificar si el operador de asignación es el igual
                bandera = true;//Activamos la bandera que nos permitira asignar el valor
            }
        }
        if ((i == 9 /*|| i == 10*/) && bandera) {//Verificar si el grupo corresponde a una cadena vacia o a una cadena de texto, además verificar si corresponde a un valor
            simbolo.valor = temp;//Asignamos el valor
            bandera = false;//Desactivamos la bandera
        }
        if (i == 12 && bandera) {//Verificar si el grupo es un numero y si la bandera esta activada para considerarla como el valor del idenficador
            simbolo.valor = temp;//Asignamos el valor al identificador
            bandera = false;//Desactivar la bandera
        }
        if(i == 14) {//Verificar si el grupo corresponde a un signo de puntuación
            if (temp.equals(";") || temp.equals(",")) {//Verificar si el signo de puntuación corresponde con el delimitador de linea
                if (!tablaSimbolos.containsKey(nombre) && simbolo.tipo!=null && simbolo.fila!=0 && !nombre.equals("")) {//Verificamos que la tabla de simbolos no contenga el identificador y verificar que realmente se esta declarando la variable y no modificando
                    tablaSimbolos.put(nombre, new Simbolo(simbolo.tipo, simbolo.valor, simbolo.fila));//Insertamos en la tabla un nuevo simbolo
                    nombre = "";
                    simbolo = new Simbolo();//inicializamos la variable simbolo para que este disponible para recibir nuevos valores
                }else{//En este caso, significa que el identificador ya existe en la tabla de simbolos
                    if (simbolo.tipo==null && !nombre.equals("") && simbolo.valor!=null) {//Verificamos si el identificador que se intenta insertar, se esta declarando o bien si se pretende modificar el valor de éste
                        if (tablaSimbolos.containsKey(nombre)) {
                            tablaSimbolos.get(nombre).valor= simbolo.valor;//Buscamos el identificador en la tabla de simbolos y actualizamos su valor
                            nombre = "";
                            simbolo =new Simbolo();//Inicializamos la variable simbolo para que este disponible para almacenar nuevo valores para los identificadores
                        }
                    }else{
                        //Error por identificador duplicado
                        //AreaErrores.setText("Error, en la linea "+simbolo.fila+" Identificador duplicado");
                    }
                    nombre="";// Inicializar la variable para el nombre del identificador
                    simbolo = new Simbolo();// Inicializar la variable simbolo
                }
            }
        }
    }


}