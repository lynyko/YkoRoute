package wangmo.doutry.com.model2

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yko.route.YkoRoute

/**
 * Created by yko on 2017/9/29.
 */
@YkoRoute(path = "/mall/mallkotlin")
class MallKotlin : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_mall_kotlin)
    }
}