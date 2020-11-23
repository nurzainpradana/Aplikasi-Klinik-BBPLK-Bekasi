package com.zainpradana.sqliteklinik.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbklinik.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE pasien(nopasien integer primary key, namapasien text, jk text, tgl_lahir date, agama text, telp text, alamat text)");
        db.execSQL("CREATE TABLE dokter(nodokter integer primary key, namadokter text, jk text, tgl_lahir date, email text, telp text, alamat text, spesialis text, tarif integer)");
        db.execSQL("CREATE TABLE rekammedis(norekam integer primary key, tgl_rekam date, nopasien integer, nodokter integer, keluhan text, diagnosa text, biaya integer)");
        //db.execSQL("CREATE TABLE pasien(norekam integer primary key, tanggal_rekam text, nopasien integer, nodokter text, agama text, telp text, alamat text)");

        db.execSQL("INSERT INTO pasien (nopasien, namapasien, jk, tgl_lahir, agama, telp, alamat) VALUES (101, 'Bambang', 'L', 2002-07-12, 'Islam', '082125652279', 'Cibitung')");
        db.execSQL("INSERT INTO pasien (nopasien, namapasien, jk, tgl_lahir, agama, telp, alamat) VALUES (102, 'Wati', 'P', 2002-07-10, 'Islam', '082125659020', 'Tambun')");

        db.execSQL("INSERT INTO dokter (nodokter, namadokter, jk, tgl_lahir, email, telp, alamat, spesialis, tarif) VALUES (201, 'Agus', 'L', 2002-07-10, 'agus@gmail.com', '082125659020', 'Tambun', 'Umum', 500000)");
        db.execSQL("INSERT INTO dokter (nodokter, namadokter, jk, tgl_lahir, email, telp, alamat, spesialis, tarif) VALUES (202, 'Jubaedah', 'P', 2002-07-12, 'Juabedah@gmail.com', '082125659020', 'Cikarang', 'Gigi', 600000)");

        db.execSQL("INSERT INTO rekammedis (norekam, tgl_rekam, nopasien, nodokter, keluhan, diagnosa, biaya) VALUES (301, 2002-07-12, 102, 201, 'Batuk Pilek', 'Radang', 70000)");
        db.execSQL("INSERT INTO rekammedis (norekam, tgl_rekam, nopasien, nodokter, keluhan, diagnosa, biaya) VALUES (302, 2002-07-15, 101, 201, 'sakit di bagian ulu hati', 'Maag', 70000)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE pasien");
        db.execSQL("DROP TABLE dokter");
        db.execSQL("DROP TABLE rekammedis");
    }
}
