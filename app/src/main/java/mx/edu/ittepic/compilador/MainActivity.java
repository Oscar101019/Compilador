package mx.edu.ittepic.compilador;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.edu.ittepic.compilador.Automatas.Agrupacion;
import mx.edu.ittepic.compilador.Automatas.Aritmeticos;
import mx.edu.ittepic.compilador.Automatas.Carro;
import mx.edu.ittepic.compilador.Automatas.Datos;
import mx.edu.ittepic.compilador.Automatas.Garra;
import mx.edu.ittepic.compilador.Automatas.Identificador;
import mx.edu.ittepic.compilador.Automatas.Literal;
import mx.edu.ittepic.compilador.Automatas.Logico;
import mx.edu.ittepic.compilador.Automatas.Numero;
import mx.edu.ittepic.compilador.Automatas.Palabras;
import mx.edu.ittepic.compilador.Automatas.Relacional;
import mx.edu.ittepic.compilador.Automatas.Valor_booleano;

public class MainActivity extends AppCompatActivity {
    EditText codigo,consola,textlineas;
    ImageButton BtnCompilar,BtnTabla,BtnTablaS,BtnSeguimiento,BtnIntermedio;
    Token tokens[];
    int linea;
    ListView LvSeg;

    boolean comentario=false;


    Agrupacion agrupacion = new Agrupacion();
    Aritmeticos aritmeticos = new Aritmeticos();
    Carro carro=new Carro();
    Datos datos = new Datos();
    Garra garra = new Garra();
    Identificador identificador = new Identificador();
    Logico logico = new Logico();
    Literal literal = new Literal();
    Numero numero = new Numero();
    Palabras palabras=new Palabras();
    Relacional relacional=new Relacional();
    Valor_booleano val_boolean = new Valor_booleano();


    public static String seguimientoAutomata = "";
    public static String seguimientoGramatica = "";
    public  String[] nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codigo = findViewById(R.id.ETcodigo);
        consola = findViewById(R.id.consola);
        BtnSeguimiento = (ImageButton)findViewById(R.id.BtnSeguimiento);
        BtnCompilar = (ImageButton)findViewById(R.id.BtnCompilar);
        BtnTabla = (ImageButton) findViewById(R.id.BtnTabla);
        BtnTablaS = (ImageButton) findViewById(R.id.BtnTablaS);
        BtnIntermedio = (ImageButton) findViewById(R.id.BtnIntermedio);
        LvSeg = (ListView)findViewById(R.id.LvSeguimiento);

        textlineas=(EditText)findViewById(R.id.textlineas);
        codigo.requestFocus();
        codigo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int lineas=codigo.getLineCount();
                String cad="";

                for(int j=1;j<=lineas;j++){
                    if (j==lineas){
                        cad+=j;
                    }else{
                        cad+=j+"\n";
                    }
                }

                textlineas.setText("");
                textlineas.setText(cad);
                textlineas.requestFocus();
                codigo.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        BtnCompilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tokens=new Token[0];
                linea=1;
                String cod=codigo.getText()+"";
                /*
                String lineas []=cod.split("\n");
                for(int i=0;i<lineas.length;i++){
                    lineas[i].trim();
                    String palabras[]=lineas[i].split(" ");
                    for(int j=0;j<palabras.length;j++){
                        agregar(palabras[j],i+"");
                    }
                }
                */
                String palabra="";
                consola.setText("");
                for(int i=0;i<cod.length();i++){
                    if(!comentario) {
                        /*antes de agregar el caracter
                         * verificar que no sea un delimitador*/
                        if (esDelimitador(cod.charAt(i) + "")) {
                            /*verificar que sea una palabra reservada, sino se toma como
                             * identificador*/
                            if (esPalabra(palabra)) {
                                palabra = "";
                            }
                            if (esId(palabra)) {
                                palabra = "";
                            } else {
                                /*error, algun caracter no pertenece al alfabeto*/
                                if (!palabra.equals("")) {
                                    String e = "Error en la linea " + linea + " cadena no identificada: " + palabra
                                            + "\n";
                                    consola.setText(e);
                                }
                            }
                        } else {
                            palabra = palabra + cod.charAt(i);
                        }
                        /*verificar que sea una palabra reservada*/
                        if (esPalabra(palabra.trim())) {
                            palabra = "";
                        }
                    }//no comentario
                    String t=cod.charAt(i)+"";
                    if(t.equals("\n")){
                        comentario=false;
                        palabra="";
                        linea++;
                    }
                }//for

                String codig = codigo.getText().toString();
                AnalizadorLexico al = new AnalizadorLexico();

                String [] re = al.compilar(codig);
                String []r = re[0].split("\n");



                String textErr = "";

                if(!re[1].equals("")){
                    textErr += re[1];
                }

                AnalizadorSintactico as = new AnalizadorSintactico(al.secCod,AnalizadorLexico.tablaSimbolos);
                String []errAnalSint = as.compilar();

                seguimientoAutomata = as.avAutomata;
                seguimientoGramatica = as.avGramatica;


                if(!errAnalSint[0].equals("")){
                    textErr += errAnalSint[0];
                }


                AnalizadorSemantico aSem=new AnalizadorSemantico(errAnalSint[1].split("\n"));

