package mx.edu.ittepic.compilador;


public class Gramatica {
    //Variables
    //Teminales
    private String[] t;
    //No terminales
    private String[] n;
    //No terminal inicial
    private String s;
    //Producciones
    private String[] p;
    //Contiene la cadena mas parecida a la que se evalua, en el caso de que de error, esta valor contiene la cadena
    private String err;
    public Gramatica(){
    //Inicializacion de las variables
        t = n = p = null;
        s = "";
        err = "";
    }
    
    public String geterr(){
        return err;
    }
    //Metodos para asignar las variables de la clase ya que estas se encuentran como privadas
    
    public int indxError(String cad){
        for(int i = 0; i < cad.length(); i++){
            if(err.charAt(i) != cad.charAt(i)){
                return i;
            }
        }
        return cad.length()-1;
    }
    
    public void setT(String[] t) {
        this.t = t;
    }

    public void setN(String[] n) {
        this.n = n;
    }

    public void setS(String s) {
        this.s = s;
    }

    public void setP(String[] p) {
        this.p = p;
    }
//Metodo que retorna las derivaciones que hicieron y como se va formando la expresion
//exp, es el parametro que tiene la expresion que tecleo el usuario
    public String recorrido(String exp){
        //temp2 es un temporal que contiene la sucecion de las producciones al derivar ejemplo: 1-2-3-4
        String temp2 = validar(exp);
        //Si es null, significa que la expresion es incorrecta
        if(temp2 == null){
            //Por lo que la vuelve a validar, pero con la expresion que mas se le parecia, se le concatena ERROR, por que 
            //la cadena tuvo error, y en temp2 se alamacena por ejemplo: 1-2-3-ERROR
            temp2 = validar(err)+"ERROR";
            //La variables err se "Inicializa de nuevo"
            err = "";
        }
        //temp contiene el recorrdio de las derivaciones que hicieron y como se va formando la expresion, por ejemplo
        /*
            -   E
            3   T
            6   F
            7   ID
        Como siempre la gramatica va a empezar por el no terminal inicial, por eso se inicializa temp de esa forma "-   E"
        */
        String temp = "-\t"+s+"\n";
        //El arreglo t contiene la secuencia de derivaciones, y como te habras dado cuenta, estaban convenientemente con 
        // forma 1-2-3, por lo tanto tendra un arreglo de la forma {"1", "2", "3"}, esto es por mera practicidad
        String[]t = temp2.split("-");
        //t3 contiene el desarrollo de las derivaciones osea esta columna (la de la derecha):
        /*
            -   E
            3   T
            6   F
            7   ID
                ^
                |
        pero obviamente va cambiando con cada iteracion del for de abajo enseguida
        */
        String t3 = s;
        //n es  un temporal que nos ayuda a almacenar el valor actual de la sucesion en el ciclo for, y como recuerdan los
        //arreglo empiezo en 0 y la gramatica en 1, por eso debo restarle un 1
        int n;
        //for que realiza las derivaciones paso por paso conforme a las sucesiones en el arreglo t, la forma del for es 
        // como la de python ejemplo for r in(t):
        for(String n1 : t){
            //Como recordaran se le concateno la palabra ERROR, por si la expresion del usuario esta mal
            //y condiciono de que si encuentra en n1 la palabra ERROR lo concatene a la variable temp
            //que es el que contiene lo que se muestra en el area de texto, break es para romper el ciclo
            //ya que ERROR, no es un numero
            if(n1.equals("ERROR")){
                temp += "ERROR";
                break;
            }
            //La sucesion se convierte a numero
            n = Integer.parseInt(n1);
            //Se le resta 1, debido a lo que ya se habia comentado
            n -= 1;
            //En esta parte se hace como la derivacion, remplazando el noterminal por las producciones, se usa el
            //replaceFirst para que no remplace todos los no terminales que se lleven en ese momento
            // como recordara las producciones tiene la forma por ejemplo
            //  E = E + T
            // 0 1 2 3 4 5 (indices de longitud de la cadena)
            //obtengo la subcadena apartir del 2 por que apartir de ese punto a la derecha esta la produccion y omito lo
            //que es el noterminal y el signo =
            t3 = t3.replaceFirst(p[n].charAt(0)+"", p[n].substring(2, p[n].length()));
            //como t3 contiene la derivacion de la iteracion actual, se almacenan en la variable temp
            // y se le ponen salto de linea para que se diferencien las derivaciones y sucesiones por ejemplo
        /*
            -   (tab)  E (salto de linea)
            3   (tab)  T (salto de linea)
            6   (tab)  F (salto de linea)
            7   (tab)  ID (salto de linea)
          (n+1) +"\t"+ t3+"\n"
        */
            temp += (n+1)+"\t"+t3+"\n";
        }
        // como la funcion tiene que retornar un String, retorna temp que es de tipo String y es el resultado final del
        // proceso de validacion
        return temp;
    }
    
