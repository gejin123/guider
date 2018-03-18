package cn.edu.buct.zxxe.beijingguider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 84978 on 2018/1/4.
 */

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.ViewHolder> {
    private static final String TAG = "RouteAdapter";
    private List<Route> routeList;
    private MainActivity mainActivity;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView routeImage;
        TextView routeName;
        TextView timeText;
        TextView posText;

        public ViewHolder(View view) {
            super(view);
            routeImage = view.findViewById(R.id.site_name);
            routeName = view.findViewById(R.id.site_describe);
            timeText = view.findViewById(R.id.play_time);
            posText = view.findViewById(R.id.play_location);

        }
    }

    public RouteAdapter(List<Route> routeList, MainActivity mainActivity) {
        this.routeList = routeList;
        this.mainActivity = mainActivity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playrout_item,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Route route = routeList.get(position);
        Bitmap bitmap = Utility.GetBitMap(route.getImageId(), 2);
        holder.routeImage.setImageBitmap(bitmap);
        holder.routeName.setText(route.getName());
        holder.routeImage.setOnClickListener(new ImageOnClickListener(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }

    public class ImageOnClickListener implements View.OnClickListener {
        private int position;

        public ImageOnClickListener(int pos) {
            position = pos;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mainActivity, PlayRouteActivity.class);
            intent.putExtra("Pos", position);
            mainActivity.startActivity(intent);
//                Toast.makeText(v.getContext(), "you clicked image  " + site.getName(), Toast
//                        .LENGTH_SHORT).show();
        }
    }

}
