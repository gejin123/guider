package cn.edu.buct.zxxe.beijingguider;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AboutusActivity extends AppCompatActivity {

    private TextView verSion;
    private ImageView backButton;

    @Override
    protected void onDestroy() {
        Utility.removeActivity(this);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.addActivity(this);
        setContentView(R.layout.activity_aboutus);
        verSion=findViewById(R.id.version);
        verSion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.showText(AboutusActivity.this, "您使用的版本已经是当前最新版本！");
            }
        });

        backButton=findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent()
                finish();
            }
        });
    }
}
