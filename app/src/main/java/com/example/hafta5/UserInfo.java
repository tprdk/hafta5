package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class UserInfo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        ArrayList<Person> personList = new ArrayList<Person>();

        personList.add( new Person("İbrahim", "Tıpırdık"
        , "tprdk@outlook.com", "tprdk", "qwe123"));

        personList.add( new Person("Ceylin" , "Tıpırdık"
        , "cylntprdk@outlook.com",  "cyln", "123qwe"));

        personList.add( new Person("İbrahim", "Tıpırdık"
                , "tprdk@outlook.com", "tprdk", "qwe123"));

        personList.add( new Person("Ceylin" , "Tıpırdık"
                , "cylntprdk@outlook.com",  "cyln", "123qwe"));
        personList.add( new Person("İbrahim", "Tıpırdık"
                , "tprdk@outlook.com", "tprdk", "qwe123"));

        personList.add( new Person("Ceylin" , "Tıpırdık"
                , "cylntprdk@outlook.com",  "cyln", "123qwe"));
        personList.add( new Person("İbrahim", "Tıpırdık"
                , "tprdk@outlook.com", "tprdk", "qwe123"));

        personList.add( new Person("Ceylin" , "Tıpırdık"
                , "cylntprdk@outlook.com",  "cyln", "123qwe"));
        personList.add( new Person("İbrahim", "Tıpırdık"
                , "tprdk@outlook.com", "tprdk", "qwe123"));

        personList.add( new Person("Ceylin" , "Tıpırdık"
                , "cylntprdk@outlook.com",  "cyln", "123qwe"));
        personList.add( new Person("İbrahim", "Tıpırdık"
                , "tprdk@outlook.com", "tprdk", "qwe123"));

        personList.add( new Person("Ceylin" , "Tıpırdık"
                , "cylntprdk@outlook.com",  "cyln", "123qwe"));
        personList.add( new Person("İbrahim", "Tıpırdık"
                , "tprdk@outlook.com", "tprdk", "qwe123"));

        personList.add( new Person("Ceylin" , "Tıpırdık"
                , "cylntprdk@outlook.com",  "cyln", "123qwe"));
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new PersonAdapter(personList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
