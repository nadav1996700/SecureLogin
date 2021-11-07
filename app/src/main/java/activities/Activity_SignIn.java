package activities;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.securelogin.R;

public class Activity_SignIn extends AppCompatActivity {
    private EditText password;
    private TextView message;
    private Button sign_in;
    private ProgressBar progressBar;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setValues();

        sign_in.setOnClickListener(view -> checkCredentials());
    }

    // check credentials and move to actions page
    private void checkCredentials() {
        progressBar.setVisibility(View.VISIBLE);
        if(password.getText() != null && checkBattery() && checkBluetooth() && checkBrightness()
            && checkOrientation()) {
            message.setText(R.string.LoginSuccessfully);
            message.setTextColor(Color.GREEN);
        }
        else {
            message.setText(R.string.LoginFailed);
            message.setTextColor(Color.RED);
        }
        message.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    // check if Brightness value is less than 50
    private boolean checkBrightness() {
        int curBrightnessValue = android.provider.Settings.System.getInt(getContentResolver(),
                android.provider.Settings.System.SCREEN_BRIGHTNESS,-1);
        return curBrightnessValue < 50;
    }

    // check if Bluetooth is enabled
    private boolean checkBluetooth() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            return false;
        } else return mBluetoothAdapter.isEnabled();
    }

    // check that orientation is portrait
    private boolean checkOrientation() {
        int orientation = getResources().getConfiguration().orientation;
        boolean condition = orientation != Configuration.ORIENTATION_LANDSCAPE;
        Log.d("pttt", "orientation: " + condition);
        return orientation != Configuration.ORIENTATION_LANDSCAPE;
    }

    // check if password is equal to battery percentage
    private boolean checkBattery() {
        // Call battery manager service
        BatteryManager bm = (BatteryManager) context.getSystemService(BATTERY_SERVICE);
        // Get the battery percentage and store it in a INT variable
        int batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        return Integer.parseInt(password.getText().toString()) == batLevel;
    }

    // initialize variables
    private void setValues() {
        password = findViewById(R.id.SignIn_EDT_password);
        message = findViewById(R.id.SignIn_TV_error);
        sign_in = findViewById(R.id.SignIn_BTN_login);
        progressBar = findViewById(R.id.SignIn_PB_progressBar);
    }
}