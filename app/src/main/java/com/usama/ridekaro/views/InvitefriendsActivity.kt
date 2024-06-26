package com.usama.ridekaro.views

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.exaple.splitwise_clone.vinod.recyclerviews.ContactCommunicator
import com.exaple.splitwise_clone.vinod.recyclerviews.ContactTempAddAdapter
import com.exaple.splitwise_clone.vinod.recyclerviews.ContactTempModel
import com.froyo.usama.R
import kotlinx.android.synthetic.main.activity_invitefriends.*


class InvitefriendsActivity : AppCompatActivity(), ContactCommunicator {
    private val REQ_CODE = 1
    private lateinit var cursor: Cursor
    private var contactList = mutableListOf<ContactTempModel>()
    var PICK_CONTACT = 0
//    private var usersList = mutableListOf<UserEntity>()
    private lateinit var contactAdapter: ContactTempAddAdapter
    private lateinit var to: IntArray
//    private val preferenceHelper = PreferenceHelper(this)
//    private lateinit var friendTransactionViewModel: FriendTransactionViewModel
//    private lateinit var userViewModel: UserViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invitefriends)

        share_user.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
//                val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)

//                startActivityForResult(intent, PICK_CONTACT)
                val sendIntent = Intent(Intent.ACTION_VIEW)
//                sendIntent.data = Uri.parse("sms:")
                val x ="Please download Health and Rescue from plays tore and reach to your destiny"
                sendIntent.putExtra("sms_body", x);
                sendIntent.data = Uri.parse("smsto:"+923009410503);
                startActivity(sendIntent);
            }
        })

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS
            ), REQ_CODE
        )


//        etNameEmailPhone.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
//            if (hasFocus) {
//                lvContacts.visibility = View.VISIBLE
//                ibClose.visibility = View.VISIBLE
//
//            } else {
//                lvContacts.visibility = View.GONE
//                ibClose.visibility = View.GONE
//
//            }
//        }

        lvContacts.setOnItemClickListener { parent, view, position, id ->
            var cTM =
                ContactTempModel(
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                    cursor.getString(cursor.getColumnIndex("DISPLAY_NAME"))
                )
            var flag = true
            for (i in contactList) {
                if (i.name == cTM.name && i.number == cTM.number) {
                    Toast.makeText(this, "Already Added", Toast.LENGTH_SHORT).show()
                    flag = false
                    break;
                }
            }
            if (flag) {
                contactList.add(cTM)
                contactAdapter =
                    ContactTempAddAdapter(
                        contactList,
                        this
                    )
                rvContactAddTemp.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                rvContactAddTemp.adapter = contactAdapter
            }
        }

    }

    override fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(reqCode, resultCode, data)
        when (reqCode) {
            PICK_CONTACT -> if (resultCode == RESULT_OK) {
                val contactData: Uri? = data?.data
                val c = managedQuery(contactData, null, null, null, null)
                if (c.moveToFirst()) {
                    val name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    // TODO Fetch other Contact details as you want to use
                }
            }
        }
    }

    private fun fetchContacts() {

        val cursor: Cursor? = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        this.cursor = cursor!!
        startManagingCursor(cursor)

        val from = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone._ID
        )

        to = IntArray(2)
        to[0] = android.R.id.text1
        to[1] = android.R.id.text2

        val simpleCursorAdapter =
            SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from, to)

        lvContacts.adapter = simpleCursorAdapter
        lvContacts.choiceMode = ListView.CHOICE_MODE_MULTIPLE
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQ_CODE -> {
                if (grantResults.isNotEmpty()) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        fetchContacts()
                    } else {
                        Toast.makeText(
                            this,
                            "Contact Permission Denied",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }



    override fun onContactDelete(tempModel: ContactTempModel) {
        var x = 0
        for (i in contactList) {
            if (i.name == tempModel.name && i.number == tempModel.number) {
                contactList.removeAt(x)
                break;
            }
            x++
        }
        contactAdapter.notifyDataSetChanged()
    }

}