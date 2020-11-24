package com.zainpradana.sqliteklinik.ui.rekammedis;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zainpradana.sqliteklinik.R;
import com.zainpradana.sqliteklinik.database.DatabaseHelper;
import com.zainpradana.sqliteklinik.ui.dokter.DataDokter;

public class UpdateRekamMedis extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper dbHelper;
    Button buttonSimpanRekamMedis, buttonKembali;
    EditText etNomorRekamMedis, etTanggalRekam, etNoPasien, etNoDokter, etKeluhan, etDiagnosa, etBiaya;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_rekam_medis);

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

        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT R.norekam, R.tgl_rekam, R.nopasien, D.nodokter, R.keluhan, R.diagnosa, R.biaya FROM rekammedis as R JOIN dokter as D ON R.nodokter = D.nodokter JOIN pasien as P ON R.nopasien = P.nopasien WHERE norekam ='" + getIntent().getStringExtra("norekam") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            etNomorRekamMedis.setText(cursor.getString(0));
            etTanggalRekam.setText(cursor.getString(1));
            etNoPasien.setText(cursor.getString(2));
            etNoDokter.setText(cursor.getString(3));
            etKeluhan.setText(cursor.getString(4));
            etDiagnosa.setText(cursor.getString(5));
            etBiaya.setText(cursor.getString(6));
        }

        buttonSimpanRekamMedis.setOnClickListener(view -> {
            db = dbHelper.getWritableDatabase();

            db.execSQL("UPDATE rekammedis set tgl_rekam='" +
                    etTanggalRekam.getText().toString() + "', nopasien='" +
                    etNoPasien.getText().toString() + "', nodokter='" +
                    etNoDokter.getText().toString() + "', keluhan='" +
                    etKeluhan.getText().toString() + "', diagnosa='" +
                    etDiagnosa.getText().toString() + "', biaya='" +
                    etBiaya.getText().toString() + "'" + "where norekam='" +
                    etNomorRekamMedis.getText().toString() + "'");

            Toast.makeText(UpdateRekamMedis.this.getApplicationContext(), "Berhasil",
                    Toast.LENGTH_LONG).show();

            DataRekamMedis.drm.RefreshList();
            UpdateRekamMedis.this.finish();
        });

        buttonKembali.setOnClickListener(view -> {
            onBackPressed();
        });


    }
}