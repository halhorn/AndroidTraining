
package jp.mixi.assignment.res.string.beg;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apple = getResources().getQuantityString(R.plurals.my_apple, 5, 5);
        TextView textView = (TextView) findViewById(R.id.DynamicText);
        textView.setText(apple);
    }
}