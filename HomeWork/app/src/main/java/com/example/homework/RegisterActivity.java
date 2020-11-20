package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private String realCode;
    private DBOpenHelper mDBOpenHelper;
    private Button mBtRegisteractivityRegister;
    private RelativeLayout mRIRegisteractivityTop;
    private ImageView mlvRegisteractivityBack;
    private LinearLayout mLIRegisteractivityBody;
    private EditText mEtRegisteractivityUser;
    private EditText mEtRegisteractivityPassword1;
    private EditText mEtRegisteractivityPassword2;
    private EditText mEtRegisteractivityPhonecodes;
    private ImageView mlvRegisteractivityShowcode;
    private RelativeLayout mRIRegisteractivityBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        mDBOpenHelper=new DBOpenHelper(this);
        mlvRegisteractivityShowcode.setImageBitmap(Code.getInstance().createBitmap());
        realCode=Code.getInstance().getCode().toLowerCase();
    }
    private void initView() {
        mBtRegisteractivityRegister = findViewById(R.id.bt_registeractivity_register);
        mRIRegisteractivityTop = findViewById(R.id.rl_registeractivity_top);
        mlvRegisteractivityBack = findViewById(R.id.iv_registeractivity_back);
        mLIRegisteractivityBody = findViewById(R.id.ll_registeractivity_body);
        mEtRegisteractivityUser = findViewById(R.id.et_registeractivity_user);
        mEtRegisteractivityPassword1 = findViewById(R.id.et_registeractivity_password1);
        mEtRegisteractivityPassword2 = findViewById(R.id.et_registeractivity_password2);
        mEtRegisteractivityPhonecodes=findViewById(R.id.et_registeractivity_phoneCodes);
        mlvRegisteractivityShowcode=findViewById(R.id.iv_registeractivity_showCode);
        mRIRegisteractivityBottom=findViewById(R.id.rl_registeractivity_buttom);
        mlvRegisteractivityBack.setOnClickListener(this);
        mlvRegisteractivityShowcode.setOnClickListener(this);
        mBtRegisteractivityRegister.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.iv_registeractivity_back:
                    startActivity(new Intent(this,LoginActivity.class));
                    finish();
                    break;
                case R.id.iv_registeractivity_showCode:
                    mlvRegisteractivityShowcode.setImageBitmap(Code.getInstance().createBitmap());
                    realCode=Code.getInstance().getCode().toLowerCase();
                    break;
                case R.id.bt_registeractivity_register:
                    String user=mEtRegisteractivityUser.getText().toString().trim();
                    String key=mEtRegisteractivityPassword2.getText().toString().trim();
                    String phoneCode=mEtRegisteractivityPhonecodes.getText().toString().toLowerCase();

                    if (!TextUtils.isEmpty(user)&&!TextUtils.isEmpty(key)&&!TextUtils.isEmpty(phoneCode)){
                        if (phoneCode.equals(realCode)){
                            mDBOpenHelper.add(user,key);
                            startActivity(new Intent(this,MainActivity.class));
                            finish();
                            Toast.makeText(this,"验证通过，注册成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(this,"验证码错误，注册失败",Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(this,"未完善信息，注册失败",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }


}