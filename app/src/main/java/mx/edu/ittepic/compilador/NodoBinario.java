package mx.edu.ittepic.compilador;


public class NodoBinario {
    NodoBinario nodP;
    NodoBinario nodHD, nodHI;
    public Object valor;
    
    public NodoBinario(Object v){
        valor = v;
        nodP = nodHI = nodHD = null;
    }
    //Método utilizado para determinar si un nodo es hoja o bien un nodo final
    public boolean esHoja(){
        return nodHI == null && nodHD == null;
    }
    //Método utilizado para determinar si un nodo es rama o bien un nodo interno
    public boolean esRama(){
        return !esHoja();
    }
    //Método utilizado para crear los hijos de un nodo: izquierdo y derecho
    public void crearNodHs(){
        nodHI = new NodoBinario(null);
        nodHD = new NodoBinario(null);
        nodHI.nodP = nodHD.nodP = this;
    }
    
}