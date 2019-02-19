package vitcon.example.baucua.presenter;

import java.util.List;

import vitcon.example.baucua.model.Item;

public interface GamePresenter {

    void openDisk();

    void closeDisk();

    void addCoin(int position);

    void subCoin(int position);

    List<Item> getItems();

    void addMyCoin();

}
