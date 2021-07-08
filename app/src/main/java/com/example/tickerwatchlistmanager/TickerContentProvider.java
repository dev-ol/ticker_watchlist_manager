package com.example.tickerwatchlistmanager;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class TickerContentProvider extends ContentProvider {

    public final static String DB_NAME = "TickerDb";
    public final static String TABLE_TICKERS = "Tickers";
    public final static String[] TABLE_TICKER_COLUMNS = { "ticker" ,
            "company"};
    public final static String AUTHORITY = "TickerDB";
    public final static Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/"+ TABLE_TICKERS);


    private static final String SQL_CREATE_MAIN =
            "CREATE TABLE "+ TABLE_TICKERS + "( _ID INTEGER PRIMARY KEY, "+
                    TABLE_TICKER_COLUMNS[0] + " TEXT,"+
                    TABLE_TICKER_COLUMNS[1] + " TEXT)";

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {


        public MainDatabaseHelper(Context context) {
            super(context, DB_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


    private static UriMatcher sUriMatcher;
    private MainDatabaseHelper mOpenHelper;

    public TickerContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().delete(TABLE_TICKERS, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        if(values.getAsString(TABLE_TICKER_COLUMNS[0]).trim().equals("")||
                values.getAsString(TABLE_TICKER_COLUMNS[1]).trim().equals("")){
            return null;
        }

        long id = mOpenHelper.getWritableDatabase()
                .insert(TABLE_TICKERS, null, values);

        return Uri.withAppendedPath(CONTENT_URI, ""+id);
    }

    @Override
    public boolean onCreate() {
        mOpenHelper =new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(TABLE_TICKERS,
                projection,selection,selectionArgs,null,null,sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().update(TABLE_TICKERS, values, selection, selectionArgs);
    }
}