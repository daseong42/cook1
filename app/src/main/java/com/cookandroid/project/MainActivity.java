package com.cookandroid.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private ListView listView;

    private List<String> itemList;
    private List<String> Budaejjigae, JaeyukDeopbap, Rjjigae, Gimbap, CurryRice, Tteokbokki;
    private List<String> filteredList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 아이템 리스트 초기화
        itemList = Arrays.asList("부대찌개", "제육덮밥", "고추장찌개", "김밥", "카레라이스", "떡볶이");

        Budaejjigae= Arrays.asList("김치","비엔나","스팸","두부","떡","대파","홍고추","청양고추","사골곰탕");
        JaeyukDeopbap = Arrays.asList("밥","돼지고기","양파","당근","표고","대파","청양고추","홍고추");
        Rjjigae = Arrays.asList("고추장","애호박","쇠고기","대파","마늘","양파","감자");
        Gimbap = Arrays.asList("김", "밥", "햄", "단무지", "오이", "당근", "계란", "맛살");
        CurryRice = Arrays.asList();
        Tteokbokki = Arrays.asList();


        // 검색 결과를 담을 필터링된 리스트 초기화
        filteredList = new ArrayList<>();

        // 어댑터 초기화
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredList);

        // 레이아웃 요소 참조
        searchEditText = findViewById(R.id.searchEditText);
        listView = findViewById(R.id.listView);

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);

        // 검색어 입력 변화를 감지하는 텍스트 와처 설정
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 검색어가 변경될 때마다 필터링된 리스트 업데이트
                filterItems(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // 아이템 클릭 리스너 설정
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = filteredList.get(position);
                // 선택된 아이템 정보를 DetailActivity로 전달하고 액티비티를 시작
                Intent intent = new Intent(MainActivity.this, DetailActivity
                        .class);
                intent.putExtra("selectedItem", selectedItem);
                startActivity(intent);
            }
        });
    }

    private void filterItems(String query) {
        filteredList.clear();

        if (query.isEmpty()) {
            // 검색어가 비어있을 경우 모든 아이템을 추가
            filteredList.addAll(itemList);
        } else {
            // 검색어와 일치하는 아이템만 추가
            for (String item : itemList) {
                if (item.contains(query)) {
                    filteredList.add(item);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }

}