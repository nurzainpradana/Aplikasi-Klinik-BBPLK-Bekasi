package com.zainpradana.sqliteklinik.ui.pasien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zainpradana.sqliteklinik.R;
import com.zainpradana.sqliteklinik.database.DatabaseHelper;

public class TambahPasien extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper dbHelper;
    Button buttonSimpanPasien, buttonKembali;
    EditText etNomorPasien, etNamaPasien, etJenisKelamin, etTanggalLahir, etAgama, etTelp, etAlamat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pasien);

        dbHelper = new DatabaseHelper(this);

        etNomorPasien = findViewById(R.id.et_nomor_pasien);
        etNamaPasien = findViewById(R.id.et_nama_pasien);
        etJenisKelamin = findViewById(R.id.et_jenis_kelamin);
        etTanggalLahir = findViewById(R.id.et_tanggal_lahir);
        etAgama = findViewById(R.id.et_agama);
        etTelp = findViewById(R.id.et_telp);
        etAlamat = findViewById(R.id.et_alamat);

        buttonKembali = findViewById(R.id.button_kembali);
        buttonSimpanPasien = findViewById(R.id.button_simpan_pasien);

        buttonKembali.setOnClickListener(view -> {
            finish();
        });

        buttonSimpanPasien.setOnClickListener(view -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("insert into pasien(nopasien, namapasien, jk, tgl_lahir, agama, telp, alamat) values('" +
                    etNomorPasien.getText().toString() + "','" +
                    etNamaPasien.getText().toString() + "','" +
                    etJenisKelamin.getText().toString() + "','" +
                    etTanggalLahir.getText().toString() + "','" +
                    etAgama.getText().toString() + "','" +
                    etTelp.getText().toString() + "','" +
                    etAlamat.getText().toString() + "')");
            Toast.makeText(getApplicationContext(), "Berhasil" ,
                    Toast.LENGTH_LONG).show();
            DataPasien.dp.RefreshList();
            finish();
        });
    }
}