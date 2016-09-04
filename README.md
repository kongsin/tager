# tager
It is a ViewHolder follower.
You can get actual ViewHolder of each positions.

##Exaple basicly using

##Activity Class
```JAVA
public class MainActivity extends AppCompatActivity implements TagerCallback<MagazineListViewHolder> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tager.getInstance().setCallBack(this);
    }
        
        @Override
    public void onReceived(int position, MagazineListViewHolder viewHolder) {
      //Do something with ViewHolder
    }
}
```
##Adapter Class
```JAVA
public class GridAdapter extends TagerAdapter<MagazineListViewHolder> {
    @Override
    public MagazineListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MagazineListViewHolder(mContext, parent);
    }

    @Override
    public void onBindViewHolder(MagazineListViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setupData(mMagazineItem.get(position));
    }

    @Override
    public int getItemCount() {
        return mMagazineItem.size();
    }
}
```
##ViewHolder Class
```JAVA
public class MagazineListViewHolder extends RecyclerView.ViewHolder {

      private ImageView mProfile;
      ...
      
      public MagazineListViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.magazine_layout, parent, false));
        initView(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Tager.getInstance().sendCallback(MagazineListViewHolder.this);
            }
        });
    }
    
    public void setupData(final MagazineItem magazineItem){
      //set data to view component
    }
}
```
## Pin
####Single Selection
####Multi Selecteion

Pin is feature to remember which item was selected for single item selected and multiple item selected

##Example

```JAVA
@Override
public void onReceived(int position, MagazineListViewHolder viewHolder) {
    Tager.getInstance().pin(viewHolder);
    //Righ now you we already saved item
}
```
###Do we also can get which item was selected
```JAVA
@Override
public void onReceived(int position, MagazineListViewHolder viewHolder) {
    if(Tager.getInstance().pinniedSize() > 0){
        PinningObject pinnedObject = Tager.getInstance().getPinnedItem(0).getViewHolder();
        MagazineListViewHolder pinnedViewHolder = pinnedObject.getViewHolder();
        if (Tager.getInstance().isEqualWithPinnedView(viewHolder)){
            Log.i(TAG, "Yeah! you selected the same item");
        }
    }
}
```
