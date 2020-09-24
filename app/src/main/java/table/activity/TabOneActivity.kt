package table.activity

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import com.example.myapplication.MyApplication
import com.example.myapplication.R
import functions.Http
import functions.Tool


class TabOneActivity: Activity() {

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         this.requestWindowFeature(Window.FEATURE_NO_TITLE)
         this.getWindow().setFlags(
             WindowManager.LayoutParams.FLAG_FULLSCREEN,
             WindowManager.LayoutParams.FLAG_FULLSCREEN)
         // Inflate the layout for this fragment
         setContentView(R.layout.tab_1)

         findViewById<ImageButton>(R.id.act_close).setOnClickListener { _->
             super.finish()
             overridePendingTransition(0, 0);
         }
         var myApp =  this.application as MyApplication
         var titleText = myApp.menuListL?.get(0)?.title
         val url = this.getString(R.string.usage_table_request).format(titleText)
         Http.get().doGet(url,null)



         //findViewById<LinearLayout>(R.id.background_layout).background.alpha = 200
    }
    private fun showPictureDialog(img: Int)
    {
        val dialogView: View =
            LayoutInflater.from(this).inflate(R.layout.tab1_but1_dialog, null)
        val dialog: AlertDialog = AlertDialog.Builder(this)
            .create()
        val params = dialog.window!!.attributes
        params.width = 400
        params.height = 400
        dialog.window!!.attributes = params
        dialog.setTitle("")
        dialog.setCancelable(true)
        dialog.setView(dialogView)
        dialogView.findViewById<ImageView>(R.id.picture_dialog).setImageResource(img)
        dialog.show()
    }

    override fun onStart() {
        super.onStart()

        var myApp =  this.application as MyApplication

        var text =  myApp.menuListL?.get(0)?.content
        var titleText = myApp.menuListL?.get(0)?.title
        titleText = titleText?.replace("\n","")
        var title = titleText

        findViewById<TextView>(R.id.tab_title).text = title
        findViewById<TextView>(R.id.tab1_content).text= Tool.get().functionText(this,text!!)
        findViewById<TextView>(R.id.tab1_content).movementMethod = LinkMovementMethod.getInstance()

    }


}