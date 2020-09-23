package com.yko.route;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yko.yko.routetest.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        YkoHut.getInstance().init(this);

        findViewById(R.id.button).setOnClickListener(onClickListener);
        findViewById(R.id.button3).setOnClickListener(onClickListener);
        findViewById(R.id.button5).setOnClickListener(onClickListener);
        findViewById(R.id.button4).setOnClickListener(onClickListener);
        findViewById(R.id.button6).setOnClickListener(onClickListener);
        Fragment fragment = YkoHut.getInstance().getFragment("/mall/fragment");
        if(fragment != null){
            getFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).commit();
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button:
                    YkoHut.getInstance().startActivity(MainActivity.this, "/BBS/BBSJava", null);
                    break;
                case R.id.button3:
                    YkoHut.getInstance().startActivity(MainActivity.this, "/mall/mallJava", null);
                    break;
                case R.id.button5:
                    YkoHut.getInstance().startActivity(MainActivity.this, "/center/centerJava", null);
                    break;
                case R.id.button4:
                    YkoHut.getInstance().startActivity(MainActivity.this, "/mall/mallkotlin", null);
                    break;
                case R.id.button6:
                    YkoHut.getInstance().startController(MainActivity.this, "/bbs/controller", null, null);
                    break;
            }
        }
    };
}
