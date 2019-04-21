package com.clwater.smsfroward;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.clwater.smsfroward.email.SendMailUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText edit;
    private RecyclerView recyclerView;
    public static List<String> list = new ArrayList<>();
    private EmailAdapter emailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button_add);
        recyclerView = findViewById(R.id.recycle);
        edit = findViewById(R.id.edit);
        initData();
        initView();
    }

    private void initData() {
        list.clear();
        String emailsInfo = SPManager.getEMAILS(this);
        if (TextUtils.isEmpty(emailsInfo)){
            return;
        }
        String[] emails = emailsInfo.split("#");
        for (String email: emails){
            list.add(email);
        }
    }

    private void initView() {
        emailAdapter = new EmailAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(emailAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upEmails();
                SendMailUtil.send("123");
            }
        });

        emailAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.email_remove){
                    String emailsInfo = SPManager.getEMAILS(MainActivity.this);
                    String removeEmail = adapter.getData().get(position).toString();
                    if (emailsInfo.equals(removeEmail)){
                        emailsInfo = "";
                    }else if (emailsInfo.endsWith(removeEmail)){
                        emailsInfo = emailsInfo.replace(removeEmail, "");
                    }else {
                        emailsInfo = emailsInfo.replace(removeEmail + "#", "");
                    }
                    SPManager.setEMAILS(MainActivity.this, emailsInfo);
                    uploadRecycle();
                }
            }
        });
    }

    private void upEmails() {
        String email = edit.getText().toString();
        if (TextUtils.isEmpty(email)){
            return;
        }
        String emailsInfo = SPManager.getEMAILS(this);
        if (emailsInfo.contains(email)){
            return;
        }
        if (!TextUtils.isEmpty(emailsInfo)){
            email = emailsInfo + "#" +email;
        }
        SPManager.setEMAILS(this, email);
        uploadRecycle();
        edit.setText("");
    }

    private void uploadRecycle() {
        initData();
        emailAdapter.setNewData(list);
    }
}