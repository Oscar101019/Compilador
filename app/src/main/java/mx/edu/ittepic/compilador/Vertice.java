package mx.edu.ittepic.compilador;


public class Vertice {
    public String valor;
    Vertice ant;
    Vertice sig;
    Arista arista;
    boolean eini, efin;
    
    public Vertice(String v, boolean fst, boolean end){
        eini = fst;
        efin = end;
        valor = v;
        ant = sig = null;
        arista = null;
    }
    
    public boolean enlazar(Vertice destino, String val){
        Arista nuevaArista = new Arista(destino, val);
        if(nuevaArista == null){return false;}
        //ini == null && fin == null
        if(arista == null){
            arista = nuevaArista;
            return true;
        }
        Arista t;
        for(t = arista; t.sig != null; t = t.sig);
        t.sig = nuevaArista;
        nuevaArista.ant = t;
        return true;
    }
    
    public Arista buscarArista(String val){
        Arista temp, temp2 = null;
        for(temp = arista; temp != null; temp = temp.sig){
            if(temp.val.equals(val)){
                temp2 = temp;
            }
        }
        return temp2;
    }
    
    public boolean esEstFinal(){
        return efin;
    }
}
