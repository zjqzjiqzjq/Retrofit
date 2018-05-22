package cn.edu.gdmec.android.retrofit.Movie;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

import cn.edu.gdmec.android.retrofit.Bean.MovieBean;
import cn.edu.gdmec.android.retrofit.Movie.Presenter.MoviePresenter;
import cn.edu.gdmec.android.retrofit.Movie.View.IMovieView;
import cn.edu.gdmec.android.retrofit.R;


public class FgMovieFragment extends Fragment implements IMovieView {

    private MoviePresenter presenter;
    private int type;
    private TextView tv_movie;
    private SwipeRefreshLayout srl_movie;

    public static FgMovieFragment newInstance(){
        Bundle args = new Bundle();
        FgMovieFragment fragment = new FgMovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_movie, null);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_movie = (TextView) view.findViewById(R.id.tv_news);
        srl_movie = view.findViewById(R.id.srl_news);
        srl_movie.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        presenter = new MoviePresenter(this);
        srl_movie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadNews();
            }
        });
    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showNews(MovieBean movieBean) {

    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void showDialog() {

    }
}
