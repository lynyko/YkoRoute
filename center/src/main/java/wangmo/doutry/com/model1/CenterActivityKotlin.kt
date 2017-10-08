package wangmo.doutry.com.model1

import android.app.Activity
import android.os.Bundle
import com.yko.route.YkoRoute

/**
 * Created by yko on 2017/9/29.
 */
@YkoRoute(path = "/center/centerkotlin")
class CenterActivityKotlin : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_center_kotlin)
    }
}