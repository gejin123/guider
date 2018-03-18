package cn.edu.buct.zxxe.beijingguider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

/**
 * Created by gejin-PC on 2018/1/1.
 */


public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.ViewHolder> {
    private static final String TAG = "SiteAdapter";
    private List<Scene> siteList;
    private MainActivity mainActivity;

    //定义一个内部类ViewHolder，继承自RecyclerView.ViewHolder，用来缓存子项的各个实例，提高效率
    static class ViewHolder extends RecyclerView.ViewHolder {
        //        View siteView;
        ImageView siteImage;
        TextView siteName;

        public ViewHolder(View view) {
            super(view);
//            siteView = view;
            siteImage = view.findViewById(R.id.site_image);
            siteName = view.findViewById(R.id.site_name);
        }
    }

    //绑定传入的数据源
    public SiteAdapter(List<Scene> siteList, MainActivity mainActivity) {
        this.siteList = siteList;
        this.mainActivity = mainActivity;
    }

    //实现onCreateViewHolder方法，返回给recyclerView使用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.site_item, parent,
                false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public class ImageOnClickListener implements View.OnClickListener {
        private int position;

        public ImageOnClickListener(int pos) {
            position = pos;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mainActivity, SiteActivity.class);
            intent.putExtra("Pos", position);
            mainActivity.startActivity(intent);
//                Toast.makeText(v.getContext(), "you clicked image  " + site.getName(), Toast
//                        .LENGTH_SHORT).show();
        }
    }

    //实现onBindViewHolder方法，设置子Item上各个实例
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Scene site = siteList.get(position);
//        holder.siteImage.setImageResource(site.getImageId());
//        InputStream inputStream = mainActivity.getResources().openRawResource(site.getImageId());
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = false;
//        options.inSampleSize = 2;   //width，hight设为原来的几分之一
//        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
        Bitmap bitmap = Utility.GetBitMap(site.getImageId(), 2);
        holder.siteImage.setImageBitmap(bitmap);

        holder.siteName.setText(site.getName());
        holder.siteImage.setOnClickListener(new ImageOnClickListener(holder.getAdapterPosition()));
    }

    //返回子项个数
    @Override
    public int getItemCount() {
        return siteList.size();
    }
}

