package com.example.myapplication

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import functions.Http
import functions.Response
import functions.Tool
import mode.*


class MainActivity : AppCompatActivity() {

    private val preferences by lazy {
        val myapp = this?.application as MyApplication
        myapp.preferences
    }

    var closeBut:ImageButton? = null;
    var title:TextView? = null
    var subtitle1:TextView? = null
    var subtitle2:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


        closeBut = findViewById<ImageButton>(R.id.closeBut)
        title =  this.findViewById<TextView>(R.id.title)
        subtitle1 =  this.findViewById<TextView>(R.id.subtitle1)
        subtitle2 =  this.findViewById<TextView>(R.id.subtitle2)

      //  val mHomeKeyReceiver = MyReceiver()
       // val homeFilter = IntentFilter()
       // homeFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
      //  this.registerReceiver(mHomeKeyReceiver, homeFilter)
    }

    override fun onStart() {
        super.onStart()

        requestMainUIData()
   //     subtitle1?.visibility = View.VISIBLE
    //    subtitle2?.visibility = View.VISIBLE
        subtitle1?.text = resources.getString(R.string.title1)
        subtitle2?.text = resources.getString(R.string.title2)

    }

    override fun onRestart() {
        super.onRestart()
    }


//    class MyReceiver : BroadcastReceiver() {
//        val SYSTEM_DIALOG_REASON_KEY:String = "reason"
//        val SYSTEM_DIALOG_REASON_GLOBAL_ACTIONS:String = "globalactions"
//        val SYSTEM_DIALOG_REASON_RECENT_APPS:String = "recentapps"
//        val SYSTEM_DIALOG_REASON_HOME_KEY:String = "homekey"
//        override fun onReceive(context: Context, intent: Intent) {
//                val action = intent.action
//                if (action == Intent.ACTION_CLOSE_SYSTEM_DIALOGS) {
//                    val reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY)
//                    if (reason != null) {
//                        if (reason == SYSTEM_DIALOG_REASON_HOME_KEY) {
//                            var myactivity = context as MainActivity
//                            myactivity.ShowUrgencyDialog = true
//                        } else if (reason == SYSTEM_DIALOG_REASON_RECENT_APPS ) {
//
//                        }
//                    }
//                }
//        }
//    }


    private fun requestMainUIData()
    {

        var url = this.getString(R.string.url_menu_list)
        url = url.format(preferences.MenuListVersion)
        Http.get().doGetWithDialog(this,url,object: Response
        {
            override fun notification(s: String?) {
                if(s == null || s.equals("1"))
                {
                    parseData(preferences.MenuListData)
                }else
                {
                    parseData(s!!)
                }
            }
        })
    }
    private fun parseData(json:String)
    {
        if(json.isEmpty())
        {
            Toast.makeText(
                this,
                "disabile to connect server,program",
                Toast.LENGTH_SHORT
            ).show()
            requestMainUIData()
            return
        }

        val MyApp = this.application as MyApplication
        val gson = Gson()
        val rp = gson.fromJson(json,responsePack::class.java)
        MyApp.menuListL = rp.list
        preferences.MenuListVersion = rp.version
        preferences.MenuListData =  json
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
       // menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}