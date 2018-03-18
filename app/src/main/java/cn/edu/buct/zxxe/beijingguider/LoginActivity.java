package cn.edu.buct.zxxe.beijingguider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private ProgressDialog dialog;
    private EditText editUsername;
    private EditText editPassword;
    private Button btnLogin;
    private ImageView imageEye;
    private CheckBox checkRemember;
    private CheckBox checkAutoLogin;
    private TextView registerText;
    private String username;

    @Override
    protected void onDestroy() {
        Utility.removeActivity(this);
        super.onDestroy();
    }

    private String password;
    private boolean isShowPwd;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.addActivity(this);
        setContentView(R.layout.activity_login);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        DataBase.getDataBase();
        initView();
    }

    private void initView() {
        editUsername = findViewById(R.id.account);
        editPassword = findViewById(R.id.key);
        checkRemember = findViewById(R.id.remember_pass);
        checkRemember.setChecked(LoginActivity.sharedPreferences.getBoolean
                ("RememberPassword", false));
        checkAutoLogin = findViewById(R.id.autologin);
        checkAutoLogin.setChecked(LoginActivity.sharedPreferences.getBoolean("AutoLogin", false));
        checkAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    checkRemember.setChecked(true);
                }
            }
        });
        isShowPwd = false;
        imageEye = findViewById(R.id.eye);
        imageEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShowPwd = !isShowPwd;
                if (isShowPwd) {
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance
                            ());
                } else {
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod
                            .getInstance());
                }
            }
        });
        boolean isRemember = sharedPreferences.getBoolean("RememberPassword", false);
        if (isRemember) {
            username = sharedPreferences.getString("Username", "");
            password = sharedPreferences.getString("Password", "");
            editUsername.setText(username);
            editPassword.setText(password);
            checkRemember.setChecked(true);
        }
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        boolean isAutoLogin = sharedPreferences.getBoolean("AutoLogin", false);
        if (isAutoLogin) {
            login();
        }
        registerText = findViewById(R.id.text_register);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在登录，请稍后...");
        dialog.show();
        username = editUsername.getText().toString().trim();
        password = editPassword.getText().toString().trim();
        Log.d(TAG, "login: " + username + " " + password);
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            dialog.cancel();
            Utility.showText(LoginActivity.this, "用户名和密码不能为空");
            return;
        }
        for (Account account : DataBase.getAccountList()) {
            if (account.getUsername().trim().equals(username.trim()) &&
                    account.getPassword().trim().equals(password.trim())) {
                Intent intent = new Intent(this, MainActivity.class);
                savePassword(checkRemember.isChecked());
                autoLogin(checkAutoLogin.isChecked());
                dialog.cancel();
                Utility.setCurUsername(username);
                Utility.setCurPassword(password);
                Utility.showText(LoginActivity.this, "登录成功");
                startActivity(intent);
                finish();
                return;
            }
        }
        dialog.cancel();
        Utility.showText(LoginActivity.this, "用户名或密码错误");
    }

    private void savePassword(boolean isSave) {
        if (isSave) {
            editor.putBoolean("RememberPassword", true);
            editor.putString("Username", username);
            editor.putString("Password", password);
        } else {
            editor.putBoolean("RememberPassword", false);
        }
        editor.apply();
    }

    private void autoLogin(boolean isAutoLogin) {
        if (isAutoLogin) {
            editor.putBoolean("RememberPassword", true);
            editor.putBoolean("AutoLogin", true);
            editor.putString("Username", username);
            editor.putString("Password", password);
        } else {
            editor.putBoolean("AutoLogin", false);
        }
        editor.apply();
    }
}
