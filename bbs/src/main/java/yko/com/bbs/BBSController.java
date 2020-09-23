package yko.com.bbs;

import android.app.Activity;
import android.content.Intent;

import com.yko.route.Controller;
import com.yko.route.YkoRoute;

import java.util.Map;

@YkoRoute(path = "/bbs/controller")
public class BBSController implements Controller {

    @Override
    public void start(Object from, Map<String, Object> params, ResultCallback callback) {
        /**
         * 可在此判断是否有权限跳转到新的activity
         */
        if(from instanceof Activity){
            Activity act = (Activity)from;
            Intent intent = new Intent(act, BBSJava.class);
            act.startActivity(intent);
        }
    }
}
