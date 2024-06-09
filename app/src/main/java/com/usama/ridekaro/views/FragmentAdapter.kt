package com.usama.ridekaro.views

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.usama.ridekaro.views.navDrawerFragments.FragmentA
import com.usama.ridekaro.views.navDrawerFragments.FragmentB
import com.usama.ridekaro.views.navDrawerFragments.MyRidesFragment


class FragmentAdapter(fragmentActivity: MyRidesFragment?,val name:String) :
    FragmentStateAdapter(fragmentActivity!!) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return FragmentA.newInstance(name)
            1 -> return FragmentB.newInstance("This is second Fragment")

        }
        return FragmentA.newInstance("This is Default Fragment")
    }

    override fun getItemCount(): Int {
        return 2
    }
}