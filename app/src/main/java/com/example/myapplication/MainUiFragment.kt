package com.example.myapplication

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import functions.Http
import functions.Response
import functions.Tool


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
        mainactivity.title?.text =  this.resources.getString(R.string.title)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(!preferences.Firstflag)
        {
            showDialogForSubmitInfo(view)
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        view.findViewById<RelativeLayout>(R.id.mental_health_crisis).setOnClickListener{_ -> showErgencyDialog()
        }
        view.findViewById<ImageButton>(R.id.CALL).setOnClickListener{_ -> showErgencyDialog()
        }
        view.findViewById<RelativeLayout>(R.id.carers_resources).setOnClickListener{_->
            findNavController().navigate(R.id.action_FirstFragment_to_MenuFragment)
        }
        view.findViewById<ImageButton>(R.id.MenuBut).setOnClickListener{_->
            findNavController().navigate(R.id.action_FirstFragment_to_MenuFragment)
        }
        //view.findViewById<ScrollView>(R.id.scrollview).background.alpha = 200

       // view.findViewById<ImageButton>(R.id.DownLoadBut).setOnClickListener { _->
        //      this.DownloadFullManual();
      //  }

    }
    private fun showErgencyDialog()
    {
        val dialogView: View =
            LayoutInflater.from(this.context).inflate(R.layout.urgency_layout, null)
        val dialog = AlertDialog.Builder(this.context).create()
        dialog?.setCancelable(false)
        dialog?.setView(dialogView)
        dialog?.show()
        dialogView.setTag(0)
        val urgencyText = dialogView.findViewById<TextView>(R.id.urgency_text)
        urgencyText.text=this.getString(R.string.urgencyText1)

        val cancelBut = dialogView.findViewById<TextView>(R.id.cancel)
        cancelBut.setOnClickListener{_->
            dialog?.dismiss()
        }
        val okBut = dialogView.findViewById<TextView>(R.id.ok)
        okBut.setOnClickListener{_->
            var tag =  dialogView.getTag()
            when(tag)
            {
                0 ->{
                    urgencyText.text = this.getString(R.string.urgencyText2)
                    dialogView.setTag(1)
                }
                1->{
                    urgencyText.text = this.getString(R.string.urgencyText3)
                    dialogView.setTag(2)
                }
                2->{
                    //  if(checkReadPermission(Manifest.permission.CALL_PHONE,10111))
                    //  {
                    //     call(this.getString(R.string.emergencyCall))
                    // }
                    this@MainUiFragment.context?.let { Tool.get().call(it,this.getString(R.string.emergencyCall)) }
                    dialog?.dismiss()
                }
            }
        }
    }

    private fun DownloadFullManual()
    {

//
//
          var url = this.getString(R.string.url_download_full_manual)
          Tool.get().accessWeb(this.context!!,url)
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

    private fun showDialogForSubmitInfo(view:View)
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
        var adapter = ArrayAdapter.createFromResource(dialogView.context, R.array.gender,android.R.layout.simple_spinner_item);
        //第三步：设置下拉列表下拉时的菜单样式
        //第三步：设置下拉列表下拉时的菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //第四步：将适配器添加到下拉列表上
        //第四步：将适配器添加到下拉列表上
        gspinner.adapter = adapter



//        gspinner.setOnItemSelectedListener(object: AdapterView.OnItemSelectedListener
//        {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//
//            }
//        })

        val ageSpinner = dialogView.findViewById<Spinner>(R.id.AgeSpinner)
        adapter = ArrayAdapter.createFromResource(dialogView.context, R.array.ageGroup,android.R.layout.simple_spinner_item);
        //第三步：设置下拉列表下拉时的菜单样式
        //第三步：设置下拉列表下拉时的菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //第四步：将适配器添加到下拉列表上
        //第四步：将适配器添加到下拉列表上
        ageSpinner.adapter = adapter

        dialogView.findViewById<Button>(R.id.submitBut).setOnClickListener{ _ ->
            var postcode:String = dialogView.findViewById<EditText>(R.id.recordpostcode).text.toString()
            var gender:String = gspinner.selectedItem as String
            var age:String = ageSpinner.selectedItem as String

            when(gender)
            {
                "male" -> gender= "1"
                "female" -> gender = "0"
            }

            when(age)
            {
                "under 30" -> age= "1"
                "30 - 45" -> age = "2"
                "46 - 60" -> age = "3"
                "Over 60" -> age = "4"
            }

            //Snackbar.make(view, name+" "+gender+" "+age, Snackbar.LENGTH_LONG).setAction("Action", null).show()
            var url  = this.getString(R.string.url_submit_info).format(gender,age,postcode)
            Http.get().doGet(url, object : Response {
                override fun notification(s: String?) {
                      if(s.equals("1"))
                      {
                          preferences.Firstflag=true
                          dialog.dismiss()
                      }else
                      {
                          var toast=Toast.makeText(view.context,"please,input information",Toast.LENGTH_SHORT    )
                          toast.setGravity(Gravity.CENTER, 0, 0)
                          toast.show()
                      }
                }
            })


        }
    }
}