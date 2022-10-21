package androidsamples.java.dicegames;

import androidx.lifecycle.ViewModel;

public class ChoHanViewModel extends ViewModel {

    private int mBalance;
    private Die6 mDie1;
    private Die6 mDie2;
    private int mWager;

    public ChoHanViewModel() {
        mBalance = 0;
        mDie1 = new Die6();
        mDie2 = new Die6();
        mWager = 0;
    }

    public int getBalance() {
        return mBalance;
    }

    public void setBalance(int mBalance) {
        this.mBalance = mBalance;
    }

    public int getWager() {
        return mWager;
    }

    public void setWager(int mWager) {
        this.mWager = mWager;
    }

    public boolean rollDies() {
        mDie1.roll();
        mDie2.roll();
        return (mDie1.value() + mDie2.value()) % 2 == 0;
    }

    public int die1Value() {
        return mDie1.value();
    }

    public int die2Value() {
        return mDie2.value();
    }
}
