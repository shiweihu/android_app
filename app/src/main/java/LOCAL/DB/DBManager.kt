package LOCAL.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object FeedReaderContract {
    // Table contents are grouped together in an anonymous object.
    object CRISIS_PLAN_ENTRAL : BaseColumns {
        const val TABLE_NAME = "CRISIS_PLAN"
        const val COLUMN_1 = "COLUMN1"
        const val COLUMN_2 = "COLUMN2"
        const val COLUMN_3 = "COLUMN3"
        const val COLUMN_4 = "COLUMN4"
        const val COLUMN_5 = "COLUMN5"
        const val COLUMN_6 = "COLUMN6"
        const val COLUMN_7 = "COLUMN7"
    }
}


class DBManager(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private  val SQL_CREATE_ENTRIES = "CREATE TABLE ${FeedReaderContract.CRISIS_PLAN_ENTRAL.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +"${FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_1} TEXT," +
            "${FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_2} TEXT,"+
            "${FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_3} TEXT,"+
            "${FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_4} TEXT,"+
            "${FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_5} TEXT,"+
            "${FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_6} TEXT,"+
            "${FeedReaderContract.CRISIS_PLAN_ENTRAL.COLUMN_7} TEXT)"
    private  val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedReaderContract.CRISIS_PLAN_ENTRAL.TABLE_NAME}"

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(SQL_CREATE_ENTRIES)
        }
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES)
            onCreate(db)
        }
        override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            onUpgrade(db, oldVersion, newVersion)
        }
        companion object {
            // If you change the database schema, you must increment the database version.
            const val DATABASE_VERSION = 1
            const val DATABASE_NAME = "FeedReader.db"
        }
}