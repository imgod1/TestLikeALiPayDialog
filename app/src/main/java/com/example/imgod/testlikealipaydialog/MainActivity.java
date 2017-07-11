package com.example.imgod.testlikealipaydialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.imgod.testlikealipaydialog.dialog.PayDialog;
import com.example.imgod.testlikealipaydialog.view.CustomerKeyboard;
import com.example.imgod.testlikealipaydialog.view.PasswordEditText;

public class MainActivity extends AppCompatActivity implements CustomerKeyboard.CustomerKeyboardClickListener,
        PasswordEditText.PasswordFullListener {
    private CustomerKeyboard mCustomerKeyboard;
    private PasswordEditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPasswordEditText = (PasswordEditText) findViewById(R.id.pet_top);
        mCustomerKeyboard = (CustomerKeyboard) findViewById(R.id.ckb_main);
        // 设置监听
        mCustomerKeyboard.setOnCustomerKeyboardClickListener(this);
        mPasswordEditText.setOnPasswordFullListener(this);
    }

    /**
     * 键盘数字点击监听回调方法
     */
    @Override
    public void onNumberClick(String number) {
        mPasswordEditText.addPassword(number);
    }

    /**
     * 键盘删除点击监听回调方法
     */
    @Override
    public void delete() {
        mPasswordEditText.deleteLastPassword();
    }

    /**
     * 密码输入完毕回调方法
     */
    @Override
    public void passwordFull(String password) {
        Toast.makeText(this, "密码填充完毕：" + password, Toast.LENGTH_SHORT).show();
    }

    public void onBtnClick(View view) {
        PayDialog payDialog = new PayDialog(this);
        payDialog.setPasswordListener(new PayDialog.PasswordListener() {
            @Override
            public void forgetPwdClick() {
                Toast.makeText(MainActivity.this, "点击了忘记密码按钮", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void fullPwd(String conent) {
                Toast.makeText(MainActivity.this, "密码输入完成:" + conent, Toast.LENGTH_SHORT).show();
            }
        });
        payDialog.show();
    }

}
