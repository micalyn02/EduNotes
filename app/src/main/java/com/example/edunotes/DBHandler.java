package com.example.edunotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // creating constant variables for database
    // variable for database name
    private static final String DB_NAME = "edunotes_user_db";

    // int for database version
    private static final int DB_VERSION = 1;

    // variable for table name
    private static final String TABLE_NAME = "user_table";

    // variable for id column
    private static final String ID_COL = "id";

    // variable for ful name column
    private static final String NAME_COL = "name";

    // variable for register email column
    private static final String EMAIL_COL = "email";

    // variable for password column
    private static final String PASSWORD_COL = "password";

    // constructor for database handler
    public DBHandler (Context context) {
        super (context, DB_NAME, null, DB_VERSION);
    }

    // method for creating database by running sqlite query
    @Override
    public void onCreate (SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT, "
                + EMAIL_COL + " VARCHAR, "
                + PASSWORD_COL + " VARCHAR)";
        db.execSQL(query); // method to execute sql query
    }

    // method to add user login credentials to database
    public void addUser (String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, name);
        values.put(EMAIL_COL, email);
        values.put(PASSWORD_COL, password);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        // method to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // method to check if user is registered and in database
    public Boolean checkUser (String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL_COL + " = ? AND " + PASSWORD_COL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[] {email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;

    }

}
