package mx.edu.ittepic.compilador;


public class Simbolo {
    public Object tipo;
    public Object valor;
    public int fila;
    public Simbolo(Object tip, Object val, int fil){
        tipo=tip;
        valor=val;
        fila=fil;
    }
    public Simbolo(){
        fila = 1;
        tipo = valor = null;
    }
}
