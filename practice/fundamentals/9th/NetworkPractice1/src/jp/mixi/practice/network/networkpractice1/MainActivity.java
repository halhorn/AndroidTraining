package jp.mixi.practice.network.networkpractice1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements LoaderCallbacks<String>{
	public static final String TAG = MainActivity.class.getSimpleName();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Log.v(TAG, "onCreate");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()
                    .detectNetwork()
                    .penaltyDeath()
                    .build());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View buttonGet = findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // http getの処理を書く
            	Log.v(TAG, "click");
            	LoaderManager lm = getSupportLoaderManager();
            	lm.initLoader(0, new Bundle(), MainActivity.this);
            }
        });
        View buttonPost = findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // http postの処理を書く
            	LoaderManager lm = getSupportLoaderManager();
            	lm.initLoader(1, new Bundle(), MainActivity.this);
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
	public AsyncTaskLoader<String> onCreateLoader(final int mode, Bundle arg1) {
		Log.v(TAG, "onCreateLoader");
		return new AsyncTaskLoader<String>(this) {

			@Override
			protected void onStartLoading() {
				Log.v(TAG,"onStartLoading");
				super.onStartLoading();
				forceLoad();
			}
			
			@Override
			public String loadInBackground() {
				if (mode == 0){
					return getConnection("http://mixi.jp");
				}else{
					return postConnection("http://mixi.jp");
				}
			}
		};
	}
	
	String getConnection(String urlString){
		Log.v(TAG, "background");
    	HttpURLConnection conn = null;
    	String ret = null;
    	try{
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            Log.v(TAG, "make conn");
            conn.connect();
            Log.v(TAG, "connected");
            InputStream is = conn.getInputStream();
            StringBuilder src = new StringBuilder();
            while (true) {
                byte[] line = new byte[1024];
                int size = is.read(line);
                if (size <= 0)
                    break;
                src.append(new String(line, "euc-jp"));
            }
            ret = src.toString();
            Log.v(TAG,ret);
        } catch (IOException e) {
        	Log.e(TAG,"Failed Connection");
            e.printStackTrace();
        } finally{
        	if (conn != null){
        		conn.disconnect();
        	}
        }

		return ret;
	}
	
	String postConnection(String urlString){
	      HttpURLConnection connection = null;
	      StringBuilder src = null;
	      try {
		      URL url = new URL(urlString);
	          connection = (HttpURLConnection) url.openConnection();
	          connection.setRequestMethod("POST");
	          connection.setDoOutput(true);

	          String postData = "hoge=fuga&piyo=test";
	          OutputStream os = connection.getOutputStream();
	          os.write(postData.getBytes());
	          os.flush();
	          os.close();

	          InputStream is = connection.getInputStream();

	          src = new StringBuilder();
	          while (true) {
	              byte[] line = new byte[1024];
	              int size = is.read(line);
	              if (size <= 0)
	                  break;
	              src.append(new String(line, "euc-jp"));
	          }
	      } catch (IOException e) {
	          e.printStackTrace();
	      } finally{
	          connection.disconnect();
	      }
	      return src.toString();
	}

	@Override
	public void onLoadFinished(Loader<String> loader,
			String result) {
		Log.v(TAG,"onLoadFinished");
		TextView resTextView = (TextView)findViewById(R.id.responce);
		resTextView.setText(result);
		
	}

	@Override
	public void onLoaderReset(Loader<String> arg0) {
		// TODO Auto-generated method stub
		
	}
}
