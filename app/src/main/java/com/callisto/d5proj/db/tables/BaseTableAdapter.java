package com.callisto.d5proj.db.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class BaseTableAdapter {
    static public final String C_ID = "_id";

    protected Context context;
    protected DBHelper dbHelper;
    protected SQLiteDatabase db;
    protected String keyColumn, managedTable;
    protected String[] columns;

    public SQLiteDatabase getDb() {
        return db;
    }

    public String getManagedTable() {
        return managedTable;
    }

    public void setManagedTable(String managedTable) {
        this.managedTable = managedTable;
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(String columnName) {
        this.keyColumn = columnName;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public BaseTableAdapter(Context context) {
        this.context = context;
    }

    public void close() {
        dbHelper.close();
    }

    public long delete(long id) {
        long result;

        if (db == null) {
            open();
        }
        result = db.delete(this.getManagedTable(), "_id=" + id, null);

        // close();

        return result;
    }

    /**
     * @return Cursor containing all table rows and columns.
     * @throws SQLException
     */
    public Cursor getCursor() throws SQLException {
        if (db == null) {
            open();
        }

        Cursor c = db.query(true, this.getManagedTable(), columns, null, null,
            null, null, null, null);

        return c;
    }

    /**
     * @param filter String value used to filter results.
     * @return Cursor containing filtered table rows and columns.
     * @throws SQLException
     */
    public Cursor getCursor(String filter) throws SQLException {
        Cursor c = db.query(true, this.getManagedTable(), columns, filter,
            null, null, null, null, null);

        return c;
    }

    public long getId(String filter) {
        return this.getId(filter, getKeyColumn());
    }

    /**
     * Fetch a specific record from the database.
     *
     * @param id Row identifier.
     * @return Cursor containing the requested row.
     * @throws SQLException
     */
    public Cursor getRecord(long id) throws SQLException {
        Cursor c = db.query(true, this.getManagedTable(), columns, C_ID + "="
            + id, null, null, null, null, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    /**
     * Inserts values into a new table record.
     *
     * @param reg The set of values to insert.
     * @return
     */
    public long insert(ContentValues reg) {
        long result;

        if (db == null) {
            open();
        }

        result = db.insert(this.getManagedTable(), null, reg);

        return result;
    }

    public long insertOrThrow(ContentValues reg) {
        long result;

        if (db == null) {
            open();
        }

        result = db.insertOrThrow(this.getManagedTable(), null, reg);

        return result;
    }

    public BaseTableAdapter open() throws SQLException {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();

        return this;
    }

    public long update(ContentValues reg) {
        long result = 0;

        if (db == null) {
            open();
        }

        if (reg.containsKey(C_ID)) {
            long id = reg.getAsLong(C_ID);

            result = db.update(this.getManagedTable(), reg, "_id=" + id, null);
        }

        return result;
    }

    /**
     * @param filter     Value to match for result.
     * @param columnName Column to search.
     * @return Row identifier.
     * @throws SQLException
     */
    private long getId(String filter, String columnName) throws SQLException {
        long result = 0;

        Cursor c = this.getCursor();

        while (c.moveToNext()) {
            String t = c.getString(c.getColumnIndexOrThrow(columnName));

            if (t.compareTo(filter) == 0) {
                result = c.getLong(c.getColumnIndexOrThrow(C_ID));
                break;
            }
        }

        c.close();

        return result;
    }

    public class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_SCHEME_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) { }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

        private static final String DB_NAME = "d5proj.sqlite";
        private static final int DB_SCHEME_VERSION = 1;

    }
}

