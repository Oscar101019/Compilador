package mx.edu.ittepic.compilador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Tabla extends AppCompatActivity {
ListView LvTabla;
Token tokens[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);
        LvTabla = findViewById(R.id.LvTabla);
        tokens = (Token[]) getIntent().getExtras().getSerializable("tokens");

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        String mostrar="";
        for(int x=0;x<tokens.length;x++){
            mostrar="Token: "+tokens[x].token+"\nCadena: "+ tokens[x].cadena+
                    "\nLinea: "+tokens[x].linea+"\nProducciones: "+tokens[x].producciones;
            adapter.add(mostrar);
        }
        LvTabla.setAdapter(adapter);

    LvTabla.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(Tabla.this,position+"",Toast.LENGTH_LONG).show();
            Intent i=new Intent(Tabla.this,Automata.class);
            i.putExtra("tokens", tokens[position]);
            startActivity(i);
        }
    });
    }//onCreate

}//clase
