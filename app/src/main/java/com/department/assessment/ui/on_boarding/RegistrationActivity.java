package com.department.assessment.ui.on_boarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.department.assessment.R;
import com.department.assessment.utils.AppConstants;
import com.department.assessment.utils.PreferenceHelper;

public class RegistrationActivity extends AppCompatActivity {

    private Button signUp;
    private EditText name, password, mobile, confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.new_password);
        confirm_password = findViewById(R.id.confirm_password);


        signUp = findViewById(R.id.sign_up_btn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validate()) {
                    String user_name = name.getText().toString();
                    SharedPreferences sharedPreferences = PreferenceManager
                            .getDefaultSharedPreferences(RegistrationActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(AppConstants.NAME, user_name);
                    editor.putString(AppConstants.MOBILE,mobile.getText().toString());
                    editor.putString(AppConstants.PASSWORD,password.getText().toString());
                    editor.apply();

                    Toast.makeText(RegistrationActivity.this, "Registration successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistrationActivity.this, DashboardActivity.class));
                }
            }
        });
    }

    private boolean Validate() {
        boolean proceed = true;

        if (name.getText().toString().trim().length() <= 0) {
            proceed = false;
            name.setError("Name can't be empty");
        }

        if (mobile.getText().toString().trim().length() <= 0) {
            proceed = false;
            mobile.setError("Mobile number can't be empty");
        }

        if (password.getText().toString().trim().length() <= 0) {
            proceed = false;
            password.setError("Password can't be empty");
        }

        if (!password.getText().toString().equals(confirm_password.getText().toString())) {
            confirm_password.setError("Password does not match");
            return false;
        }

        return proceed;
    }
}