                if(!errAnalSint[1].equals("")){
                    aSem = new AnalizadorSemantico(errAnalSint[1].split("\n"));
                    aSem.ab.tablaSimbolos = as.tablaSimbolos;
                    aSem.tablaSimbolos = as.tablaSimbolos;
                    aSem.compilar();
                }


                if(!aSem.err[0].equals("")){
                    textErr += aSem.err[0];
                }

                consola.setText(textErr);


                if(!re[1].equals("")){
                    textErr += re[1];
                }
                if(!errAnalSint[0].equals("")){
                    textErr += errAnalSint[0];
                }else{
                    GeneradorDeCodigo gc = new GeneradorDeCodigo();
                    as = new AnalizadorSintactico(al.secCod, AnalizadorLexico.tablaSimbolos);
                    //Codigo intermedio
                    gc.convrt(as.codSM, as.codM);
                    //Codigo optimizados
                    // String codigoOpt = gc.opt();
                }

                if(textErr.equals("")){
                    consola.setText("Compilado con exito!!");
                }

            }//onClick


        });

        BtnIntermedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String verificar="";
                verificar=consola.getText().toString();
                if(verificar.equals("Compilado con exito!!")){
                    Intent intent=new Intent(MainActivity.this,CodIntermedio.class);
                    intent.putExtra("codigo",codigo.getText().toString());
                    startActivity(intent);
                }
            }
        });


        BtnTabla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Tabla.class);
                i.putExtra("tokens", tokens);
                startActivity(i);
            }
        });

        BtnTablaS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String g[]=seguimientoAutomata.split("%");
                String rec[] = new String[g.length];
                String estado[] = new String[g.length];
                for(int i=0;i<g.length;i++){
                 String a[]=g[i].split(" ");
                   rec[i]=a[0];
                   // estado[i]=a[1];
                 //   System.out.print(a[i].toString());
                   System.out.print(rec[i].toString());
                }

                Intent i=new Intent(MainActivity.this,TablaS.class);
                i.putExtra("seguimiento", g);
                i.putExtra("imagen", rec);
                startActivity(i);

            }
        });

        BtnSeguimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = getLayoutInflater();
                View mView1 = inflater.inflate(R.layout.activity_seguimiento, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(mView1).setTitle("Seguimiento").setMessage(seguimientoAutomata+"\n"+seguimientoGramatica)
                        .setCancelable(true)
                        .setNeutralButton("Aceptar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();

                                    }
                                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }


    private boolean esId(String p) {
        boolean resultado=false;
        if(identificador.esIdentificador(p)){
            agregarToken("identificador",p,linea+"",identificador.transiciones);
            resultado=true;
            return resultado;
        }
        if(numero.esNumero(p)){
            agregarToken("numero",p,linea+"",numero.transiciones);
            resultado=true;
            return resultado;
        }
        if(literal.esCadena(p)){
            agregarToken("cadena",p,linea+"",literal.transiciones);
            resultado=true;
            return resultado;
        }
        if(relacional.esRelacional(p)){
            agregarToken("op_relacional",p,linea+"",relacional.transiciones);
            resultado=true;
            return resultado;
        }
        return resultado;
    }

    public boolean esDelimitador(String p){
        /*los delimitadores son agrupacion, aritmeticos, logicos, ;, espacios*/
        boolean resultado=false;
        if(agrupacion.esAgupacion(p)){
            agregarToken("agrupacion",p,linea+"",agrupacion.transiciones);
            resultado=true;
            return resultado;
        }
        if(aritmeticos.esAritmetico(p)){
            agregarToken("aritmetico",p,linea+"",aritmeticos.transiciones);
            resultado=true;
            return resultado;
        }
        if(logico.esLogico(p)){
            agregarToken("op_logico",p,linea+"",logico.transiciones);
            resultado=true;
            return resultado;
        }
        if(p.equals(";")){
            agregarToken("fin_linea",p,linea+"","q0,;->q1");
            resultado=true;
            return resultado;
        }
        if(p.equals(" ")){
            resultado=true;
        }
        if(p.equals("\n")){
            resultado=true;
            linea++;
        }
        if(p.equals("#")){
            resultado=true;
            comentario=true;
        }
        return resultado;
    }//esDelimitador

    public boolean esPalabra(String p) {
        /*las palabras son carro, datos, garra, palabras, valor booleano*/
        boolean resultado=false;

        if(carro.esCarro(p)){
            agregarToken("carro",p,linea+"",carro.transiciones);
            resultado=true;
            return resultado;
        }
        if(datos.esDato(p)){
            agregarToken("tipo_dato",p,linea+"",datos.transiciones);
            resultado=true;
            return resultado;
        }
        if(garra.esGarra(p)){
            agregarToken("garra",p,linea+"",garra.transiciones);
            resultado=true;
            return resultado;
        }
        if(palabras.esPalabra(p)){
            agregarToken("palabra_reservada",p,linea+"",palabras.transiciones);
            resultado=true;
            return resultado;
        }
        if(val_boolean.esBooleano(p)){
            agregarToken("val_booleano",p,linea+"",val_boolean.transiciones);
            resultado=true;
            return resultado;
        }
        return resultado;
    }




    public void agregarToken(String t,String c, String l,String p){
        Token tt = new Token(t,c,l,p);
        Token[] temp=new Token[tokens.length+1];
        for(int i=0;i<tokens.length;i++){
            temp[i]=tokens[i];
        }
        temp[tokens.length]=tt;
        tokens=temp;
    }//agregarToken
}//clase
