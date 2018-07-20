package mx.edu.ittepic.compilador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CodIntermedio extends AppCompatActivity {

    Button btn;
    EditText codigo1,codigo2;
    String cad1="";
    String acm="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cod_intermedio);
        btn=(Button)findViewById(R.id.btn);
        codigo1=(EditText)findViewById(R.id.codigo1);
        codigo2=(EditText)findViewById(R.id.codigo2);
        Intent intent = getIntent();
        String abc = intent.getStringExtra("codigo");
        codigo1.setText(abc);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluar();
            }
        });
        evaluar();
    }

    public void evaluar() {

        String cad2="#include <Servo.h>\n" +
                "Servo servo1;\n" +
                "Servo servo2;\n" +
                "Servo servo3;\n" +
                "Servo servo4;\n" +
                "\n" +
                "void setup() {\n" +
                "  servo1.attach(12,650,2400);\n" +
                "  servo2.attach(11,650,2400);\n" +
                "  servo3.attach(10,650,2400);\n" +
                "  servo4.attach(9,650,2400);\n" +
                "}";
        String[] cod=codigo1.getText().toString().split("\n");
        for (int i=0;i<cod.length;i++){
            cad1=cod[i];
            char w='#';
            int borrar=cad1.indexOf(w);

            if(borrar!=-1){
                String temp2=cad1.substring(0,borrar);
                cad1=temp2;

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

            if(c!=-1 && d!=-1){
                String te = cad1.substring(c+1 , d);
                String te2 = cad1.substring(c , d+1);

                //Toast.makeText(MainActivity.this,te2,Toast.LENGTH_SHORT).show();
                cad1=cad1.replaceAll("SI","if");
                cad1 = cad1.replaceAll("CICLO", "for(int i=0;i<" + te + ";i++)");
                cad1=cad1.replaceAll("GARRA.ARRIBA","void subir(int "+te+"){\n" +
                        "  servo2.write(120);\n" +
                        "  delay(2000);\n" +
                        "  }");
                cad1=cad1.replaceAll("GARRA.ABAJO","void bajar(){\n" +
                        "  servo2.write(70);\n" +
                        "  delay(2000);\n" +
                        "  }");
                if(prueba==-1){
                    cad1=cad1.replace(te2,"");
                }

            }
            cad1=cad1.replaceAll("RUTA","void funcionruta()");


            acm+="\n"+cad1;
        }//for
        cad2+="\n"+acm+"\n";
        cad2+="\nvoid loop() {\n" +

                "}";

        codigo2.setText(cad2);
        acm="";
        cad2="";
    }
}
