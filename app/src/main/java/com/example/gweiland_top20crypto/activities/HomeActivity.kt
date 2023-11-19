package com.example.gweiland_top20crypto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.gweiland_top20crypto.R
import com.example.gweiland_top20crypto.databinding.ActivityHomeBinding
import com.example.gweiland_top20crypto.utility.AnimationUtil.uniClick
import com.google.android.material.textview.MaterialTextView

class HomeActivity : AppCompatActivity() {

    private var navController : NavController? = null
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        supportActionBar?.hide()
        val navHostFragment = (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment?)
        navController = navHostFragment?.navController
        navController?.let { binding.bottomNav?.setupWithNavController(it) }
        navController?.enableOnBackPressed(true)
        binding.mainMetaIcon.uniClick(true) {

        }
    }
}