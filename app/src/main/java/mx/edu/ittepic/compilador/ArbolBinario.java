package mx.edu.ittepic.compilador;

import java.util.ArrayList;
import java.util.Hashtable;


public class ArbolBinario {
    //Apuntador de la tabla de simbolos
    public Hashtable<String, Simbolo> tablaSimbolos;
    NodoBinario raiz;
    int index = -1;
    
    int indexTok = 0;
    public ArrayList<Simbolo> sim;
    
    int p[];
    private final String[]reglasSem= {"BOO OL BOO=BOO",
                                       "NU + NU=NU","NU - NU=NU","NU * NU=NU","NU / NU = NU","NU OR NU=BOO",
                                       "LT + LT=LT","LT + NU=LT","NU + LT=LT"};
    
    public String err;
    public int nl;
    
    public ArbolBinario(){
        raiz = null;
        err = "";
    }
    
    //Método utilizado para crear un nodo
    public boolean crearArbol(Object v){
        raiz = new NodoBinario(v);
        if(raiz == null){return false;}
        System.out.println(raiz.valor);
        return true;
    }
    
    public Simbolo valExpresion(int[]p){
        this.p = p;
        raiz = new NodoBinario("");
        if(raiz == null){return null;}
        System.out.println(raiz.valor);
        
        index = -1;
        indexTok = 0;
        
        crearArbol(raiz);
        return recIn();
    }
    
    public void crearArbol(NodoBinario nodoP){
        index++;
        if(index >= p.length){
            return;
        }
        switch(p[index]){
            case 1:{
                nodoP.valor="+";
                nodoP.nodHI = new NodoBinario("");
                crearArbol(nodoP.nodHI);
                
                nodoP.nodHD = new NodoBinario("");
                crearArbol(nodoP.nodHD);
                break;
            }
            case 2:{
                nodoP.valor="-";
                nodoP.nodHI = new NodoBinario("");
                crearArbol(nodoP.nodHI);
                
                nodoP.nodHD = new NodoBinario("");
                crearArbol(nodoP.nodHD);
                break;
            }
            case 3:{
                crearArbol(nodoP);
                break;
            }
            case 4:{
                nodoP.valor="*";
                nodoP.nodHI = new NodoBinario("");
                crearArbol(nodoP.nodHI);
                
                nodoP.nodHD = new NodoBinario("");
                crearArbol(nodoP.nodHD);
                break;
            }
            case 5:{
                nodoP.valor="/";
                nodoP.nodHI = new NodoBinario("");
                crearArbol(nodoP.nodHI);
                
                nodoP.nodHD = new NodoBinario("");
                crearArbol(nodoP.nodHD);
                break;
            }
            case 6:{
                crearArbol(nodoP);
                break;
            }
            case 7:{
                nodoP.valor="OR";
                nodoP.nodHI = new NodoBinario("");
                nodoP.nodHI.nodP = nodoP;
                crearArbol(nodoP.nodHI);
                
                nodoP.nodHD = new NodoBinario("");
                nodoP.nodHD.nodP = nodoP;
                crearArbol(nodoP.nodHD);
                break;
            }
            case 8:{
                crearArbol(nodoP);
                break;
            }
            case 9:{
                nodoP.valor="OL";
                nodoP.nodHI = new NodoBinario("");
                nodoP.nodHI.nodP = nodoP;
                crearArbol(nodoP.nodHI);
                
                nodoP.nodHD = new NodoBinario("");
                nodoP.nodHD.nodP = nodoP;
                crearArbol(nodoP.nodHD);
                break;
            }
            case 10:{
                crearArbol(nodoP);
                break;
            }
            case 11:{
                nodoP.valor=sim.get(indexTok);//BOO
                indexTok++;
                break;
            }
            case 12:{
                nodoP.valor=sim.get(indexTok);//ID
                indexTok++;
                break;
            }
            case 13:{
                nodoP.valor=sim.get(indexTok);//NUM
                indexTok++;
                break;
            }
            case 14:{
                nodoP.valor=sim.get(indexTok);//TXT
                indexTok++;
                break;
            }
            case 15:{
                crearArbol(nodoP);
                break;
            }
        }
    }
    
    public Simbolo recIn(){
        return recIn(raiz);
    }
    
