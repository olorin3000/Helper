package ua.olorin.helper.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ua.olorin.helper.ui.DefaultFragment

class ViewPagerFragmentAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    private val fragmentList = listOf(
        DefaultFragment.newInstance("porter"),
        DefaultFragment.newInstance("carrier"),
        DefaultFragment.newInstance("worker")
    )

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}