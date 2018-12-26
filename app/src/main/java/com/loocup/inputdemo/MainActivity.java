package com.loocup.inputdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText.setFocusable(true);

//        SimpleIME imm = MainActivity.this.getSystemService(SimpleIME.class);
        // 打开软键盘
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//        imm.

        // 关闭软键盘
//        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
