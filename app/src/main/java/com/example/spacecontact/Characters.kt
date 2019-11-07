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
    var currentSuit = 0
    var currentBeard = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

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

        var beardImg = findViewById<ImageView>(R.id.ivTopChar)
        var bodyImg = findViewById<ImageView>(R.id.ivSkinChar)
        var suitImg = findViewById<ImageView>(R.id.ivSuitChar)

        beardImg.setImageBitmap(beardList[0])
        bodyImg.setImageBitmap(bodyList[0])
        suitImg.setImageBitmap(suitList[0])

        updateCharacter()
    }



    //Method to change the next beard in the imageview
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

    //Method to change the next Skincolor in the imageview
    fun nextSkin(v:View){

        var bodyImg = findViewById<ImageView>(R.id.ivSkinChar)
        if (currentBody < bodyList.size-1){
            currentBody++
        }else{
            currentBody = 0
        }
        bodyImg.setImageBitmap(bodyList[currentBody])
        updateCharacter()

    }

    //Method to change the next Suit in the imageview
    fun nextSuit(v:View){

        var suitImg = findViewById<ImageView>(R.id.ivSuitChar)
        if (currentSuit < suitList.size-1){
            currentSuit++
        }else{
            currentSuit = 0
        }

        suitImg.setImageBitmap(suitList[currentSuit])
        updateCharacter()

    }






    //Update the imageview with the final custom character
    private fun updateCharacter() {
        finalCharacter = Bitmap.createBitmap(bodyList[0].width, bodyList[0].width, bodyList[0].config)
        var canvas = Canvas(finalCharacter)
        canvas.drawBitmap(bodyList[currentBody], Matrix(), null)
        canvas.drawBitmap(suitList[currentSuit], 0f, 0f ,null)
        canvas.drawBitmap(beardList[currentBeard], 0f, 0f, null)
        var img = findViewById<ImageView>(R.id.charFinished)
        img.setImageBitmap(finalCharacter)
    }

    // Changes the picture of a worker
    fun saveCharacter(w: Worker): Worker {
       w.picture = finalCharacter
        return w
    }
}
