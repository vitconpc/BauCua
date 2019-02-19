package vitcon.example.baucua.model;

public class Item {
    private int mId;
    private int mResource;
    private int mCoin;

    public Item(int id, int resource, int coin) {
        mId = id;
        mResource = resource;
        mCoin = coin;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getResource() {
        return mResource;
    }

    public void setResource(int resource) {
        mResource = resource;
    }

    public int getCoin() {
        return mCoin;
    }

    public void setCoin(int coin) {
        mCoin = coin;
    }
}
