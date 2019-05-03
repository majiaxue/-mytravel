package com.example.administrator.travel01.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity <V extends BaseMvpView,P extends BasePresenter>  extends AppCompatActivity implements BaseMvpView{
    //每次都要在子类中强转
    //protected BasePresenter mPresenter;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter = initPresenter();
        if (mPresenter != null){
            //可以强转,但是不对,但是这个基类的子类肯定会实现BaseMvpView或者他的子类
            //mPresenter.bind((BaseMvpView) this);
            mPresenter.bind((V)this);
        }
        initView();
        initListener();
        initData();
    }

    protected abstract P initPresenter();

    protected void initListener() {

    }

    protected void initData() {

    }

    protected void initView(){

    };

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();
        mPresenter = null;
    }

}
