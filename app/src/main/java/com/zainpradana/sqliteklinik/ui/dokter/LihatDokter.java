package com.zainpradana.sqliteklinik.ui.dokter;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zainpradana.sqliteklinik.R;
import com.zainpradana.sqliteklinik.database.DatabaseHelper;

public class LihatDokter extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper dbHelper;
    Button buttonSimpanDokter, buttonKembali;
    TextView tvNomorDokter, tvNamaDokter, tvJenisKelamin, tvTanggalLahir, tvEmail, tvTelp, tvAlamat, tvSpesialis, tvTarif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_dokter);

        dbHelper = new DatabaseHelper(this);

        tvNomorDokter = findViewById(R.id.tv_nomor_dokter);
        tvNamaDokter = findViewById(R.id.tv_nama_dokter);
        tvJenisKelamin = findViewById(R.id.tv_jenis_kelamin);
        tvTanggalLahir = findViewById(R.id.tv_tanggal_lahir);
        tvEmail = findViewById(R.id.tv_email);
        tvTelp = findViewById(R.id.tv_telp);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvSpesialis = findViewById(R.id.tv_spesialis);
        tvTarif = findViewById(R.id.tv_tarif);

        buttonKembali = findViewById(R.id.button_kembali);

        buttonKembali.setOnClickListener(view -> {
            finish();
        });

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM dokter WHERE nodokter ='" + getIntent().getStringExtra("nodokter") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            tvNomorDokter.setText(cursor.getString(0));
            tvNamaDokter.setText(cursor.getString(1));
            tvJenisKelamin.setText(cursor.getString(2));
            tvTanggalLahir.setText(cursor.getString(3));
            tvEmail.setText(cursor.getString(4));
            tvTelp.setText(cursor.getString(5));
            tvAlamat.setText(cursor.getString(6));
            tvSpesialis.setText(cursor.getString(7));
            tvTarif.setText(cursor.getString(8));
        }
    }
}