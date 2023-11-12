package com.brian.gestionproyectosmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.brian.gestionproyectosmoviles.model.Persona;
import com.brian.gestionproyectosmoviles.adapter.PersonaAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    PersonaAdapter mAdapter;
    FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirestore = FirebaseFirestore.getInstance();
        mRecycler= findViewById(R.id.recyclerViewSingle);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("persona");

        FirestoreRecyclerOptions<Persona> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Persona>().setQuery(query, Persona.class).build();
        mAdapter = new PersonaAdapter(firestoreRecyclerOptions, this);

        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);




    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}

