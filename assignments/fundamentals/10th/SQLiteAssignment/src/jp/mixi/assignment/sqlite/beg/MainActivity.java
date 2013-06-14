
package jp.mixi.assignment.sqlite.beg;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	CodeNameDBOpenHelper codeNameDbOpenHelper;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        codeNameDbOpenHelper = new CodeNameDBOpenHelper(this);
        SQLiteDatabase wdb = codeNameDbOpenHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CodeName.C_NAME, "hoge");
        cv.put(CodeName.C_VERSION, "1.0");
        wdb.insert(CodeName.TABLE_NAME, null, cv);
        
        SQLiteDatabase rdb = codeNameDbOpenHelper.getReadableDatabase();
        Cursor cursor = rdb.query(CodeName.TABLE_NAME, new String[]{CodeName.C_NAME}, null, null, null, null, null);
        cursor.moveToFirst();
        String ret = cursor.getString(cursor.getColumnIndex(CodeName.C_NAME));
        Toast.makeText(this, ret, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
