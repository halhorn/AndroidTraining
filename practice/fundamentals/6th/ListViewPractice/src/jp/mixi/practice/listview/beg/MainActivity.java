
package jp.mixi.practice.listview.beg;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MainActivity self = this;
        
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0; i < 100; i++){
        	list.add(String.format("Hoge%d", i));
        }
        final ListView listView = (ListView)findViewById(R.id.listView);
        CustomListItemAdapter adapter = new CustomListItemAdapter(this, list);
        listView.setAdapter(adapter);
        
        listView.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view, int position,
        			long id) {
        		Toast.makeText(self, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        	}
		});
        
        Button scrollTopButton = (Button)findViewById(R.id.scrollToTopButton);
        scrollTopButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				listView.smoothScrollToPosition(4);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    

}
