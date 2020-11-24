package com.zainpradana.sqliteklinik.ui.rekammedis;

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
import com.zainpradana.sqliteklinik.ui.dokter.DataDokter;
import com.zainpradana.sqliteklinik.ui.dokter.LihatDokter;
import com.zainpradana.sqliteklinik.ui.dokter.TambahDokter;
import com.zainpradana.sqliteklinik.ui.dokter.UpdateDokter;

public class DataRekamMedis extends AppCompatActivity {
    public static DataRekamMedis drm;
    protected Cursor cursor;
    String[] daftarRekamMedis, daftarNomorRekamMedis;
    ListView listViewRekamMedis;
    Button btnTambahRekamMedis;
    Menu menu;
    DatabaseHelper dbCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_rekam_medis);

        btnTambahRekamMedis = findViewById(R.id.bt_tambah_rekammedis);
        btnTambahRekamMedis.setOnClickListener(view -> {
            Intent goToTambahRekamMedis = new Intent(DataRekamMedis.this, TambahRekamMedis.class);
            startActivity(goToTambahRekamMedis);
        });

        drm = this;
        dbCenter = new DatabaseHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM rekammedis", null);
        daftarNomorRekamMedis = new String[cursor.getCount()];

        for (int cc = 0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftarNomorRekamMedis[cc] = cursor.getString(0).toString();
        }

        listViewRekamMedis = findViewById(R.id.listview_data_rekammedis);
        listViewRekamMedis.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftarNomorRekamMedis));
        listViewRekamMedis.setSelected(true);
        listViewRekamMedis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftarNomorRekamMedis[arg2];
                final CharSequence[] dialogItem = { "Lihat Rekam Medis", "Update Rekam Medis", "Hapus Rekam Medis"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DataRekamMedis.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent goToLihatRekamMedis = new Intent(DataRekamMedis.this, LihatRekamMedis.class);
                                goToLihatRekamMedis.putExtra("norekam", selection);
                                startActivity(goToLihatRekamMedis);
                                break;

                            case 1:
//                                Intent goToUpdateRekamMedis = new Intent(DataRekamMedis.this, UpdateRekamMedis.class);
//                                goToUpdateRekamMedis.putExtra("norekam", selection);
//                                Toast.makeText(getApplicationContext(), selection.toString(), Toast.LENGTH_SHORT).show();
//                                startActivity(goToUpdateRekamMedis);
                                break;

                            case 2:
                                SQLiteDatabase db = dbCenter.getWritableDatabase();
                                db.execSQL("DELETE FROM rekammedis WHERE norekam = '"+ selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listViewRekamMedis.getAdapter()).notifyDataSetInvalidated();
    }
}