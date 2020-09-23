package wangmo.doutry.com.model2

import android.app.Activity
import android.os.Bundle
import com.yko.route.Route

/**
 * Created by yko on 2017/9/29.
 */
@Route(path = "/mall/mallkotlin")
class MallKotlin : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_mall_kotlin)
    }
}