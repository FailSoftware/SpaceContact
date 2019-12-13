package com.example.spacecontact

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.spacecontact.entity.Worker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*


class Register : PrefMenu() {
     lateinit var bm:Bitmap
     lateinit var pilot:Worker
     lateinit var usr: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        overridePendingTransition(
            R.anim.fade_in,
            R.anim.fade_out
        )

    }


    fun newUser(view: View){
        var intent: Intent = Intent(this, Characters::class.java)
        var email : String = etEmail.text.toString()
        var password : String = etPassword.text.toString()

        if (!email.isEmpty() && !password.isEmpty()){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    if(!it.isSuccessful) return@addOnCompleteListener
                    startActivity(intent)

                    Toast.makeText(this,
                        R.string.userCreated, Toast.LENGTH_SHORT).show()
                    Log.d("Main", "Successfully created user with uid: ${it.result?.user?.uid}")
                    val userSaved : String = etUser.text.toString()

                    val uid = FirebaseAuth.getInstance().uid?:""
                    val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
                    val user = User(
                        uid,
                        userSaved
                    )


                    ref.setValue(user)
                }.addOnFailureListener{
                    Log.d("Main", "Failed to create user: ${it.message}")
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }

                }else if(email.isEmpty() && !password.isEmpty()){
                Toast.makeText(this,
                    R.string.noEmailWritted, Toast.LENGTH_SHORT).show()

                }else if(!email.isEmpty() && password.isEmpty()){
                Toast.makeText(this,
                    R.string.noPassWritted, Toast.LENGTH_SHORT).show()
                }else{
                Toast.makeText(this,
                    R.string.noBothFields, Toast.LENGTH_SHORT).show()
        }


    }

    fun toLogin(view: View) {
        var intent: Intent = Intent(this, Login::class.java)
        startActivity(intent)

    }


    private fun saveUser(){

    }

    class User(val uid: String, val username: String)

}
