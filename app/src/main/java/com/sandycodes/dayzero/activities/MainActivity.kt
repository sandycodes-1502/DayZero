package com.sandycodes.dayzero.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sandycodes.dayzero.R
import com.sandycodes.dayzero.databinding.ActivityMainBinding
import com.sandycodes.dayzero.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HomeFragment())
                .commit()
        }

    }
}