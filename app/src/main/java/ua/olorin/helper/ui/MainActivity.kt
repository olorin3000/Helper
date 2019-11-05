package ua.olorin.helper.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ua.olorin.helper.R
import ua.olorin.helper.adapter.ViewPagerFragmentAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ViewPagerFragmentAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    private lateinit var tabTitles : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ViewPagerFragmentAdapter(this)

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = adapter

        tabLayout = findViewById(R.id.tabLayout)
        tabTitles = resources.getStringArray(R.array.tab_titles)

        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}
