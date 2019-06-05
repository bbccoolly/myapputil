package com.lcz.myutilapp.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.lcz.myutilapp.R
import com.lcz.myutilapp.databinding.ActivitySunflowerBinding

/**
 * desc TODO
 * Created by lcz on 2019/6/5.
 */
class SunFlowerActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mAppBarConfiguration: AppBarConfiguration
    private lateinit var mNavController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySunflowerBinding = DataBindingUtil.setContentView(this, R.layout.activity_sunflower)

        mDrawerLayout = binding.drawerLayout

        mNavController = Navigation.findNavController(this, R.id.garden_nav_fragment)

        mAppBarConfiguration = AppBarConfiguration(mNavController.graph, drawerLayout = mDrawerLayout)

        // Set up ActionBar
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(mNavController, mAppBarConfiguration)

        // Set up navigation menu
        binding.navigationView.setupWithNavController(mNavController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return mNavController.navigateUp(mAppBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
