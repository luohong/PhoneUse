package com.luohong.phoneobserver.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.provider.BaseColumns;
import android.util.Log;

import com.luohong.phoneobserver.bean.Use;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luohong on 15/9/22. igufffffffy
 */
public class UseDb extends BaseDb {

    private static final String TAG = UseDb.class.getSimpleName();

    public static class Table implements BaseColumns {

        public static final String TABLE_NAME = "tb_use";

        public static final String DATE = "date";
        public static final String TIME = "time";
        public static final String ACTION = "action";
        public static final String DESC = "desc";

        public static final String DEFAULT_SORT_ORDER = Table._ID + " DESC";

        public static final String[] PROJECTION = { _ID, DATE, TIME, ACTION, DESC};
    }

    public UseDb(Context context) {
        super(context);
    }

    @Override
    public String getTableName() {
        return Table.TABLE_NAME;
    }

    protected static String getCreateTableSQL() {

        StringBuilder sb = new StringBuilder();
        sb.append(CREATE_TABLE_PREFIX).append(Table.TABLE_NAME).append(BRACKET_LEFT);
        sb.append(Table._ID).append(COLUMN_TYPE.INTEGER).append(PRIMARY_KEY_AUTOINCREMENT).append(COMMA);
        sb.append(Table.DATE).append(COLUMN_TYPE.TEXT).append(COMMA);
        sb.append(Table.TIME).append(COLUMN_TYPE.LONG).append(COMMA);
        sb.append(Table.DESC).append(COLUMN_TYPE.TEXT).append(COMMA);
        sb.append(Table.ACTION).append(COLUMN_TYPE.TEXT);
        sb.append(BRACKET_RIGHT);

        return sb.toString();
    }

    protected static String getDropTableSQL() {
        return DROP_TABLE_PREFIX + Table.TABLE_NAME;
    }

    @Override
    protected Object parseCursor(Cursor cursor) {
        Use entity = new Use();

        entity.id = cursor.getInt(cursor.getColumnIndexOrThrow(Table._ID));
        entity.date = cursor.getString(cursor.getColumnIndexOrThrow(Table.DATE));
        entity.time = cursor.getLong(cursor.getColumnIndexOrThrow(Table.TIME));
		entity.action = cursor.getString(cursor.getColumnIndexOrThrow(Table.ACTION));
		entity.desc = cursor.getString(cursor.getColumnIndexOrThrow(Table.DESC));

        return entity;
    }

    public List<Use> findAll() {
        List<Use> list = new ArrayList<Use>();

        String selection = null;
        String[] selectionArgs = null;

        Cursor cursor = null;
        try {
            checkDb();
            cursor = db.query(Table.TABLE_NAME, Table.PROJECTION, selection, selectionArgs, null, null, Table._ID + " asc" );
            while (cursor != null && cursor.moveToNext()) {
                Use use = (Use)parseCursor(cursor);
                list.add(use);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }

    public List<Use> findAllOnAndOff(String date) {
        List<Use> list = new ArrayList<Use>();

        String selection = String.format(" %s = ? and ( %s = ? or %s = ?) ", Table.DATE, Table.ACTION, Table.ACTION);
        String[] selectionArgs = new String[] { date, "on", "off" };

        Cursor cursor = null;
        try {
            checkDb();
            cursor = db.query(Table.TABLE_NAME, Table.PROJECTION, selection, selectionArgs, null, null, Table._ID + " asc" );
            while (cursor != null && cursor.moveToNext()) {
                Use use = (Use)parseCursor(cursor);
                list.add(use);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }

    public Use find(String userId) {
        String selection = String.format(" %s = ? ", Table._ID);
        String[] selectionArgs = new String[] { userId };

        Cursor cursor = null;
        Use use = null;
        try {
            checkDb();
            cursor = db.query(Table.TABLE_NAME, Table.PROJECTION, selection, selectionArgs, null, null, Table._ID + " asc" );
            if (cursor != null && cursor.moveToNext()) {
                use = (Use)parseCursor(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return use;
    }

    public int findOpenCountByDate(String date) {
        String selection = String.format(" %s = ? and %s = ? ", Table.DATE, Table.ACTION);
        String[] selectionArgs = new String[] { date, "on" };

        Cursor cursor = null;
        List<Use> list = new ArrayList<Use>();
        try {
            checkDb();
            cursor = db.query(Table.TABLE_NAME, Table.PROJECTION, selection, selectionArgs, null, null, Table._ID + " asc" );
            while (cursor != null && cursor.moveToNext()) {
                Use use = (Use)parseCursor(cursor);
                list.add(use);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return list.size();
    }

    public void saveAll(List<Use> list) {
        checkDb();
        beginTransaction();
        try {
            clearAllData();
            if (list != null && list.size() > 0) {
                for (Use entity : list) {
                    insert(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            endTransaction();
        }
    }

    public int getCount(int parentId) {
        int count = 0;
        Cursor cursor = null;
        try {
            checkDb();

            String selection = String.format(" %s = ? ", Table.TIME);
            String[] selectionArgs = new String[] { Integer.toString(parentId) };

            cursor = db.query(Table.TABLE_NAME, Table.PROJECTION, selection, selectionArgs, null, null, Table._ID + " asc limit 1");
            if (cursor != null) {
                cursor.moveToFirst();
                count = cursor.getCount();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return count;
    }

    public void insert(Use entity) {
        if (entity != null) {
            checkDb();

            ContentValues values = new ContentValues();
            values.put(Table.DATE, entity.date);
            values.put(Table.TIME, entity.time);
			values.put(Table.ACTION, entity.action);
			values.put(Table.DESC, entity.desc);

            long res = db.insert(Table.TABLE_NAME, null, values);
            Log.d(TAG, "insert use result: "+ res);
        }
    }

    public void update(Use entity) {
        if (entity != null) {
            checkDb();

            String whereClause = String.format(" %s = ? ", Table._ID);
            String[] whereArgs = new String[] { Long.toString(entity.id) };

            ContentValues values = new ContentValues();
            values.put(Table._ID, entity.id);
            values.put(Table.DATE, entity.date);
            values.put(Table.TIME, entity.time);
			values.put(Table.ACTION, entity.action);
			values.put(Table.DESC, entity.desc);

            db.update(Table.TABLE_NAME, values, whereClause, whereArgs);
        }
    }

    public void delete(int id) {
        try {
            checkDb();
            String whereClause = String.format(" %s = ? ", Table._ID);
            String[] whereArgs = new String[] { Long.toString(id) };
            db.delete(Table.TABLE_NAME, whereClause, whereArgs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDbAndCursor();
        }
    }
}
