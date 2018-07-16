package mx.edu.ittepic.compilador;

import java.util.ArrayList;

//Est clase aguarda los tokens por linea, para facilitar su uso en los posterioires analizadores
public class LineaCod {
    public ArrayList<String> tokens;
    public LineaCod(){
        tokens  = new ArrayList();
    }
}
