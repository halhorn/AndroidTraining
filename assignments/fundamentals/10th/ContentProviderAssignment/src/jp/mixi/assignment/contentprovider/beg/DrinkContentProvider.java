package jp.mixi.assignment.contentprovider.beg;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DrinkContentProvider extends ContentProvider {

	DrinkOpenHelper drinkOpenHelper;
	
    // 利用者がメソッドを呼び出したURIに対応する処理を判定処理に使用します
    private static final UriMatcher URI_MATCHER;
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(Drink.AUTHORITY, Drink.DRINK_TABLE_NAME, Drink.DRINK);
    }
    
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		validUrlOrThrow(uri);
		SQLiteDatabase db = drinkOpenHelper.getWritableDatabase();
		return db.delete(Drink.DRINK_TABLE_NAME, selection, selectionArgs);
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues val) {
		validUrlOrThrow(uri);
		SQLiteDatabase db = drinkOpenHelper.getWritableDatabase();
		db.insert(Drink.DRINK_TABLE_NAME, null, val);
		return uri;
	}
	

	@Override
	public boolean onCreate() {
		drinkOpenHelper = new DrinkOpenHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] fields, String selection, String[] selectionArgs,
			String oder) {
		validUrlOrThrow(uri);
		SQLiteDatabase db = drinkOpenHelper.getReadableDatabase();
		return db.query(Drink.DRINK_TABLE_NAME, fields, selection, selectionArgs, null, null, oder);
	}

	@Override
	public int update(Uri uri, ContentValues val, String selection, String[] selectionArgs) {
		validUrlOrThrow(uri);
		SQLiteDatabase db = drinkOpenHelper.getWritableDatabase();
		return db.update(Drink.DRINK_TABLE_NAME, val, selection, selectionArgs);
	}
	
	private void validUrlOrThrow(Uri uri){
        if (URI_MATCHER.match(uri) != Drink.DRINK) {
            throw new IllegalArgumentException("Unknown URI : " + uri);
        }
	}
}
