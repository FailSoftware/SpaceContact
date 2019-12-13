package com.example.spacecontact

import android.Manifest.permission.CALL_PHONE
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat


class Contact : PrefMenu() {

    private val PERMISO_LLAMADA = 3
    private val i = Intent(Intent.ACTION_CALL, Uri.parse("tel:952312703"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        overridePendingTransition(
            R.anim.fade_in,
            R.anim.fade_out
        )
    }

    fun toWeb(view: View) {
        val uri =
            Uri.parse("https://github.com/FailSoftware/SpaceContact") // missing 'http://' will cause crashed
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    fun toSocial(view: View) {
        val uri = Uri.parse("https://twitter.com/fail_software") // missing 'http://' will cause crashed
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }


    fun alertPermission() {

        var title : String = this.getString(R.string.alertTitlePerm)
        var msg : String = this.getString(R.string.alertMsgPerm)
        val bdr = AlertDialog.Builder(this)
        bdr.setTitle(title)
        bdr.setMessage(msg)
        bdr.create()
        bdr.show()
    }

    fun toCall(view: View) {

        if (ActivityCompat.checkSelfPermission(this, CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, CALL_PHONE)) {
                alertPermission()
            }else{
                ActivityCompat.requestPermissions(this, arrayOf(CALL_PHONE), PERMISO_LLAMADA)
            }
        } else {
            startActivity(i)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISO_LLAMADA) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity(i)
            }
        }
    }
}




