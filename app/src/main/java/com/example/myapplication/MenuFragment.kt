package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import mode.TabOne
import mode.TabThree
import mode.TabTwo
import mode.menuItem
import table.activity.TabFourActivity
import table.activity.TabOneActivity
import table.activity.TabThreeActivity
import table.activity.TabTwoActivity


class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.list_view_close).setOnClickListener { _->
            findNavController().navigate(R.id.action_MenuFragment_to_FirstFragment)
        }

        var myApp =  this.activity?.application as MyApplication
        val menuString:MutableList<String> = ArrayList<String>()
        for(i in myApp.menuListL!!.indices)
        {
            myApp .menuListL?.get(i)?.title?.let { menuString.add(it) }
        }
        view.findViewById<ListView>(R.id.listView).adapter = listAdapt(this,view.context,menuString,myApp.menuListL!!)

    }
    private class listAdapt(val fragment:Fragment,val ctx: Context, val menuText:List<String>,val menuListL:MutableList<menuItem>):BaseAdapter()
    {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view = convertView
            if( view == null)
            {
                view =  LayoutInflater.from(ctx).inflate(R.layout.list_item_layout,null) as RelativeLayout
            }
            view.findViewById<TextView>(R.id.menuText).text = getItem(position)
            view.findViewById<TextView>(R.id.menuText).setOnClickListener{
                if(position ==0)
                {
                    val intent = Intent(fragment.activity, TabOneActivity::class.java)
                    fragment.activity?.startActivity(intent)
                    fragment.activity?.overridePendingTransition(0,0)
                }else if(position == 1)
                {
                    val intent = Intent(fragment.activity, TabTwoActivity::class.java)
                    fragment.activity?.startActivity(intent)
                    fragment.activity?.overridePendingTransition(0,0)
                }else if(position == 2)
                {
                    val intent = Intent(fragment.activity, TabThreeActivity::class.java)
                    fragment.activity?.startActivity(intent)
                    fragment.activity?.overridePendingTransition(0,0)
                }else if(position == 3)
                {
                    val intent = Intent(fragment.activity, TabFourActivity::class.java)
                    fragment.activity?.startActivity(intent)
                    fragment.activity?.overridePendingTransition(0,0)
                }
            }
            return view
        }

        override fun getItem(position: Int): String {
            return menuText.get(position)
        }

        override fun getItemId(position: Int): Long {
            return 0L
        }

        override fun getCount(): Int {
           return menuText.size
        }

    }

}