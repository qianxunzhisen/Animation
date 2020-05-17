package com.wuqiqi.animation.mvp.contract;



import com.wuqiqi.animation.mvp.base.BaseView;
import com.wuqiqi.animation.mvp.bean.BaseObjectBean;
import com.wuqiqi.animation.mvp.bean.LoginBean;

import io.reactivex.Flowable;
import retrofit2.http.Url;

public interface MainContract {
    interface Model{
        Flowable<BaseObjectBean<LoginBean>> login(String username,String password);
    }

    interface View extends BaseView{
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean<LoginBean> bean);
    }

    interface Presenter{
        /**
         * 登陆
         *
         * @param username
         * @param password
         */
        void login(String username, String password);
    }
}
