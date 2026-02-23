package com.sandycodes.dayzero.ui.Auth

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sandycodes.dayzero.ui.Auth.LoginFragment
import com.sandycodes.dayzero.ui.Auth.SignupFragment

class AuthPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> LoginFragment()
            else -> SignupFragment()
        }
    }
}