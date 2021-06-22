package com.pucpr.realtimefirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
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



public class EditarCliente extends AppCompatActivity {


    TextView NomeCli;
    TextView EmailCli;
    TextView EndCli;
    TextView CpfCli;
    FloatingActionButton FloatingClick;
    FloatingActionButton FloatingClick2;


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        NomeCli = findViewById(R.id.NomeCli);
        EmailCli = findViewById(R.id.EmailCli);
        EndCli = findViewById(R.id.EndCli);
        CpfCli = findViewById(R.id.CpfCli);
        FloatingClick = findViewById(R.id.floatingClick);
        FloatingClick2 = findViewById(R.id.floatingClick1);


        Intent intent = getIntent();
        if (null != intent) {
            String Nome = intent.getStringExtra("Nome");
            String Email = intent.getStringExtra("Email");
            String End = intent.getStringExtra("End");
            String Cpf = intent.getStringExtra("Cpf");
            key =  intent.getStringExtra("Key");

            NomeCli.setText(Nome);
            EmailCli.setText(Email);
            EndCli.setText(End);
            CpfCli.setText(Cpf);

        }


        FloatingClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editarCliente(NomeCli.getText().toString(), EmailCli.getText().toString(), EndCli.getText().toString(), Integer.parseInt(CpfCli.getText().toString()));

                EditarCliente.super.onBackPressed();


                Toast.makeText(EditarCliente.this, "Cliente editado", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EditarCliente.this, ClienteView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }

        });


        FloatingClick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deletarCliente();

                EditarCliente.super.onBackPressed();


                Toast.makeText(EditarCliente.this, "Cliente deletado", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EditarCliente.this, ClienteView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }

        });





    }



    void editarCliente(String nome, String Email, String End, int Cpf){



        DatabaseReference rootReference = firebaseDatabase.getReference();



        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(firebaseUser.getUid()).child("cliente");



        Map<String, String> clienteMap = new HashMap<>();



        clienteMap.put("Nome", nome);
        clienteMap.put("Email", Email);
        clienteMap.put("End", End);
        clienteMap.put("Cpf", String.valueOf(Cpf));

        rootReference.child(firebaseUser.getUid()).child(key).setValue(clienteMap);

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

    void deletarCliente(){



        DatabaseReference rootReference = firebaseDatabase.getReference();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(firebaseUser.getUid()).child("cliente");





        rootReference.child(firebaseUser.getUid()).child(key).removeValue();



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
}

