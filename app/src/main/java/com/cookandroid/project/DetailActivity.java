package com.cookandroid.project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView searchResultTextView;
    private List<String> ingredientsList;
    private HashMap<String, Double> selectedIngredients;
    private int[] buttonStates;
    private List<String> itemList;
    private ListView listView;
    private String searchQuery;

    //식재료 가격
    HashMap<String, Double>ingredientPrices = new HashMap<>() ;
    //선택한 식재료
    // 제거할 부분: HashMap<String, Double>selectedIngredients = new HashMap<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        selectedIngredients = new HashMap<>();

        // 레이아웃 요소 참조
        searchResultTextView = findViewById(R.id.searchResultTextView);

        // MainActivity에서 전달한 검색어 받기
        String searchQuery = getIntent().getStringExtra("selectedItem");

        // 검색어를 TextView에 설정
        searchResultTextView.setText(searchQuery);

        // 식재료 임시목록
        ingredientPrices.put("스팸", 3000.0);
        ingredientPrices.put("소시지", 1500.0);
        ingredientPrices.put("대파", 500.0);
        ingredientPrices.put("양파", 600.0);
        ingredientPrices.put("신김치", 600.0);
        ingredientPrices.put("베이크드 빈스", 300.0); //30g
        ingredientPrices.put("체다슬라이스 치즈", 300.0);

        createButtons();

        Button nextButton = findViewById(R.id.Detailbutton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToResultActivity();
            }
        });
    }

    private void createButtons() {
        LinearLayout buttonContainer = findViewById(R.id.buttonContainer);
        for (String ingredient : ingredientPrices.keySet()) {
            Button ingredientButton = new Button(this);
            ingredientButton.setText(ingredient);
            ingredientButton.setBackgroundColor(Color.GRAY);

            ingredientButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (!selectedIngredients.containsKey(ingredient)) {
                        selectedIngredients.put(ingredient, ingredientPrices.get(ingredient));
                        ingredientButton.setBackgroundColor(Color.GREEN);
                    } else {
                        selectedIngredients.remove(ingredient);
                        ingredientButton.setBackgroundColor(Color.GRAY);
                    }
                }
            });

            buttonContainer.addView(ingredientButton);
        }
    }

    private void goToResultActivity() {
        Intent intent = new Intent(DetailActivity.this, resultActivity.class);
        intent.putExtra("selectedIngredients", (Serializable) selectedIngredients);
        intent.putExtra("selectedItem", searchQuery);
        startActivity(intent);
    }
}
