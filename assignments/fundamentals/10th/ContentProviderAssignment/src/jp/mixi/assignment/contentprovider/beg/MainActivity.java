
package jp.mixi.assignment.contentprovider.beg;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("content://" + "jp.mixi.assignment.contentprovider.beg.Drink" + "/Drink");
        ContentValues cv = new ContentValues();
        cv.put(Drink.COLUMN_NAME_DRINK_NAME, "のみもの");
        cv.put(Drink.COLUMN_NAME_DRINK_PRICE, "100");
        getContentResolver().insert(uri, cv);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
        	String res = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
        }
        cursor.close();
       

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
