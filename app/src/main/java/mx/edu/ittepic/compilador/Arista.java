package mx.edu.ittepic.compilador;


public class Arista {
   Vertice punteroaVertice;
    Arista ant;
    Arista sig;
   String val;
   
   public Arista(Vertice vertice, String val){
       punteroaVertice = vertice;
       ant = sig = null;
       this.val = val;
   }
}
