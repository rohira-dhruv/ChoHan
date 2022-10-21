package androidsamples.java.dicegames;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {

    private static final String TAG = "WalletViewModel";

    private static final int WIN_VALUE = 6;
    private static final int INCREMENT = 5;

    private int mBalance;
    public Die mDie;

    public WalletViewModel(){
        mDie = new Die6();
        mBalance = 0;
    }

    public int balance(){
        return mBalance;
    }

    public void setBalance(int balance) {
        this.mBalance = balance;
    }

    public void rollDie(){
        mDie.roll();
        if(mDie.value() == WIN_VALUE){
            mBalance += INCREMENT;
        }
    }

    public int dieValue() {
        return mDie.value();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared");
    }
}
