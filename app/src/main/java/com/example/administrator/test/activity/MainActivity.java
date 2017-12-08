package com.example.administrator.test.activity;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.test.R;

/**
 * IP拨号器
 * 2017年12月8日20:19:59
 */
public class MainActivity extends AppCompatActivity {

    private EditText et_ip_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1.检查权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.PROCESS_OUTGOING_CALLS) !=
                PackageManager.PERMISSION_GRANTED) {
            // 2.申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS}, 1);
        }
        et_ip_number = (EditText) findViewById(R.id.et_ip_number);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 6.0之上,若用户授权,则应用一切正常
                    Toast.makeText(this, "you allow the permission", Toast.LENGTH_SHORT).show();
                } else {
                    // 6.0之上,若用户拒绝,则无法接收到指定广播
                    Toast.makeText(this, "you denied the permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    public void save(View view) {
        String ipNumber = et_ip_number.getText().toString().trim();
        if (ipNumber.isEmpty()) {
            Toast.makeText(this, "please input the ip number", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ipNumber", ipNumber);
        boolean commit = editor.commit();
        if (commit) {
            Toast.makeText(this, "save success", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


}
