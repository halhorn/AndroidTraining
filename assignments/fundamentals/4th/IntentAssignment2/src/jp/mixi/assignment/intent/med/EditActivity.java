package jp.mixi.assignment.intent.med;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class EditActivity extends Activity {
	public static final int REQUEST_CODE_EDIT = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        findViewById(R.id.Submit).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	TextView textView = (TextView)findViewById(R.id.MyEdit);
            	Intent intent = new Intent();
            	intent.putExtra("text", textView.getText().toString());
            	setResult(RESULT_OK, intent);
            	//Log.v("a",(String) textView.getText());
            	finish();
                // TODO ここで、id が MyEdit の EditText からテキストを取得して、結果にセットする
            }
        });
    }
}