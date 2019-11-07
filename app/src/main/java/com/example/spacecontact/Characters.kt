package com.example.spacecontact

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.spacecontact.entity.Worker

class Characters : AppCompatActivity() {
    lateinit var finalCharacter:Bitmap
    var bodyList = ArrayList<Bitmap>()
    var suitList = ArrayList<Bitmap>()
    var beardList = ArrayList<Bitmap>()

    var currentBody = 0
    var curretSuit = 0
    var currentBeard = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        // val icon = BitmapFactory.decodeResource(this.getResources(), android.R.drawable.)

        //region Skin tones
        bodyList.add(BitmapFactory.decodeResource(this.resources, R.drawable.bodyzero))
        bodyList.add(BitmapFactory.decodeResource(this.resources, R.drawable.bodyone))
        bodyList.add(BitmapFactory.decodeResource(this.resources, R.drawable.bodytwo))
        //endregion

        //region suits
        suitList.add(BitmapFactory.decodeResource(this.resources, R.drawable.suitzero))
        suitList.add(BitmapFactory.decodeResource(this.resources, R.drawable.suitone))
        //endregion

        //region beards
        beardList.add(BitmapFactory.decodeResource(this.resources, R.drawable.beardzero))
        beardList.add(BitmapFactory.decodeResource(this.resources, R.drawable.beardone))
        beardList.add(BitmapFactory.decodeResource(this.resources, R.drawable.beardtwo))
        beardList.add(BitmapFactory.decodeResource(this.resources, R.drawable.beardthree))
        //endregion


        //TODO añadir y cambiar las imageviews
        var bodyImg = findViewById<ImageView>(R.id.ivTopChar)
        var suitImg = findViewById<ImageView>(R.id.ivTopChar)
        var beardImg = findViewById<ImageView>(R.id.ivTopChar)

        updateCharacter()

    }


    fun nextBeard(v:View){
        var beardImg = findViewById<ImageView>(R.id.ivTopChar)
        if (currentBeard < beardList.size-1){
            currentBeard++
        } else {
            currentBeard = 0
        }

        beardImg.setImageBitmap(beardList[currentBeard])
        updateCharacter()

    }

    private fun updateCharacter() {
        finalCharacter = Bitmap.createBitmap(bodyList[0].width, bodyList[0].width, bodyList[0].config)
        var canvas = Canvas(finalCharacter)
        canvas.drawBitmap(bodyList[currentBody], Matrix(), null)
        canvas.drawBitmap(suitList[curretSuit], 0f, 0f ,null)
        canvas.drawBitmap(beardList[currentBeard], 0f, 0f, null)
        var img = findViewById<ImageView>(R.id.testImg)
        img.setImageBitmap(finalCharacter)
    }

    // Changes the picture of a worker
    fun saveCharacter(w: Worker): Worker {
       w.picture = finalCharacter
        return w
    }
}
