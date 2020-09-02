package com.example.myapplication

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import functions.Http
import functions.Response
import mode.TabOne
import mode.TabTwo
import functions.Tool
import mode.TabFour
import mode.TabThree


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))





        requestMainUIData()
      //  val mHomeKeyReceiver = MyReceiver()
       // val homeFilter = IntentFilter()
       // homeFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
      //  this.registerReceiver(mHomeKeyReceiver, homeFilter)
    }

    override fun onStart() {
        super.onStart()

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
        val MyApp = this.application as MyApplication
        var url = this.getString(R.string.url_menu_list)
        Http.get().doGet(url,object: Response
        {
            override fun notification(s: String?) {
                val gson = Gson()
                var strS =  s?.split("------------")
                for(i in strS!!.indices)
                {
                    if(i==0)
                    {
                       var json = strS.get(i)
                        val menuitem = gson.fromJson(json, TabOne::class.java)
                        MyApp.menuListL?.add(menuitem)
                    }
                    if(i==1)
                    {
                        var json = strS.get(i)
                        val menuitem = gson.fromJson(json, TabTwo::class.java)
                        MyApp.menuListL?.add(menuitem)
                    }
                    if(i==2)
                    {
                        var json = strS.get(i)
                        val menuitem = gson.fromJson(json, TabThree::class.java)
                        MyApp.menuListL?.add(menuitem)
                    }
                    if(i==3)
                    {
                        var json = strS.get(i)
                        val menuitem = gson.fromJson(json, TabFour::class.java)
                        MyApp.menuListL?.add(menuitem)
                    }

                }
            }
        })
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