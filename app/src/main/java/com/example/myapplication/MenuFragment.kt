package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import mode.menuItem
import table.activity.*


class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.manu_ui, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //view.findViewById<LinearLayout>(R.id.layout_id).background.alpha = 100
        var myApp =  this.activity?.application as MyApplication
        val menuString:MutableList<String> = ArrayList<String>()
        for(i in myApp.menuListL!!.indices)
        {
            myApp .menuListL?.get(i)?.title?.let { menuString.add(it) }
        }




        view.findViewById<GridView>(R.id.grid_menu).adapter = gridItemAdapt(this,view.context,menuString)


    }

    override fun onStart() {
        super.onStart()
        val mainactivity =  this.activity as MainActivity
        mainactivity.closeBut?.visibility = View.VISIBLE
        mainactivity.closeBut?.setOnClickListener{ _->
            findNavController().navigate(R.id.action_MenuFragment_to_FirstFragment)
            mainactivity.closeBut?.visibility = View.INVISIBLE
        }
        mainactivity.title?.text = this.resources.getString(R.string.menu_list)

    }
    private class gridItemAdapt(val fragment:Fragment,val ctx: Context, val menuText:List<String>):BaseAdapter()
    {
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view = convertView

            val grid_icon_list =  ctx.resources.getStringArray(R.array.grid_icon_array)
            if( view == null)
            {
                view =  LayoutInflater.from(ctx).inflate(R.layout.menu_grid_item,null) as RelativeLayout
            }

            val icon_img =  view.findViewById<ImageView>(R.id.menu_icon)
            val title_text = view.findViewById<TextView>(R.id.menu_title)


            icon_img?.setImageResource(ctx.resources.getIdentifier(grid_icon_list[position], "mipmap", ctx.packageName))
            title_text?.text = menuText[position].replace("\n", "")

            view.setOnClickListener{_->
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
                }else if(position == 4)
                {
                    val intent = Intent(fragment.activity, TabFiveActivity::class.java)
                    fragment.activity?.startActivity(intent)
                    fragment.activity?.overridePendingTransition(0,0)
                }else if(position == 5)
                {
                    val intent = Intent(fragment.activity, TabSixActivity::class.java)
                    fragment.activity?.startActivity(intent)
                    fragment.activity?.overridePendingTransition(0,0)
                }else if(position == 6)
                {
                    val intent = Intent(fragment.activity, TabSevenActivity::class.java)
                    fragment.activity?.startActivity(intent)
                    fragment.activity?.overridePendingTransition(0,0)
                }else if(position == 7)
                {
                    val intent = Intent(fragment.activity, TabEightActivity::class.java)
                    fragment.activity?.startActivity(intent)
                    fragment.activity?.overridePendingTransition(0,0)
                }else if(position == 8)
                {
                    val intent = Intent(fragment.activity, TabNineActivity::class.java)
                    fragment.activity?.startActivity(intent)
                    fragment.activity?.overridePendingTransition(0,0)
                }else if(position == 9)
                {
                    val intent = Intent(fragment.activity, TabTenActivity::class.java)
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