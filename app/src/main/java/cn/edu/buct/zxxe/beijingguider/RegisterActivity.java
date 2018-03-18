package cn.edu.buct.zxxe.beijingguider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.litepal.crud.DataSupport;

import java.util.Timer;
import java.util.TimerTask;

public class RegisterActivity extends AppCompatActivity {

    private EditText editUsername;
    private EditText editPwd;
    private EditText editNewPwd;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editUsername = findViewById(R.id.register_username);
        editPwd = findViewById(R.id.register_pwd);
        editNewPwd = findViewById(R.id.register_new_pwd);
        btnRegister = findViewById(R.id.register_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editUsername.getText().toString();
                String pwd = editPwd.getText().toString();
                String newPwd = editNewPwd.getText().toString();
                Account account = DataSupport.where("username = ?", username).findFirst(Account
                        .class);
                if (account != null) {
                    Utility.showText(RegisterActivity.this, "用户名已存在");
                    return;
                }
                if (!pwd.equals(newPwd)) {
                    Utility.showText(RegisterActivity.this, "两次输入的密码不一致");
                    return;
                }
                account = new Account();
                account.setUsername(username);
                account.setPassword(pwd);
                account.save();
                Utility.resetData();
                Utility.showText(RegisterActivity.this, "注册成功！");
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        finish();
                    }
                };
                timer.schedule(timerTask, 1000);
            }
        });

    }
}
