package cn.edu.buct.zxxe.beijingguider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class SiteActivity extends AppCompatActivity {
    private int pos;
    private int id;
    private String describeComplete;
    private String describeSimple;
    private String name;
    private String englishName;
    private int imageId;
    private ImageView imageSite;
    private ImageView fulling;
    private TextView textName;
    private TextView textEnglishName;
    private TextView textDetail;

    private ImageView imageExtent;
    private ImageView moreButton;
    private LinearLayout openMap;
    private boolean isSimple;

    @Override
    protected void onDestroy() {
        Utility.removeActivity(this);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.addActivity(this);
        setContentView(R.layout.activity_site);
        Intent intent = getIntent();
        int pos = intent.getIntExtra("Pos", -1);
        Scene scene = new Scene();
        if (pos >= 0) {
            scene = DataBase.getSceneList().get(pos);
        } else {
            int id = intent.getIntExtra("Id", 0);
            if (id > 0) {
                for (Scene s : DataBase.getSceneList()) {
                    if (s.getId() == id) {
                        scene = s;
                        break;
                    }
                }
            }
        }

        id = scene.getId();
        describeComplete = scene.getDescribeComplete();
        describeSimple = scene.getDescribeSimple();
        name = scene.getName();
        englishName = scene.getEnglishName();
        imageId = scene.getImageId();
//        holder.siteImage.setImageResource(site.getImageId());
        imageSite = findViewById(R.id.site_photo);
        textName = findViewById(R.id.chi_title_head);
        textEnglishName = findViewById(R.id.eng_title_head);
        textDetail = findViewById(R.id.detail);

        Bitmap bitmap = Utility.GetBitMap(imageId, 2);
        imageSite.setImageBitmap(bitmap);
//        imageSite.setImageResource(imageId);
        textName.setText(name);
        textEnglishName.setText(englishName);
        textDetail.setText(describeSimple);

        isSimple = true;
        imageExtent = findViewById(R.id.extent);
        imageExtent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSimple) {
                    isSimple = false;
                    textDetail.setText(describeComplete);
                    imageExtent.setRotationX(180f);
                } else {
                    isSimple = true;
                    textDetail.setText(describeSimple);
                    imageExtent.setRotationX(0f);
                }

            }
        });

        openMap = findViewById(R.id.openmap);
        final String geo = scene.getGeography();
        final String name = scene.getName();
        openMap.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
//                Uri mapUri = Uri.parse(geo);
                Uri mapUri = Uri.parse(Utility.getBaiDuMapString(geo, name));
                Intent intent = new Intent();
                intent.setData(mapUri);
//                intent.setPackage("com.google.android.apps.maps");
//                Intent intent=new Intent(Intent.createChooser())
                startActivity(intent);
            }
        });
        //moreButton.findViewById(R.id.more_button);
//        moreButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SiteActivity.this, SearchActivity.class);
//                startActivity(intent);
//            }
//        });

        ImageView[] imageViews = new ImageView[4];
        imageViews[0] = findViewById(R.id.leftup);
        imageViews[1] = findViewById(R.id.rightup);
        imageViews[2] = findViewById(R.id.leftdown);
        imageViews[3] = findViewById(R.id.rightdown);
        Random random = new Random();
        int size = DataBase.getSceneList().size();
        int _pos = random.nextInt(size);
        for (int i = 0; i < 4; ++i) {
            ImageView imageView = imageViews[i];
            final int ppos = (_pos + i) % size;
            Bitmap bitmap1 = Utility.GetBitMap(DataBase.getSceneList().get(ppos).getImageId(), 1);
            imageView.setImageBitmap(bitmap1);
//                    imageView.setImageResource(DataBase.getSceneList().get(ppos).getImageId());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SiteActivity.this, SiteActivity.class);
                    intent.putExtra("Pos", ppos);
                    startActivity(intent);
                }
            });
        }

        moreButton = findViewById(R.id.more_button);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SiteActivity.this, MainActivity.class);
                intent1.putExtra("Item", 1);
                startActivity(intent1);
            }
        });
    }

    private void getExtraByPos(int pos) {

    }
}
