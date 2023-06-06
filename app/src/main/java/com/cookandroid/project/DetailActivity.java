package com.cookandroid.project;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView searchResultTextView;
    private List<String> ingredientsList;
    private List<String> selectedIngredients;
    private int[] buttonStates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // 레이아웃 요소 참조
        searchResultTextView = findViewById(R.id.searchResultTextView);

        // MainActivity에서 전달한 검색어 받기
        String searchQuery = getIntent().getStringExtra("selectedItem");

        // 검색어를 TextView에 설정
        searchResultTextView.setText(searchQuery);

        // 음식에 따른 식재료 목록 설정
        ingredientsList = getIngredientsList(searchQuery);

        // 선택된 식재료를 저장할 리스트 초기화
        selectedIngredients = new ArrayList<>();

        LinearLayout buttonContainer = findViewById(R.id.buttonContainer); // 버튼이 추가될 컨테이너


        for (String ingredient : ingredientsList) {
            Button button = new Button(this); // 버튼 생성
            button.setText(ingredient); // 버튼 텍스트 설정
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleIngredient(button, ingredient);
                }
            });
            buttonContainer.addView(button); // 버튼을 컨테이너에 추가
        }
    }

    // 음식에 따른 식재료 목록을 가져오는 메서드
    private List<String> getIngredientsList(String food) {
        List<String> ingredients = new ArrayList<>();

        // 해당 음식에 따른 식재료 목록을 가져오는 로직
        switch (food) {
            case "부대찌개":
                ingredients = Arrays.asList("김치", "비엔나", "스팸", "두부", "떡", "대파", "홍고추", "청양고추", "사골곰탕");
                break;
            case "제육덮밥":
                ingredients = Arrays.asList("밥", "돼지고기", "양파", "당근", "표고", "대파", "청양고추", "홍고추");
                break;
            case "고추장찌개":
                ingredients = Arrays.asList("고추장", "애호박", "쇠고기", "대파", "마늘", "양파", "감자");
                break;
            // 다른 음식에 대한 식재료 목록 설정
        }

        return ingredients;
    }

    // 식재료 토글 기능 처리
    private void toggleIngredient(Button button, String ingredient) {
        if (selectedIngredients.contains(ingredient)) {
            selectedIngredients.remove(ingredient);
        }
    }

    //뒤로가기
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 뒤로 가기 버튼을 눌렀을 때의 동작 처리
        if (item.getItemId() == android.R.id.home) {
            finish(); // 액티비티 종료
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
