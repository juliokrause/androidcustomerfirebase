package com.pucpr.realtimefirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;



public class cadastro extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;

    EditText editTextTextPersonName2;
    EditText editTextTextPassword2;
    EditText editTextNumb;
    TextView textView;
    Button FirebaseRegi;
    Button Firebaseadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Firebaseadd = findViewById(R.id.firebaseadd);
        FirebaseRegi = findViewById(R.id.firebaseregi);
        editTextNumb = findViewById(R.id.editTextNum);
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();



        FirebaseRegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                register();

            }

        });

        Firebaseadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                addNumber(editTextNumb.getText().toString());


            }

        });


    }

    void databaseListeningTesting(){
        ValueEventListener itensEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d : snapshot.getChildren()){
                    Log.d("FirebaseDatabase",
                            d.getKey() + ":"+d.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        DatabaseReference rootReference = firebaseDatabase.getReference();
        rootReference.child("itens").addValueEventListener(itensEventListener);

        ChildEventListener itensChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.v("FirebaseDatabase","onChildAdded"+
                        snapshot.getKey() +":"+snapshot.getValue());

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        rootReference.child("lista").addChildEventListener(itensChildEventListener);





    }




    void register(){

        createUser(editTextTextPersonName2.getText().toString(), editTextTextPassword2.getText().toString());


    }

    void createUser(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(cadastro.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(cadastro.this, "Usuário criado com sucesso", Toast.LENGTH_SHORT).show();
                                    cadastro.super.onBackPressed();
                                    Toast.makeText(cadastro.this, "Faça o login", Toast.LENGTH_SHORT).show();


                                }else{
                                    Toast.makeText(cadastro.this, "Houve um problema na criação de usuário", Toast.LENGTH_SHORT).show();

                                    Log.e("FIREBASEAUTH","Create Error"
                                            +task.getException().toString());
                                }
                            }
                        });
    }


    void addNumber(String value){

        DatabaseReference rootReference = firebaseDatabase.getReference();

        Map<String, String> myMap = new HashMap<>();

        rootReference.child(firebaseUser.getUid()).child("Hashmap").setValue(myMap);
        rootReference.child("myNumber").child("Teste").push().setValue(value);


    }


}