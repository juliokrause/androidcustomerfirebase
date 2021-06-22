package com.pucpr.realtimefirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

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



public class CriarCliente extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;



    TextView NomeCli;
    TextView EmailCli;
    TextView EndCli;
    TextView CpfCli;
    FloatingActionButton FloatingClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_cliente);




        NomeCli = findViewById(R.id.NomeCli);
        EmailCli = findViewById(R.id.EmailCli);
        EndCli = findViewById(R.id.EndCli);
        CpfCli = findViewById(R.id.CpfCli);
        FloatingClick = findViewById(R.id.floatingClick1);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        FloatingClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                criarCliente(NomeCli.getText().toString(), EmailCli.getText().toString(), EndCli.getText().toString(), Integer.parseInt(CpfCli.getText().toString()));

                CriarCliente.super.onBackPressed();
                Toast.makeText(CriarCliente.this, "Cliente Criado", Toast.LENGTH_SHORT).show();

            }

        });




    }


    void criarCliente(String nome, String Email, String End, int Cpf){



     DatabaseReference rootReference = firebaseDatabase.getReference();



     Query queries;

     queries = FirebaseDatabase.getInstance().getReference(firebaseUser.getUid()).orderByChild("nClientes");

     Query Query = rootReference.child(firebaseUser.getUid());



     DatabaseReference ref = FirebaseDatabase.getInstance().getReference(firebaseUser.getUid()).child("cliente");

     //rootReference.child(firebaseUser.getUid()).child("nClientes").setValue(4);

        Map<String, String> clienteMap = new HashMap<>();
        //rootReference.child(firebaseUser.getUid()).child("Hashmap").setValue(myMap);


    clienteMap.put("Nome", nome);
    clienteMap.put("Email", Email);
    clienteMap.put("End", End);
    clienteMap.put("Cpf", String.valueOf(Cpf));

    rootReference.child(firebaseUser.getUid()).push().setValue(clienteMap);

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

    void addNumber(String value){


        DatabaseReference rootReference = firebaseDatabase.getReference();

        Map<String, String> myMap = new HashMap<>();

        rootReference.child(firebaseUser.getUid()).child("Hashmap").setValue(myMap);
        rootReference.child("myNumber").child("Teste").push().setValue(2);


    }







}