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

import java.io.File;
import java.io.OutputStreamWriter;

public class CodIntermedio extends AppCompatActivity {

    Button btn;
    EditText codigo1,codigo2,codigo3;
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
        nombrearchivo=new EditText(this);
        Intent intent = getIntent();
        String abc = intent.getStringExtra("codigo");
        codigo1.setText(abc);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inter();
                evaluar();
                //guardar();
            }
        });
        //evaluar();
    }

/*
    public void optimizar(){
        String[] vec=codigo1.getText().toString().split("\n");
        for(int i=0;i<vec.length;i++){
            if(vec[i].equals()){

            }
        }
        if(cad[i]==cad[i+1])
    }
*/


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
    codigo3.setText(acm);
    acm2=acm2.replace(acm+"",""+tmp2);



    codigo2.setText(acm2);

}


public void optimizado(){
    char a='(';
    String[] vec=codigo2.getText().toString().split("\n");
    String[] v1,v2;
    for (int i=0;i<vec.length;i++){
        if(i==vec.length-1){
            v2=vec[i+1].split(""+a);
        }
        v1=vec[i].split(""+a);
        v2=vec[i+1].split(""+a);
        if(v1[0].equals(v2[0])){

        }

    }
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
        String[] cod=codigo2
                .getText().toString().split("\n");
        for (int i=0;i<cod.length;i++){
            cad1=cod[i];
            char w='#';
            int borrar=cad1.indexOf(w);

            if(borrar!=-1){
                String temp2=cad1.substring(0,borrar);
                cad1=temp2;

            }

            //empieza

            //termina
            if(cad1.indexOf("RUTA")!=-1) {
                cad1="";
                for(int k=i;k<cod.length;k++){
                    if(cod[k].indexOf('}')!=-1){
                        cod[k]=cod[k].replace("}","");
                        break;
                    }
                }


            }
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
            if(cad1.indexOf("INICIO")!=-1) {
                cad1="";
                for(int k=i;k<cod.length;k++){
                    if(cod[k].indexOf('}')!=-1){
                        cod[k]=cod[k].replace("}","");
                        break;
                    }
                }


            }

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
        final String[] miarchivo = {""};

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(CodIntermedio.this);
        builder.setView(nombrearchivo).setTitle("Guardar Como").setMessage("Nombre Archivo")
                .setCancelable(true)
                .setNeutralButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                miarchivo[0] =nombrearchivo.getText().toString();
                                try
                                {
                                    OutputStreamWriter fout=
                                            new OutputStreamWriter(
                                                    openFileOutput(miarchivo[0]+".ino", Context.MODE_PRIVATE));

                                    fout.write(codigo3.getText().toString());
                                    fout.close();
                                }
                                catch (Exception ex)
                                {
                                    Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
                                }
                                dialog.cancel();

                            }
                        });

        android.app.AlertDialog alert = builder.create();
        alert.show();




    }
}
