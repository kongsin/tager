package com.example.sample;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.tager.Tager;
import com.example.tager.TagerCallback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TagerCallback<GridViewHolder> {

    private ArrayList<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tager.getInstance().setCallBack(this);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 8;
                outRect.left = 8;
                outRect.right = 8;
                outRect.top = 8;
            }
        });

        for (char i = 'A'; i <= 'Z'; i++) {
            mData.add(String.valueOf(i));
        }

        for (char i = 'ก'; i <= 'ฮ'; i++) {
            mData.add(String.valueOf(i));
        }

        GridAdapter adapter = new GridAdapter(this, mData);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onReceived(int position, GridViewHolder viewHolder) {
        if (Tager.getInstance().isMatchWithPinnedView(viewHolder)){
            viewHolder.resetTextColor();
            Tager.getInstance().removePin(position);
        } else {
            viewHolder.setTextColor(Color.BLUE);
            Tager.getInstance().pin(viewHolder);
        }

    }
}
