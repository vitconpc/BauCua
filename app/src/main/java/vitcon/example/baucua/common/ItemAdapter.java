package vitcon.example.baucua.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vitcon.example.baucua.R;
import vitcon.example.baucua.model.Item;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context mContext;
    private List<Item> mItems;
    private ItemCallback mItemCallback;

    public ItemAdapter(Context context, ItemCallback itemCallback) {
        mContext = context;
        mItemCallback = itemCallback;
    }

    public void setItems(List<Item> items) {
        mItems = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.custom_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.bindData(mItems.get(i));
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImageItem;
        private ImageView mImageAdd;
        private ImageView mImageSub;
        private TextView mTvCoin;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvCoin = itemView.findViewById(R.id.tv_set_coin);
            mImageAdd = itemView.findViewById(R.id.image_add);
            mImageItem = itemView.findViewById(R.id.image_item);
            mImageSub = itemView.findViewById(R.id.image_sub);
            setEvent();
        }

        private void setEvent() {
            mImageAdd.setOnClickListener(this);
            mImageSub.setOnClickListener(this);
        }

        public void bindData(Item item) {
            mImageItem.setImageResource(item.getResource());
            if (item.getCoin() >= 1000) {
                mTvCoin.setText((float) item.getCoin() / 1000 +"k");
                return;
            }
            mTvCoin.setText(item.getCoin() + "");
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_add: {
                    mItemCallback.addCoin(getAdapterPosition());
                }
                break;
                case R.id.image_sub: {
                    mItemCallback.subCoin(getAdapterPosition());
                }
                break;
            }
        }
    }
}
