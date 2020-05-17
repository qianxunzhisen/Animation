package com.wuqiqi.animation.mvp.ui.act;


import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.wuqiqi.animation.R;
import com.wuqiqi.animation.mvp.base.BaseMvpActivity;
import com.wuqiqi.animation.mvp.bean.BaseObjectBean;
import com.wuqiqi.animation.mvp.contract.MainContract;
import com.wuqiqi.animation.mvp.presenter.LoginPresenter;
import com.wuqiqi.animation.mvp.ui.dialog.ProgressDialog;


public class LoginAct extends BaseMvpActivity<LoginPresenter> implements MainContract.View, View.OnClickListener {


    private TextInputEditText etUsernameLogin;
    private TextInputEditText etPasswordLogin;
    private Button btn_signin_login;

    @Override
    public int getLayoutId() {
        return R.layout.act_login;
    }

    @Override
    public int getTitleId() {
        return R.id.tv_title_toolbar;
    }


    @Override
    public void initView() {
        etUsernameLogin = findViewById(R.id.et_username_login);
        etPasswordLogin = findViewById(R.id.et_password_login);
        btn_signin_login = findViewById(R.id.btn_signin_login);
        setTv_title("登录");
        btn_signin_login.setOnClickListener(this);
        mPresenter = new LoginPresenter();
        mPresenter.attachView(this);

    }

    /**
     * @return 帐号
     */
    private String getUsername() {
        return etUsernameLogin.getText().toString().trim();
    }

    /**
     * @return 密码
     */
    private String getPassword() {
        return etPasswordLogin.getText().toString().trim();
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {

        Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(this);
    }

    @Override
    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.btn_signin_login){
            if (getUsername().isEmpty() || getPassword().isEmpty()) {
                Toast.makeText(this, "帐号密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            mPresenter.login(getUsername(), getPassword());
        }
    }
}
