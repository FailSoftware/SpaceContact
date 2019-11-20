package com.example.spacecontact

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.spacecontact.entity.User
import com.example.spacecontact.entity.Worker
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class Register : AppCompatActivity() {
     lateinit var bm:Bitmap
     lateinit var pilot:Worker
     lateinit var usr:User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        /*
        bm = BitmapFactory.decodeResource(resources, R.drawable.bodyzero)
        pilot = Worker("Test", 1, 1, 1, 100, bm, 2, 100, 100, Worker.Job.PILOT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            usr = User(0, false, "Falso", "Falso", "Un usuario", LocalDate.parse("31-12-2018", formatter), pilot)
        }else{
            Toast.makeText(this,"Your device is not compatible with this version of the program", Toast.LENGTH_SHORT).show()
        }*/
    }

    fun toCharacters(view: View){
        var intent = Intent(this, Characters::class.java)
        intent.putExtra("usr", usr)
        startActivity(intent)
    }

    fun toLogin(view: View) {
        var intent: Intent = Intent(this, Login::class.java)
        startActivity(intent)

    }


}
