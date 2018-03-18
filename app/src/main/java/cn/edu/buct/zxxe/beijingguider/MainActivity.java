package cn.edu.buct.zxxe.beijingguider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private ArrayList<View> viewList;
    private List<Scene> siteList = new ArrayList<>();


    private TextView indexText;
    private TextView sitesText;
    private TextView playRouteText;

    private ImageView viewHead;
    private ImageView scrollImage;
    private ImageView search;
    private ImageView guide;


    // 滚动条初始偏移量
    private int offset = 0;
    // 当前页编号
    private int curIndex = 0;
    // 滚动条宽度
    private int barWidth;
    //一倍滚动量
    private int one;

    @Override
    protected void onDestroy() {
        Utility.removeActivity(this);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.addActivity(this);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        Utility.setMainActivity(this);
//        initSites();
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        Log.d(TAG, "onCreate: " + (recyclerView == null));
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
//                StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//        SiteAdapter adapter = new SiteAdapter(siteList);
//        recyclerView.setAdapter(adapter);
        NavigationView navView = findViewById(R.id.nav_view);
        TextView navUser = navView.getHeaderView(0).findViewById(R.id.nav_username);
        navUser.setText(LoginActivity.sharedPreferences.getString("Username", ""));
        navView.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.logout:
                        finish();
                        LoginActivity.editor.putBoolean("AutoLogin", false);
                        LoginActivity.editor.apply();
                        intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.aboutus:
                        intent = new Intent(MainActivity.this, AboutusActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.account_man:
                        intent = new Intent(MainActivity.this, AccountManActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.anothersite:
                        viewPager.setCurrentItem(1);
                        drawerLayout.closeDrawers();
                        //intent = new Intent(MainActivity.this,viewPager.setCurrentItem(1));
                }
                return true;
            }
        });
