package com.zainpradana.sqliteklinik.ui.rekammedis;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zainpradana.sqliteklinik.R;
import com.zainpradana.sqliteklinik.database.DatabaseHelper;

public class LihatRekamMedis extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper dbHelper;
    Button buttonKembali;
    TextView tvNomorRekamMedis, tvTanggalRekam, tvNoPasien, tvNoDokter, tvKeluhan, tvDiagnosa, tvBiaya;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_rekam_medis);

        dbHelper = new DatabaseHelper(this);

        buttonKembali = findViewById(R.id.button_kembali);

        tvNomorRekamMedis = findViewById(R.id.tv_nomor_rekam_medis);
        tvTanggalRekam = findViewById(R.id.tv_tgl_rekam);
        tvNoPasien = findViewById(R.id.tv_nomor_pasien);
        tvNoDokter = findViewById(R.id.tv_nomor_dokter);
        tvKeluhan = findViewById(R.id.tv_keluhan);
        tvDiagnosa = findViewById(R.id.tv_diagnosa);
        tvBiaya = findViewById(R.id.tv_biaya);


        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT R.norekam, R.tgl_rekam, P.namapasien, D.namadokter, R.keluhan, R.diagnosa, R.biaya FROM rekammedis as R JOIN dokter as D ON R.nodokter = D.nodokter JOIN pasien as P ON R.nopasien = P.nopasien WHERE norekam ='" + getIntent().getStringExtra("norekam") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            tvNomorRekamMedis.setText(cursor.getString(0));
            tvTanggalRekam.setText(cursor.getString(1));
            tvNoPasien.setText(cursor.getString(2));
            tvNoDokter.setText(cursor.getString(3));
            tvKeluhan.setText(cursor.getString(4));
            tvDiagnosa.setText(cursor.getString(5));
            tvBiaya.setText(cursor.getString(6));
        }

        buttonKembali.setOnClickListener(view -> {
            finish();
        });
    }
}