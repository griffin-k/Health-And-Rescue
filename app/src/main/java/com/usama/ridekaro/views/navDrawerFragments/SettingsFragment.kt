package com.usama.ridekaro.views.navDrawerFragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.froyo.usama.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.usama.ridekaro.helper.PreferenceHelper
import com.usama.ridekaro.profile
import com.usama.ridekaro.views.LanguageFragment
import com.usama.ridekaro.views.OTPValidation
import com.usama.ridekaro.views.fav_place
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment(R.layout.fragment_settings) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        activity?.actionBar?.hide()
        super.onViewCreated(view, savedInstanceState)

        PreferenceHelper.getSharedPreferences(requireContext())
        val auth = FirebaseAuth.getInstance()





        clAppLanguage.setOnClickListener {

            val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
            ft.add(
                R.id.fragmentContainerView,
                LanguageFragment(),
                "LanguageFragment"
            ).addToBackStack("LanguageFragment")
            ft.commit()
        }



        val value = PreferenceHelper.getStringFromPreference("languagePreferenceString")
        when (value) {

            "hi" -> {
                tvLanguageChange.text = "Urdu"
            }
            "en" -> {
                tvLanguageChange.text = "English"
            }
            "kn" -> {
                tvLanguageChange.text = "Punjabi"
            }
            "ta" -> {
                tvLanguageChange.text = "Sindhi"
            }
            "te" -> {
                tvLanguageChange.text = "`Pashto"
            }

        }



//        profile k buttons
        ibProfile.setOnClickListener {
            val intent = Intent(requireContext(), profile::class.java)
            startActivity(intent)
        }
        tvProfile.setOnClickListener {
            val intent = Intent(requireContext(), profile::class.java)
            startActivity(intent)
        }
        txtProfile.setOnClickListener {
            val intent = Intent(requireContext(), profile::class.java)
            startActivity(intent)
        }


//

        ibPreferences.setOnClickListener {
            val intent = Intent(requireContext(), fav_place::class.java)
            startActivity(intent)
        }
        tvPreferences.setOnClickListener {
            val intent = Intent(requireContext(), fav_place::class.java)
            startActivity(intent)
        }
        txtPreferences.setOnClickListener {
            val intent = Intent(requireContext(), fav_place::class.java)
            startActivity(intent)
        }


        //logout
        ibLogout.setOnClickListener {

            auth.signOut()


            val googleSignInClient = GoogleSignIn.getClient(requireActivity(), GoogleSignInOptions.DEFAULT_SIGN_IN)
            googleSignInClient.signOut().addOnCompleteListener {

                val intent = Intent(requireContext(), OTPValidation::class.java)
                startActivity(intent)
            }
        }



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_support_button, menu)
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.ibProfile -> {
                Toast.makeText(requireContext(), "clicked", Toast.LENGTH_LONG).show()
                true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }




}








