package com.department.assessment.ui.on_boarding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.department.assessment.R;
import com.department.assessment.utils.AppConstants;
import com.department.assessment.utils.HelperClass;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

public class LoginActivity extends AppCompatActivity {

    private int NUMBER_OF_PAGES = 3;
    private CarouselView customCarouselView;
    private ImageView ivCorousel;
    private TextView tvTitle, tvSubTitle, signUp;
    private Button loginBtn;
    private EditText userName, password;

    private int[] images = new int[]{
            R.drawable.movie_img1, R.drawable.movie_img2, R.drawable.movie_img3
    };

    String[] titles = {"Lights, Camera, Action!", "Enter the World of Cinematic Delights", "Your Personal Movie Haven"};
    String[] subTitles = {"Your Ultimate Movie Companion on the Go",
            "Immerse Yourself in a World of Cinema",
            "Stay Updated with the Latest Releases and Reviews"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        customCarouselView = (CarouselView) findViewById(R.id.loginCarouselView);
        customCarouselView.setPageCount(NUMBER_OF_PAGES);
        customCarouselView.setViewListener(viewListener);

        userName = findViewById(R.id.mobile_number);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_btn);
        signUp = findViewById(R.id.sign_up_txt);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validate()) {
                    Toast.makeText(LoginActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

        customCarouselView.setIndicatorGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);

    }

    ViewListener viewListener = position -> {

        View customView = getLayoutInflater().inflate(R.layout.login_carousel_one, null);



        ivCorousel = customView.findViewById(R.id.ivCorousel);


        ivCorousel.setImageResource(images[position]);

        return customView;
    };

    private boolean Validate() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(LoginActivity.this);
        String user_name = sharedPreferences.getString(AppConstants.MOBILE,"");
        String user_password = sharedPreferences.getString(AppConstants.PASSWORD,"");
        boolean proceed = true;


        if (userName.getText().toString().trim().length() <= 0) {
            proceed = false;
            userName.setError("Mobile number can't be empty");
        }

        if (password.getText().toString().trim().length() <= 0) {
            proceed = false;
            password.setError("Password can't be empty");
        }

        if (!password.getText().toString().equals(user_password) && !userName.getText().toString().equals(user_name)) {
            HelperClass.showSnackbarwithMsg(LoginActivity.this,"Invalid login details");
            return false;
        }

        return proceed;
    }
}