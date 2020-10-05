package table.activity.event_fragment

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.myapplication.MyApplication
import com.example.myapplication.R
import functions.Http
import functions.Response
import functions.Response_binary
import functions.ThreadManager
import mode.Event
import java.io.InputStream
import java.util.concurrent.Callable


class EventDetailFragment : Fragment() {

    private  var join_but:Button? = null
    private var autoPlay:Boolean = true
    private var view_page:ViewPager? = null
    private var currentIndex:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private val handler = Handler { msg->
        play()
        false
    }
    private val runnable = Runnable {
        view_page?.currentItem = ++currentIndex
    }
    private val restart_runnable = Runnable {
        autoPlay = true
        play()
    }
    fun play() {
        if (autoPlay) {
            handler.postDelayed(runnable, 3000)
        } else {
            handler.removeCallbacks(runnable)
        }
    }

    /**
     * 取消播放
     */
    fun cancel() {
        handler.removeCallbacks(runnable)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_detail, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
        handler.removeCallbacks(restart_runnable)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myapp = this.activity?.application as MyApplication

        view.findViewById<ImageButton>(R.id.act_close).setOnClickListener{ _->
            this@EventDetailFragment.findNavController().navigate(R.id.action_goback)
        }

        val bundle = getArguments()
        if (bundle != null)
        {
             var position = bundle?.getInt("position")
             val event:Event? = position?.let { myapp.eventList?.get(it) }
             view.findViewById<TextView>(R.id.tab_title).text = event?.ename
             view_page =  view.findViewById<ViewPager>(R.id.view_page)
             play()
             var image_list =event?.eimage?.split(";")
             image_list = image_list?.filterNot { it.isEmpty() }
             if(image_list?.size ==0)
             {
                 view_page?.visibility= View.GONE
                // view_page?.isGone = true
             }else
             {
                 view_page?.adapter = image_list?.let { pagerAdapter(this, it,view_page!!) }
             }
            view_page?.addOnPageChangeListener(object : OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    if(position == (view_page?.adapter?.count )?.minus(1) ?:true )
                    {
                        currentIndex = -1
                    }else
                    {
                        currentIndex = position
                    }


                    //此处currentIndex是从1开始的，要注意
                    //mLinearLayout.getChildAt(oldPosition).setEnabled(false)
                    // mLinearLayout.getChildAt(currentIndex - 1).setEnabled(true)
                    //oldPosition = currentIndex - 1
                    // Log.e("wenzhihao", "position==$position,currentIndex=$currentIndex")
                }

                override fun onPageScrollStateChanged(state: Int) {
                    if (state == ViewPager.SCROLL_STATE_IDLE) {
                        //view_page?.setCurrentItem(currentIndex, false)
                        if(autoPlay)
                        {
                            play()
                        }

                    } else if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                        cancel()
                        autoPlay = false
                        handler.removeCallbacks(restart_runnable)
                        handler.postDelayed(restart_runnable,5000)

                    }
                }
            })

             val event_content = view.findViewById<TextView>(R.id.event_content)
             event_content.text = event?.econtent
             val event_date= view.findViewById<TextView>(R.id.date_view)
             event_date.text = event?.edate
             val event_venue= view.findViewById<TextView>(R.id.venue_text)
             event_venue.text = event?.venue
             val event_rsvp= view.findViewById<TextView>(R.id.rsvp_text)
             event_rsvp.text = event?.rsvp
             join_but = view.findViewById<Button>(R.id.join_but)
             if(event?.join!=0)
             {
                 join_but?.text = this.getString(R.string.cancel)
             }else
             {
                 join_but?.text = this.getString(R.string.join)
             }
             join_but?.setOnClickListener{ _->
                 val uuid = myapp.preferences.UUID
                 var url = this.getString(R.string.join_event_url)
                 if(event?.join!=0)
                 {
                     //it means user wanna cancel
                       join_but?.isEnabled = false
                       url = url.format("1", event?.eid, uuid, "", "")
                       Http.get().doGet(url, object : Response {
                           override fun notification(s: String?) {
                               join_but?.isEnabled = true
                               s?.let {
                                   event?.join = Integer.parseInt(it)
                                   if (event?.join != 0) {
                                       join_but?.text =
                                           this@EventDetailFragment.getString(R.string.cancel)
                                   } else {
                                       join_but?.text =
                                           this@EventDetailFragment.getString(R.string.join)
                                   }
                               }
                           }
                       })
                 }else
                 {
                     showDialog("0", event?.eid, uuid, url, event)
                 }
             }

        }

    }

    private fun showDialog(type: String, eid: Int, uuid: String, url: String, event: Event?)
    {
        val dialogView: View =
            LayoutInflater.from(this.context).inflate(R.layout.input_info_dialog_layout, null)
        val dialog = AlertDialog.Builder(this.context).create()
        dialog?.setCancelable(false)
        dialog?.setView(dialogView)
        dialog?.show()
        dialogView.setTag(0)
        val name_edit = dialogView.findViewById<EditText>(R.id.name_edit)
        val pnumber_edit = dialogView.findViewById<EditText>(R.id.phone_edit)
        val submit_but = dialogView.findViewById<Button>(R.id.submit_but)
        val close_but = dialogView.findViewById<ImageButton>(R.id.close_but)
        close_but.setOnClickListener{
            dialog.dismiss()
        }
        submit_but.setOnClickListener{ _->
            if(name_edit.text.isEmpty())
            {
                Toast.makeText(this.context, R.string.toast_input_name, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pnumber_edit.text.isEmpty())
            {

                Toast.makeText(this.context, R.string.toast_input_number, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            submit_but.isEnabled = false;
            var  formaturl = url.format("0", event?.eid, uuid, name_edit.text, pnumber_edit.text)
            Http.get().doGet(formaturl, object : Response {
                override fun notification(s: String?) {
                    dialog.dismiss()
                    s?.let {
                        event?.join = Integer.parseInt(it)
                        if (event?.join != 0) {
                            join_but?.text = this@EventDetailFragment.getString(R.string.cancel)
                        } else {
                            join_but?.text = this@EventDetailFragment.getString(R.string.join)
                        }
                    }
                }
            })
        }

    }


    private class pagerAdapter(val frag: Fragment, val image_list: List<String>,val view_page:ViewPager): PagerAdapter()
    {

        val listview:MutableList<View>  = ArrayList<View>()
        override fun getCount(): Int {
            return image_list.size
        }
        override  fun instantiateItem(container: ViewGroup, position: Int): Any {
//        return super.instantiateItem(container, position);
            var view:View? = null
            if(position<listview.size)
            {
                view = listview.get(position)
                if(view!=null)
                {
                    container.addView(view)
                    return view
                }
            }
            val image_file_name = image_list.get(position)
            view =  LayoutInflater.from(frag.context).inflate(R.layout.viewpager_item, null) as LinearLayout
            val imgview:ImageView = view.findViewById<ImageView>(R.id.view_page_image)
            //val  bitmap:Bitmap = Bitmap()
           // imgview.setImageBitmap()
            var url = frag.getString(R.string.image_url)
            url = url.format(image_file_name)
            Http.get().doGetBinary(url, object : Response_binary {
                override fun notification(inputS: InputStream?) {
                    inputS?.let {
                        var bitmap:Bitmap? = ThreadManager.get().submit<Bitmap?>(Callable<Bitmap?> {
                             var bitmap: Bitmap? = null
                             bitmap = BitmapFactory.decodeStream(inputS)
                             bitmap
                        })
                        if(bitmap == null)
                        {
                            //security settings, if bitmap is null,the reason is server do not has this picture
                            //so handle it as a event without image.
                            view_page.visibility = View.GONE
                        }else
                        {
                            bitmap?.let{imgview.setImageBitmap(bitmap)}
                        }
                    }
                }
            })
            listview.add(position, view)
            container.addView(view)
            return view
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
           // super.destroyItem(container, position, `object`)
            //listview.removeAt(position)
            container.removeView(`object` as View)
        }


    }




}