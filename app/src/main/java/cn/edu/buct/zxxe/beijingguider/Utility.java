package cn.edu.buct.zxxe.beijingguider;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 84978 on 2018/1/4.
 */

public class Utility {
    private static MainActivity mainActivity;
    private static String curUsername;
    private static String curPassword;
    private static Toast toast = null;
    private static List<Activity> activityList;

    public static Bitmap GetBitMap(int imageId, int rate) {
        InputStream inputStream = mainActivity.getResources().openRawResource(imageId);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = rate;   //width，hight设为原来的几分之一
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
        return bitmap;
    }

    public static void setMainActivity(MainActivity mainActivity) {
        Utility.mainActivity = mainActivity;
    }

    public static String getBaiDuMapString(String geo, String name) {
        String g = geo.substring(4);
//        return String.format("intent://map/marker?location=%s&title=%s&content " +
//                "=%s;scheme=bdapp;package=com.baidu" +
//                ".BaiduMap;end", g, name, name);
//        return "intent://map/marker?location=" + g + "&title=我的位置&content " +
//                "=百度奎科大厦&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu" +
//                ".BaiduMap;end"
        return String.format("baidumap://map/marker?location=%s&title=%s&zoom=13&content=%s" +
                        "&traffic=on",
                g, name, name);
    }

    public static void setCurUsername(String curUsername) {
        Utility.curUsername = curUsername;
    }

    public static String getCurUsername() {
        return curUsername;
    }

    public static String getCurPassword() {
        return curPassword;
    }

    public static void setCurPassword(String curPassword) {
        Utility.curPassword = curPassword;
    }

    public static void showText(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        }
        toast.setText(text);
        toast.show();
    }

    public static Activity addActivity(Activity activity) {
        if (activityList == null)
            activityList = new ArrayList<>();
        activityList.add(activity);
        return activity;
    }

    public static void removeActivity(Activity activity) {
        if (activityList == null)
            return;
        activityList.remove(activity);
    }

    public static void removeAllActivity() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
                activityList.remove(activity);
            }
        }
    }

    public static void resetData(){
        DataBase.restart();
    }
}
