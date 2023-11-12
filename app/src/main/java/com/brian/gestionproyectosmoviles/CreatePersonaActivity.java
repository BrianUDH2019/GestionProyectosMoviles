package com.brian.gestionproyectosmoviles;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class CreatePersonaActivity extends AppCompatActivity {


    Button btn_add;
    EditText name, code, percentage;

    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_persona);

        mfirestore = FirebaseFirestore.getInstance();

        name = findViewById(R.id.nombre);
        code = findViewById(R.id.codigo);
        percentage = findViewById(R.id.porcentaje);
        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namepersona = name.getText().toString().trim();
                String codepersona = code.getText().toString().trim();
                String percentagepersona = percentage.getText().toString().trim();

                if(namepersona.isEmpty() && codepersona.isEmpty() && percentagepersona.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else{
                    postPersona(namepersona, codepersona, percentagepersona);
                }
            }
        });


    }

    private void postPersona(String namepersona, String codepersona, String percentagepersona) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", namepersona);
        map.put("code", codepersona);
        map.put("percentage", percentagepersona);
        mfirestore.collection("persona").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
