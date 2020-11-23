package com.zainpradana.sqliteklinik.ui.pasien;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zainpradana.sqliteklinik.R;
import com.zainpradana.sqliteklinik.database.DatabaseHelper;

public class UpdatePasien extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper dbHelper;
    Button buttonSimpan, buttonKembali;
    EditText etNomorPasien, etNamaPasien, etJenisKelamin, etTanggalLahir, etAgama, etTelp, etAlamat;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pasien);
        dbHelper = new DatabaseHelper(this);
        etNomorPasien = findViewById(R.id.et_nomor_pasien);
        etNamaPasien = findViewById(R.id.et_nama_pasien);
        etJenisKelamin = findViewById(R.id.et_jenis_kelamin);
        etTanggalLahir = findViewById(R.id.et_tanggal_lahir);
        etAgama = findViewById(R.id.et_agama);
        etTelp = findViewById(R.id.et_telp);
        etAlamat = findViewById(R.id.et_alamat);

        buttonKembali = findViewById(R.id.button_kembali);
        buttonSimpan = findViewById(R.id.button_simpan_pasien);

        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pasien WHERE nopasien = '" + getIntent().getStringExtra("nopasien") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            etNomorPasien.setText(cursor.getString(0));
            etNamaPasien.setText(cursor.getString(1));
            etJenisKelamin.setText(cursor.getString(2));
            etTanggalLahir.setText(cursor.getString(3));
            etAgama.setText(cursor.getString(4));
            etTelp.setText(cursor.getString(5));
            etAlamat.setText(cursor.getString(6));
        }

        buttonKembali.setOnClickListener(view -> {
            onBackPressed();
        });

        buttonSimpan.setOnClickListener(view -> {
            db = dbHelper.getWritableDatabase();
            db.execSQL("UPDATE pasien SET namapasien = '" +
                    etNamaPasien.getText().toString() + "', jk ='" +
                    etJenisKelamin.getText().toString() + "', tgl_lahir ='" +
                    etTanggalLahir.getText().toString() + "', agama ='" +
                    etAgama.getText().toString() + "', telp ='" +
                    etTelp.getText().toString() + "', alamat ='" +
                    etAlamat.getText().toString() + "' WHERE nopasien ='" +
                    etNomorPasien.getText().toString() +"'");
            Toast.makeText(this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
            DataPasien.dp.RefreshList();
            finish();
        });

    }
}