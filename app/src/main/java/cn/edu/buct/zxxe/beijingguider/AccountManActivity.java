package cn.edu.buct.zxxe.beijingguider;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 84978 on 2018/1/6.
 */

public class AccountManActivity extends AppCompatActivity {
    private static final String TAG = "AccountManActivity";
    private TextView textChangePwd;
    private CheckBox checkRememberPwd;
    private CheckBox checkAutoLogin;
    private ImageView btnBack;

    @Override
    protected void onDestroy() {
        Utility.removeActivity(this);
        super.onDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.addActivity(this);
        setContentView(R.layout.activity_account_man);
        textChangePwd = findViewById(R.id.text_change_pwd);
        textChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountManActivity.this, ChangePwdActivity.class);
                startActivity(intent);
            }
        });
        checkRememberPwd = findViewById(R.id.checkbox_remember_pwd);
        checkRememberPwd.setChecked(LoginActivity.sharedPreferences.getBoolean
                ("RememberPassword", false));
        checkRememberPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                LoginActivity.editor.putBoolean("RememberPassword", b);
                LoginActivity.editor.apply();
            }
        });
        checkAutoLogin = findViewById(R.id.check_auto_login);
        checkAutoLogin.setChecked(LoginActivity.sharedPreferences.getBoolean("AutoLogin", false));
        checkAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                LoginActivity.editor.putBoolean("AutoLogin", b);
                LoginActivity.editor.apply();
            }
        });
        btnBack = findViewById(R.id.account_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