    private Simbolo recIn(NodoBinario nodo){
        if(nodo.esHoja()){
            return (nodo.valor instanceof Simbolo)?(Simbolo)nodo.valor: new Simbolo(nodo.valor, nodo.valor, 0);
        }
        //Obtener operador 1.
        Simbolo op1 = recIn(nodo.nodHI);
        
        if(op1.tipo.equals("ERR")){
            return new Simbolo("ERR","ERR",0);
        }
        
        if(!(op1.tipo+"").equals("ID")){
            if(!tablaSimbolos.containsKey(op1.valor+"")){
                err+="Error semantico, en la linea "+(op1.fila+1)+". Variable \""+op1.valor+"\" inexistente en el programa. Solucion crear la variable lol.\n";
                return new Simbolo("ERR","ERR",0);
            }else if(tablaSimbolos.get(op1.valor+"").fila > op1.fila){
                err+="Error semantico, en la linea "+(op1.fila+1)+". Variable \""+op1.valor+"\" inicializada despues de su uso. Solucion: Declara la variables antes de darle uso.\n";
                return new Simbolo("ERR","ERR",0);
            }
            if(tablaSimbolos.get(op1.valor+"").valor == null){
                err+="Error semantico, en la linea "+(op1.fila+1)+". Variable \""+op1.valor+"\" sin inicializar. Solucion: Asignarle un valor a la varibles antes de usarla.\n";
                return new Simbolo("ERR","ERR",0);
            }
        }
        Simbolo op2 = recIn(nodo.nodHD);
        
        if(op2.tipo.equals("ERR")){
            return new Simbolo("ERR","ERR",0);
        }
        
        if(!(op2.tipo+"").equals("ID")){
            if(!tablaSimbolos.containsKey(op2.valor+"")){
                err+="Error semantico, en la linea "+(op1.fila+1)+". Variable \""+op2.valor+"\" inexistente en el programa. Solucion crear la variable lol.\n";
                return new Simbolo("ERR","ERR",0);
            }else if(tablaSimbolos.get(op2.valor).fila > op2.fila){
                err+="Error semantico, en la linea "+(op1.fila+1)+". Variable \""+op2.valor+"\" inicializada despues de su uso. Solucion: Declara la variables antes de darle uso.\n";
                return new Simbolo("ERR","ERR",0);
            }
            if(tablaSimbolos.get(op2.valor).valor == null){
                err+="Error semantico, en la linea "+(op1.fila+1)+". Variable \""+op2.valor+"\" sin inicializar. Solucion: Asignarle un valor a la varibles antes de usarla.\n";
                return new Simbolo("ERR","ERR",0);
            }
        }
        /*if(((Simbolo)nodo.valor).tipo.equals("ERROR")){
            return new Simbolo("ERR","ERR",0);
        }*/
        String[]aux;
        String temp = auxSem3(tablaSimbolos.containsKey(op1.valor+"")?tablaSimbolos.get(op1.valor).tipo+"":op1.tipo+"")
                +" "+nodo.valor+" "
                +auxSem3(tablaSimbolos.containsKey(op2.valor+"")?tablaSimbolos.get(op2.valor).tipo+"":op2.tipo+"");
        
        for(String i:reglasSem){
            aux = i.split("=");
            if(aux[0].equals(temp)){
                return new Simbolo(aux[1],aux[1],0);
            }
        }
        err+="Error semantico, en la linea "+(op1.fila+1)+". Operacion de "+auxErr(nodo.valor+"")+" entre un \""+auxErr(op1.tipo+"")+"\" y un"
                    + " \""+auxErr(op1.tipo+"")+"\". Solucion: Corregir la operacion, de acuerdo a las siguientes reglas "+auxErr2(nodo.valor+"")+".\n";
        return new Simbolo("ERR","ERR",0);
    }
    
    public String auxErr(String i){
        switch(i){
            case "+":{return "suma";}
            case "-":{return "resta";}
            case "/":{return "dividision";}
            case "*":{return "multiplicacion";}
            case "ID":{return "identificador";}
            case "ENTERO":{return "numero";}
            case "LT":{return "literal de texto";}
            case "BOO":{return "valor booleano";}
        }
        return i;
    }
    public String auxSem3(String i){
        switch(i){
            case "ENTERO":{return "ENTERO";}
            case "string":{return "LT";}
            case "boolean":{return "BOO";}
        }
        return i;
    }
    
    public String auxErr2(String i){
        switch(i){
            case "+":{return "Numero + Numero = Numero ó Numero + Texto = Texto";}
            case "-":{return "Numero - Numero = Numero";}
            case "/":{return "Numero / Numero = Numero";}
            case "*":{return "Numero * Numero = Numero";}
            case "OP":{return "Numero < Numero = Booleano ó Numero <= Numero = Booleano ó Numero > Numero = Booleano ó Numero >= Numero = Booleano";}
            case "OR":{return "Booleano and Booleano = Booleano ó Booleano or Booleano";}
        }
        return i;
    }
    
}
