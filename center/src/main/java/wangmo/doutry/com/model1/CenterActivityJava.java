package wangmo.doutry.com.model1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yko.route.YkoRoute;

/**
 * Created by yko on 2017/9/22.
 */
@YkoRoute(path="/center/centerJava")
public class CenterActivityJava extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_java);
    }
}
