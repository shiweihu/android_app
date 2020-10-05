package com.example.myapplication

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import functions.Http
import functions.Response
import functions.Tool

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Tips.newInstance] factory method to
 * create an instance of this fragment.
 */
class TipsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onStart() {
        super.onStart()
        val mainactivity =  this.activity as MainActivity
        mainactivity.closeBut?.visibility = View.VISIBLE
        mainactivity.closeBut?.setOnClickListener{ _->
            findNavController().navigate(R.id.action_TipsFragment_to_FirstFragment)
           // mainactivity.closeBut?.visibility = View.INVISIBLE
        }
        mainactivity.title?.text = this.resources.getString(R.string.tips)
        mainactivity.title?.visibility = View.VISIBLE
        mainactivity.title_img?.visibility = View.INVISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tips, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tip_content = view.findViewById<TextView>(R.id.tips_content)
        var url = this.getString(R.string.tips_url)
        this.context?.let {
            Http.get().doGetWithDialog(it,url,object:Response{
                override fun notification(s: String?) {
                    s?.let { tip_content.text = Tool.get().functionText(this@TipsFragment.context!!,it)
                        tip_content.movementMethod  = LinkMovementMethod.getInstance()
                    }
                }
            })
        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Tips.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TipsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}