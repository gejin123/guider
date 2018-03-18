package cn.edu.buct.zxxe.beijingguider;

import org.litepal.crud.DataSupport;

/**
 * Created by 84978 on 2018/1/1.
 */

public class Account extends DataSupport {
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
