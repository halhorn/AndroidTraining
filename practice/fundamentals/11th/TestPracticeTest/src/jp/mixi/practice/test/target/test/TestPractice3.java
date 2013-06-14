package jp.mixi.practice.test.target.test;

import jp.mixi.practice.test.target.R;
import jp.mixi.practice.test.target.TestTarget3;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

public class TestPractice3 extends ActivityInstrumentationTestCase2<TestTarget3> {

	public TestPractice3() {
		this(TestTarget3.class);
	}

	public TestPractice3(Class<TestTarget3> class1) {
		super(class1);
	}
	
	public void testCountSenario(){
		Activity target = getActivity();
		EditText editTitle = (EditText) target.findViewById(R.id.TitleEditor);
		EditText editContent = (EditText) target.findViewById(R.id.ContentEditor);
		TextView titleCounter = (TextView) target.findViewById(R.id.TitleCounter);
		TextView contentCounter = (TextView) target.findViewById(R.id.ContentCounter);
		
		assertEquals("", titleCounter.getText().toString());
		assertEquals("", contentCounter.getText().toString());
		
		editTitle.setText("hoge");
		editContent.setText("aaa");
		
		assertEquals("4 / 10", titleCounter.getText().toString());
		assertEquals("3 / 10000", contentCounter.getText().toString());
		
	}

}
