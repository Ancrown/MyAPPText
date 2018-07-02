package com.example.administrator.myapptextttttttt.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/11
 * 描述:
 */

public class TextViewSizeActivity extends BaseActivity {
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.aactext)
    AppCompatAutoCompleteTextView aactext;


    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.usernameWrapper)
    TextInputLayout usernameWrapper;

    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.passwordWrapper)
    TextInputLayout passwordWrapper;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                text.setText(edit.getText().toString());
            }
        });
    }

    @Override
    protected void initView() {
        usernameWrapper.setHint("Username");
        passwordWrapper.setHint("Password");

    }

    @Override
    protected int getLayout() {
        return R.layout.aty_textview_size;
    }


    @OnClick(R.id.btn)
    public void onViewClicked() {
        hideKeyboard();
        String username = usernameWrapper.getEditText().getText().toString();
        String password = passwordWrapper.getEditText().getText().toString();

        if (!validateEmail(username)) {

            usernameWrapper.setError("Not a valid email address!");

        } else if (!validatePassword(password)) {

            passwordWrapper.setError("Not a valid password!");

        } else {

            usernameWrapper.setErrorEnabled(false);

            passwordWrapper.setErrorEnabled(false);

            //登陆了
            ToolUtils.showToast(this, "成功了");
        }
    }


    //关闭键盘
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();

    }

    public boolean validatePassword(String password) {
        return password.length() > 5;

    }
}
