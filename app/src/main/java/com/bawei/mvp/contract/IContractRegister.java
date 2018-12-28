package com.bawei.mvp.contract;

public interface IContractRegister {

    //v层接口
    public interface IRegisterView{
        //数据展示
        public void showData(String responseData);
    }

    //P层接口
    public interface IRegisterPresenter<IRegisterView>{
        // 绑定
        public void attachView(IRegisterView registerView);
        // 解绑
        public void detachView(IRegisterView registerView);

        public void requstRegisterData(String userName, String password);
        //数据请求，请求M层数据，做登录处理
        public void requstLoginData(String userName, String password);
    }

    // M层接口
    public interface IRegisterModel{

        public interface CallBack{
            public void responseData(String responseData);
        }
        //做注册的接口的请求
        public void RegisterResponseData(String userName, String password, CallBack callback);
        //做登录的接口的请求
        public void LoginResponseData(String userName, String password, CallBack callback);
    }
}
