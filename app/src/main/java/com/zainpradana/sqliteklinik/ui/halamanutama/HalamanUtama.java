package com.zainpradana.sqliteklinik.ui.halamanutama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.zainpradana.sqliteklinik.R;
import com.zainpradana.sqliteklinik.ui.dokter.DataDokter;
import com.zainpradana.sqliteklinik.ui.pasien.DataPasien;

public class HalamanUtama extends AppCompatActivity {
    Button buttonDataPasien, buttonDataDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);

        buttonDataPasien = findViewById(R.id.button_data_pasien);
        buttonDataDokter = findViewById(R.id.button_data_dokter);

        buttonDataPasien.setOnClickListener(view -> {
            Intent goToDataPasien = new Intent(HalamanUtama.this, DataPasien.class);
            startActivity(goToDataPasien);
        });

        buttonDataDokter.setOnClickListener(view -> {
            Intent goToDataDokter = new Intent(HalamanUtama.this, DataDokter.class);
            startActivity(goToDataDokter);
        });


    }
}