package com.kcode.reamlexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kcode.reamlexample.model.User;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etAge;
    private TextView tvLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.name);
        etAge = (EditText) findViewById(R.id.age);
        tvLog = (TextView) findViewById(R.id.log);

    }

    public void insert(View view) {

        final String name = etName.getText().toString();
        final String age = etAge.getText().toString();

        if (TextUtils.isEmpty(name)) {
            print("姓名不能为空");
            return;
        }

        if (TextUtils.isEmpty(age)) {
            print("年龄不能为空");
            return;
        }
        print("开始插入");
        Realm realm = Realm.getDefaultInstance();
        /***写法一***/
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class);
                user.setName(name);
                user.setAge(Integer.parseInt(age));

                //也可以使用下面的方法
                /*
                User user = new User();
                user.setName(name);
                user.setAge(Integer.parseInt(age));
                realm.copyToRealm(user);
                */
            }
        });


        /* 写法二
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class);
                user.setName(name);
                user.setAge(Integer.parseInt(age));
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                //成功回调
            }
        });*/

        //写法三
        /*realm.beginTransaction();
        User user = realm.createObject(User.class);
        user.setName(name);
        user.setAge(Integer.parseInt(age));
        realm.commitTransaction();*/

        print("插入结束");

    }

    //这里根据姓名去更新年龄
    public void update(View view) {

        final String name = etName.getText().toString();
        final String age = etAge.getText().toString();

        if (TextUtils.isEmpty(name)) {
            print("姓名不能为空");
            return;
        }

        if (TextUtils.isEmpty(age)) {
            print("年龄不能为空");
            return;
        }

        Realm realm = Realm.getDefaultInstance();
        final User user = new User();
        user.setName(name);
        user.setAge(Integer.parseInt(age));

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                List<User> data = realm.where(User.class).equalTo("name",name).findAll();
                if (data.size() == 0){
                    print("未查询到对应的记录");
                    return;
                }

                //将所有姓名为{name}的年龄修改为10
                for (User user : data) {
                    user.setAge(10);
                }

                print("更新完成");
            }
        });

    }

    public void queryAll(View view){
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<User> query =  realm.where(User.class);
        List<User> data =  query.findAll();
        print("查询结果数：" + data.size());
        StringBuilder sb = new StringBuilder();
        for (User user : data) {
            if (sb.length() == 0) {
                sb.append(user.toString());
            }else {
                sb.append("\n").append(user.toString());
            }
        }

        print(sb.toString());
    }

    public void delete(View view) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<User> results = realm.where(User.class).findAll();
                if (results.size() == 0) {
                    print("无数据");
                    return;
                }
                //删除第一条数据
                results.deleteFirstFromRealm();
                print("删除成功");
                //删除最后一条数据
                //results.deleteLastFromRealm();
            }
        });
    }

    private void print(int msg) {
        print("" + msg);
    }
    private void print(String msg){
        final String currentLog = tvLog.getText().toString();
        tvLog.setText(TextUtils.isEmpty(currentLog) ? msg : (msg + "\n" + currentLog));
    }
}
