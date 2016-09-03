package com.example.kongsin.autogridlayoutmanager;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import com.example.kongsin.autogridlayoutmanager.magazine_entities.Magazine;
import com.example.roofit.Caller;
import com.example.roofit.RooFit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private GridAdapter mGridAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        layoutManager.generateDefaultLayoutParams();
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 8;
                outRect.right = 8;
                outRect.top = 8;
                outRect.bottom = 8;
            }
        });
        APILoader apiLoader = new RooFit().create(APILoader.class, getString(R.string.main_domain));
        Caller<Magazine> magazineCaller = apiLoader.getMagazine();
        magazineCaller.setReturnAs(Caller.ReturnAs.OBJECT);
        magazineCaller.enqueue(new Caller.RooFitCallBack<Magazine>() {
            @Override
            public void onResponse(final Magazine magazine) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mGridAdapter = new GridAdapter(MainActivity.this, mRecyclerView);
                        mGridAdapter.setDataSet(magazine.data.itemGroups);
                        mRecyclerView.setAdapter(mGridAdapter);
                    }
                });
            }

            @Override
            public void onFailed(String s) {
                Log.i(TAG, "onFailed: " + s);
            }
        });
    }
}
