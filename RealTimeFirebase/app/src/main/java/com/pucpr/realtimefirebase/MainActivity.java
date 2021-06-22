package com.pucpr.realtimefirebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

//Olá professor, eu acabei fazendo todas as funcionalidades nesta mesma tela utilizando setVisibility para aparecer os elementos conforme necessário

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    TextView textView;
    EditText editTextTextPersonName;
    EditText editTextTextPassword;
    EditText editTextTextPersonName2;
    EditText editTextTextPassword2;
    EditText editTextNumb;

    Button FirebaseRegi;
    Button FirebaseLoginB;
    Button Firebaseadd;
    Button firabaseRegiInt;
    Button CriarCliente;
    Button VerClientes;
    Button CriarLoja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        FirebaseLoginB = findViewById(R.id.Firebaselogin);
        Firebaseadd = findViewById(R.id.firebaseadd);
        FirebaseRegi = findViewById(R.id.firebaseregi);
        editTextNumb = findViewById(R.id.editTextNum);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        CriarCliente = findViewById(R.id.CriarCliente);
        VerClientes = findViewById(R.id.VerClientes);
        CriarLoja = findViewById(R.id.menuLojas);

        CriarCliente.setVisibility(View.GONE);
        VerClientes.setVisibility(View.GONE);
        CriarLoja.setVisibility(View.GONE);



        firabaseRegiInt = findViewById(R.id.firabaseRegiInt);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();




        editTextNumb.setVisibility(View.GONE);
        Firebaseadd.setVisibility(View.GONE);

        FirebaseLoginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                loginClick();

            }

        });


        Firebaseadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                addNumber(editTextNumb.getText().toString());


            }

        });

        FirebaseRegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                register();

            }

        });




    }

    public void goToCadastro(View view) {
        Intent intent = new Intent(MainActivity.this, cadastro.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void goToLojas(View view) {
        Intent intent = new Intent(MainActivity.this, CriarLoja.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void goToCliente(View view) {
        Intent intent = new Intent(MainActivity.this, CriarCliente.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void goToVClient(View view) {
        Intent intent = new Intent(MainActivity.this, ClienteView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    void databaseTesting(){
        DatabaseReference rootReference = firebaseDatabase.getReference();
//        rootReference.child("String").setValue("Uma string");
//        rootReference.child("Boolean").setValue(true);
//        rootReference.child("Int").setValue(4);
//        rootReference.child("double").setValue(3.3);
//        firebaseDatabase.getReference("mark").setValue("Joselli");

//        Map<String, String> myMap = new HashMap<>();
//
//        rootReference.child(firebaseUser.getUid()).setValue(
//                new UserData("Julio", editTextTextPersonName.getText().toString(),
//                        "2021-05-17")
//
//        );





    }


    void addNumber(String value){

        DatabaseReference rootReference = firebaseDatabase.getReference();

        Map<String, String> myMap = new HashMap<>();

        rootReference.child(firebaseUser.getUid()).child("Hashmap").setValue(myMap);
        rootReference.child("myNumber").push().setValue(value);


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

    @Override
    protected void onStart() {
        super.onStart();




    }



    void loginClick(){



        if(firebaseUser != null){

            textView.setText("Usuário logado:"+firebaseUser.getUid());
            FirebaseLoginB.setVisibility(View.GONE);
            editTextTextPersonName.setVisibility(View.GONE);
            editTextTextPersonName2.setVisibility(View.GONE);
            editTextTextPassword.setVisibility(View.GONE);
            editTextTextPassword2.setVisibility(View.GONE);
            FirebaseRegi.setVisibility(View.GONE);
            firabaseRegiInt.setVisibility(View.GONE);
            CriarCliente.setVisibility(View.VISIBLE);
            VerClientes.setVisibility(View.VISIBLE);
            CriarLoja.setVisibility(View.VISIBLE);


            editTextNumb.setVisibility(View.VISIBLE);
            Firebaseadd.setVisibility(View.VISIBLE);

            //databaseTesting();
        }else{
            signInUser(editTextTextPersonName.getText().toString(),editTextTextPassword.getText().toString());

            FirebaseLoginB.setVisibility(View.GONE);
            editTextTextPersonName.setVisibility(View.GONE);
            editTextTextPersonName2.setVisibility(View.GONE);
            editTextTextPassword.setVisibility(View.GONE);
            editTextTextPassword2.setVisibility(View.GONE);
            FirebaseRegi.setVisibility(View.GONE);
            firabaseRegiInt.setVisibility(View.GONE);
            CriarCliente.setVisibility(View.VISIBLE);
            VerClientes.setVisibility(View.VISIBLE);
            CriarLoja.setVisibility(View.VISIBLE);
            editTextNumb.setVisibility(View.VISIBLE);
            Firebaseadd.setVisibility(View.VISIBLE);



        }

    //     databaseListeningTesting();




    }

    void register(){

        createUser(editTextTextPersonName2.getText().toString(), editTextTextPassword2.getText().toString());


    }

    void signInUser(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(MainActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    firebaseUser = firebaseAuth.getCurrentUser();
                                    if(firebaseUser != null){
                                        textView.setText("User SignIn:"+firebaseUser.getUid());
                                    }
                                }else{
                                    textView.setText("Usuário não logou");
                                    Log.e("FIREBASEAUTH","SignIn Error"
                                            +task.getException().toString());
                                }
                            }
                        });
    }

    void createUser(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this,
                        new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            textView.setText("User Created");
                        }else{
                            textView.setText("User not Created");
                            Log.e("FIREBASEAUTH","Create Error"
                                    +task.getException().toString());
                        }
                    }
                });
    }

   @Override
   public void onBackPressed() {

        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(getIntent());

   }


}