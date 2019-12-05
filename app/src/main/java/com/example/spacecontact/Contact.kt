package com.example.spacecontact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Contact : Login() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
    }
}
