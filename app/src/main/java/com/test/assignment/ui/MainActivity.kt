package com.test.assignment.ui

import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.test.assignment.R
import com.test.assignment.ui.adapter.ResourcePagerAdapter
import com.test.assignment.ui.fragment.ApplicationFragment
import com.test.assignment.ui.fragment.SettingsFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var mainViewModel: MainViewModel
    private var pagerAdapter: ResourcePagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        mainViewModel = getViewModel()





        mainViewModel.fetchTestApi(378)


        mainViewModel.loadingVisibility.observe(this) {
            if (it != null) {
                findViewById<RelativeLayout>(R.id.loader).visibility = it
            }
        }

        mainViewModel.errorMessage.observe(this) {
            if (it != null) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        mainViewModel.testResponse.observe(this) {
            if (it != null) {
                findViewById<TextView>(R.id.noDataFound).visibility = View.GONE

                val fragments = listOf(
                    ApplicationFragment(it),
                    SettingsFragment()
                )
                pagerAdapter =
                    ResourcePagerAdapter(fragments, supportFragmentManager, lifecycle)
                findViewById<ViewPager2>(R.id.viewPager).adapter = pagerAdapter

                TabLayoutMediator(
                    findViewById<TabLayout>(R.id.tabLayout),
                    findViewById<ViewPager2>(R.id.viewPager)
                ) { tab, position ->
                    tab.text = arrayOf("Application", "Setting")[position]
                }.attach()
            }else{
                findViewById<TextView>(R.id.viewPager).visibility = View.GONE
            }
        }

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/


    }


}