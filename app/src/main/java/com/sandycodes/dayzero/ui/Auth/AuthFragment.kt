package com.sandycodes.dayzero.ui.Auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.sandycodes.dayzero.R
import com.sandycodes.dayzero.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {
    lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth, container, false)
        binding = FragmentAuthBinding.bind(view)

        val adapter = AuthPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = if (position == 0) "Login" else "Sign Up"
        }.attach()

        binding.viewPager.setPageTransformer { page, position ->
            page.alpha = 0.5f + (1 - kotlin.math.abs(position))
        }

        return view
    }

}