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

public class UpdateDokter extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper dbHelper;
    Button buttonSimpanDokter, buttonKembali;
    EditText etNomorDokter, etNamaDokter, etJenisKelamin, etTanggalLahir, etEmail, etTelp, etAlamat, etSpesialis, etTarif;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dokter);

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

        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM dokter WHERE nodokter ='" + getIntent().getStringExtra("nodokter") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            etNomorDokter.setText(cursor.getString(0));
            etNamaDokter.setText(cursor.getString(1));
            etJenisKelamin.setText(cursor.getString(2));
            etTanggalLahir.setText(cursor.getString(3));
            etEmail.setText(cursor.getString(4));
            etTelp.setText(cursor.getString(5));
            etAlamat.setText(cursor.getString(6));
            etSpesialis.setText(cursor.getString(7));
            etTarif.setText(cursor.getString(8));
        }

        buttonKembali.setOnClickListener(view -> {
            finish();
        });

        buttonSimpanDokter.setOnClickListener(view -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            db.execSQL("UPDATE dokter set namadokter='" +
                    etNamaDokter.getText().toString() + "', jk='" +
                    etJenisKelamin.getText().toString() + "', tgl_lahir='" +
                    etTanggalLahir.getText().toString() + "', email='" +
                    etEmail.getText().toString() + "', telp='" +
                    etTelp.getText().toString() + "', alamat='" +
                    etAlamat.getText().toString() + "', spesialis='" +
                    etSpesialis.getText().toString() + "', tarif='" +
                    etTarif.getText().toString() + "' where nodokter='" +
                    etNomorDokter.getText().toString() + "'");

            Toast.makeText(getApplicationContext(), "Berhasil",
                    Toast.LENGTH_LONG).show();

            DataDokter.dd.RefreshList();
            finish();
        });
    }
}