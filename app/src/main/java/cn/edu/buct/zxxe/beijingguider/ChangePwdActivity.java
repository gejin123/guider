package cn.edu.buct.zxxe.beijingguider;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 84978 on 2018/1/6.
 */

public class ChangePwdActivity extends AppCompatActivity {
    private static final String TAG = "ChangePwdActivity";
    private EditText editRawPwd;
    private EditText editNewPwd;
    private EditText editNewMorePwd;

    private Button btnConfirm;
    private Button btnReset;
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
        setContentView(R.layout.activity_change_pwd);
        editRawPwd = findViewById(R.id.edit_raw_pwd);
        editNewPwd = findViewById(R.id.edit_new_pwd);
        editNewMorePwd = findViewById(R.id.edit_more_new_pwd);

        btnConfirm = findViewById(R.id.btn_confirm_change_pwd);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isEmpty = editRawPwd.getText().toString().isEmpty() || editNewPwd.getText
                        ().toString().isEmpty() || editNewMorePwd.getText().toString().isEmpty();
                if (isEmpty) {
                    Utility.showText(ChangePwdActivity.this, "密码不可为空");
                    return;
                }
                boolean rawPwdTrue = editRawPwd.getText().toString().equals(Utility
                        .getCurPassword().toString());
                boolean newPwdConfirm = editNewPwd.getText().toString().equals(editNewMorePwd
                        .getText().toString());
                if (rawPwdTrue) {
                    if (newPwdConfirm) {
                        String username = Utility.getCurUsername();
                        String newPassword = editNewPwd.getText().toString();
                        Account account = DataSupport.where("username = ?", username).findFirst
                                (Account.class);
                        account.setPassword(newPassword);
                        int i = account.updateAll("username = ?", username);
                        Log.d(TAG, "onClick: updateAll: " + i);
//                        {
//                            Account a = DataSupport.where("username = ?", username).findFirst
//                                    (Account.class);
//                            Log.d(TAG, "onClick: " + a.getUsername() + " " + a.getPassword());
//                        }
                        Utility.resetData();
                        {
                            Account a = new Account();
                            for (Account ac : DataBase.getAccountList()) {
                                if (ac.getUsername().equals(username)) {
                                    a = ac;
                                    break;
                                }
                            }
                            Log.d(TAG, "onClick: " + a.getUsername() + " " + a.getPassword());
                        }
//                        account.save();
                        LoginActivity.editor.putBoolean("RememberPassword", false);
                        LoginActivity.editor.putBoolean("AutoLogin", false);
                        LoginActivity.editor.apply();
                        Utility.showText(ChangePwdActivity.this, "修改成功，请重新登录");
                        Timer timer = new Timer();
                        TimerTask timerTask = new TimerTask() {
                            @Override
                            public long scheduledExecutionTime() {
                                return super.scheduledExecutionTime();
                            }

                            @Override
                            public void run() {
//                                Utility.removeAllActivity();
//                                Intent intent = new Intent(ChangePwdActivity.this, LoginActivity
//                                        .class);
//                                startActivity(intent);

                                Intent intent = getPackageManager().getLaunchIntentForPackage
                                        (getPackageName());
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        };
                        timer.schedule(timerTask, 1000);

                    } else {
                        Utility.showText(ChangePwdActivity.this, "两次输入的新密码不一致");
                    }
                } else {
                    Utility.showText(ChangePwdActivity.this, "原密码输入错误");
                }
            }
        });
        btnReset = findViewById(R.id.btn_reset_change_pwd);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editRawPwd.setText("");
                editNewPwd.setText("");
                editNewMorePwd.setText("");
            }
        });
        btnBack = findViewById(R.id.changepwd_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
