package com.zainpradana.sqliteklinik.ui.rekammedis;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zainpradana.sqliteklinik.R;
import com.zainpradana.sqliteklinik.database.DatabaseHelper;
import com.zainpradana.sqliteklinik.ui.dokter.DataDokter;

public class TambahRekamMedis extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper dbHelper;
    Button buttonSimpanRekamMedis, buttonKembali;
    EditText etNomorRekamMedis, etTanggalRekam, etNoPasien, etNoDokter, etKeluhan, etDiagnosa, etBiaya;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_rekam_medis);

        dbHelper = new DatabaseHelper(this);
        
        buttonKembali = findViewById(R.id.button_kembali);
        buttonSimpanRekamMedis = findViewById(R.id.button_simpan_rekam_medis);
        
        etNomorRekamMedis = findViewById(R.id.et_nomor_rekam_medis);
        etTanggalRekam = findViewById(R.id.et_tgl_rekam);
        etNoPasien = findViewById(R.id.et_nomor_pasien);
        etNoDokter = findViewById(R.id.et_nomor_dokter);
        etKeluhan = findViewById(R.id.et_keluhan);
        etDiagnosa = findViewById(R.id.et_diagnosa);
        etBiaya = findViewById(R.id.et_biaya);

        buttonSimpanRekamMedis.setOnClickListener(view -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("insert into rekammedis(norekam, tgl_rekam, nopasien, nodokter, keluhan, diagnosa, biaya) values('" +
                    etNomorRekamMedis.getText().toString() + "','" +
                    etTanggalRekam.getText().toString() + "','" +
                    etNoPasien.getText().toString() + "','" +
                    etNoDokter.getText().toString() + "','" +
                    etKeluhan.getText().toString() + "','" +
                    etDiagnosa.getText().toString() + "','" +
                    etBiaya.getText().toString() + "');");
            Toast.makeText(getApplicationContext(), "Berhasil" ,
                    Toast.LENGTH_LONG).show();
            DataRekamMedis.drm.RefreshList();
            finish();
        });
        
        buttonKembali.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}