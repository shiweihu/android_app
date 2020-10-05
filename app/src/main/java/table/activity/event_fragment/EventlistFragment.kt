package table.activity.event_fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.myapplication.MyApplication
import com.example.myapplication.R
import functions.Tool
import functions.ToolCallBack
import mode.Event
import mode.menuItem


class EventlistFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eventlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myapp = this.activity?.application as MyApplication

        view.findViewById<ImageButton>(R.id.act_close).setOnClickListener{_->
            this@EventlistFragment.activity?.finish()
        }
        val list_view = view.findViewById<ListView>(R.id.list_view)

        myapp.eventList?.let {
            list_view.adapter = ListItemAdapt(this,myapp.eventList!!)
        }
    }

    private class ListItemAdapt(val frag: Fragment, val list:MutableList<Event>): BaseAdapter()
    {
        override fun getCount(): Int {
            return list.size
        }

        override fun getItem(p0: Int): Any {
            return list.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return 0L
        }
        override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
            var view:View? = p1
            //if(view== null)
            //{
                view =  LayoutInflater.from(frag.context).inflate(R.layout.event_item,null) as RelativeLayout
                val title = view.findViewById<TextView>(R.id.event_title)
                val date = view.findViewById<TextView>(R.id.event_date)
                title.text  = list[position].ename
                date.text = list[position].edate
                view.setOnClickListener{_->
                    val bundle = Bundle()
                    bundle.putInt("position",position)
                    frag.findNavController().navigate(R.id.action_goto_detail,bundle)
                }
            //}
            return view
        }

    }

}