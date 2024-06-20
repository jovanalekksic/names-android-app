package com.jovanaleksic.apiimena;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ArrayList<Stavka> stavke;
    Adapter adapter;
    RecyclerView recyclerView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugi_activity);
        recyclerView = findViewById(R.id.recyclerView);

        stavke = new ArrayList<>();
        stavke.add(new Stavka(R.drawable.ic_human,"Mark","California"));
        stavke.add(new Stavka(R.drawable.ic_human,"Brad", "New York"));
        stavke.add(new Stavka(R.drawable.ic_human,"George","Texas"));
        stavke.add(new Stavka(R.drawable.ic_human,"Angelina", "New York"));
        stavke.add(new Stavka(R.drawable.ic_human,"Thomas","California"));
        stavke.add(new Stavka(R.drawable.ic_human,"Selena", "Texas"));
        stavke.add(new Stavka(R.drawable.ic_human,"Justin","Maryland"));
        stavke.add(new Stavka(R.drawable.ic_human,"Kim", "New York"));
        stavke.add(new Stavka(R.drawable.ic_human,"Taylor","Oregon"));
        stavke.add(new Stavka(R.drawable.ic_human,"Ariana", "New York"));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new Adapter(stavke);

        recyclerView.setAdapter(adapter);

    }

}

