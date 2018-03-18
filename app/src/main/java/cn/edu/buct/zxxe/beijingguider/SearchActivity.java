package cn.edu.buct.zxxe.beijingguider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    private ImageView quitButton;
    private ImageView deleteButton;
    private EditText searchEdit;
    private ListView listSearch;
    private ArrayAdapter<String> adapter;
    private List<String> sceneNameList;
    private List<String> searchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.addActivity(this);
        setContentView(R.layout.activity_search);
        quitButton = findViewById(R.id.quit_button);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                finish();
            }
        });
        searchEdit = findViewById(R.id.search_edit);
        listSearch = findViewById(R.id.search_listview);
        sceneNameList = new ArrayList<>();
        searchData = new ArrayList<>();
//        for (Scene scene : DataBase.getSceneList()) {
//            sceneNameList.add(scene.getName());
//        }
        adapter = new ArrayAdapter<>(this, android.R.layout
                .simple_list_item_1, sceneNameList);
        listSearch.setAdapter(adapter);
        deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEdit.setText("");
                sceneNameList.clear();
            }
        });
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String text = searchEdit.getText().toString().trim();
                if (text.isEmpty()) {
                    for (Scene scene : DataBase.getSceneList()) {
                        if (!sceneNameList.contains(scene.getName()))
                            sceneNameList.add(scene.getName());
                    }
                    adapter.notifyDataSetChanged();
                    return;
                }
                sceneNameList.clear();
                for (Scene scene : DataBase.getSceneList()) {
                    if ((scene.getName() != null && scene.getName().contains(text)) ||
                            (scene.getEnglishName() != null && scene.getEnglishName().contains
                                    (text))) {
                        if (!sceneNameList.contains(scene.getName()))
                            sceneNameList.add(scene.getName());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
        listSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = view.findViewById(android.R.id.text1);
                String text = textView.getText().toString();
                for (Scene scene : DataBase.getSceneList()) {
                    if ((scene.getName() != null && scene.getName().contains(text)) ||
                            (scene.getEnglishName() != null && scene.getEnglishName().contains
                                    (text))) {
                        Intent intent = new Intent(SearchActivity.this, SiteActivity.class);
                        intent.putExtra("Id", scene.getId());
                        startActivity(intent);
                    }
                }

            }
        });
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchActivity.this,android.R
// .layout.simple_list_item_1,)

    }

    @Override
    protected void onDestroy() {
        Utility.removeActivity(this);
        super.onDestroy();
    }


}
