package com.zainpradana.sqliteklinik.ui.pasien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.zainpradana.sqliteklinik.R;
import com.zainpradana.sqliteklinik.database.DatabaseHelper;

public class DataPasien extends AppCompatActivity {
    public static DataPasien dp;
    protected Cursor cursor;
    String[] daftarPasien, daftarNomorPasien;
    ListView listViewPasien;
    Button btnTambahPasien;
    Menu menu;
    DatabaseHelper dbCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pasien);

        btnTambahPasien = findViewById(R.id.bt_tambah_pasien);
        btnTambahPasien.setOnClickListener(view -> {
            Intent goToTambahPasien = new Intent(DataPasien.this, TambahPasien.class);
            startActivity(goToTambahPasien);
        });

        dp = this;
        dbCenter = new DatabaseHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pasien", null);
        daftarPasien = new String[cursor.getCount()];
        daftarNomorPasien = new String[cursor.getCount()];

        for (int cc = 0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftarPasien[cc] = cursor.getString(1).toString();
            daftarNomorPasien[cc] = cursor.getString(0).toString();
        }

        listViewPasien = findViewById(R.id.listview_data_pasien);
        listViewPasien.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftarPasien));
        listViewPasien.setSelected(true);
        listViewPasien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftarNomorPasien[arg2];
                final CharSequence[] dialogItem = { "Lihat Pasien", "Update Pasien", "Hapus Pasien"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DataPasien.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent goToLihatPasien = new Intent(DataPasien.this, LihatPasien.class);
                                goToLihatPasien.putExtra("nopasien", selection);
                                startActivity(goToLihatPasien);
                                break;

                            case 1:
                                Intent goToUpdatePasien = new Intent(DataPasien.this, UpdatePasien.class);
                                goToUpdatePasien.putExtra("nopasien", selection);
                                Toast.makeText(getApplicationContext(), selection.toString(), Toast.LENGTH_SHORT).show();
                                startActivity(goToUpdatePasien);
                                break;

                            case 2:
                                SQLiteDatabase db = dbCenter.getWritableDatabase();
                                db.execSQL("DELETE FROM pasien WHERE nopasien = '"+ selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listViewPasien.getAdapter()).notifyDataSetInvalidated();
    }
}