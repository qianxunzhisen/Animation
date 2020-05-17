package com.wuqiqi.animation.mvp.presenter;


import com.wuqiqi.animation.mvp.base.BasePresenter;
import com.wuqiqi.animation.mvp.bean.BaseObjectBean;
import com.wuqiqi.animation.mvp.bean.LoginBean;
import com.wuqiqi.animation.mvp.contract.MainContract;
import com.wuqiqi.animation.mvp.model.MainModel;
import com.wuqiqi.animation.net.RxScheduler;

import io.reactivex.functions.Consumer;

/**
 * @author azheng
 * @date 2018/6/4.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：
 */
public class LoginPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private MainContract.Model model;

    public LoginPresenter() {
        model = new MainModel();
    }

    @Override
    public void login(String username, String password) {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        model.login(username, password)
                .compose(RxScheduler.<BaseObjectBean<LoginBean>>Flo_io_main())
                .as(mView.<BaseObjectBean<LoginBean>>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean<LoginBean>>() {
                    @Override
                    public void accept(BaseObjectBean<LoginBean> bean) throws Exception {
                        if(isViewAttached()) {
                            mView.onSuccess(bean);
                            mView.hideLoading();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(isViewAttached()) {
                            mView.onError(throwable);
                            mView.hideLoading();
                        }
                    }
                });
    }
}
