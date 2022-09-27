package androidsamples.java.dicegames;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class WalletActivity extends AppCompatActivity {

    private static final String TAG = "WalletActivity";
    private static final String KEY_BALANCE = "KEY_BALANCE";
    private static final String KEY_DIE_VALUE = "KEY_DIE_VALUE";
    private static final int WIN_VALUE = 6;
    private static final int INCREMENT = 5;
    private Die mDie;
    private int mBalance;
    private TextView mTxtBalance;
    private Button mBtnRoll;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_wallet);

        mDie = new Die6();

        mTxtBalance = findViewById(R.id.txt_balance);
        mBtnRoll = findViewById(R.id.btn_roll);

        if(savedInstanceState!=null){
            mBalance = savedInstanceState.getInt(KEY_BALANCE, 0);
            int dieValue = savedInstanceState.getInt(KEY_DIE_VALUE, 0);
            mBtnRoll.setText(Integer.toString(dieValue));
            mTxtBalance.setText(Integer.toString(mBalance));
            Log.d(TAG, "Restored: Balance= "+ mBalance +", die= "+ dieValue);

        }

        mBtnRoll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //roll the die.
                mDie.roll();
                Log.d(TAG, "Die Roll= "+mDie.value());

                //add coins if the win_value is rolled.
                if(mDie.value()==WIN_VALUE){
                    mBalance += INCREMENT;
                    Log.d(TAG, "New Balance= " + mBalance);
                }

                //update UI.
                updateUI();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putInt(KEY_BALANCE, mBalance);
        outState.putInt(KEY_DIE_VALUE, mDie.value());
        Log.d(TAG, "Saved: Balance= "+ mBalance + ", die= "+ mDie.value());
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private void updateUI() {
        mBtnRoll.setText(Integer.toString(mDie.value()));
        mTxtBalance.setText(Integer.toString(mBalance));
    }
}
