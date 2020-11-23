package com.zainpradana.sqliteklinik.ui.pasien;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zainpradana.sqliteklinik.R;
import com.zainpradana.sqliteklinik.database.DatabaseHelper;

public class LihatPasien extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper dbHelper;
    Button buttonKembali;
    TextView tvNomorPasien, tvNamaPasien, tvJenisKelamin, tvTanggalLahir, tvAgama, tvTelp, tvAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pasien);

        dbHelper = new DatabaseHelper(this);

        buttonKembali = findViewById(R.id.button_kembali);

        tvNomorPasien = findViewById(R.id.tv_nomor_pasien);
        tvNamaPasien = findViewById(R.id.tv_nama_pasien);
        tvJenisKelamin = findViewById(R.id.tv_jenis_kelamin);
        tvTanggalLahir = findViewById(R.id.tv_tanggal_lahir);
        tvAgama = findViewById(R.id.tv_agama);
        tvTelp = findViewById(R.id.tv_telp);
        tvAlamat = findViewById(R.id.tv_alamat);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pasien WHERE nopasien ='" + getIntent().getStringExtra("nopasien") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            tvNomorPasien.setText(cursor.getString(0));
            tvNamaPasien.setText(cursor.getString(1));
            tvJenisKelamin.setText(cursor.getString(2));
            tvTanggalLahir.setText(cursor.getString(3));
            tvAgama.setText(cursor.getString(4));
            tvTelp.setText(cursor.getString(5));
            tvAlamat.setText(cursor.getString(6));
        }

        buttonKembali.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}