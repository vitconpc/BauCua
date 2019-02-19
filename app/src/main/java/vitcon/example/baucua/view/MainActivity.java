package vitcon.example.baucua.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vitcon.example.baucua.R;
import vitcon.example.baucua.common.Constants;
import vitcon.example.baucua.common.ItemAdapter;
import vitcon.example.baucua.common.ItemCallback;
import vitcon.example.baucua.common.Utils;
import vitcon.example.baucua.model.Item;
import vitcon.example.baucua.presenter.GamePresenter;
import vitcon.example.baucua.presenter.GamePresenterImpl;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener, ItemCallback, MainView, View.OnClickListener {

    private ImageView mImageDisk;
    private ImageView mImageItem1;
    private ImageView mImageItem2;
    private ImageView mImageItem3;
    private TextView mTvTotalCoin;
    private ImageView mImageAddCoin;
    private Button mButtonOpen;
    private Animation mAnimGoIn;
    private Animation mAnimGoOut;
    private GamePresenter mPresenter;
    private ItemAdapter mAdapter;
    private RecyclerView mRecyclerListItem;
    private boolean isOpen;
    private boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setEvent();
    }

    private void setEvent() {
        mAnimGoOut.setAnimationListener(this);
        mAnimGoIn.setAnimationListener(this);
        mButtonOpen.setOnClickListener(this);
        mImageAddCoin.setOnClickListener(this);
    }

    private void initView() {
        mImageAddCoin = findViewById(R.id.image_add_coin);
        mTvTotalCoin = findViewById(R.id.tv_total_coin);
        mImageDisk = findViewById(R.id.image_disk);
        mImageItem1 = findViewById(R.id.item_1);
        mImageItem2 = findViewById(R.id.item_2);
        mImageItem3 = findViewById(R.id.item_3);
        mRecyclerListItem = findViewById(R.id.rv_list_item);
        mButtonOpen = findViewById(R.id.button_open);
        isOpen = true;
        isLoading = false;
        mButtonOpen.setText(getString(R.string.vibrate));
        mImageDisk.setVisibility(View.INVISIBLE);
    }

    private void initData() {
        mAnimGoOut = AnimationUtils.loadAnimation(this, R.anim.anim_go_out);
        mAnimGoIn = AnimationUtils.loadAnimation(this, R.anim.anim_go_in);
        mImageItem1.setImageResource(R.drawable.huou1);
        mImageItem2.setImageResource(R.drawable.huou1);
        mImageItem3.setImageResource(R.drawable.huou1);
        showTotalCoin(Utils.getCoin(this));
        mPresenter = new GamePresenterImpl(this, this);
        mAdapter = new ItemAdapter(this, this);
        mAdapter.setItems(mPresenter.getItems());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerListItem.setNestedScrollingEnabled(false);
        mRecyclerListItem.setLayoutManager(gridLayoutManager);
        mRecyclerListItem.setAdapter(mAdapter);

    }

    @Override
    public void onAnimationStart(Animation animation) {
        isLoading = true;
        mButtonOpen.setEnabled(false);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        isLoading = false;
        mButtonOpen.setEnabled(true);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void addCoin(int position) {
        if (!isLoading){
            mPresenter.addCoin(position);
        }
    }

    @Override
    public void subCoin(int position) {
        if (!isLoading){
            mPresenter.subCoin(position);
        }
    }

    @Override
    public void notifyItem(int position) {
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_add_coin: {
                mPresenter.addMyCoin();
            }
            break;
            case R.id.button_open: {
                if (isOpen) {
                    mPresenter.closeDisk();
                    mImageDisk.startAnimation(mAnimGoIn);
                    mImageDisk.setVisibility(View.VISIBLE);
                    isOpen = false;
                    mButtonOpen.setText(getString(R.string.open));
                    return;
                }
                mPresenter.openDisk();
                mImageDisk.startAnimation(mAnimGoOut);
                mImageDisk.setVisibility(View.INVISIBLE);
                isOpen = true;
                mButtonOpen.setText(getString(R.string.vibrate));
            }
            break;
        }
    }

    @Override
    public void showTotalCoin(int totalCoin) {
        if (totalCoin >= 1000) {
            mTvTotalCoin.setText((float) totalCoin / 1000 + Constants.MY_K_COIN);
            return;
        }
        mTvTotalCoin.setText(totalCoin + Constants.MY_COIN);
    }

    @Override
    public void setItem(Item item1, Item item2, Item item3) {
        mImageItem1.setImageResource(item1.getResource());
        mImageItem2.setImageResource(item2.getResource());
        mImageItem3.setImageResource(item3.getResource());
    }

    @Override
    public void notifyAllData() {
        mAdapter.notifyDataSetChanged();
    }
}
