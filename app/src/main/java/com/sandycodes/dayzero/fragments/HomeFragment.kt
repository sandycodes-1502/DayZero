package com.sandycodes.dayzero.fragments

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.sandycodes.dayzero.R
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

//        val logo = view.findViewById<ImageView>(R.id.logo)

//        val drawable = logo.drawable
//        if (drawable is AnimatedVectorDrawable) {
//            drawable.start()
//        }

        return view
    }

}