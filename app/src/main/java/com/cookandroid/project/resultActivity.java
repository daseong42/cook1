package com.cookandroid.project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class resultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        HashMap<String, Double> selectedIngredients = (HashMap<String, Double>) intent.getSerializableExtra("selectedIngredients");

        displaySelectedIngredients(selectedIngredients);

        //처음으로 돌아가는 버튼
        Button nextButton = findViewById(R.id.backbutton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToMainActivity();
            }
        });
    }

    private void displaySelectedIngredients(HashMap<String, Double> selectedIngredients) {
        TextView resultTextView = findViewById(R.id.ResultTextView);
        String resultText = "";
        for (String ingredient : selectedIngredients.keySet()) {
            resultText += ingredient + " (" + selectedIngredients.get(ingredient) + "원)\n";
        }
        resultTextView.setText(resultText);
    }

    //처음으로 돌아가는 버튼 기능
    private void goToMainActivity() {
        Intent intent = new Intent(resultActivity.this, MainActivity.class);
        startActivity(intent);
    }
}