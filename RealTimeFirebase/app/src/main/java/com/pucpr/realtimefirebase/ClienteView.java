package com.pucpr.realtimefirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClienteView extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    LinearLayout Cliente1;
    TextView Nome1;
    TextView Email1;
    TextView End1;
    TextView Cpf1;


    LinearLayout Cliente2;
    TextView Nome2;
    TextView Email2;
    TextView End2;
    TextView Cpf2;


    LinearLayout Cliente3;
    TextView Nome3;
    TextView Email3;
    TextView End3;
    TextView Cpf3;

    String key1;
    String key2;
    String key3;

    private ArrayList<model> list;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_view);

        Cliente1 = findViewById(R.id.linearLayout);
        Nome1 = findViewById(R.id.nome_text);
        Email1= findViewById(R.id.email_text);
        End1 = findViewById(R.id.end_text);
        Cpf1 = findViewById(R.id.cpf_text);

        Cliente2 = findViewById(R.id.linearLayout2);
        Nome2 = findViewById(R.id.nome_text2);
        Email2= findViewById(R.id.email_text2);
        End2 = findViewById(R.id.end_text2);
        Cpf2 = findViewById(R.id.cpf_text2);


        Cliente3 = findViewById(R.id.linearLayout3);
        Nome3 = findViewById(R.id.nome_text3);
        Email3= findViewById(R.id.email_text3);
        End3 = findViewById(R.id.end_text3);
        Cpf3 = findViewById(R.id.cpf_text3);


        Cliente1.setVisibility(View.GONE);
        Cliente2.setVisibility(View.GONE);
        Cliente3.setVisibility(View.GONE);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        DatabaseReference root = db.getReference().child(firebaseUser.getUid());

        DatabaseReference rootReference = firebaseDatabase.getReference().child(firebaseUser.getUid());
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(firebaseUser.getUid()).child("cliente");

        list = new ArrayList<>();

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    model m = dataSnapshot.getValue(model.class);

                    if(Nome1.getText().equals("Clientela")){

                        Nome1.setText(m.getNome());
                        Email1.setText(m.getEmail());
                        End1.setText(m.getEnd());
                        Cpf1.setText(m.getCpf());

                        key1 = dataSnapshot.getKey();

                        Cliente1.setVisibility(View.VISIBLE);

                        continue;
                    }



                    if(Nome2.getText().equals("Clientela")){

                        Nome2.setText(m.getNome());
                        Email2.setText(m.getEmail());
                        End2.setText(m.getEnd());
                        Cpf2.setText(m.getCpf());

                        key2 = dataSnapshot.getKey();

                        Cliente2.setVisibility(View.VISIBLE);

                        continue;
                    }

                    if(Nome3.getText().equals("Clientela")){

                        Nome3.setText(m.getNome());
                        Email3.setText(m.getEmail());
                        End3.setText(m.getEnd());
                        Cpf3.setText(m.getCpf());

                        key3 = dataSnapshot.getKey();

                        Cliente3.setVisibility(View.VISIBLE);

                        continue;

                    }




                    list.add(m);

                }

//                adapter.notifyDataSetChanged();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

    public void goToCliente1(View view) {
        Intent intent = new Intent(ClienteView.this, EditarCliente.class);
        intent.putExtra("Nome", Nome1.getText());
        intent.putExtra("Email", Email1.getText());
        intent.putExtra("End", End1.getText());
        intent.putExtra("Cpf", Cpf1.getText());
        intent.putExtra("Key", key1);

        startActivity(intent);
    }

    public void goToCliente2(View view) {
        Intent intent = new Intent(ClienteView.this, EditarCliente.class);
        intent.putExtra("Nome", Nome2.getText());
        intent.putExtra("Email", Email2.getText());
        intent.putExtra("End", End2.getText());
        intent.putExtra("Cpf", Cpf2.getText());
        intent.putExtra("Key", key2);

        startActivity(intent);
    }

    public void goToCliente3(View view) {
        Intent intent = new Intent(ClienteView.this, EditarCliente.class);
        intent.putExtra("Nome", Nome3.getText());
        intent.putExtra("Email", Email3.getText());
        intent.putExtra("End", End3.getText());
        intent.putExtra("Cpf", Cpf3.getText());
        intent.putExtra("Key", key3);

        startActivity(intent);
    }


    public void onBackPressed()
    {

        finish();

    }

}