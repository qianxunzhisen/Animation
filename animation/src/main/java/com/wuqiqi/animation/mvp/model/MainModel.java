package com.wuqiqi.animation.mvp.model;



import com.wuqiqi.animation.mvp.bean.BaseObjectBean;
import com.wuqiqi.animation.mvp.bean.LoginBean;
import com.wuqiqi.animation.mvp.contract.MainContract;
import com.wuqiqi.animation.net.RetrofitClient;

import io.reactivex.Flowable;

/**
 * @author wuqiqi
 * @date 2018/6/4.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：
 */
public class MainModel implements MainContract.Model {
    @Override
    public Flowable<BaseObjectBean<LoginBean>> login(String username, String password) {
        return RetrofitClient.getInstance().getApi().login(username,password);
    }
}
