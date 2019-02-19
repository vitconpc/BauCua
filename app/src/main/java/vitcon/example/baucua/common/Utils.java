package vitcon.example.baucua.common;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils {
    public static void saveCoin(Context context, int coin) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.FILE_SAVE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.TOTAL_COIN, coin);
        editor.apply();
    }

    public static int getCoin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.FILE_SAVE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(Constants.TOTAL_COIN, 1000);
    }
}
