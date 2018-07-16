package mx.edu.ittepic.compilador;


import java.util.ArrayList;
import java.util.Hashtable;


public class AnalizadorSemantico {
    //Apuntador de la tabla de simboloes
    public Hashtable<String, Simbolo> tablaSimbolos;
    
    String sentencias[];
    public String err[];
    public ArbolBinario ab;
    
    public AnalizadorSemantico(String[]s){
        sentencias = s;
        err = new String[2];
        err[0] = err[1] = "";
        ab = new ArbolBinario();
    }
    
    public void compilar(){
        for(String i: sentencias){
            String[]aux = i.split(",");
            String sec = aux[4];
            sec = sec.substring(0, sec.length()-1);
        
            genSimb(aux[2].split(" "),aux[3].split(" "), Integer.parseInt(aux[0]));
        
            Simbolo temp = ab.valExpresion(secAEnt(sec));
            switch(aux[2].split(" ")[0]){
                case "IF":{
                    if(!temp.tipo.equals("BOO") || temp.tipo.equals("ERR")){
                        err[0] += "Error semantico, en la linea "+(Integer.parseInt(aux[0])+1)+". La estructura IF solo acepta valores boolenos. Solucion: Verificar la condicion del IF.\n";
                    }
                    break;
                }
                case "TD":{
                    if((temp.tipo+"").equals("ID")){
                        temp = tablaSimbolos.get(temp.valor+"");
                    }

                    try{
                        String tipo = aux[3].split(" ")[0];
                        tipo = aux(tipo);

                        if(!(auxErr(temp.tipo+"")).equals(tipo) || temp.tipo.equals("ERR")){
                            err[0] += "Error semantico, en la linea "+(Integer.parseInt(aux[0])+1)+". La variable es de tipo "+auxErr(aux[3].split(" ")[0])+". Solucion: Verificar la sentencia de inicializacion.\n";
                        }

                    }catch (NullPointerException e){
                        err[0] += "Error semantico, en la linea "+(Integer.parseInt(aux[0])+1)+". La variable es de tipo "+auxErr(aux[3].split(" ")[0])+". Solucion: Verificar la sentencia de inicializacion.\n";
                    }
                    
                    String val = "";
                    String[]arr = aux[3].split(" ");
                    for(int j = 3; j<arr.length-1;j++){
                        val+=arr[j];
                    }
                    String idObj = aux[3].split(" ")[1];
                    tablaSimbolos.get(idObj).valor = val;
                    break;
                }
                case "DURING":{
                    if(!temp.tipo.equals("BOO") || temp.tipo.equals("ERR")){
                        err[0] += "Error semantico, en la linea "+(Integer.parseInt(aux[0])+1)+". La estructura DURING solo acepta valores boolenos. Solucion: Verificar la condicion del DURING.\n";
                    }
                }
                case "ID":{
                    if((temp.tipo+"").equals("ID")){
                        temp = tablaSimbolos.get(temp.valor+"");
                    }
                    String tipo = (String)tablaSimbolos.get(aux[3].split(" ")[0]).tipo;
                    if(!temp.tipo.equals(tipo) && !temp.tipo.equals("ERR")){
                        err[0] += "Error semantico, en la linea "+(Integer.parseInt(aux[0])+1)+". La estructura IF solo acepta valores boolenos. Solucion: Verificar la condicion del IF.\n";
                    }
                    String val = "";
                    String[]arr = aux[3].split(" ");
                    for(int j = 2; j<arr.length-1;j++){
                        val+=arr[j];
                    }
                    String idObj = aux[3].split(" ")[1];
                    tablaSimbolos.get(idObj).valor = val;
                    break;
                }
            }
        }
        err[0]+=ab.err;
    }
    
    public String auxErr(String val){
        switch(val){
            case "BOO":{return "BOOLEANO";}
            case "NU":{return "ENTERO";}
            case"LT":{return "CADENA";}
        }
        return val;
    }
    public String aux(String val){
        switch(val){
            case "number":{return "NU";}
            case "string":{return "LT";}
            case"boolean":{return "BOO";}
        }
        return val;
    }
    
    public void genSimb(String[] codM, String[] codSM, int nl){
        ArrayList<Simbolo> s = new ArrayList();
        for(int i = 0; i < codM.length; i++){
            if(codM[0].equals("ID") && i == 0 || codM[1].equals("ID") && i == 1){
                continue;
            }
            
            if(codM[i].equals("BOO") ||codM[i].equals("NU") ||codM[i].equals("LT")){
                s.add(new Simbolo(codM[i], codSM[i], nl));
            }
            if(codM[i].equals("ID")){
                if(tablaSimbolos.containsKey(codSM[i])){
                    s.add(new Simbolo(aux(tablaSimbolos.get(codSM[i]).tipo+""),aux(tablaSimbolos.get(codSM[i]).tipo+""),nl));
                }else{
                    s.add(new Simbolo(codM[i], codSM[i], nl));
                }
            }
        }
        ab.sim = s;
    }
    
    public int[] secAEnt(String p){
        String[]produccion = p.split("-");
        int []pSec = new int[produccion.length];
        for(int i = 0; i < produccion.length; i++){
            pSec[i] = Integer.parseInt(produccion[i]);
        }
        return pSec;
    }
}
    
