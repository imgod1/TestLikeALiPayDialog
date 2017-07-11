package com.example.imgod.testlikealipaydialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imgod.testlikealipaydialog.R;
import com.example.imgod.testlikealipaydialog.view.CustomerKeyboard;
import com.example.imgod.testlikealipaydialog.view.PasswordEditText;

/**
 * Created by imgod on 2017/7/11.
 */

public class PayDialog extends Dialog implements View.OnClickListener, PasswordEditText.PasswordFullListener, CustomerKeyboard.CustomerKeyboardClickListener {
    private View root;
    private ImageView img_cancel;
    private TextView txt_title;
    private PasswordEditText pet_top;
    private TextView txt_forget_pwd;
    private CustomerKeyboard ckb_main;

    public PayDialog(@NonNull Context context) {
        this(context, R.style.bottom_dialog_style);
    }

    public PayDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        root = LayoutInflater.from(context).inflate(R.layout.dialog_pay, null);
        setContentView(root);
        getWindow().setGravity(Gravity.BOTTOM);
        initViews();
        initEvent();
    }

    private void initEvent() {
        img_cancel.setOnClickListener(this);
        txt_forget_pwd.setOnClickListener(this);
        pet_top.setOnPasswordFullListener(this);
        ckb_main.setOnCustomerKeyboardClickListener(this);
    }

    private void initViews() {
        img_cancel = (ImageView) root.findViewById(R.id.img_cancel);
        txt_title = (TextView) root.findViewById(R.id.txt_title);
        pet_top = (PasswordEditText) root.findViewById(R.id.pet_top);
        txt_forget_pwd = (TextView) root.findViewById(R.id.txt_forget_pwd);
        ckb_main = (CustomerKeyboard) root.findViewById(R.id.ckb_main);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_cancel:
                if (isShowing()) {
                    cancel();
                }
                break;
            case R.id.txt_forget_pwd:
                if (null != passwordListener) {
                    passwordListener.forgetPwdClick();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onNumberClick(String number) {
        pet_top.addPassword(number);
    }

    @Override
    public void delete() {
        pet_top.deleteLastPassword();
    }

    @Override
    public void passwordFull(String content) {
        if (null != passwordListener) {
            passwordListener.fullPwd(content);
        }
    }

    public interface PasswordListener {
        void forgetPwdClick();

        void fullPwd(String conent);
    }

    private PasswordListener passwordListener;

    public void setPasswordListener(PasswordListener passwordListener) {
        this.passwordListener = passwordListener;
    }
}
