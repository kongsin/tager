# tager
It is a ViewHolder follower.
You can get actual ViewHolder of each positions.

##Exaple basicly using

##Activity Class

public class MainActivity extends AppCompatActivity implements TagerCallback<MagazineListViewHolder> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tager.getInstance().setCallBack(this);
        }
        
        @Override
    public void onReceived(int position, MagazineListViewHolder viewHolder) {
      //Do something with ViewHolder
    }
  }

##Adapter Class

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

##ViewHolder Class

public class MagazineListViewHolder extends RecyclerView.ViewHolder {

      private ImageView mProfile;
      ...
      
      public MagazineListViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.magazine_layout, parent, false));
        initView(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MagazineListViewHolder m = Tager.getInstance().getActualView(MagazineListViewHolder.this);
                Tager.getInstance().sendCallback(m, getAdapterPosition());
            }
        });
    }
    
    public void setupData(final MagazineItem magazineItem){
      //set data to view component
    }
    
}
