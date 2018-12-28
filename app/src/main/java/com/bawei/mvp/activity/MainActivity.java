package com.bawei.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.mvp.contract.IContractRegister;
import com.bawei.mvp.R;
import com.bawei.mvp.presenter.IRegisterPresenterImp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IContractRegister.IRegisterView {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private IContractRegister.IRegisterPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //创建P层
        presenter = new IRegisterPresenterImp();
        presenter.attachView(this);
    }

    @Override
    public void showData(final String responseData) {
          runOnUiThread(new Runnable() {
              @Override
              public void run() {
                  //子线程切换到UI线程中
                  Toast.makeText(MainActivity.this,responseData,Toast.LENGTH_SHORT).show();
              }
          });
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String names = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                //获取P层,触发请求动作
                presenter.requstLoginData(names, password);
                break;
            case R.id.btn_register:
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
