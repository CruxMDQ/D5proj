package com.callisto.d5proj.db.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.callisto.d5proj.db.DBHelper;

@SuppressWarnings("unused")
public abstract class BaseTableHelper {
    static final String C_ID = "_id";

    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private String keyColumn;
    private String managedTable;
    private String[] columns;

    public SQLiteDatabase getDb() {
        return db;
    }

    private String getManagedTable() {
        return managedTable;
    }

    void setManagedTable(String managedTable) {
        this.managedTable = managedTable;
    }

    private String getKeyColumn() {
        return keyColumn;
    }

    void setKeyColumn(String columnName) {
        this.keyColumn = columnName;
    }

    void setColumns(String[] columns) {
        this.columns = columns;
    }

    BaseTableHelper(Context context) {
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
    Cursor getCursor() throws SQLException {
        if (db == null) {
            open();
        }

        return db.query(true, this.getManagedTable(), columns, null, null,
            null, null, null, null);
    }

    /**
     * @param filter String value used to filter results.
     * @return Cursor containing filtered table rows and columns.
     * @throws SQLException
     */
    public Cursor getCursor(String filter) throws SQLException {

        return db.query(true, this.getManagedTable(), columns, filter,
            null, null, null, null, null);
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

    private BaseTableHelper open() throws SQLException {
        dbHelper = DBHelper.getInstance(context);
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
}
