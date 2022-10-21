package androidsamples.java.dicegames;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class WalletActivity extends AppCompatActivity {

    private static final String TAG = "WalletActivity";
    protected static final String KEY_BALANCE = "KEY_BALANCE";
    private static final int CHOHAN_REQUEST_CODE = 2;

    private TextView mTxtBalance;
    private Button mBtnRoll;
    private Button mBtnLaunchChoHan;
    private WalletViewModel mWalletVM;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_wallet);

        mTxtBalance = findViewById(R.id.txt_balance);
        mBtnRoll = findViewById(R.id.btn_roll);
        mBtnLaunchChoHan = findViewById(R.id.btn_launch_chohan);

        mWalletVM = new ViewModelProvider(this).get(WalletViewModel.class);

        updateUI();

        mBtnRoll.setOnClickListener(view -> {
            //roll the die.
            mWalletVM.rollDie();
            //update UI.
            updateUI();
        });

        mBtnLaunchChoHan.setOnClickListener(view -> {
            Intent intent = new Intent(this, ChoHanActivity.class);
            intent.putExtra(KEY_BALANCE, mWalletVM.balance());
            startActivityForResult(intent, CHOHAN_REQUEST_CODE);
        });

    }

//    @Override
//    public void onStart(){
//        super.onStart();
//        Log.d(TAG, "onStart");
//    }
//
//    @Override
//    public void onResume(){
//        super.onResume();
//        Log.d(TAG, "onResume");
//    }
//
//    @Override
//    public void onPause(){
//        super.onPause();
//        Log.d(TAG, "onPause");
//    }
//
//    @Override
//    public void onStop(){
//        super.onStop();
//        Log.d(TAG, "onStop");
//    }
//
//    @Override
//    public void onDestroy(){
//        super.onDestroy();
//        Log.d(TAG, "onDestroy");
//    }

    private void updateUI() {
        mBtnRoll.setText(Integer.toString(mWalletVM.dieValue()));
        mTxtBalance.setText(Integer.toString(mWalletVM.balance()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult");
        if(requestCode == CHOHAN_REQUEST_CODE && (resultCode == RESULT_OK || resultCode == RESULT_CANCELED)){
            if (data != null) {
                int balance = data.getIntExtra(ChoHanActivity.KEY_BALANCE_RETURN, 0);
                Log.d(TAG,"Balance After Return "+balance);
                mWalletVM.setBalance(balance);
                updateUI();
            }

        }
    }
}
