package activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.securelogin.R;

public class Activity_SignIn extends AppCompatActivity {
    private EditText password;
    private TextView error_message;
    private Button sign_in;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setValues();

        sign_in.setOnClickListener(view -> {
            checkCredentials();
        });
    }

    // check credentials and move to actions page
    private void checkCredentials() {
        if(password.getText() != null && !password.getText().toString().isEmpty()) {
            progressBar.setVisibility(View.VISIBLE);

        }
        else
            error_message.setVisibility(View.VISIBLE);
    }

    // initialize variables
    private void setValues() {
        password = findViewById(R.id.SignIn_EDT_password);
        error_message = findViewById(R.id.SignIn_TV_error);
        sign_in = findViewById(R.id.SignIn_BTN_login);
        progressBar = findViewById(R.id.SignIn_PB_progressBar);
    }
}