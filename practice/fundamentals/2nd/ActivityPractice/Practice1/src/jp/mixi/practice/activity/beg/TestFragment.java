package jp.mixi.practice.activity.beg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TestFragment extends Fragment {
    public TestFragment(){}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // LayoutInflater を利用して、レイアウトをリソースとして読み込む
    	System.out.println("fragment.onCreateView");
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        return view;
    }

}

