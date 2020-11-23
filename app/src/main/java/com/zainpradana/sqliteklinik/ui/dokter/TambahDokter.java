package com.zainpradana.sqliteklinik.ui.dokter;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zainpradana.sqliteklinik.R;
import com.zainpradana.sqliteklinik.database.DatabaseHelper;
import com.zainpradana.sqliteklinik.ui.pasien.DataPasien;

public class TambahDokter extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper dbHelper;
    Button buttonSimpanDokter, buttonKembali;
    EditText etNomorDokter, etNamaDokter, etJenisKelamin, etTanggalLahir, etEmail, etTelp, etAlamat, etSpesialis, etTarif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_dokter);

        dbHelper = new DatabaseHelper(this);

        etNomorDokter = findViewById(R.id.et_nomor_dokter);
        etNamaDokter = findViewById(R.id.et_nama_dokter);
        etJenisKelamin = findViewById(R.id.et_jenis_kelamin);
        etTanggalLahir = findViewById(R.id.et_tanggal_lahir);
        etEmail = findViewById(R.id.et_email);
        etTelp = findViewById(R.id.et_telp);
        etAlamat = findViewById(R.id.et_alamat);
        etSpesialis = findViewById(R.id.et_spesialis);
        etTarif = findViewById(R.id.et_tarif);

        buttonKembali = findViewById(R.id.button_kembali);
        buttonSimpanDokter = findViewById(R.id.button_simpan_dokter);

        buttonKembali.setOnClickListener(view -> {
            finish();
        });

        buttonSimpanDokter.setOnClickListener(view -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("insert into dokter(nodokter, namadokter, jk, tgl_lahir, email, telp, alamat, spesialis, tarif) values('" +
                    etNomorDokter.getText().toString() + "','" +
                    etNamaDokter.getText().toString() + "','" +
                    etJenisKelamin.getText().toString() + "','" +
                    etTanggalLahir.getText().toString() + "','" +
                    etEmail.getText().toString() + "','" +
                    etTelp.getText().toString() + "','" +
                    etAlamat.getText().toString() + "','" +
                    etSpesialis.getText().toString() + "','" +
                    etTarif.getText().toString() + "')");
            Toast.makeText(getApplicationContext(), "Berhasil" ,
                    Toast.LENGTH_LONG).show();
            DataDokter.dd.RefreshList();
            finish();
        });
    }
}