package mx.edu.ittepic.compilador;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Automata extends AppCompatActivity {
Token token;
ImageView imagen;
TextView producciones;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automata);
        token = (Token) getIntent().getExtras().getSerializable("tokens");
        imagen = findViewById(R.id.imagen);
        producciones = findViewById(R.id.producciones);
        producciones.setText(token.producciones);
        colocarImagen();
    }
    public void colocarImagen(){

        if(token.token.equals("carro")){
            imagen.setImageResource(R.drawable.carro);
        }
        if(token.token.equals("valor booleano")){
            imagen.setImageResource(R.drawable.booleano);
        }
        if(token.token.equals("palabra")){
            imagen.setImageResource(R.drawable.palabras);
        }
        if(token.token.equals("garra")){
            imagen.setImageResource(R.drawable.gizquierda);
        }


    }
}
