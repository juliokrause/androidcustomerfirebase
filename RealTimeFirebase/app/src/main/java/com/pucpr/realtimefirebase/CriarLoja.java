package com.pucpr.realtimefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;


public class CriarLoja extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;



    TextView NomeCli;
    TextView EndCli;
    FloatingActionButton FloatingClick;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_loja);




        NomeCli = findViewById(R.id.NomeCli);
        EndCli = findViewById(R.id.EndCli);
        FloatingClick = findViewById(R.id.floatingClick1);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        FloatingClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                criarLoja(NomeCli.getText().toString(), EndCli.getText().toString());

                CriarLoja.super.onBackPressed();
                Toast.makeText(CriarLoja.this, "Loja Criada", Toast.LENGTH_SHORT).show();

            }

        });










        }




    void criarLoja(String nome, String End){


        String Estoque = "0";

        DatabaseReference rootReference = firebaseDatabase.getReference();



        Query queries;

        queries = FirebaseDatabase.getInstance().getReference(firebaseUser.getUid()).orderByChild("nClientes");

        Query Query = rootReference.child(firebaseUser.getUid());



        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(firebaseUser.getUid()).child("cliente");

        //rootReference.child(firebaseUser.getUid()).child("nClientes").setValue(4);

        Map<String, String> clienteMap = new HashMap<>();
        //rootReference.child(firebaseUser.getUid()).child("Hashmap").setValue(myMap);


        clienteMap.put("Nome", nome);
        clienteMap.put("End", End);
        clienteMap.put("Est", Estoque);

        rootReference.child(firebaseUser.getUid()).child("loja").push().setValue(clienteMap);

//    rootReference.child(firebaseUser.getUid()).child("cliente").child("Nome").setValue(nome);
//     rootReference.child(firebaseUser.getUid()).child("cliente").child("Email").setValue(Email);
//     rootReference.child(firebaseUser.getUid()).child("cliente").child("End").setValue(End);
//     rootReference.child(firebaseUser.getUid()).child("cliente").child("Cpf").setValue(Cpf);


        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override


            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });




    }


    public void goToLojas(View view) {
        Intent intent = new Intent(CriarLoja.this, LojaView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}