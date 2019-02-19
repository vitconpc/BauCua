package vitcon.example.baucua.presenter;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vitcon.example.baucua.R;
import vitcon.example.baucua.common.Utils;
import vitcon.example.baucua.model.Item;
import vitcon.example.baucua.view.MainView;

public class GamePresenterImpl implements GamePresenter {
    private List<Item> mItems;
    private Context mContext;
    private MainView mMainView;
    private int mTotalCoin;
    private int mLostCoin;
    private int mItem1;
    private int mItem2;
    private int mItem3;
    private Random mRandom;

    public GamePresenterImpl(Context context, MainView mainView) {
        mContext = context;
        mMainView = mainView;
        createList();
        mTotalCoin = Utils.getCoin(mContext);
        mLostCoin = 0;
        mItem1 = 0;
        mItem2 = 0;
        mItem3 = 0;
        mRandom = new Random();
    }

    private void createList() {
        mItems = new ArrayList<>();
        mItems.add(new Item(1, R.drawable.huou1, 0));
        mItems.add(new Item(2, R.drawable.bau1, 0));
        mItems.add(new Item(3, R.drawable.ga1, 0));
        mItems.add(new Item(4, R.drawable.ca1, 0));
        mItems.add(new Item(5, R.drawable.cua1, 0));
        mItems.add(new Item(6, R.drawable.tom1, 0));
    }

    @Override
    public List<Item> getItems() {
        return mItems;
    }

    @Override
    public void openDisk() {
        for (int i = 0; i < mItems.size(); i++) {
            if (mItem1 == i) {
                mLostCoin += mItems.get(i).getCoin() * 2;
            }
            if (mItem2 == i) {
                mLostCoin += mItems.get(i).getCoin() * 2;
            }
            if (mItem3 == i) {
                mLostCoin += mItems.get(i).getCoin() * 2;
            }
        }
        mTotalCoin += mLostCoin;
        mMainView.showTotalCoin(mTotalCoin);
        Utils.saveCoin(mContext, mTotalCoin);
        mMainView.setItem(mItems.get(mItem1), mItems.get(mItem2), mItems.get(mItem3));
        mLostCoin = 0;
        resetCoin();
    }

    private void resetCoin() {
        for (int i = 0; i < mItems.size(); i++) {
            mItems.get(i).setCoin(0);
        }
        mMainView.notifyAllData();
    }

    @Override
    public void closeDisk() {
        mItem1 = mRandom.nextInt(6);
        mItem2 = mRandom.nextInt(6);
        mItem3 = mRandom.nextInt(6);
    }

    @Override
    public void addCoin(int position) {
        Item item = mItems.get(position);
        if (mTotalCoin <= 0) {
            mMainView.showErrorMessage(mContext.getString(R.string.can_not_set_coin));
            return;
        }
        item.setCoin(item.getCoin() + 100);
        mTotalCoin -= 100;
        mMainView.showTotalCoin(mTotalCoin);
        mMainView.notifyItem(position);
    }

    @Override
    public void subCoin(int position) {
        Item item = mItems.get(position);
        if (item.getCoin() <= 0) {
            mMainView.showErrorMessage(mContext.getString(R.string.can_not_below_0_coin));
            return;
        }
        item.setCoin(item.getCoin() - 100);
        mTotalCoin += 100;
        mMainView.showTotalCoin(mTotalCoin);
        mMainView.notifyItem(position);
    }

    @Override
    public void addMyCoin() {
        if (mTotalCoin < 2000) {
            mTotalCoin += 2000;
            Utils.saveCoin(mContext, mTotalCoin);
            mMainView.showTotalCoin(mTotalCoin);
            mMainView.showErrorMessage(mContext.getString(R.string.add_2000_coin_success));
            return;
        }
        mMainView.showErrorMessage(mContext.getString(R.string.add_coin_fails));
    }
}
