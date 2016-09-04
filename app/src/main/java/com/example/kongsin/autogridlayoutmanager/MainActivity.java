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
import com.example.tager.Tager;
import com.example.tager.TagerCallback;

public class MainActivity extends AppCompatActivity implements TagerCallback<MagazineListViewHolder> {
    private static final String TAG = "MainActivity";
    private GridAdapter mGridAdapter;
    private RecyclerView mRecyclerView;
    private MagazineListViewHolder mShowingView;
    private int mShowingViewPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tager.getInstance().setCallBack(this);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        Tager.getInstance().attach(mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
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
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                StaggeredGridLayoutManager m = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                int[] c = new int[m.getSpanCount()];
                m.findFirstVisibleItemPositions(c);
                for (int i : c) {
                    if (i == 0) m.invalidateSpanAssignments();
                }
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
                        mGridAdapter = new GridAdapter(MainActivity.this);
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

    @Override
    public void onReceived(int position, MagazineListViewHolder viewHolder) {
        if (Tager.getInstance().pinniedSize() > 0){
            mGridAdapter.getItem(Tager.getInstance().getPinnedItem(0).getPosition()).progress = false;
            if (Tager.getInstance().getPinnedItem(0).getViewHolder().getAdapterPosition() == position){
                ((MagazineListViewHolder)Tager.getInstance().getPinnedItem(0).getViewHolder()).clearState();
                Tager.getInstance().removePinAll();
            } else {
                ((MagazineListViewHolder)Tager.getInstance().getPinnedItem(0).getViewHolder()).clearState();
                Tager.getInstance().removePinAll();

                setValue(viewHolder, position);
                Tager.getInstance().pin(viewHolder);
            }
        } else {
            setValue(viewHolder, position);
            Tager.getInstance().pin(viewHolder);
        }
    }

    private void setValue(MagazineListViewHolder viewHolder, int position) {
        mGridAdapter.getItem(position).progress = !mGridAdapter.getItem(position).progress; // inverse progress value
        viewHolder.showState(mGridAdapter.getItem(viewHolder.getAdapterPosition()));
    }

}
