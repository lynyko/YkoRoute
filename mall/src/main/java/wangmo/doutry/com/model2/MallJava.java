package wangmo.doutry.com.model2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yko.route.YkoRoute;

/**
 * Created by yko on 2017/9/21.
 */
@YkoRoute(path = "/mall/mallJava")
public class MallJava extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_java);
    }
}
