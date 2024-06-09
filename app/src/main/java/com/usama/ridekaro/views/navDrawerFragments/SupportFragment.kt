package com.usama.ridekaro.views.navDrawerFragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.froyo.usama.R
import com.usama.ridekaro.profilefaqs
import com.usama.ridekaro.views.appfaqs
import kotlinx.android.synthetic.main.fragment_support.ivpayment
import kotlinx.android.synthetic.main.fragment_support.ivprofile
import kotlinx.android.synthetic.main.fragment_support.ivrefral
import kotlinx.android.synthetic.main.fragment_support.ivride
import kotlinx.android.synthetic.main.fragment_support.ivsafty
import kotlinx.android.synthetic.main.fragment_support.ivservices


class SupportFragment : Fragment(R.layout.fragment_support) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_support, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set click listeners for the ImageButtons
        ivsafty.setOnClickListener {
            startActivity(Intent(activity, appfaqs::class.java))
        }

        ivprofile.setOnClickListener {
            startActivity(Intent(activity, profilefaqs::class.java))
        }

        ivpayment.setOnClickListener {
            startActivity(Intent(activity, appfaqs::class.java))
        }

        ivrefral.setOnClickListener {
            startActivity(Intent(activity, appfaqs::class.java))
        }

        ivride.setOnClickListener {
            startActivity(Intent(activity, appfaqs::class.java))
        }

        ivservices.setOnClickListener {
            startActivity(Intent(activity, appfaqs::class.java))
        }

    }
}




