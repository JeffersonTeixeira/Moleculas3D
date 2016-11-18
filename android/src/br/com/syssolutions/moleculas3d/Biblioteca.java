package br.com.syssolutions.moleculas3d;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Biblioteca extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_biblioteca);

        String menu[] = new String[]{
                "Água",
                "Álcoois ->",
                "Aldeídos ->",
                "Alcanos ->",
                "Alcenos ->",
                "Alcinos ->",
                "Amídos ->",
                "Amíd@s ->",
                "Amino ácidos ->",
                "Aromáticos ->",
                "Ureia ->",
                "Carboidratos ->",
                "Ácidos Carboxílicos ->",
                "COORDINATIONS ->",
                "Cofatores",
                "Cicloalcano ->",
                "CÍCLICA AÇUCAR ->",
                "DNA ->",
                "Éteres ->",
                "Ácidos Graxos ->",
                "HETEROMATICS ->",
                "Cetonas ->",
                "Macrociclos ->",
                "Nucleobases ->",
                "Esteróides ->",
                "Sulfóxidos ->",
                "Tióis ->"


        };


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu);

        setListAdapter(arrayAdapter);

    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Object o = this.getListAdapter().getItem(position);

        String caminhoArq = null;
        String dirMain = "mol";


        switch (position) {
            case 0:
                caminhoArq = dirMain + "water.cml";

                getIntent().putExtra("diretorio", caminhoArq);
                setResult(RESULT_OK, getIntent());

                //CarregaMolecula.carregarMolecula()

                finish();
                break;

        }


    }
}