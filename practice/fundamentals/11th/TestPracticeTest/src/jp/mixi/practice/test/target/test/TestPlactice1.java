package jp.mixi.practice.test.target.test;

import jp.mixi.practice.test.target.SubActivity;
import jp.mixi.practice.test.target.TestTarget1;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;

public class TestPlactice1 extends AndroidTestCase {
	
	public void testStartSubActivity() throws Exception{
		TestTarget1 target = new TestTarget1();
		target.startSubActivity(new TestContext(getContext()), "title");
	}
	
	private static class TestContext extends MockContext
	{
		Context baseContext;
		public TestContext(Context base) {
			baseContext = base;
		}
		
		@Override
		public String getPackageName() {
			return baseContext.getPackageName();
		}
		
		@Override
		public void startActivity(Intent intent) {
			assertEquals(SubActivity.class.getCanonicalName(), intent.getComponent().getClassName());
			assertEquals(Uri.parse("http://mixi.jp"), intent.getData());
			assertTrue(intent.hasExtra(Intent.EXTRA_SUBJECT));
			assertEquals("title", intent.getStringExtra(Intent.EXTRA_SUBJECT));
		}
	}
	
}
