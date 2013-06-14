package jp.mixi.assignment.listview.beg;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class BookArrayAdapter extends ArrayAdapter<String> {

	public BookArrayAdapter(Context context, List<String> objects) {
		super(context, 0, objects);
		
	}

}
