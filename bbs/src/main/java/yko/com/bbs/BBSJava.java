package yko.com.bbs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yko.route.YkoRoute;

/**
 * Created by yko on 2017/9/29.
 */
@YkoRoute(path = "/BBS/BBSJava")
public class BBSJava extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbs_java);
    }
}
