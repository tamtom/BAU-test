package com.itdeveapps.bau;

import android.content.Intent;
import android.os.DropBoxManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itdeveapps.bau.API.ApiClient;
import com.itdeveapps.bau.model.Entry;
import com.itdeveapps.bau.model.Link;
import com.itdeveapps.bau.model.MainResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
private RecyclerView rvNews ;
    private TextView tvNoInternet;
    private Button retry;
    private ProgressBar progressBar;
    private NewsAdpater mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvNews = (RecyclerView) findViewById(R.id.recycler_view);
        tvNoInternet = (TextView) findViewById(R.id.no_internet_text);
        retry = (Button) findViewById(R.id.retry);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);
        noInternet(true);
        fetch();
    }
    void  initAdapter(ArrayList<Entry> list){
        if(list == null)
            return;
        mAdapter = new NewsAdpater(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvNews.setLayoutManager(mLayoutManager);
        rvNews.setItemAnimator(new SlideInUpAnimator());
        rvNews.setAdapter(mAdapter);
    }
    void noInternet(boolean isConnected){
        tvNoInternet.setVisibility(isConnected?View.GONE:View.VISIBLE);
        retry.setVisibility(isConnected?View.GONE:View.VISIBLE);
    }
    class NewsAdpater extends RecyclerView.Adapter<NewsAdpater.MyViewHolder> {

        private List<Entry> newsList;

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public ImageView ivThumpnail;
            public TextView title;
            public Entry entry;

            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.title);
                ivThumpnail = (ImageView) view.findViewById(R.id.image);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Details.class);
                ArrayList<Link> links = entry.getLinks();
                for(Link link : links)
                {
                    if(link.getRel().equals(Link.LINK)){
                        intent.putExtra(Details.EXTRA_URL,link.getHref());
                                break;
                    }
                }
                startActivity(intent);

            }

            public void bind(int pos) {
               entry = newsList.get(pos);
               title.setText(entry.getTitle().getText());
                if(entry.getThumbnail() != null){
                    Picasso.with(getApplicationContext()).load(entry.getThumbnail().getUrl()).into(ivThumpnail);
                }
                else {
                    Picasso.with(getApplicationContext()).load(R.drawable.ic_bau).into(ivThumpnail);
                }
            }
        }


        public NewsAdpater(List<Entry> moviesList) {
            this.newsList= moviesList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_card_new, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
           holder.bind( position);


        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }
    }
    void fetch(){
        if(progressBar!=null)
            progressBar.setVisibility(View.VISIBLE);
        ApiClient.getApi().getResult()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MainResult>() {
                    @Override
                    public void onCompleted() {
                        if(progressBar!=null)
                            progressBar.setVisibility(View.GONE);
                        noInternet(true);
                    }

                    @Override
                    public void onError(Throwable e) {

                        if(progressBar!=null)
                            progressBar.setVisibility(View.GONE);
                        noInternet(false);
                    }

                    @Override
                    public void onNext(MainResult mainResult) {
                        initAdapter((ArrayList<Entry>) mainResult.getFeed().getEntry());

                    }
                });
    }

}
