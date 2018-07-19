package mx.edu.ittepic.compilador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TablaS extends AppCompatActivity {
ListView LvTabla;
Token tokens[];
String nomb[];
String recorrido[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);
        LvTabla = findViewById(R.id.LvTabla);
        nomb = (String[]) getIntent().getExtras().getSerializable("seguimiento");
        recorrido = (String[]) getIntent().getExtras().getSerializable("imagen");


        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        String mostrar="";
        for(int x=0;x<nomb.length;x++){
            mostrar=nomb[x];
            adapter.add(mostrar);
        }
        LvTabla.setAdapter(adapter);

    LvTabla.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(Tabla.this,position+"",Toast.LENGTH_LONG).show();
            Intent i=new Intent(TablaS.this,Automata2.class);
           // i.putExtra("tokens", nomb[position]);
            i.putExtra("rec", recorrido[position]);
            i.putExtra("nombre", nomb[position]);
            startActivity(i);
        }
    });
    }//onCreate

}//clase
