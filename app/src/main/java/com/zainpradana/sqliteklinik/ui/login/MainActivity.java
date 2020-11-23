package com.zainpradana.sqliteklinik.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zainpradana.sqliteklinik.ui.halamanutama.HalamanUtama;
import com.zainpradana.sqliteklinik.R;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    EditText etUser;
    EditText etPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUser = (EditText) findViewById(R.id.et_username);
        etPass = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.bt_tambah_dokter);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUser.getText().toString().equals("admin") && etPass.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_LONG).show();
                    // Pindah Ke Halaman Utama Jika Berhasil Login
                    Intent goToHalamanUtama = new Intent(MainActivity.this, HalamanUtama.class);
                    startActivity(goToHalamanUtama);
                } else {
                    Toast.makeText(getApplicationContext(), "Gagal Login", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}