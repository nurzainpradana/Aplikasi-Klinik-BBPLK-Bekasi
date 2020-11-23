package com.zainpradana.sqliteklinik.ui.dokter;

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
import com.zainpradana.sqliteklinik.ui.pasien.DataPasien;
import com.zainpradana.sqliteklinik.ui.pasien.LihatPasien;
import com.zainpradana.sqliteklinik.ui.pasien.TambahPasien;
import com.zainpradana.sqliteklinik.ui.pasien.UpdatePasien;

public class DataDokter extends AppCompatActivity {
    public static DataDokter dd;
    protected Cursor cursor;
    String[] daftarDokter, daftarNomorDokter;
    ListView listViewDokter;
    Button btnTambahDokter;
    Menu menu;
    DatabaseHelper dbCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_dokter);

        btnTambahDokter = findViewById(R.id.bt_tambah_dokter);
        btnTambahDokter.setOnClickListener(view -> {
//            Intent goToTambahDokter = new Intent(DataDokter.this, TambahDokter.class);
//            startActivity(goToTambahDokter);
        });

        dd = this;
        dbCenter = new DatabaseHelper(this);
        RefreshList();
    }

    private void RefreshList() {
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM dokter", null);
        daftarDokter = new String[cursor.getCount()];
        daftarNomorDokter = new String[cursor.getCount()];

        for (int cc = 0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftarDokter[cc] = cursor.getString(1).toString();
            daftarNomorDokter[cc] = cursor.getString(0).toString();
        }

        listViewDokter = findViewById(R.id.listview_data_dokter);
        listViewDokter.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftarDokter));
        listViewDokter.setSelected(true);
        listViewDokter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftarNomorDokter[arg2];
                final CharSequence[] dialogItem = { "Lihat Dokter", "Update Dokter", "Hapus Dokter"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DataDokter.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
//                                Intent goToLihatDokter = new Intent(DataDokter.this, LihatDokter.class);
//                                goToLihatDokter.putExtra("nodokter", selection);
//                                startActivity(goToLihatDokter);
                                break;

                            case 1:
//                                Intent goToUpdateDokter = new Intent(DataDokter.this, UpdateDokter.class);
//                                goToUpdateDokter.putExtra("nodokter", selection);
//                                Toast.makeText(getApplicationContext(), selection.toString(), Toast.LENGTH_SHORT).show();
//                                startActivity(goToUpdateDokter);
                                break;

                            case 2:
                                SQLiteDatabase db = dbCenter.getWritableDatabase();
                                db.execSQL("DELETE FROM dokter WHERE nodokter = '"+ selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listViewDokter.getAdapter()).notifyDataSetInvalidated();
    }
}