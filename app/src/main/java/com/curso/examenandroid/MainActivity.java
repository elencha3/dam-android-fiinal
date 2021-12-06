package com.curso.examenandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editName, editAge, editOffens;
    Button addButton;
    RecyclerView recyclerView;
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    Adapter myAdapter;
    String name, offens;
    View view;
    int age;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editOffens = findViewById(R.id.editOffens);
        addButton = findViewById(R.id.button);
        recyclerView = findViewById(R.id.recycler);
        view = findViewById(R.id.constraint);
        textView = findViewById(R.id.tittle);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        myAdapter = new Adapter(this, enemies);
        recyclerView.setAdapter(myAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String number = editAge.getText().toString();
                    age = Integer.parseInt(number);
                } catch (NumberFormatException nfe) {
                    age = 0;
                }
                name = editName.getText().toString().trim();
                offens = editOffens.getText().toString().trim();

                if (!name.isEmpty()) {
                    if (!editAge.getText().toString().isEmpty()) {
                        if (age > 0 && age < 150) {
                            if (!offens.isEmpty()) {
                                enemies.add(new Enemy(name, age, offens));
                                myAdapter.notifyDataSetChanged();
                            } else {
                                Snackbar.make(view, "Introduce la ofensa", Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
                            Snackbar.make(view, "Debe introducir una edad válida", Snackbar.LENGTH_SHORT).show();

                        }
                    } else {
                        Snackbar.make(view, "Debe introducir una edad", Snackbar.LENGTH_SHORT).show();
                    }

                } else {
                    Snackbar.make(view, "Debe introducir el nombre del enemigo", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        myAdapter.setOnClickListener(new Adapter.CustomItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                Intent intent = new Intent(MainActivity.this, EnemyScreen.class);
                intent.putExtra("name", enemies.get(position).name);
                intent.putExtra("age", enemies.get(position).edad);
                intent.putExtra("offens", enemies.get(position).offens);
                startActivity(intent);
                finish();
            }
        });


    }
}