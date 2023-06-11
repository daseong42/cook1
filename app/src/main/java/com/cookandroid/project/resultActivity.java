package com.cookandroid.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;


public class resultActivity extends AppCompatActivity {

    private TextView ResultTextView;
    private TextView ResultText;
    HashMap<String, Double>RestaurantPrices = new HashMap<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //가게 가격
        RestaurantPrices.put("국민포차", 17900.0);
        RestaurantPrices.put("땅스부대찌개", 18500.0);

        Intent intent = getIntent();
        //선택한 식재료 출력
        HashMap<String, Double> selectedIngredients = (HashMap<String, Double>) intent.getSerializableExtra("selectedIngredients");
        double totalSelectedIngredientsCost = displaySelectedIngredients(selectedIngredients);

        // 각 레스토랑 추천메뉴 세부 가격 출력
        double totalRestaurantCost = displayRestaurantPrices();

        // 가격 비교 및 추천
        comparePricesAndRecommend(totalSelectedIngredientsCost, totalRestaurantCost);

        //처음으로 돌아가는 버튼
        Button nextButton = findViewById(R.id.backbutton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToMainActivity();
            }
        });
    }

    //선택한 식재료 가격 합산 출력
    private double displaySelectedIngredients(HashMap<String, Double> selectedIngredients) {
        TextView resultTextView = findViewById(R.id.ResultTextView);
        double totalCost = 0;
        for (String ingredient : selectedIngredients.keySet()) {
            totalCost += selectedIngredients.get(ingredient);
        }
        resultTextView.setText("직접 요리: " + Math.round(totalCost) + "원");
        return totalCost;
    }

    //가게 메뉴 가격 목록 출력
    private double displayRestaurantPrices() {
        TextView restaurantPricesTextView = findViewById(R.id.Restaurant);
        String restaurantPricesText = "";
        double totalRestaurantCost = 0;
        for (String restaurant : RestaurantPrices.keySet()) {
            restaurantPricesText += restaurant + " : " + Math.round(RestaurantPrices.get(restaurant)) + "원\n";
            totalRestaurantCost += RestaurantPrices.get(restaurant);
        }
        restaurantPricesTextView.setText(restaurantPricesText);
        return totalRestaurantCost;
    }

    //가격 비교
    private void comparePricesAndRecommend(double selectedIngredientsTotal, double restaurantTotal) {
        TextView compareText = findViewById(R.id.comparetext);
        if (selectedIngredientsTotal > restaurantTotal) {
            compareText.setText("배달 추천!");
        } else {
            compareText.setText("직접 요리 추천!");
        }
    }

    //처음으로 돌아가는 버튼 기능
    private void goToMainActivity() {
        Intent intent = new Intent(resultActivity.this, MainActivity.class);
        startActivity(intent);
    }
}