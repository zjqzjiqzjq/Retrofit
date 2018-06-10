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
import cn.edu.gdmec.android.retrofit.Http.Api;
import cn.edu.gdmec.android.retrofit.Movie.Presenter.MoviePresenter;
import cn.edu.gdmec.android.retrofit.Movie.View.IMovieView;
import cn.edu.gdmec.android.retrofit.R;


public class FgMovieFragment extends Fragment implements IMovieView , View.OnClickListener{

    private MoviePresenter moviePresenter;
    private ItemMovieOnAdapter movieOnAdapter;
    private ItemMovieTopAdapter movieTopAdapter;
    private SwipeRefreshLayout srl_movie;
    private RecyclerView rv_movie_top;
    private RecyclerView rv_movie_on;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_movie, null);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviePresenter = new MoviePresenter(this);
        srl_movie = view.findViewById(R.id.srl_movie);
        rv_movie_on = view.findViewById(R.id.rv_movie_hot);
        rv_movie_top = view.findViewById(R.id.rv_movie_top);

        movieOnAdapter = new ItemMovieOnAdapter(getActivity());
        movieTopAdapter = new ItemMovieTopAdapter(getActivity());
        srl_movie.setColorSchemeColors(Color.parseColor("#ffce3d3a"));


        moviePresenter.loadMovie(Api.MOVIE_ID);
        moviePresenter.loadMovie(Api.MOVIE_ID2);


        srl_movie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviePresenter.loadMovie(Api.MOVIE_ID);
                moviePresenter.loadMovie(Api.MOVIE_ID2);

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
        movieTopAdapter.setData(movieBean.getSubjects());
        rv_movie_on.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_movie_on.setAdapter(movieOnAdapter);
        rv_movie_top.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,false));
        rv_movie_top.setHorizontalScrollBarEnabled(true);
        rv_movie_top.setAdapter(movieTopAdapter);
    }

    @Override
    public void showErrorMsg(Throwable throwable) {
        Toast.makeText(getContext(),"加载失败" + throwable.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {
        srl_movie.setRefreshing(true);
    }

    @Override
    public void onClick(View view) {

    }
}












