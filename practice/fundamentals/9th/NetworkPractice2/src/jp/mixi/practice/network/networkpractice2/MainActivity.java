package jp.mixi.practice.network.networkpractice2;

import java.io.IOException;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements LoaderCallbacks<String>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()
                    .detectNetwork()
                    .penaltyDeath()
                    .build());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View buttonGet = (Button)findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                getSupportLoaderManager().initLoader(0, new Bundle(), MainActivity.this);
            }
        });
        
        View buttonPost = findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                getSupportLoaderManager().initLoader(1, new Bundle(), MainActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public Loader<String> onCreateLoader(final int mode, Bundle arg1) {
		return new android.support.v4.content.AsyncTaskLoader<String>(this) {
			@Override
			protected void onStartLoading() {
				super.onStartLoading();
				forceLoad();
			}
			
			@Override
			public String loadInBackground() {
				String ret = null;
			    HttpClient client = new DefaultHttpClient();
			    try {
			    	HttpUriRequest request;
			    	if (mode == 0){
			    		request = new HttpGet("http://mixi.jp");
			    	}else{
			    		request = new HttpPost("http://mixi.jp");
			    	}
			          ret = client.execute(request,
			                  new ResponseHandler<String>() {
			                      public String handleResponse(HttpResponse response)
			                              throws ClientProtocolException, IOException {
			                          return EntityUtils.toString(response.getEntity());
			                      }
			                  });
			    } catch (IOException e) {
			    	e.printStackTrace();
			    }
			    return ret;
			}
			
		};
	}

	@Override
	public void onLoadFinished(Loader<String> arg0, String result) {
		TextView resTextView = (TextView)findViewById(R.id.responce);
		resTextView.setText(result);
	}

	@Override
	public void onLoaderReset(Loader<String> arg0) {
		// TODO Auto-generated method stub
		
	}

}
