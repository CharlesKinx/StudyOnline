package com.example.studyonline_client.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studyonline_client.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.btn_register)
    Button register;

    @BindView(R.id.tx_register_back)
    TextView backLogin;

    @BindView(R.id.et_register_user)
    EditText editTextUser;

    @BindView(R.id.et_register_telephone)
    EditText telephone;

    @BindView(R.id.et_register_password)
    EditText password;

    @BindView(R.id.et_register_password1)
    EditText password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
