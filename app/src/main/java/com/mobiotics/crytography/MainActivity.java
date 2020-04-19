package com.mobiotics.crytography;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.encrypt)
    Button btnEncrypt;

    @BindView(R.id.decrypt)
    Button btnDecrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Crypto String");

    }

    @OnClick({R.id.encrypt, R.id.decrypt})
    void onClick(View view){
        switch (view.getId()){
            case R.id.encrypt:
                startActivity(new Intent(getApplicationContext(), EncryptActivity.class));
                break;

            case R.id.decrypt:
                startActivity(new Intent(getApplicationContext(), DecryptActivity.class));
                break;
        }
    }


}
