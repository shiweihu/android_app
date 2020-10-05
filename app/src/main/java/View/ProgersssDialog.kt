package View


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R


class ProgersssDialog(context: Context) : Dialog(context) {
    private var img: ImageView? = null
    private var txt: TextView? = null
    fun setMsg(msg: String?) {
        txt?.text = msg
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.progress_dialog, null)
        img = view.findViewById(R.id.progress_dialog_img) as ImageView
        txt = view.findViewById(R.id.progress_dialog_txt)
        //给图片添加动态效果
        val anim: Animation =
            AnimationUtils.loadAnimation(context, R.anim.loading_dialog_progressbar)
        img?.animation = anim
        txt?.setText(R.string.progressbar_dialog_txt)
        //dialog添加视图
        setContentView(view)
    }
   override fun create()
   {

   }
    init {

    }

}