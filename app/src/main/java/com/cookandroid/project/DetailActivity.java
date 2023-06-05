package com.cookandroid.project;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView searchResultTextView;
    private List<String> Budaejjigae, JaeyukDeopbap, Rjjigae, Gimbap, CurryRice, Tteokbokki;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 뒤로 가기 버튼을 눌렀을 때의 동작 처리
        if (item.getItemId() == android.R.id.home) {
            finish(); // 액티비티 종료
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

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
        // 뒤로 가기 버튼을 활성화
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayout buttonContainer = findViewById(R.id.buttonContainer); // 버튼이 추가될 컨테이너

        // 버튼 생성 및 속성 설정
        Rjjigae = Arrays.asList("고추장","애호박","쇠고기","대파","마늘","양파","감자");

        for (String ingredient : Rjjigae) {
            Button button = new Button(this); // 버튼 생성
            button.setText(ingredient); // 버튼 텍스트 설정
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 버튼 클릭 이벤트 처리

                    // 예시: 해당 재료에 대한 동작 수행
                }
            });
            buttonContainer.addView(button); // 버튼을 컨테이너에 추가
        }

    }

}
