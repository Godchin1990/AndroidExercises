package com.example.junyizhou.recyclerviewdemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junyizhou.recyclerviewdemo.R;
import com.example.junyizhou.recyclerviewdemo.decoration.DividerItemDecoration;
import com.example.junyizhou.recyclerviewdemo.foundation.IOnItemClickListener;
import com.example.junyizhou.recyclerviewdemo.foundation.IOnItemLongClickListener;
import com.example.junyizhou.recyclerviewdemo.foundation.RecyclerListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunyiZhou on 2016/1/4.
 */
public class LinearListDemoFragment extends RecyclerListFragment implements IOnItemClickListener, IOnItemLongClickListener{

    private List<String> mDataList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setOnItemClickListener(this);
        setOnItemLongClickListener(this);
    }

    @Override
    public ViewHolder getViewHolder(ViewGroup parent) {
        return new CharViewHolder(parent);
    }

    protected void initData() {
        mDataList = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDataList.add("" + (char) i);
        }
        setDataList(mDataList);
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public RecyclerView.ItemDecoration createItemDecoration() {
        return new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
    }

    @Override
    public RecyclerView.ItemAnimator createItemAnimator() {
        DefaultItemAnimator mDefaultItemAnimator = new DefaultItemAnimator();
        mDefaultItemAnimator.setAddDuration(1000);
        mDefaultItemAnimator.setChangeDuration(1000);
        mDefaultItemAnimator.setMoveDuration(1000);
        mDefaultItemAnimator.setRemoveDuration(1000);
        return mDefaultItemAnimator;
    }

    @Override
    public void onItemClick(View view, int position) {
        addData(position, "CYP");
    }

    @Override
    public void onItemLongClick(View view, int position) {
        removeData(position);
    }

    class CharViewHolder extends ViewHolder<String> {

        TextView tvChar;

        public CharViewHolder(ViewGroup parent) {
            this(LayoutInflater.from(getActivity()).inflate(R.layout.item_linear, parent, false));
        }

        public CharViewHolder(View view) {
            super(view);
            tvChar = (TextView) view.findViewById(R.id.tv_char);
            tvChar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
            if (v.getId() == R.id.tv_char) {
                Toast.makeText(getActivity(), "我是" + mDataList.get(getPosition()), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void bind(String item, int position) {
            tvChar.setText(item);
        }
    }
}
