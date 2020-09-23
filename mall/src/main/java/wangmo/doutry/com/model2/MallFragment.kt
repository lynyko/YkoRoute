package wangmo.doutry.com.model2

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yko.route.YkoRoute

@YkoRoute(path = "/mall/fragment")
class MallFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = TextView(activity)
        view.text = "fragment"
        return view
    }
}