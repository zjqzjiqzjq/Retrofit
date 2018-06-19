package cn.edu.gdmec.android.retrofit.News;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.TimerTask;

import cn.edu.gdmec.android.retrofit.Bean.NewsBean;
import cn.edu.gdmec.android.retrofit.News.Presenter.NewsPresenter;
import cn.edu.gdmec.android.retrofit.News.View.INewsView;
import cn.edu.gdmec.android.retrofit.R;

public class FgNewsListFragment extends Fragment implements INewsView {

    private NewsPresenter presenter;
    private int type;
    private SwipeRefreshLayout srl_news;
    private RecyclerView rv_news;
    private TextView tv_news_list;
    private ItemNewsAdapter adapter;
    private List<NewsBean.Bean> newsBeanList;
    private LinearLayoutManager layoutManager;
    private int startPage = 0;

    public static FgNewsListFragment newInstance(int type){
        Bundle args = new Bundle();
        FgNewsListFragment fragment = new FgNewsListFragment();
        args.putInt("type",type);
        fragment.setArguments(args);
        return fragment;
    }

    private void loadMore(){
        startPage += 20;
        presenter.loadNews(type,startPage);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_news_list, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type = getArguments().getInt("type");
        presenter = new NewsPresenter(this);
        rv_news = view.findViewById(R.id.rv_news);
        adapter = new ItemNewsAdapter(getActivity());
        tv_news_list = view.findViewById(R.id.tv_news_list);
        srl_news = view.findViewById(R.id.srl_news);
        srl_news.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        presenter = new NewsPresenter(this);
        srl_news.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadNews(type,0);
            }
        });
        presenter.loadNews(type,0);

        rv_news.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        (layoutManager.findLastVisibleItemPosition() + 1) == layoutManager.getItemCount()){
                    loadMore();
                }
            }
        });
    }

    @Override
    public void hideDialog() {
        srl_news.setRefreshing(false);
    }

    @Override
    public void showNews(final NewsBean newsBean) {
        getActivity().runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                switch (type){
                    case FgNewsFragment.NEWS_TYPE_TOP:
                        newsBeanList = newsBean.getTop();
                        break;
                    case FgNewsFragment.NEWS_TYPE_NBA:
                        newsBeanList = newsBean.getNba();
                        break;
                    case FgNewsFragment.NEWS_TYPE_JOKES:
                        newsBeanList = newsBean.getJoke();
                        break;
                }
                Log.i("list","showNews:"+newsBeanList.size());
                adapter.setData(newsBeanList);
                layoutManager = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.VERTICAL,false);
                rv_news.setLayoutManager(layoutManager);
                rv_news.setAdapter(adapter);
                tv_news_list.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showMoreNews(NewsBean newsBean) {
        switch (type){
            case FgNewsFragment.NEWS_TYPE_TOP:
                adapter.addData(newsBean.getTop());
                break;
            case FgNewsFragment.NEWS_TYPE_NBA:
                adapter.addData(newsBean.getNba());
                break;
            case FgNewsFragment.NEWS_TYPE_JOKES:
                adapter.addData(newsBean.getJoke());
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(Throwable throwable) {
        adapter.notifyItemRemoved(adapter.getItemCount());
        Toast.makeText(getContext(),"加载失败" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {
        srl_news.setRefreshing(true);
    }
}
