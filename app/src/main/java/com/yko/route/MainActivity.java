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
        YkoHut.init(this);

        findViewById(R.id.button).setOnClickListener(onClickListener);
        findViewById(R.id.button3).setOnClickListener(onClickListener);
        findViewById(R.id.button5).setOnClickListener(onClickListener);
        findViewById(R.id.button4).setOnClickListener(onClickListener);
        findViewById(R.id.button6).setOnClickListener(onClickListener);
        Class cls = YkoHut.getClass("/mall/fragment");
        if(Fragment.class.isAssignableFrom(cls)){
            try {
                Fragment fragment = (Fragment)cls.newInstance();
                getFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).commit();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            Class cls;
            switch(v.getId()){
                case R.id.button:
                    cls = YkoHut.getClass("/BBS/BBSJava");
                    intent = new Intent(MainActivity.this, cls);
                    startActivity(intent);
                    break;
                case R.id.button3:
                    cls = YkoHut.getClass("/mall/mallJava");
                    intent = new Intent(MainActivity.this, cls);
                    startActivity(intent);
                    break;
                case R.id.button5:
                    cls = YkoHut.getClass("/center/centerJava");
                    intent = new Intent(MainActivity.this, cls);
                    startActivity(intent);
                    break;
                case R.id.button4:
                    cls = YkoHut.getClass("/mall/mallkotlin");
                    intent = new Intent(MainActivity.this, cls);
                    startActivity(intent);
                    break;
                case R.id.button6:
                    cls = YkoHut.getClass("/bbs/controller");
                    if(Controller.class.isAssignableFrom(cls)){
                        try {
                            Controller controller = (Controller)cls.newInstance();
                            controller.start(MainActivity.this, null, null);
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    };
}
