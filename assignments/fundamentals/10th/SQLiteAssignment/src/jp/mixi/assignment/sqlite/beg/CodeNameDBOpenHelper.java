package jp.mixi.assignment.sqlite.beg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CodeNameDBOpenHelper extends SQLiteOpenHelper {
	private static final String CREATE_TABLE = 
			String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT NOT NULL, %s TEXT);", 
					CodeName.TABLE_NAME, CodeName._ID, CodeName.C_NAME, CodeName.C_VERSION);
	
	private static final String DROP_TABLE =
			String.format("DROP TABLE IF EXISTS %s;", CodeName.TABLE_NAME);
	
	public CodeNameDBOpenHelper(Context context) {
		super(context, CodeName.DB_NAME, null, CodeName.SCHEME_VER);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DROP_TABLE);
		onCreate(db);
	}

}
