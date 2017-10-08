package com.yko.route;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.yko.route.hutlib.YkoHut;
import com.yko.yko.routetest.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        YkoHut.init(this);

        findViewById(R.id.button).setOnClickListener(onClickListener);
        findViewById(R.id.button3).setOnClickListener(onClickListener);
        findViewById(R.id.button5).setOnClickListener(onClickListener);
        findViewById(R.id.button4).setOnClickListener(onClickListener);
        getFragmentManager().beginTransaction().replace(R.id.flContainer, YkoHut.getFragment("/mall/mallkotlin")).commit();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            Class cls;
            switch(v.getId()){
                case R.id.button:
                    cls = YkoHut.getActivityClass("/BBS/BBSJava");
                    intent = new Intent(MainActivity.this, cls);
                    startActivity(intent);
                    break;
                case R.id.button3:
                    cls = YkoHut.getActivityClass("/mall/mallJava");
                    intent = new Intent(MainActivity.this, cls);
                    startActivity(intent);
                    break;
                case R.id.button5:
                    cls = YkoHut.getActivityClass("/center/centerJava");
                    intent = new Intent(MainActivity.this, cls);
                    startActivity(intent);
                    break;
                case R.id.button4:
                    cls = YkoHut.getActivityClass("/center/centerkotlin");
                    intent = new Intent(MainActivity.this, cls);
                    startActivity(intent);
                    break;
            }
        }
    };
}
