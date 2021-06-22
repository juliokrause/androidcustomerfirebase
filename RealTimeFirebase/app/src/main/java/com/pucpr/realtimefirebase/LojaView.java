package com.pucpr.realtimefirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LojaView extends AppCompatActivity {


    FloatingActionButton floatingActionButton;
    FloatingActionButton floatingActionButton2;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    LinearLayout Cliente1;
    TextView Nome1;
    TextView Estoque1;
    TextView End1;
    String key1;

    public int EstoqueValor = 0;



    private FirebaseDatabase db = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja_view);

        Cliente1 = findViewById(R.id.linearLayout2);
        Nome1 = findViewById(R.id.nome_text2);
        End1 = findViewById(R.id.end_text2);
        Estoque1 = findViewById(R.id.estoque_text2);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton2 = findViewById(R.id.floatingActionButton2);


        Cliente1.setVisibility(View.GONE);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        DatabaseReference root = db.getReference().child(firebaseUser.getUid()).child("loja");


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    model m = dataSnapshot.getValue(model.class);

                    if(Nome1.getText().equals("Clientela")){

                        Nome1.setText(m.getNome());
                        End1.setText(m.getEnd());
                        Estoque1.setText(m.getEst());

                        Cliente1.setVisibility(View.VISIBLE);

                        key1 = dataSnapshot.getKey();

                    }



                }

//                adapter.notifyDataSetChanged();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AddEstoque();

            }
        });


        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                MinusEstoque();

            }
        });



    }

    public void AddEstoque(){

        DatabaseReference root = db.getReference().child(firebaseUser.getUid()).child("loja");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(firebaseUser.getUid()).child("loja");

        root.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    model m = dataSnapshot.getValue(model.class);




                    String estoque = m.getEst();

                    EstoqueValor = Integer.valueOf(estoque);

                    EstoqueValor++;


                    key1 = dataSnapshot.getKey();

                    Estoque1.setText(String.valueOf(EstoqueValor));






                }

//


            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

        ref.child(key1).child("Est").setValue(String.valueOf(EstoqueValor));

        return;

    }

    public void MinusEstoque(){

        DatabaseReference root = db.getReference().child(firebaseUser.getUid()).child("loja");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(firebaseUser.getUid()).child("loja");

        root.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    model m = dataSnapshot.getValue(model.class);




                    String estoque = m.getEst();

                    EstoqueValor = Integer.valueOf(estoque);

                    EstoqueValor--;


                    key1 = dataSnapshot.getKey();

                    Estoque1.setText(String.valueOf(EstoqueValor));






                }

//


            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

        ref.child(key1).child("Est").setValue(String.valueOf(EstoqueValor));

        return;

    }



}