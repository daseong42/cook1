package com.cookandroid.project;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView searchResultTextView;


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
        for (int i = 0; i < 5; i++) {
            Button button = new Button(this);
            button.setText("Button " + i);
            button.setTag(i); // 버튼에 고유한 값을 설정하기 위해 태그를 사용할 수도 있습니다.
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int buttonValue = (int) v.getTag(); // 클릭된 버튼의 값 가져오기
                    // 클릭된 버튼에 대한 동작 수행
                    // 예: 다른 액티비티로 이동하거나 값을 처리하는 등의 동작
                }
            });
            buttonContainer.addView(button); // 버튼을 컨테이너에 추가
        }
    }

}