//        NavigationView navView1 = findViewById(R.id.nav_view);
//        navView1.setNavigationItemSelectedListener(new NavigationView
//                .OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.aboutus:
//                        Intent intent = new Intent(MainActivity.this, AboutusActivity.class);
//                        startActivity(intent);
//                }
//                return true;
//            }
//        });
        viewHead = findViewById(R.id.head);
        viewHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //查找布局文件用LayoutInflater.inflate
        final LayoutInflater inflater = getLayoutInflater();
        View viewIndex = inflater.inflate(R.layout.index, null);
        View viewAllSites = inflater.inflate(R.layout.allsites, null);
        View viewPlayRoute = inflater.inflate(R.layout.playroute, null);

        indexText = findViewById(R.id.index);
        sitesText = findViewById(R.id.allsites);
        playRouteText = findViewById(R.id.palyroute);
        search = findViewById(R.id.search);
        indexText.setOnClickListener(new LabelOnClickListener());
        sitesText.setOnClickListener(new LabelOnClickListener());
        playRouteText.setOnClickListener(new LabelOnClickListener());
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        viewList = new ArrayList<>();
        viewList.add(viewIndex);
        viewList.add(viewAllSites);
        viewList.add(viewPlayRoute);
        //数据适配器
        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                if (position == 0) {
                    instantiateIndex();
                } else if (position == 1) {
                    instantiateAllSites();
                } else if (position == 2) {
                    instantiateRoute();
                }
                return viewList.get(position);
            }

            public void instantiateIndex() {
                ImageView imageMain = findViewById(R.id.main_img);
//                Bitmap bitmap = Utility.GetBitMap(DataBase.getSceneList().get(0).getImageId(), 2);
//                imageMain.setImageBitmap(bitmap);
                imageMain.setImageResource(DataBase.getSceneList().get(0).getImageId());
                imageMain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, SiteActivity.class);
                        intent.putExtra("Pos", 0);
                        startActivity(intent);
                    }
                });
                ImageView[] imageScenes = new ImageView[5];
                imageScenes[0] = findViewById(R.id.main_img01);
                imageScenes[1] = findViewById(R.id.main_img02);
                imageScenes[2] = findViewById(R.id.main_img03);
                imageScenes[3] = findViewById(R.id.main_img04);
                imageScenes[4] = findViewById(R.id.main_img05);
                Random random = new Random();
                int size = DataBase.getSceneList().size();
                int pos = random.nextInt(size);
                for (int i = 0; i < 5; ++i) {
                    ImageView imageView = imageScenes[i];
                    final int ppos = (pos + i) % size;
                    Bitmap bitmap1 = Utility.GetBitMap(DataBase.getSceneList().get(ppos)
                            .getImageId(), 2);
                    imageView.setImageBitmap(bitmap1);
//                    imageView.setImageResource(DataBase.getSceneList().get(ppos).getImageId());
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MainActivity.this, SiteActivity.class);
                            intent.putExtra("Pos", ppos);
                            startActivity(intent);
                        }
                    });
                }
            }

            public void instantiateAllSites() {
                RecyclerView recyclerView = findViewById(R.id.recycler_view);
//                Log.d(TAG, "instantiateItem: " + (recyclerView != null));
                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                SiteAdapter adapter = new SiteAdapter(DataBase.getSceneList(), MainActivity
                        .this);
                recyclerView.setAdapter(adapter);
            }

            public void instantiateRoute() {
                RecyclerView recyclerView = findViewById(R.id.playroute_view);
                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,
                        StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                RouteAdapter adapter = new RouteAdapter(DataBase.getRouteList(), MainActivity.this);
                recyclerView.setAdapter(adapter);
            }
        };
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);        //绑定适配器
        viewPager.setCurrentItem(0);//设置viewPager的初始界面为第一个界面
        viewPager.addOnPageChangeListener(new OnPageChangeListener());//添加切换界面的监听器
        // 获取滚动条的宽度
        barWidth = BitmapFactory.decodeResource(getResources(), R.drawable.scrollbar).getWidth();
        //为了获取屏幕宽度，新建一个DisplayMetrics对象
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //将当前窗口的一些信息放在DisplayMetrics类中
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //得到屏幕的宽度
        int screenW = displayMetrics.widthPixels;
        //计算出滚动条初始的偏移量
        offset = (screenW / 2 - barWidth) / 2;
        //计算出切换一个界面时，滚动条的位移量
        one = offset * 2 + barWidth;
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        //将滚动条的初始位置设置成与左边界间隔一个offset
        scrollImage = findViewById(R.id.scrollbar);
        scrollImage.setImageMatrix(matrix);

        Intent intent = getIntent();
        int itemPos = intent.getIntExtra("Item", -1);
        if (itemPos >= 0) {
            viewPager.setCurrentItem(itemPos);
        }
    }


    private String getRandomLengthName(String name) {
//        Random random = new Random();
//        int length = random.nextInt(20) + 1;
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < length; i++) {
//            builder.append(name);
//        }
//        return builder.toString();
        return name;

    }

    public class OnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
                case 0:
                    /**
                     * TranslateAnimation的四个属性分别为
                     * float fromXDelta 动画开始的点离当前View X坐标上的差值
                     * float toXDelta 动画结束的点离当前View X坐标上的差值
                     * float fromYDelta 动画开始的点离当前View Y坐标上的差值
                     * float toYDelta 动画开始的点离当前View Y坐标上的差值
                     **/
                    animation = new TranslateAnimation(one, 0, 0, 0);
                    break;
                case 1:
                    animation = new TranslateAnimation(offset, one, 0, 0);
                    break;
                case 2:
                    animation = new TranslateAnimation(offset, one, 0, 0);
            }
            //arg0为切换到的页的编码
            curIndex = arg0;
            // 将此属性设置为true可以使得图片停在动画结束时的位置
            animation.setFillAfter(true);
            //动画持续时间，单位为毫秒
            animation.setDuration(200);
            //滚动条开始动画
            scrollImage.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    public class LabelOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.index:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.allsites:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.palyroute:
                    viewPager.setCurrentItem(2);
            }
        }
    }
//    public void startNaviGoogle() {
//        if ((getContext(), "com.google.android.apps.maps")) {
//            Uri gmmIntentUri = Uri.parse("google.navigation");
//            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//            mapIntent.setPackage("com.google.android.apps.maps");
//            startActivity(mapIntent);
//        } else {
//            ToastUtil.showToast("您尚未安装谷歌地图或地图版本过低");
//        }
//    }
}
