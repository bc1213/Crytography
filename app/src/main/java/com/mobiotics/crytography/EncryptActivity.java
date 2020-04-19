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

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EncryptActivity extends AppCompatActivity {

    String newString = "";

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
        setContentView(R.layout.activity_encrypt);
        ButterKnife.bind(this);
        mContext = this;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Encryption");

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
                    newString = "";
                }
            }
        };

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encyptTheData(etInputText.getText().toString());
                isBtnClicked = true;
                newString = "";
                Utility.hideKeyPad(mContext, etInputText);
            }
        });

        etInputText.addTextChangedListener(textWatcher);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void encyptTheData(String encypt) {
        char[] ch = new char[encypt.length()];

        // Copy character by character into array
        for (int i = 0; i < encypt.length(); i++) {
            ch[i] = encypt.charAt(i);
        }

        for(int i=0; i<ch.length;i++){

            if(((i+1) < ch.length) && ch[i] == ch[i+1]){
                newString = newString + String.valueOf(ch[i])+"2";
                i++;
            }else{
                newString = newString + String.valueOf(ch[i])+"1";
            }
        }
        tvConvertedText.setText(newString);
    }
}
