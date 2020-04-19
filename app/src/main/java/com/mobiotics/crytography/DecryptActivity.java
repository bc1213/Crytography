package com.mobiotics.crytography;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DecryptActivity extends AppCompatActivity {
    String decodedString = "";

    @BindView(R.id.inputText)
    EditText etInputText;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    @BindView(R.id.convertedText)
    TextView tvConvertedText;

    private boolean isBtnClicked = false;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);

        ButterKnife.bind(this);
        mContext = this;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Decryption");

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    btnSubmit.setEnabled(true);
                }else{
                    btnSubmit.setEnabled(false);
                }
                if(isBtnClicked){
                    tvConvertedText.setText("");
                    isBtnClicked = false;
                    decodedString = "";
                }
            }
        };

        etInputText.addTextChangedListener(textWatcher);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encyptTheData(etInputText.getText().toString());
                isBtnClicked = true;
                Utility.hideKeyPad(mContext, etInputText);
                decodedString = "";
            }
        });
    }

    private void encyptTheData(String decrypted) {
        char[] encryptedChars= new char[decrypted.length()];

        for(int i=0; i<decrypted.length(); i++){
            encryptedChars[i] = decrypted.charAt(i);
        }

        for(int i=0; i<encryptedChars.length;i++){
            if(((i+1) < encryptedChars.length) && Character.isDigit(encryptedChars[i+1])){
                if(Integer.parseInt(String.valueOf(encryptedChars[i+1])) == 2){
                    decodedString = decodedString + String.valueOf(encryptedChars[i]) + String.valueOf(encryptedChars[i]);
                }else{
                    decodedString = decodedString + String.valueOf(encryptedChars[i]);
                }
                i++;
            }else{
                decodedString = decodedString + String.valueOf(encryptedChars[i]);
            }
        }
        tvConvertedText.setText(decodedString);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
