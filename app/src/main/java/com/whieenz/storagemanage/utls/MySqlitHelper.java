package com.whieenz.storagemanage.utls;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by heziwen on 2017/3/8.
 * 提供回调函数
 * 提供获取数据库对象的函数
 */

public class MySqlitHelper extends SQLiteOpenHelper {

    /**
     *
     * @param context 上下文对象
     * @param name  表示创建数据库的名称
     * @param factory  游标工厂
     * @param version  表示创建数据库的版本
     */
    public MySqlitHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqlitHelper(Context context){
        super(context, SQLitConstant.DATABASE_NAME,null, SQLitConstant.DATABASE_VERSION);
    }
    /**
     * 当数据库创建时回调的函数
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: *****************");

    }

    /**
     * 当数据库版本更新时的回调函数
     * @param sqLiteDatabase  数据库对象
     * @param i    数据库旧版本
     * @param i1   数据库新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade: *******//////");
    }

    /**
     * 当数据库打开时回调
     * @param db
     */

    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.d(TAG, "onOpen: ******************//");

        super.onOpen(db);
    }
}
