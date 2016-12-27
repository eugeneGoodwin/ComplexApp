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
import com.test.my.complexapp.presenters.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CacheFragment extends Fragment implements MainPresenter.View {

    private MainPresenter mPresenter;

    @BindView(R.id.update_button)
    Button updateButton;

    @BindView(R.id.text_view)
    TextView textView;

    @BindView(R.id.clock_view)
    TextView clockView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public CacheFragment() {
    }

    public static CacheFragment instance() {
        CacheFragment fragment = new CacheFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("CacheFragment onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cache, container, false);

        ButterKnife.bind(this, view);

        mPresenter = new MainPresenter();

        RxView.clicks(updateButton).subscribe(v -> mPresenter.start());

        mPresenter.attachView(this);
        mPresenter.init();
        System.out.println("CacheFragment onCreateView");
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
    public TextView getClock(){
        return clockView;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onStart(){
        super.onStart();
        System.out.println("CacheFragment onStart");
    }

    @Override
    public void onStop(){
        super.onStop();
        System.out.println("CacheFragment onStop");
    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("CacheFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("CacheFragment onPause");
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        System.out.println("CacheFragment onDestroyView");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("CacheFragment onAttach");
    }

    @Override
    public void onDetach() {
        mPresenter.destroy();
        super.onDetach();
        System.out.println("CacheFragment onDetach");
    }
}
