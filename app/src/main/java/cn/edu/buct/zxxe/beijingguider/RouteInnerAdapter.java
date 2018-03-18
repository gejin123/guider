package cn.edu.buct.zxxe.beijingguider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by 84978 on 2018/1/5.
 */

public class RouteInnerAdapter extends RecyclerView.Adapter<RouteInnerAdapter.ViewHolder> {
    private static final String TAG = "RouteInnerAdapter";
    private Route route;
    private String[] lines;
    private PlayRouteActivity playRouteActivity;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        ImageView mainView;
        TextView textDescribe;
        TextView textShow;

        public ViewHolder(View view) {
            super(view);
            textName = view.findViewById(R.id.site_name1);
            mainView = view.findViewById(R.id.site_image1);
            textDescribe = view.findViewById(R.id.site_describe1);
            textShow = view.findViewById(R.id.map_show);
        }
    }

    public RouteInnerAdapter(Route route, PlayRouteActivity playRouteActivity) {
        this.route = route;
        this.lines = route.getLines();
        this.playRouteActivity = playRouteActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_activity_play_route, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List<Scene> sceneList = DataSupport.where("name = ?", lines[position]).find(Scene.class);
        Log.d(TAG, "onBindViewHolder: " + lines[position]);
        if (sceneList == null) {
            return;
        }
        final Scene scene = sceneList.get(0);
        holder.textName.setText(scene.getName());
        holder.textDescribe.setText(scene.getDescribeSimple());
        Bitmap bitmap = Utility.GetBitMap(scene.getImageId(), 2);
        holder.mainView.setImageBitmap(bitmap);
        holder.mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(playRouteActivity, SiteActivity.class);
                intent.putExtra("Id", scene.getId());
                playRouteActivity.startActivity(intent);
            }
        });
        final String geo = scene.getGeography();
        final String name = scene.getName();
        holder.textShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri mapUri = Uri.parse(Utility.getBaiDuMapString(geo, name));
                Intent intent = new Intent();
                intent.setData(mapUri);
                playRouteActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lines.length;
    }
}
