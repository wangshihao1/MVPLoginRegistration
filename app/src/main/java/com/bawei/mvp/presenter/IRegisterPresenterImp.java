package com.bawei.mvp.presenter;

import com.bawei.mvp.contract.IContractRegister;
import com.bawei.mvp.model.IRegisterModelImp;

import java.lang.ref.SoftReference;

public class IRegisterPresenterImp implements IContractRegister.IRegisterPresenter<IContractRegister.IRegisterView>{

    IContractRegister.IRegisterView registerView;
    IRegisterModelImp registerModelImp;
    private SoftReference<IContractRegister.IRegisterView> reference;
    @Override
    public void attachView(IContractRegister.IRegisterView registerView) {
           this.registerView = registerView;
           //软引用包裹
          reference = new SoftReference<>(registerView);
          registerModelImp = new IRegisterModelImp();

    }

    @Override
    public void detachView(IContractRegister.IRegisterView registerView) {
        reference.clear();
    }

    @Override
    public void requstRegisterData(String userName, String password) {
        registerModelImp.RegisterResponseData(userName, password, new IContractRegister.IRegisterModel.CallBack() {
            @Override
            public void responseData(String responseData) {
                registerView.showData(responseData);
            }
        });
    }

    @Override
    public void requstLoginData(String userName, String password) {
        registerModelImp.LoginResponseData(userName, password, new IContractRegister.IRegisterModel.CallBack() {
            @Override
            public void responseData(String responseData) {
                registerView.showData(responseData);
            }
        });
    }
}
