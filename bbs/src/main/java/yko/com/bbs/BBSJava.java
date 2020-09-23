package yko.com.bbs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yko.route.Route;

/**
 * Created by yko on 2017/9/29.
 */
@Route(path = "/BBS/BBSJava")
public class BBSJava extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbs_java);
    }
}