    //Metodo que retorna la sucesiones a derivar para obtener la expresion, exp es el parametro que contiene la
    //expresion introducida por el usuario
    public String validar(String exp){
        //av es  "avance", es la variable que contiene el avance al hacer las derivaciones
        String av = s;
        //busquedaCamino es un metodo recursivo, que hace todas las combinaciones para lograr encontrar la cadena, en 
        //base a las prudcciones, retorna null, si esta mal la expresion del usuario
        String r = busquedaCamino(av, exp);
        //retorna null si r es null, en caso contrario retorna r
        return (r == null)?null:r;
    }
    
    private String busquedaCamino(String av, String exp){
        //variables temporales para almacenar valores para sus posterioir uso
        String temp, temp2;
        //Condicion de paro de la recursividad, si su longitud es igual y la cadena es igual
        if(exp.equals(av)){
                return "";
        }
        //comprueba que la variables av, no tenga terminales que la variables exp no tenga
        if(cond(av, exp)){
            return null;
        }
        //for para remplzar los no terminales dentro de la variable av
        for(int i = 0; i < av.length(); i++){
            //Condicion para segurarse que los terminales este bien posicionados
                if(i < exp.length()){
                    if(!esNoterm(av.substring(i, i+1))){
                        if(!av.substring(i, i+1).equals(exp.substring(i, i+1))){
                            return null;                     
                        }
                    }
                }
                //si el caracter actual en la iteracion es un noterminal, se procede a derivar
                if(esNoterm(av.substring(i, i+1))){
                    //busca la produccion del no terminal
                    for(int y = 0; y < p.length; y++){
                        if(p[y].charAt(0) == av.charAt(i)){
                            //temp2 contienen la produccion que va a remplazar el noterminal
                            temp2 = p[y].substring(2, p[y].length());
                            //se remplaza el no terminal por la produccion
                            String g = av.replaceFirst(p[y].charAt(0)+"", temp2);
                            // se vuelve a buscar pero con el avance actual
                            temp = busquedaCamino(g, exp);
                            if(temp != null){ return (y+1)+"-"+temp;}
                        }
                    }
                    // para almacenar la cadena mas parecida a la expresion del usuario, si hay error con la expresion del
                    // usuario
                    if(numNoTerm(av) == 1){
                        if(av.length() > err.length()){
                            err = av;
                        }
                    }
                    //significa que no encontro producciones que satisfagan
                    return null;
                }
            }
        //return default
        return null;
    }
   //Metodo para saber cuantos noterminales tiene la cadena av, no me dentendre a explicar, por que es facil de entender
    public int numNoTerm(String av){
        int temp = 0;
        for(int i = 0; i < av.length();i++){
            if(esNoterm(av.substring(i, i+1))){
                temp++;
            }
        }
        return temp;
    }
    
    //Metodo cond boolean que nos dice si la cadena av tiene noterminales que no tenga la cadena exp, sus nombre son
    //analogos a las variables del metodo busquedaCamino, por ejemplo
    /*
        exp = I+I
        av = F-D
    retornaria falso por que la expresion del usuario no tiene -, si no un +
    no me detendre a explicar a detalle, por que me esta facil de entender y me da huewa
    */
    /*
    public boolean cond(String av, String exp){
        String temp1 = av;
        String temp2 = exp;
        
        for(String i:n){
            temp1 = temp1.replace(i, "");
            temp2 = temp2.replace(i, "");
        }
        if(temp1.equals("")){
            return false;
        }
        for(int i = 0; i < av.length(); i++){
            if(temp1.charAt(i) != temp2.charAt(i)){
                return true;
            }
        }
        return false;
    }*/
    public boolean cond(String av, String exp){
        String temp;
        for(int i = 0; i < av.length();i++){
            if(!esNoterm(av.substring(i, i+1))){
                temp = exp;
                try{
                    exp = exp.replaceFirst(av.substring(i, i+1), "");
                }catch(java.util.regex.PatternSyntaxException err){
                    exp = exp.replaceFirst('\\'+av.substring(i, i+1), "");
                }
                if(temp.equals(exp)){
                    return true;                
                }
            }
        }
        return false;
    }
    
    //Metodo para saber si la cadena c es un noterminal, no me dentendre a explicar, por que es facil de entender
    public boolean esNoterm(String c){
        for(String n1 : n) {
            if (c.equals(n1)) {
                return true;
            }
        }
        return false;
    }
    
}
