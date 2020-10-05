package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.*
import android.view.View.OnTouchListener
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import functions.Http
import functions.Response
import functions.Tool
import functions.ToolCallBack
import mode.Event
import table.activity.ContactUs
import table.activity.EventActivity
import table.activity.TabSevenActivity


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainUiFragment : Fragment() {
    private val preferences by lazy {
        val myapp = this.activity?.application as MyApplication
        myapp.preferences
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onStart() {
        super.onStart()
        val mainactivity =  this.activity as MainActivity
        mainactivity.closeBut?.visibility = View.INVISIBLE
        mainactivity.title?.visibility = View.INVISIBLE
        mainactivity.title_img?.visibility = View.VISIBLE

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(!preferences.Firstflag)
        {
            showDialogForSubmitInfo(view)
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        val metal_health_crisis_but = view.findViewById<RelativeLayout>(R.id.mental_health_crisis)

        metal_health_crisis_but.setOnClickListener{ _ ->
            Tool.get().showErgencyDialog(this@MainUiFragment.context)
        }

        metal_health_crisis_but.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                metal_health_crisis_but.setBackgroundResource(R.drawable.rectange_corner_gray)
            } else if (event.action == MotionEvent.ACTION_UP) {
                metal_health_crisis_but.setBackgroundResource(R.drawable.rectange_corner_alpha)
            } else if (event.action == MotionEvent.ACTION_CANCEL) {
                metal_health_crisis_but.setBackgroundResource(R.drawable.rectange_corner_alpha)
            }
            false
        })

        val menuBook  = view.findViewById<RelativeLayout>(R.id.carers_resources)
        menuBook.setOnClickListener{ _->
            findNavController().navigate(R.id.action_FirstFragment_to_MenuFragment)
        }
        menuBook.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                menuBook.setBackgroundResource(R.drawable.rectange_corner_gray)
            } else if (event.action == MotionEvent.ACTION_UP) {
                menuBook.setBackgroundResource(R.drawable.rectange_corner_alpha)
            } else if (event.action == MotionEvent.ACTION_CANCEL) {
                menuBook.setBackgroundResource(R.drawable.rectange_corner_alpha)
            }
            false
        })
        val contactUs_layout = view.findViewById<RelativeLayout>(R.id.contact_us)
        contactUs_layout.setOnClickListener{ _->
            val intent = Intent(activity, ContactUs::class.java)
            this@MainUiFragment.activity?.startActivity(intent)
            this@MainUiFragment.activity?.overridePendingTransition(0, 0)
        }
        contactUs_layout.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                contactUs_layout.setBackgroundResource(R.drawable.rectange_corner_gray)
            } else if (event.action == MotionEvent.ACTION_UP) {
                contactUs_layout.setBackgroundResource(R.drawable.rectange_corner_alpha)
            } else if (event.action == MotionEvent.ACTION_CANCEL) {
                contactUs_layout.setBackgroundResource(R.drawable.rectange_corner_alpha)
            }
            false
        })

        val tips_layout = view.findViewById<RelativeLayout>(R.id.carers_tips)
        tips_layout.setOnClickListener{ _->
            findNavController().navigate(R.id.action_FirstFragment_to_tipsFragment)
        }
        tips_layout.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                tips_layout.setBackgroundResource(R.drawable.rectange_corner_gray)
            } else if (event.action == MotionEvent.ACTION_UP) {
                tips_layout.setBackgroundResource(R.drawable.rectange_corner_alpha)
            } else if (event.action == MotionEvent.ACTION_CANCEL) {
                tips_layout.setBackgroundResource(R.drawable.rectange_corner_alpha)
            }
            false
        })




        val come_up_layout = view.findViewById<RelativeLayout>(R.id.come_up)
        come_up_layout.setOnClickListener{ _->
            var url = this.getString(R.string.select_events)
            url=url.format(preferences.UUID)
            Http.get().doGetWithDialog(
                this.context!!,
                url,
                object : Response {
                    override fun notification(s: String?) {
                        val gson = Gson()
                        val event_list =  gson.fromJson<MutableList<Event>>(s!!, object : TypeToken<MutableList<Event>>() {}.type)
                        val myapp = this@MainUiFragment?.activity?.application as MyApplication
                        myapp.eventList = event_list
                        val intent = Intent(activity, EventActivity::class.java)
                        this@MainUiFragment.activity?.startActivity(intent)
                        this@MainUiFragment.activity?.overridePendingTransition(0, 0)
                    }
                })
        }
        come_up_layout.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                come_up_layout.setBackgroundResource(R.drawable.rectange_corner_gray)
            } else if (event.action == MotionEvent.ACTION_UP) {
                come_up_layout.setBackgroundResource(R.drawable.rectange_corner_alpha)
            } else if (event.action == MotionEvent.ACTION_CANCEL) {
                come_up_layout.setBackgroundResource(R.drawable.rectange_corner_alpha)
            }
            false
        })
        val resis_layout = view.findViewById<RelativeLayout>(R.id.cresis_plan)
        resis_layout.setOnClickListener{ _->
            val intent = Intent(activity, TabSevenActivity::class.java)
            this@MainUiFragment.activity?.startActivity(intent)
            this@MainUiFragment.activity?.overridePendingTransition(0, 0)
        }
        resis_layout.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                resis_layout.setBackgroundResource(R.drawable.rectange_corner_gray)
            } else if (event.action == MotionEvent.ACTION_UP) {
                resis_layout.setBackgroundResource(R.drawable.rectange_corner_alpha)
            } else if (event.action == MotionEvent.ACTION_CANCEL) {
                resis_layout.setBackgroundResource(R.drawable.rectange_corner_alpha)
            }
            false
        })



        view.findViewById<RelativeLayout>(R.id.carers_resources).setOnClickListener{ _->
            findNavController().navigate(R.id.action_FirstFragment_to_MenuFragment)
        }

        //view.findViewById<ScrollView>(R.id.scrollview).background.alpha = 200

       // view.findViewById<ImageButton>(R.id.DownLoadBut).setOnClickListener { _->
        //      this.DownloadFullManual();
      //  }

    }


    private fun DownloadFullManual()
    {

//
//
          var url = this.getString(R.string.url_download_full_manual)
          Tool.get().accessWeb(this.context!!, url)
//        //创建下载任务,downloadUrl就是下载链接
//        val request = DownloadManager.Request(Uri.parse(url))
//        //指定下载路径和下载文件名
//        request
//            .setAllowedNetworkTypes(
//                DownloadManager.Request.NETWORK_WIFI
//                        or DownloadManager.Request.NETWORK_MOBILE
//            )
//        request.setMimeType("attachment;filename=\""+"full_manual.pdf"+"\"");
//        request.setTitle(getString(R.string.download_title))
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"full_manual.pdf")
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
//
//        //获取下载管理器
//        var downloadManage =  this.context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//        //将下载任务加入下载队列，否则不会进行下载
//
//        var fileId = downloadManage.enqueue(request)
//        this.context?.registerReceiver(receiver,
//            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
//        )




    }
    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

        }
    }

    private fun showDialogForSubmitInfo(view: View)
    {
        val dialogView: View =
            LayoutInflater.from(view.context).inflate(R.layout.user_info_dialog, null)
        val dialog: AlertDialog = AlertDialog.Builder(view.context)
            .create()
        dialog.setTitle("write your information")
        dialog.setCancelable(false)
        dialog.setView(dialogView)
        dialog.show()
        val gspinner = dialogView.findViewById<Spinner>(R.id.GenderSpinner)
        var adapter = ArrayAdapter.createFromResource(
            dialogView.context,
            R.array.gender,
            android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        gspinner.adapter = adapter



        val ageSpinner = dialogView.findViewById<Spinner>(R.id.AgeSpinner)
        adapter = ArrayAdapter.createFromResource(
            dialogView.context,
            R.array.ageGroup,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        ageSpinner.adapter = adapter
        dialogView.findViewById<Button>(R.id.submitBut).setOnClickListener{ _ ->
            var postcode:String = dialogView.findViewById<EditText>(R.id.recordpostcode).text.toString()
            var gender:String = gspinner.selectedItem as String
            var age:String = ageSpinner.selectedItem as String

            when(gender)
            {
                "male" -> gender = "1"
                "female" -> gender = "0"
                "Non-binary" -> gender = "2"
                "Rather Not Say" -> gender = "3"
            }

            when(age)
            {
                "18-35" -> age = "1"
                "30 - 50" -> age = "2"
                "51 - 65" -> age = "3"
                "66 or above" -> age = "4"
            }

            //Snackbar.make(view, name+" "+gender+" "+age, Snackbar.LENGTH_LONG).setAction("Action", null).show()
            var url  = this.getString(R.string.url_submit_info).format(gender, age, postcode)
            Http.get().doGet(url, object : Response {
                override fun notification(s: String?) {
                    if (!s.equals("0")) {
                        preferences.Firstflag = true
                        preferences.UUID = s!!
                        dialog.dismiss()
                    } else {
                        var toast = Toast.makeText(
                            view.context,
                            "please,input information",
                            Toast.LENGTH_SHORT
                        )
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                    }
                }
            })


        }
    }
}