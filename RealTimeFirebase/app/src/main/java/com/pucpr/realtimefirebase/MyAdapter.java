package com.pucpr.realtimefirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pucpr.realtimefirebase.model;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    ArrayList<model> mList;
    Context context;

    public MyAdapter(Context context, ArrayList<model> mList){

        this.mList = mList;
        this.context = context;

    }

//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
//        return new MyViewHolder(v);
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.cardview,
                        parent,false
                );

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        model model = mList.get(position);
        holder.Nome.setText(model.getNome());
        holder.Email.setText(model.getEmail());
        holder.End.setText(model.getEnd());
        holder.Cpf.setText(model.getCpf());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Nome, Email, End, Cpf;

        public MyViewHolder(@NonNull View itemView){

            super(itemView);
            Nome = itemView.findViewById(R.id.nome_text);
            Email = itemView.findViewById(R.id.email_text);
            End = itemView.findViewById(R.id.end_text);
            Cpf = itemView.findViewById(R.id.cpf_text);



        }


    }

}
