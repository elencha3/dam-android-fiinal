package com.curso.examenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class EnemyScreen extends AppCompatActivity {

    TextView nameTitle, ageTitle, offensTitle, nameText, ageText, offensText;
    String name, offens;
    int age;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_screen);

        nameTitle = findViewById(R.id.nameTitle);
        nameText = findViewById(R.id.nameText);
        ageTitle = findViewById(R.id.ageTitle);
        ageText = findViewById(R.id.ageText);
        offensTitle = findViewById(R.id.offensTitle);
        offensText = findViewById(R.id.offensText);
        button = findViewById(R.id.backButton);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        age = intent.getIntExtra("age", 0);
        offens = intent.getStringExtra("offens");

        if (!name.isEmpty() && !offens.isEmpty()) {
            nameText.setText(name);
            ageText.setText(String.valueOf(age));
            offensText.setText(offens);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnemyScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}