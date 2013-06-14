
package jp.mixi.practice.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	BookOpenHelper dbhelper;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbhelper = new BookOpenHelper(this);
        
        findViewById(R.id.Insert).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
        findViewById(R.id.Delete).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        findViewById(R.id.Update).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        findViewById(R.id.Query).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });

    }

    private void insert() {
        ContentValues cv = new ContentValues();
        cv.put(Book.COLUMN_NAME_BOOK_TITLE, "カモメに飛ぶことを教えた猫");
        cv.put(Book.COLUMN_NAME_BOOK_PUBLISHER, "白水Uブックス");
        cv.put(Book.COLUMN_NAME_BOOK_PRICE, "840");
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.insert(Book.BOOK_TABLE_NAME, null, cv);
    }

    private void delete() {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        db.delete(Book.BOOK_TABLE_NAME, Book.COLUMN_NAME_BOOK_PRICE + " LIKE ?", new String[] {"840"});
    }

    private void update() {
        ContentValues cv = new ContentValues();
        cv.put(Book.COLUMN_NAME_BOOK_TITLE, "にせほにしゃべることを教えたついったらー");
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.update(Book.BOOK_TABLE_NAME, cv, Book.COLUMN_NAME_BOOK_TITLE + " LIKE ?", new String[]{"カモメに飛ぶことを教えた猫"});
    }

    private void query() {
        String[] projection = {
        		Book._ID,
        		Book.COLUMN_NAME_BOOK_TITLE,
        		Book.COLUMN_NAME_BOOK_PUBLISHER,
        		Book.COLUMN_NAME_BOOK_PRICE
        };
        String selection = Book.COLUMN_NAME_BOOK_PUBLISHER + " LIKE ?";
        String[] selectionArgs = {"白水Uブックス"};
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.query(Book.BOOK_TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();
        String name = null;
        try{
        	name = cursor.getString(cursor.getColumnIndexOrThrow(Book.COLUMN_NAME_BOOK_TITLE));
        }catch(CursorIndexOutOfBoundsException ex){
        	Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT);
        }
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
