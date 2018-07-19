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

        if(token.token.equals("agrupacion")){
            imagen.setImageResource(R.drawable.agrupacion);
        }
        if(token.token.equals("aritmetico")){
            imagen.setImageResource(R.drawable.aritmetico);
        }
        if(token.token.equals("carro")){
            imagen.setImageResource(R.drawable.carro);
        }
        if(token.token.equals("tipo_dato")){
            imagen.setImageResource(R.drawable.tipo_dato);
        }
        if(token.token.equals("garra")){
            imagen.setImageResource(R.drawable.garra);
        }
        if(token.token.equals("identificador")){
            imagen.setImageResource(R.drawable.identificador);
        }
        if(token.token.equals("cadena")){
            imagen.setImageResource(R.drawable.cadena);
        }
        if(token.token.equals("op_logico")){
            imagen.setImageResource(R.drawable.op_logico);
        }
        if(token.token.equals("numero")){
            imagen.setImageResource(R.drawable.numero);
        }
        if(token.token.equals("palabra_reservada")){
            imagen.setImageResource(R.drawable.palabra_reservada);
        }
        if(token.token.equals("op_relacional")){
            imagen.setImageResource(R.drawable.op_relacional);
        }
        if(token.token.equals("val_booleano")){
            imagen.setImageResource(R.drawable.val_booleano);
        }
        if(token.token.equals("fin_linea")){
            imagen.setImageResource(R.drawable.fin_linea);
        }
    }
}
