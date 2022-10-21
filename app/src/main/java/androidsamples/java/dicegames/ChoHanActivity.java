package androidsamples.java.dicegames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ChoHanActivity extends AppCompatActivity {

    static final String KEY_BALANCE_RETURN = "KEY_BALANCE_RETURN";
    private static final String TAG = "ChoHanActivity";
    private ChoHanViewModel mChoHanVM;
    private TextView mTxtBalance, mTxtWager, mTxtDie1, mTxtDie2;
    private Button mBtnMinus, mBtnPlus, mBtnRoll, mBtnInfo;
    private ToggleButton mToggleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cho_han);

        mBtnMinus = findViewById(R.id.btn_minus);
        mBtnPlus = findViewById(R.id.btn_plus);
        mBtnRoll = findViewById(R.id.btn_roll);
        mToggleBtn = findViewById(R.id.btn_guess);
        mTxtWager = findViewById(R.id.txt_wager);
        mTxtDie1 = findViewById(R.id.txt_die1);
        mTxtDie2 = findViewById(R.id.txt_die2);
        mTxtBalance = findViewById(R.id.txt_balance);
        mBtnInfo = findViewById(R.id.btn_info);

        mChoHanVM = new ViewModelProvider(this).get(ChoHanViewModel.class);

        if(savedInstanceState == null){
            int balance = getIntent().getIntExtra(WalletActivity.KEY_BALANCE, 0);
            mChoHanVM.setBalance(balance);
            updateUI();
        }

        mBtnMinus.setOnClickListener(view -> {
            mChoHanVM.setWager(mChoHanVM.getWager() - 1);
            mTxtWager.setText(Integer.toString(mChoHanVM.getWager()));
        });

        mBtnPlus.setOnClickListener(view -> {
            mChoHanVM.setWager(mChoHanVM.getWager() + 1);
            mTxtWager.setText(Integer.toString(mChoHanVM.getWager()));
        });

        mBtnRoll.setOnClickListener(view -> {
//            System.out.println(mToggleBtn.isChecked());
            if ((mChoHanVM.rollDies() && mToggleBtn.isChecked()) || !(mChoHanVM.rollDies() || mToggleBtn.isChecked())) {
                mChoHanVM.setBalance(mChoHanVM.getBalance() + 2 * mChoHanVM.getWager());
            } else {
                mChoHanVM.setBalance(mChoHanVM.getBalance() - 2 * mChoHanVM.getWager());
            }
            updateUI();
        });

        mBtnInfo.setOnClickListener(view -> {
            String url = getResources().getString(R.string.cho_han_url);
            Uri choHanWiki = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, choHanWiki);
//            if(intent.resolveActivity(getPackageManager())!= null){
//                startActivity(intent);
//            }
//            else{
//                Log.d(TAG, "Cannot Launch Web Browser.");
//            }
            startActivity(intent);
        });

    }

    public void updateUI(){
        mTxtWager.setText(Integer.toString(mChoHanVM.getWager()));
        mTxtBalance.setText(Integer.toString(mChoHanVM.getBalance()));
        mTxtDie1.setText(Integer.toString(mChoHanVM.die1Value()));
        mTxtDie2.setText(Integer.toString(mChoHanVM.die2Value()));
    }


    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
        Intent resIntent = new Intent();
        resIntent.putExtra(KEY_BALANCE_RETURN, mChoHanVM.getBalance());
        setResult(RESULT_CANCELED, resIntent);
        super.onBackPressed();
    }
}