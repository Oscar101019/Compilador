package mx.edu.ittepic.compilador;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CodIntermedio extends AppCompatActivity {
    /*
INICIO a{
CICLO(3){
CARRO.ADELANTE(4);#ESTO SE REPIRE 3X4
}
CICLO(2){
CARRO.ATRAS(3);#ESTO SE REPITE 2X3
}
CICLO(4){
CARRO.IZQUIERDA(3);#ESTO SE REPITE 4X3
}
CICLO(5){
CARRO.DERECHA(3);#5X3
}
}*/
    Button btn;
    EditText codigo1,codigo2,codigo3,codigo4;
    String cad1="";
    String acm="";
    EditText nombrearchivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cod_intermedio);
        btn=(Button)findViewById(R.id.btn);
        codigo1=(EditText)findViewById(R.id.codigo1);
        codigo2=(EditText)findViewById(R.id.codigo2);
        codigo3=(EditText)findViewById(R.id.codigo3);
        codigo4=(EditText)findViewById(R.id.codigo4);
        nombrearchivo=new EditText(this);
        Intent intent = getIntent();
        String abc = intent.getStringExtra("codigo");
        codigo1.setText(abc);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //evaluar();
        inter();
        eliminarcosasquenosirven();
        evaluar();
        guardar();

    }
    public void inter(){
        String[] vect=codigo1.getText().toString().split("\n");
        String cad="";
        String acm="";
        String acm2="";
        String tmp="",tmp2="";
        char a='}';
        char b='{';
        char c='(';
        char d=')';
        int e,f,termina = 0;
        String[] abc;
        String bcd="";
        int letranumero=0;
        for(int i=0;i<vect.length;i++){
            cad=vect[i];
            if(cad.indexOf("CICLO")!=-1){
                e=cad.indexOf(c);
                f=cad.indexOf(d);
                String numero=cad.substring(e + 1, f);
                try {
                    termina = Integer.parseInt(numero);

                }catch (NumberFormatException e1){
                    for(int l=i-1;l>=0;l--){
                        if(vect[l].indexOf(numero)!=-1){
                            abc=vect[l].split("=");
                            bcd=abc[1];
                            bcd=bcd.replaceAll(";","");
                            letranumero=Integer.parseInt(bcd);
                        }
                    }
                    termina=letranumero;
                }
                for (int j=i;j<vect.length;j++){
                    if(vect[j].indexOf(a)!=-1){
                        acm+=vect[j];
                        break;
                    }
                    acm+=vect[j]+"\n";

                }

                int ini=acm.indexOf(b);
                int fin=acm.indexOf(a);
                if(ini!=-1 && fin!=-1){
                    tmp=acm.substring(ini+2,fin-1);
                }
                if(termina!=0){
                    for(int k=0;k<termina;k++){
                        if(k==termina-1){
                            tmp2+=tmp;
                        }else{
                            tmp2+=tmp+"\n";
                        }

                    }//for para repetir la cadena
                }//if verificar que las veces que se repitira son >0

            }//if ciclo



        }//for recorrer la cad

        acm2=codigo1.getText().toString();
        //codigo3.setText(acm);
        acm2=acm2.replace(acm+"",""+tmp2);



        codigo2.setText(acm2);

    }


    private void eliminarcosasquenosirven() {
        String ac="",cad11="";

        String[] cod=codigo2
                .getText().toString().split("\n");
        for (int i=0;i<cod.length;i++) {
            cad11 = cod[i];
            char w = '#';
            int borrar = cad11.indexOf(w);

            if (borrar != -1) {
                String temp2 = cad11.substring(0, borrar);
                cad11 = temp2;

            }
            try {
                if(cod[i].indexOf("ENTERO")!=-1){
                    if(cod[i+1].indexOf("ENTERO")!=-1){
                        if(cod[i].equals(cod[i+1])){
                            cod[i+1]="";
                        }

                    }
                }

                if(cod[i].indexOf("CADENA")!=-1){
                    if(cod[i+1].indexOf("CADENA")!=-1){
                        if(cod[i].equals(cod[i+1])){
                            cod[i+1]="";
                        }

                    }
                }
                if(cod[i].indexOf("BOOLEANO")!=-1){
                    if(cod[i+1].indexOf("BOOLEANO")!=-1){
                        if(cod[i].equals(cod[i+1])){
                            cod[i+1]="";
                        }

                    }
                }




            }catch (ArrayIndexOutOfBoundsException e){

            }

            if(cad11.indexOf("CARRO.ADELANTE")!=-1){
                if(cod[i+1].indexOf("CARRO.ADELANTE")!=-1){
                    char a='(';
                    char b=')';
                    int c=cod[i].indexOf(a);
                    int d=cod[i].indexOf(b);
                    int c2=cod[i+1].indexOf(a);
                    int d2=cod[i+1].indexOf(b);
                    String te = cod[i].substring(c+1 , d);
                    String act = cod[i].substring(c , d+1);

                    String te2 = cod[i+1].substring(c2+1 , d2);
                    String sig = cod[i+1].substring(c2 , d2+1);
                    int aux=Integer.parseInt(te);
                    int aux2=Integer.parseInt(te2);
                    int suma=aux+aux2;
                    if(i==cod.length-1){
                        cod[i]=cod[i].replace(aux+"",suma+"");
                    }else {
                        cod[i+1]=cod[i+1].replace(sig,'('+""+suma+""+')'+"");
                    }
                    cad11="";
                }
            }

            if(cad11.indexOf("CARRO.ATRAS")!=-1){
                if(cod[i+1].indexOf("CARRO.ATRAS")!=-1){
                    char a='(';
                    char b=')';
                    int c=cod[i].indexOf(a);
                    int d=cod[i].indexOf(b);
                    int c2=cod[i+1].indexOf(a);
                    int d2=cod[i+1].indexOf(b);
                    String te = cod[i].substring(c+1 , d);
                    String act = cod[i].substring(c , d+1);

                    String te2 = cod[i+1].substring(c2+1 , d2);
                    String sig = cod[i+1].substring(c2 , d2+1);
                    int aux=Integer.parseInt(te);
                    int aux2=Integer.parseInt(te2);
                    int suma=aux+aux2;
                    if(i==cod.length-1){
                        cod[i]=cod[i].replace(aux+"",suma+"");
                    }else {
                        cod[i+1]=cod[i+1].replace(sig,'('+""+suma+""+')'+"");
                    }
                    cad11="";
                }
            }

            if(cad11.indexOf("CARRO.DERECHA")!=-1){
                if(cod[i+1].indexOf("CARRO.DERECHA")!=-1){
                    char a='(';
                    char b=')';
                    int c=cod[i].indexOf(a);
                    int d=cod[i].indexOf(b);
                    int c2=cod[i+1].indexOf(a);
                    int d2=cod[i+1].indexOf(b);
                    String te = cod[i].substring(c+1 , d);
                    String act = cod[i].substring(c , d+1);

                    String te2 = cod[i+1].substring(c2+1 , d2);
                    String sig = cod[i+1].substring(c2 , d2+1);
                    int aux=Integer.parseInt(te);
                    int aux2=Integer.parseInt(te2);
                    int suma=aux+aux2;
                    if(i==cod.length-1){
                        cod[i]=cod[i].replace(aux+"",suma+"");
                    }else {
                        cod[i+1]=cod[i+1].replace(sig,'('+""+suma+""+')'+"");
                    }
                    cad11="";
                }
            }

            if(cad11.indexOf("CARRO.IZQUIERDA")!=-1){
                if(cod[i+1].indexOf("CARRO.IZQUIERDA")!=-1){
                    char a='(';
                    char b=')';
                    int c=cod[i].indexOf(a);
                    int d=cod[i].indexOf(b);
                    int c2=cod[i+1].indexOf(a);
                    int d2=cod[i+1].indexOf(b);
                    String te = cod[i].substring(c+1 , d);
                    String act = cod[i].substring(c , d+1);

                    String te2 = cod[i+1].substring(c2+1 , d2);
                    String sig = cod[i+1].substring(c2 , d2+1);
                    int aux=Integer.parseInt(te);
                    int aux2=Integer.parseInt(te2);
                    int suma=aux+aux2;
                    if(i==cod.length-1){
                        cod[i]=cod[i].replace(aux+"",suma+"");
                    }else {
                        cod[i+1]=cod[i+1].replace(sig,'('+""+suma+""+')'+"");
                    }
                    cad11="";
                }
            }

            if (cad11.indexOf("RUTA") != -1) {
                cad11 = "";
                for (int k = i; k < cod.length; k++) {
                    if (cod[k].indexOf('}') != -1) {
                        cod[k] = cod[k].replace("}", "");
                        break;
                    }
                }


            }
            if (cad11.indexOf("INICIO") != -1) {
                cad11 = "";
                for (int k = i; k < cod.length; k++) {
                    if (cod[k].indexOf('}') != -1) {
                        cod[k] = cod[k].replace("}", "");
                        break;
                    }
                }


            }
            if(i==cod.length-1 || cad11.isEmpty()){
                ac+=cad11;
            }else{
                ac+=cad11+"\n";
            }

        }

        codigo4.setText(ac);
    }











    public void evaluar() {

        String cad2="#include <Servo.h>\n" +
                "const int led = 13;\n" +
                "boolean encendido = false;\n" +
                "Servo servo1;\n" +
                "Servo servo2;\n" +
                "const int MotorAtrasDerecha =  8;\n" +
                "const int MotorAtrasIzquierda =  7;\n" +
                "const int MotorAdelanteDerecha =  6;\n" +
                "const int MotorAdelanteIzquierda =  5;\n" +
                "\n" +
                "void setup() {\n" +
                "  servo1.attach(12,650,2400);//abrir\n" +
                "  servo2.attach(11,650,2400);//arriba\n" +
                "  pinMode(MotorAtrasDerecha,OUTPUT);\n" +
                "  pinMode(MotorAtrasIzquierda,OUTPUT);\n" +
                "  pinMode(MotorAdelanteDerecha,OUTPUT);\n" +
                "  pinMode(MotorAdelanteIzquierda,OUTPUT);\n" +
                "  pinMode(led,OUTPUT);\n" ;
        String[] cod=codigo4
                .getText().toString().split("\n");
        for (int i=0;i<cod.length;i++){
            cad1=cod[i];

            /*
            INICIO prueba{
ENTERO a=1;
BOOLEANO b=VERDADERO;
CADENA c= "cadena holi";
RUTA{
CARRO.ENCENDER();
CARRO.ADELANTE(2);
CARRO.ATRAS(3);
GARRA.ABRIR(3);
GARRA.CERRAR(2);
CARRO.APAGAR();
}
CICLO(3){
  CARRO.ADELANTE(2);
  CARRO.ATRAS(2);
}
}*/


            cad1=cad1.replaceAll("SINO","else");
            cad1=cad1.replaceAll("ENTERO","int");
            cad1=cad1.replaceAll("CADENA","String");
            cad1=cad1.replaceAll("BOOLEANO","boolean");
            cad1=cad1.replaceAll("VERDADERO","true");
            cad1=cad1.replaceAll("FALSO","false");

            char a='(';
            char b=')';
            int c=cad1.indexOf(a);
            int d=cad1.indexOf(b);
            int prueba=cad1.indexOf("SI");
            int prueba2=cad1.indexOf("CARRO.ENCENDER");
            int prueba3=cad1.indexOf("CARRO.APAGAR");

            if(c!=-1 && d!=-1){
                String te = cad1.substring(c+1 , d);
                String te2 = cad1.substring(c , d+1);

                //Toast.makeText(MainActivity.this,te2,Toast.LENGTH_SHORT).show();
                cad1=cad1.replaceAll("SI","if");
                cad1 = cad1.replaceAll("CICLO", "for(int i=0;i<" + te + ";i++)");

                cad1 = cad1.replaceAll("CARRO.DRECHA", "carro_derecha( "+te+");");
                cad1 = cad1.replaceAll("CARRO.IZQUIERDA", "carro_izquierda( "+te+");");
                cad1 = cad1.replaceAll("CARRO.ATRAS", "carro_atras( "+te+");");
                cad1 = cad1.replaceAll("CARRO.ADELANTE", "carro_adelante( "+te+");");

                cad1=cad1.replaceAll("GARRA.ARRIBA","pinza_arriba( "+te+");");
                cad1=cad1.replaceAll("GARRA.ABAJO","pinza_abajo( "+te+");");
                cad1=cad1.replaceAll("GARRA.ABRIR","pinza_abrir( "+te+");");
                cad1=cad1.replaceAll("GARRA.CERRAR","pinza_cerrar( "+te+");");
                if(prueba==-1){
                    cad1=cad1.replace(te2,"");
                }

            }
            cad1 = cad1.replaceAll("CARRO.APAGAR"+a+b, "apagar();");
            cad1 = cad1.replaceAll("CARRO.ENCENDER"+a+b, "encender();");
            cad1=cad1.replaceAll("RUTA","funcionruta()");


            acm+="\n"+cad1;
        }//for
        cad2+="\n"+acm+"\n";
        cad2+= "}\n" +
                "\n" +
                "void loop() {\n" +
                "}\n" +
                "" +
                "\nvoid carro_adelante(int seg){\n" +
                "    digitalWrite(MotorAtrasDerecha, HIGH);\n" +
                "    digitalWrite(MotorAtrasIzquierda, HIGH);\n" +
                "    delay(seg*1000);\n" +
                "    digitalWrite(MotorAtrasDerecha, LOW);\n" +
                "    digitalWrite(MotorAtrasIzquierda, LOW);\n" +
                "}\n" +
                "\n" +
                "void carro_atras(int seg){\n" +
                "    digitalWrite(MotorAdelanteDerecha, HIGH);\n" +
                "    digitalWrite(MotorAdelanteIzquierda, HIGH);\n" +
                "    delay(seg*1000);\n" +
                "    digitalWrite(MotorAdelanteDerecha, LOW);\n" +
                "    digitalWrite(MotorAdelanteIzquierda, LOW);\n" +
                "}\n" +
                "\n" +
                "void carro_izquierda(int seg){\n" +
                "    digitalWrite(MotorAdelanteIzquierda, HIGH);\n" +
                "    delay(seg*1000);\n" +
                "    digitalWrite(MotorAdelanteIzquierda, LOW);\n" +
                "}\n" +
                "\n" +
                "void carro_derecha(int seg){\n" +
                "    digitalWrite(MotorAdelanteDerecha, HIGH);\n" +
                "    delay(seg*1000);\n" +
                "    digitalWrite(MotorAdelanteDerecha, LOW);\n" +
                "}\n" +
                "\n" +
                "//parametro de 0 a 3\n" +
                "void pinza_abrir(int grados){\n" +
                "  int g=180-(grados*30);\n" +
                "  if(g<90){g=90;}\n" +
                "  servo1.write(g);\n" +
                "  delay(1000);\n" +
                "  }\n" +
                "\n" +
                "\n" +
                "//parametro de 0 a 3\n" +
                "void pinza_cerrar(int grados){\n" +
                "  servo1.write(90+(grados*30));\n" +
                "  delay(1000);\n" +
                "  }\n" +
                "\n" +
                "//parametros de 0 a 3\n" +
                "void pinza_arriba(int grados){\n" +
                "  servo2.write(60+(grados*20));\n" +
                "  delay(1000);\n" +
                "  }\n" +
                "  \n" +
                "  //parametros de 0 a 3\n" +
                "void pinza_abajo(int grados){\n" +
                "  servo2.write(60-(grados*5));\n" +
                "  delay(1000);\n" +
                "  }\n" +
                "\n" +
                "void encender(){\n" +
                "  encendido=true;\n" +
                "  digitalWrite(led, HIGH);\n" +
                "  }\n" +
                "\n" +
                "void apagar(){\n" +
                "  encendido=false;\n" +
                "  digitalWrite(led, LOW);\n" +
                "  }";

        codigo3.setText(cad2);
        acm="";
        cad2="";
    }//evaluar
    public void guardar(){
        AlertDialog.Builder pregunta=new AlertDialog.Builder(CodIntermedio.this);

        pregunta.setTitle("Guardar Como").setMessage("Nombre del Archivo").setView(nombrearchivo).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String textonombre=nombrearchivo.getText().toString();
                if(textonombre.isEmpty()==false) {
                    //guardar
                    FileOutputStream flujo=null;
                    OutputStreamWriter escritor = null;
                    try
                    {
                        File ruta = Environment.getExternalStorageDirectory();
                        File fichero = new File(ruta.getAbsolutePath(), textonombre+".ino");
                        flujo =new FileOutputStream(fichero);
                        escritor=new OutputStreamWriter(flujo);
                        escritor.write(codigo3.getText().toString());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();

                    }
                    finally
                    {
                        try {
                            if(escritor!=null)
                                escritor.close();
                            Toast.makeText(CodIntermedio.this, "¡Archivo guardado correctamente!", Toast.LENGTH_SHORT).show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }else{
                    Toast.makeText(CodIntermedio.this,"no se pudo guardar el archivo",Toast.LENGTH_SHORT).show();
                }
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(CodIntermedio.this,"Se Cancelo el guardado",Toast.LENGTH_SHORT).show();

            }
        }).show();





    }
}
