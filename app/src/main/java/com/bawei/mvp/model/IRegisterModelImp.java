package com.bawei.mvp.model;

import com.bawei.mvp.contract.IContractRegister;
import com.bawei.mvp.api.Apis;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IRegisterModelImp implements IContractRegister.IRegisterModel {

    @Override
    public void RegisterResponseData(String userName, String password, CallBack callback) {
        //OKHttp的异步请求
        requestRegisterDataEnqueue(userName, password, callback);
    }

    @Override
    public void LoginResponseData(String userName, String password, CallBack callback) {
        requestLoginDataEnqueue(userName, password, callback);
    }
    // 登录 post
    private void requestLoginDataEnqueue(String userName, String password, final CallBack callback) {

        //创建OKHttp对象
        OkHttpClient client = new OkHttpClient.Builder().build();
        //空的表单请求体 post才有
        FormBody formBody = new FormBody.Builder().build();
        //获取Request对象
        Request request = new Request.Builder()
                .method("POST",formBody)
                .url(Apis.LOGIN_URL+"?phone="+userName+"&pwd="+password)
                .build();
        //开启请求
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.responseData(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //响应数据
                String responseData = response.body().string();
                callback.responseData(responseData);
            }
        });
    }

    // 注册 post
    private void requestRegisterDataEnqueue(String userName, String password, final CallBack callback) {
        //创建OKHttp对象
        OkHttpClient client = new OkHttpClient.Builder().build();
        //空的表单请求体 post才有
        FormBody formBody = new FormBody.Builder().build();
        //获取Request对象
        Request request = new Request.Builder()
                .method("POST",formBody)
                .url(Apis.REGISTER_URL+"?phone="+userName+"&pwd="+password)
                .build();
        //开启请求
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.responseData(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //响应数据
                String responseData = response.body().string();
                callback.responseData(responseData);
            }
        });
    }
}
