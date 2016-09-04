package com.example.kongsin.autogridlayoutmanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kongsin.autogridlayoutmanager.magazine_entities.MagazineItem;
import com.example.tager.Tager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import java.io.IOException;

/**
 * Created by DroidDev on 8/16/16.
 */
public class MagazineListViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "MagazineListViewHolder";
    private CustomImage mMagazineItem;
    private ImageView mHandset;
    private TextView mTextTitle, mTextContent, mTextRating;
    private ProgressBar progressBar;
    private ItemClickCallBack mListener;

    public MagazineListViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.magazine_layout, parent, false));
        initView(itemView);
        itemView.measure(0, 0);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) mListener.onClicked(getAdapterPosition());
                MagazineListViewHolder m = Tager.getInstance().getActualView(MagazineListViewHolder.this);
                Tager.getInstance().sendCallback(m, getAdapterPosition());
            }
        });
    }

    public void setOnClickListener(final ItemClickCallBack listener){
        this.mListener = listener;
    }

    private void initView(View view){
        mMagazineItem = (CustomImage) view.findViewById(R.id.image_view_magazine_fragment_show_magazine_image);
        mHandset = (ImageView) view.findViewById(R.id.imageView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mMagazineItem.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mTextTitle = (TextView) view.findViewById(R.id.textView_magazineFragment_showMagazineTitle);
        mTextContent = (TextView) view.findViewById(R.id.textView_magazineFragment_showSubContent);
        mTextRating = (TextView) view.findViewById(R.id.textView_magazineFragment_showRating);
    }

    public void setProgressBar(MagazineItem magazineItem){
        progressBar.setVisibility(magazineItem.progress ? View.VISIBLE : View.INVISIBLE);
        progressBar.setIndeterminate(magazineItem.progress);
    }

    public void setImageAlpha(MagazineItem magazineItem){
        mMagazineItem.setAlpha(magazineItem.progress ? 1.0F : 0.4F);
    }

    public void showHandSet(MagazineItem magazineItem) {
        mHandset.setVisibility(magazineItem.progress ? View.VISIBLE : View.INVISIBLE);
    }

    public void clearState(){
        progressBar.setVisibility(View.INVISIBLE);
        mMagazineItem.setAlpha(0.4F);
        mHandset.setVisibility(View.INVISIBLE);
    }

    public void showState(MagazineItem magazineItem){
        setImageAlpha(magazineItem);
        setProgressBar(magazineItem);
        showHandSet(magazineItem);
    }

    public void setupData(final MagazineItem magazineItem){
        showState(magazineItem);

        Drawable drawable = new GradientDrawable();
        mMagazineItem.getLayoutParams().width = 344;
        mMagazineItem.getLayoutParams().height = 427;
        drawable.setBounds(0, 0, 344, 427);

        mMagazineItem.requestLayout();
        mMagazineItem.setImageResource(R.mipmap.ic_launcher);

        mMagazineItem.setTag(magazineItem);

        RequestCreator creator = Picasso.with(itemView.getContext()).load(magazineItem.imageUrl).placeholder(drawable);
        creator.resize(itemView.getMeasuredWidth(), 0);
        creator.into(mMagazineItem);

        mMagazineItem.url = magazineItem.imageUrl;
        mMagazineItem.position = getAdapterPosition();
        mMagazineItem.magazineItem = magazineItem;

        mTextTitle.setText(magazineItem.title);
        mTextContent.setText(magazineItem.subtitle);
        mTextRating.setText(String.valueOf(magazineItem.rating));
    }

    private void resizeImageView(RequestCreator url, final CustomImage imageView, final ItemSizeCallBack itemSizeCallBack){

        new AsyncTask<RequestCreator, Void, Bitmap>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Bitmap doInBackground(RequestCreator... params) {
                try {
                    return params[0].get();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                imageView.getLayoutParams().width = bitmap.getWidth();
                imageView.getLayoutParams().height = bitmap.getHeight();
                imageView.magazineItem.width = bitmap.getWidth();
                imageView.magazineItem.height = bitmap.getHeight();
                imageView.isResized = true;
                imageView.requestLayout();
                itemView.requestLayout();
                itemSizeCallBack.onNewSize(imageView.position, bitmap.getWidth(), bitmap.getHeight());
            }
        }.execute(url);
    }

    public interface ItemSizeCallBack{
        void onNewSize(int position, int width, int height);
    }

    public interface ItemClickCallBack{
        void onClicked(int position);
    }

    public class ItemLoader{
        public MagazineListViewHolder mViewHolder;
        public MagazineItem mMagazineItem;
        public int imgWidth;
        public Bitmap bitmap;
        public ItemLoader(MagazineListViewHolder viewHolder, MagazineItem magazineItem){
            this.mMagazineItem = magazineItem;
            this.imgWidth = viewHolder.itemView.getMeasuredWidth();
            this.mViewHolder = viewHolder;
        }
    }
}
