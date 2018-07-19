package mx.edu.ittepic.compilador;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Automata2 extends AppCompatActivity {
   // Token token;
    String a,b;
    ImageView imagen;
    TextView producciones;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automata);
       // token = (Token) getIntent().getExtras().getSerializable("tokens");
        a= (String) getIntent().getExtras().getSerializable("rec");
        a=a.trim();
        b= (String) getIntent().getExtras().getSerializable("nombre");
        imagen = findViewById(R.id.imagen);
        producciones = findViewById(R.id.producciones);
        producciones.setText(b);
        colocarImagen();
    }
    public void colocarImagen(){

        if(a.equals("GARRA.IZQUIERDA")){
            imagen.setImageResource(R.drawable.gizquierda);
        }
        if(a.equals("GARRA.DERECHA")){
            imagen.setImageResource(R.drawable.gderecha);
        }
        if(a.equals("GARRA.ARRIBA")){
            imagen.setImageResource(R.drawable.garriba);
        }
        if(a.equals("GARRA.ABAJO")){
            imagen.setImageResource(R.drawable.gabajo);
        }
        if(a.equals("GARRA.ABRIR")){
            imagen.setImageResource(R.drawable.gabrir);
        }
        if(a.equals("GARRA.CERRAR")){
            imagen.setImageResource(R.drawable.gcerrar);
        }
        if(a.equals("CARRO.IZQUIERDA")){
            imagen.setImageResource(R.drawable.cizquierda);
        }
        if(a.equals("CARRO.DERECHA")){
            imagen.setImageResource(R.drawable.cderecha);
        }
        if(a.equals("CARRO.ATRAS")){
            imagen.setImageResource(R.drawable.catras);
        }
        if(a.equals("CARRO.ADELANTE")){
            imagen.setImageResource(R.drawable.cadelante);
        }
        if(a.equals("CARRO.ENCENDER")){
            imagen.setImageResource(R.drawable.cencender);
        }
        if(a.equals("CARRO.APAGAR")){
            imagen.setImageResource(R.drawable.capagar);
        }
        if(a.equals("RUTA")){
            imagen.setImageResource(R.drawable.ruta);
        }
        if(a.equals("INICIO")){
            imagen.setImageResource(R.drawable.inicio);
        }
        if(a.equals("FUNCION")){
            imagen.setImageResource(R.drawable.funcionn);
        }
        if(a.equals("CICLO")){
            imagen.setImageResource(R.drawable.ciclo);
        }

    }
}
