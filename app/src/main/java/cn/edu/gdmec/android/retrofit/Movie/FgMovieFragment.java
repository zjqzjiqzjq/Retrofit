package cn.edu.gdmec.android.retrofit.Movie;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

import java.util.TimerTask;

import cn.edu.gdmec.android.retrofit.Bean.MovieBean;
import cn.edu.gdmec.android.retrofit.Movie.Presenter.MoviePresenter;
import cn.edu.gdmec.android.retrofit.Movie.View.IMovieView;
import cn.edu.gdmec.android.retrofit.R;


public class FgMovieFragment extends Fragment implements IMovieView {

    private MoviePresenter presenter;
    private TextView tv_movie;
    private SwipeRefreshLayout srl_movie;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_movie, null);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_movie = view.findViewById(R.id.tv_movie);
        srl_movie = view.findViewById(R.id.srl_news);
        srl_movie.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        presenter = new MoviePresenter(this);
        srl_movie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadMovie();
            }
        });
    }

    @Override
    public void hideDialog() {
        srl_movie.setRefreshing(false);
    }

    @Override
    public void showMovie(MovieBean movieBean) {
        tv_movie.setText(movieBean.getSubjects().get(0).getTitle()+movieBean.getSubjects().get(0).getDirectors());
    }

    @Override
    public void showErrorMsg(String error) {
        tv_movie.setText("加载失败" + error);
    }

    @Override
    public void showDialog() {
        srl_movie.setRefreshing(true);
    }
}
