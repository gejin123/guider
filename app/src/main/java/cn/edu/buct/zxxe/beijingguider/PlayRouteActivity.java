package cn.edu.buct.zxxe.beijingguider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayRouteActivity extends AppCompatActivity {

    private ImageView mainImage;
    private TextView mainName;
    private TextView mainDescribe;
    private RecyclerView recyclerView;

    @Override
    protected void onDestroy() {
        Utility.removeActivity(this);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.addActivity(this);
        setContentView(R.layout.activity_play_route);
        mainImage = findViewById(R.id.site_name);
        mainName = findViewById(R.id.sitename_yiriyou);
        mainDescribe = findViewById(R.id.site_describe);
        Intent intent = getIntent();
        int pos = intent.getIntExtra("Pos", -1);
        if (pos >= 0) {
            Route route = DataBase.getRouteList().get(pos);
            Bitmap bitmap = Utility.GetBitMap(route.getImageId(), 2);
            mainImage.setImageBitmap(bitmap);
            mainName.setText(route.getName());
            mainDescribe.setText(route.getDescribe());
        }
        recyclerView = findViewById(R.id.recycler_view_inner);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RouteInnerAdapter adapter = new RouteInnerAdapter(DataBase.getRouteList().get(pos),
                PlayRouteActivity.this);
        recyclerView.setAdapter(adapter);
    }
}
