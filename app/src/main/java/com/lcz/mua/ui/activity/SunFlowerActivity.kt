package com.lcz.mua.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.lcz.mua.R

/**
 * desc TODO
 * Created by lcz on 2019/6/5.
 */
class SunFlowerActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: androidx.drawerlayout.widget.DrawerLayout
    private lateinit var mAppBarConfiguration: AppBarConfiguration
    private lateinit var mNavController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: com.lcz.mua.databinding.ActivitySunflowerBinding = DataBindingUtil.setContentView(this, R.layout.activity_sunflower)

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
