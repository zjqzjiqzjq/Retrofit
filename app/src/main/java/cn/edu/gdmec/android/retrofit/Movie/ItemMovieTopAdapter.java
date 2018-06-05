package cn.edu.gdmec.android.retrofit.Movie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.retrofit.ADetailActivity;
import cn.edu.gdmec.android.retrofit.Bean.MovieBean;
import cn.edu.gdmec.android.retrofit.R;

public class ItemMovieTopAdapter extends RecyclerView.Adapter<ItemMovieTopAdapter.ViewHolder> {

    private List<MovieBean.SubjectsBean> objects = new ArrayList<MovieBean.SubjectsBean>();

    private Context context;

    public ItemMovieTopAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<MovieBean.SubjectsBean> objects){
        this.objects = objects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_top,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MovieBean.SubjectsBean bean = objects.get(position);
        if (bean == null){
            return;
        }
        Glide.with(context)
                .load(bean.getImages().getSmall())
                .into(holder.ivMovieTop);
        holder.tvMovieTopTitle.setText(bean.getTitle());
        holder.rlMovieTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ADetailActivity.class);
                intent.putExtra("url", bean.getAlt());
                intent.putExtra("title", bean.getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout rlMovieTop;
        private ImageView ivMovieTop;
        private TextView tvMovieTopTitle;

        public ViewHolder(View view) {
            super(view);
            rlMovieTop = (LinearLayout) view.findViewById(R.id.rl_movie_top);
            ivMovieTop = (ImageView) view.findViewById(R.id.iv_movie_top);
            tvMovieTopTitle = (TextView) view.findViewById(R.id.tv_movie_top_title);
        }
    }
}
