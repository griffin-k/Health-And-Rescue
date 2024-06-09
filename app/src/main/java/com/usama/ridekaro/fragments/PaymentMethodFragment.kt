package com.usama.ridekaro.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.froyo.usama.R

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_payment_method.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PaymentMethodFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_method, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnPaytmLink.setOnClickListener {
            val appPackageName = "com.techlogix.mobilinkcustomer"
            val pm: PackageManager = context?.applicationContext!!.getPackageManager()

            val appstart = pm.getLaunchIntentForPackage(appPackageName)
            if (null != appstart) {
                context?.getApplicationContext()!!.startActivity(appstart)
            } else {
                Toast.makeText(
                    context?.getApplicationContext(), R.string.Install_PayTm_on_your_device,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        btnGooglePay.setOnClickListener {
            val appPackageName = "com.google.android.apps.walletnfcrel"
            val pm: PackageManager = context?.applicationContext!!.getPackageManager()
            val appstart = pm.getLaunchIntentForPackage(appPackageName)
            if (null != appstart) {
                context?.getApplicationContext()!!.startActivity(appstart)
            } else {
                Toast.makeText(
                    context?.getApplicationContext(), R.string.Install_GooglePay_on_your_device,
                    Toast.LENGTH_SHORT
                ).show()
            }


        }

        btnPhonePay.setOnClickListener {
            val appPackageName = "pk.com.telenor.phoenix"
            val pm: PackageManager = context?.applicationContext!!.getPackageManager()
            val appstart = pm.getLaunchIntentForPackage(appPackageName)
            if (null != appstart) {
                context?.getApplicationContext()!!.startActivity(appstart)
            } else {
                Toast.makeText(
                    context?.getApplicationContext(),
                    R.string.Install_Mobikwik_on_your_device,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}