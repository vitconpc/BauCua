package vitcon.example.baucua.view;

import vitcon.example.baucua.model.Item;

public interface MainView {

    void notifyItem(int position);

    void notifyAllData();

    void showErrorMessage(String message);

    void showTotalCoin(int totalCoin);

    void setItem(Item item1, Item item2, Item item3);

}
