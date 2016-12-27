package com.test.my.complexapp.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.test.my.complexapp.R;
import com.test.my.complexapp.presenters.CacheDBPresenter;
import com.test.my.complexapp.presenters.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CacheDBFragment extends Fragment implements CacheDBPresenter.View{

    @BindView(R.id.recyclerViewUser)
    RecyclerView recyclerView;

    @BindView(R.id.update_button_db)
    Button updateButton;

    @BindView(R.id.text_view_db)
    TextView textView;

    @BindView(R.id.text_view_net)
    TextView netTextView;

    private CacheDBPresenter mPresenter;

    public CacheDBFragment() {
    }

    public static CacheDBFragment instance() {
        CacheDBFragment fragment = new CacheDBFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("CacheDBFragment onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cache_db, container, false);

        ButterKnife.bind(this, view);

        mPresenter = new CacheDBPresenter();

        RxView.clicks(updateButton).subscribe(v -> mPresenter.update());

        mPresenter.attachView(this);
        mPresenter.init();

        System.out.println("CacheDBFragment onCreateView");
        return view;
    }

    @Override
    public RecyclerView getRecyclerView(){
        return recyclerView;
    }

    @Override
    public TextView getStatus(){
        return textView;
    }

    @Override
    public TextView getNetStatus(){
        return netTextView;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        System.out.println("CacheDBFragment onDestroyView");
    }

    @Override
    public void onStart(){
        super.onStart();
        System.out.println("CacheDBFragment onStart");
    }

    @Override
    public void onStop(){
        super.onStop();
        System.out.println("CacheDBFragment onStop");
    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("CacheDBFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("CacheDBFragment onPause");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("CacheDBFragment onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.destroy();
        System.out.println("CacheDBFragment onDetach");
    }
}
