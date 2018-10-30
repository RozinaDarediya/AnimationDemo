package com.theta.animationdemo.CoordinatorLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by ashish on 1/5/18.
 */

public class TextWatcher1 implements TextWatcher {

    public EditText editText;

    //constructor
    public TextWatcher1(EditText et) {
        super();
        editText = et;
        //Code for monitoring keystrokes
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    editText.setText("");
                }
                return false;
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(editText.getText().length() == 12){
            editText.setText(editText.getText().delete(editText.getText().length() - 1, editText.getText().length()));
            editText.setSelection(editText.getText().toString().length());
        }
        if (editText.getText().length()==2||editText.getText().length()==5||editText.getText().length()==8){
            editText.setText(editText.getText()+"/");
            editText.setSelection(editText.getText().toString().length());
        }
    }
}
