package com.usama.ridekaro.views.navDrawerFragments

import androidx.fragment.app.Fragment
import com.froyo.usama.R
import com.usama.ridekaro.viewModel.AfterClickingRideNow


class MyRidesFragment : Fragment(R.layout.fragment_my_rides) {

    private lateinit var afterClickingRideNow: AfterClickingRideNow

    override fun onResume() {
        super.onResume()
//        afterClickingRideNow = ViewModelProviders.of(this).get(AfterClickingRideNow::class.java)
//
//        afterClickingRideNow.getMapRider().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
//            val riderCount = it
//            CoroutineScope(Dispatchers.IO).launch {
//            }
//        })
    }

//    private fun ShowDetials() {
//        cardView2.visibility = View.VISIBLE
//        btnCallRider.setOnClickListener {
//            val intent = Intent(Intent.ACTION_DIAL)
//            intent.data = Uri.parse("tel:9584730428")
//            startActivity(intent)
//        }

//        btnCancel.setOnClickListener {
//            val alertDialog = AlertDialog.Builder(context)
//            alertDialog
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .setTitle("Cancelling Ride")
//                .setMessage("Are you sure you want to cancel the ride?")
//                .setPositiveButton(
//                    "Yes",
//                    DialogInterface.OnClickListener { dialog, which -> changeText() })
//                .setNegativeButton("No", null).show()
//        }
}

//    private fun changeText() {
//        tvRideStatus.text = "Cancelled"
//        tvRideStatus.setTextColor(ContextCompat.getColor(requireContext(), R.color.Red))
//
//        tvTotalRupeee2.text = "0"
//        btnCallRider.visibility = View.INVISIBLE
//        btnCancel.visibility = View.INVISIBLE
//    }