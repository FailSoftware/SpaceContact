package com.example.spacecontact

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.spacecontact.entity.User
import com.example.spacecontact.entity.Worker
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class Register : Login() {
     lateinit var bm:Bitmap
     lateinit var pilot:Worker
     lateinit var usr:User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        /*bm = BitmapFactory.decodeResource(resources, R.drawable.bodyzero)
        pilot = Worker("Test", 1, 1, 1, 100, bm, 2, 100, 100, Worker.Job.PILOT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            usr = User(0, false, "Falso", "Falso", "Un usuario", LocalDate.parse("31-12-2018", formatter), pilot)
        }else{
            Toast.makeText(this,"Your device is not compatible with this version of the program", Toast.LENGTH_SHORT).show()
        }*/




    }

    fun alertMatch() {

        var title : String = this.getString(R.string.notMatchTitle)
        var msg : String = this.getString(R.string.passNotMatch)
        val bdr = AlertDialog.Builder(this)
        bdr.setTitle(title)
        bdr.setMessage(msg)
        bdr.create()
        bdr.show()
    }

    fun alertVoidUser() {

        var title : String = this.getString(R.string.alertNoEmailTitle)
        var msg : String = this.getString(R.string.alertEmailMsg)
        val bdr = AlertDialog.Builder(this)
        bdr.setTitle(title)
        bdr.setMessage(msg)
        bdr.create()
        bdr.show()
    }



    fun newUser(view: View){
        var intent: Intent = Intent(this, Characters::class.java)


        if (etPassword.text.toString().equals(etPasswordConf.text.toString())){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString())
                .addOnCompleteListener{
                    if(!it.isSuccessful) return@addOnCompleteListener
                    startActivity(intent)
                    Toast.makeText(this, "Usuario creado", Toast.LENGTH_SHORT).show()
                    Log.d("Main", "Successfully created user with uid: ${it.result?.user?.uid}")
                }
        }else{
            alertMatch()
        }

    }

    fun toLogin(view: View) {
        var intent: Intent = Intent(this, Login::class.java)
        startActivity(intent)

    }


}
