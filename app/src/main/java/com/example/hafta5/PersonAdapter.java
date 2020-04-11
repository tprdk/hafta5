package com.example.hafta5;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.personViewHolder> {

    private ArrayList<Person> personList;

    public static class personViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView_person;
        public TextView textView_username;
        public TextView textView_password;

        public personViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_person = itemView.findViewById(R.id.imageView_person_image);
            textView_username = itemView.findViewById(R.id.textView_card_username);
            textView_password = itemView.findViewById(R.id.textView_card_password);
        }
    }

    public PersonAdapter(ArrayList<Person> personList){
        this.personList = personList;
    }

    @NonNull
    @Override
    public personViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_person_cardview, parent, false);
        personViewHolder pvh = new personViewHolder(view);
        return pvh;

    }

    @Override
    public void onBindViewHolder(@NonNull personViewHolder holder, int position) {

        Person currentPerson = personList.get(position);

        holder.imageView_person.setImageResource(currentPerson.getPicture());
        holder.textView_username.setText(currentPerson.getUsername());
        holder.textView_password.setText(currentPerson.getPassword());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
}
