
package jp.mixi.practice.actionbar.beg;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

public class MainActivity extends SherlockActivity implements TabListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // �^�u�i�r�Q�[�V�������[�h�ɐݒ�
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // �^�u���쐬���Ēǉ��B�^�u�̑I���E�����E�đI�����n���h�����O����R�[���o�b�N�� TabListener ���Z�b�g���Ȃ��Ǝ��s����O�ŃN���b�V������
        getSupportActionBar().addTab(getSupportActionBar().newTab().setText("Tab1").setTabListener(this));
        getSupportActionBar().addTab(getSupportActionBar().newTab().setText("Tab2").setTabListener(this));
        getSupportActionBar().addTab(getSupportActionBar().newTab().setText("Tab3").setTabListener(this));
    }

    // �^�u�i�r�Q�[�V������ Tab ���I�����ꂽ���̃R�[���o�b�N
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
    	String mes = tab.getText().toString();
        Toast.makeText(this, mes, Toast.LENGTH_SHORT).show();
    }

    // �^�u�i�r�Q�[�V������ Tab ���I���������ꂽ���̃R�[���o�b�N
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        
    }

    // �^�u�i�r�Q�[�V������ Tab ���ēx�I�����ꂽ���̃R�[���o�b�N
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        
    }
}
