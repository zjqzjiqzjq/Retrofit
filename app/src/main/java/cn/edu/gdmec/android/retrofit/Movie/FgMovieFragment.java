package cn.edu.gdmec.android.retrofit.Movie;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TimerTask;

import cn.edu.gdmec.android.retrofit.Bean.MovieBean;
import cn.edu.gdmec.android.retrofit.Movie.Presenter.MoviePresenter;
import cn.edu.gdmec.android.retrofit.Movie.View.IMovieView;
import cn.edu.gdmec.android.retrofit.R;


public class FgMovieFragment extends Fragment implements IMovieView {

    private MoviePresenter moviePresenter;
    private RecyclerView rv_movie_on;
    private ItemMovieOnAdapter movieOnAdapter;
    private SwipeRefreshLayout srl_movie;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_movie, null);

    }

    @SuppressLint("CutPasteId")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviePresenter = new MoviePresenter(this);
        srl_movie = view.findViewById(R.id.srl_movie);
        rv_movie_on = view.findViewById(R.id.rv_movie_hot);
        movieOnAdapter = new ItemMovieOnAdapter(getActivity());
        srl_movie.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        moviePresenter.loadMovie();
        srl_movie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviePresenter.loadMovie();
            }
        });
    }

    @Override
    public void hideDialog() {
        srl_movie.setRefreshing(false);
    }

    @Override
    public void showMovie(MovieBean movieBean) {
        movieOnAdapter.setData(movieBean.getSubjects());
        rv_movie_on.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_movie_on.setAdapter(movieOnAdapter);
    }

    @Override
    public void showErrorMsg(String error) {
        Toast.makeText(getContext(),"加载失败" + error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {
        srl_movie.setRefreshing(true);
    }
}












