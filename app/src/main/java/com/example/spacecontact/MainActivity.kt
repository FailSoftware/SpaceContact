package com.example.spacecontact

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import com.example.spacecontact.entity.Ship
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onDestroy() {
        super.onDestroy()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logoSplash.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_in))
        Handler().postDelayed({
            logoSplash.startAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_out))
            Handler().postDelayed({
                logoSplash.visibility = View.GONE
                startActivity(Intent(this, Login::class.java))
                finish()
            },500)
        },2500)



        //TODO Este apartado solo está por motivos de prueba, borrar al tener el menu principal acabado
        //loadCharacter()
        //loadNewGame()
        //loadPlanet()
        //rewardTest()
    }

    //TODO Este apartado solo está por motivos de prueba, borrar al tener el menu principal acabado
    fun loadLogin(){
     var intent: Intent = Intent(this, Login::class.java)
        startActivity(intent)

    }
    fun loadPlanet(){
        var intent: Intent = Intent(this, Planet::class.java)
        startActivity(intent)
    }

    fun rewardTest(){
        var ship1 : Ship = Ship(this)
        var ship2 : Ship = Ship(80)

        ship1.battleRewards(this, ship2)
    }
}
