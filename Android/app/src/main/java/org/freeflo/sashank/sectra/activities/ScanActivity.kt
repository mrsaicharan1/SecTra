package org.freeflo.sashank.sectra.activities

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wonderkiln.camerakit.*
import kotlinx.android.synthetic.main.activity_scan.*
import android.graphics.Bitmap
import android.support.v7.app.ActionBar
import android.view.View
import android.view.WindowManager
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import com.androidnetworking.error.ANError
import org.json.JSONObject
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import kotlinx.android.synthetic.main.app_bar.*
import org.freeflo.sashank.sectra.R
import org.jetbrains.anko.image
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import android.R.attr.bitmap
import android.R.attr.name
import android.util.Log
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.freeflo.sashank.sectra.utils.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import java.io.OutputStream


class ScanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        appBarTitle.text = "Scan Your Baggage"
        menuIV.visibility = View.GONE

        cameraView.addCameraKitListener(object : CameraKitEventListener {
            override fun onVideo(p0: CameraKitVideo?) {}

            override fun onEvent(p0: CameraKitEvent?) {}

            override fun onImage(p0: CameraKitImage?) {
                var bitmap = p0!!.bitmap
                bitmap = Bitmap.createScaledBitmap(bitmap, 128, 128, false)
                sendImageToServer(bitmap)
            }

            override fun onError(p0: CameraKitError?) {}

        })

        fab.setOnClickListener {
            cameraView.captureImage()
        }
    }

    override fun onResume() {
        super.onResume()
        cameraView.start()
    }

    override fun onPause() {
        super.onPause()
        cameraView.stop()
    }

    private fun sendImageToServer(bitmap: Bitmap) {
        val file = convertBitmapToFile(bitmap)

        val dialog = ProgressDialog.show(this, "Uploading", "Please Wait...")
        dialog.max = 100
        dialog.show()
        AndroidNetworking.upload("https://sectra.herokuapp.com/upload/")
            .addMultipartFile("file", file)
            .setTag("uploadTest")
            .setPriority(Priority.HIGH)
            .build()
            .setUploadProgressListener { bytesUploaded, totalBytes ->
                dialog.progress = (bytesUploaded / totalBytes).toInt()
            }
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    toast("Success!")
                    dialog.dismiss()
                    startActivity<ResultActivity>()
                }

                override fun onError(error: ANError) {
                    toast("Error Occured" + error.errorCode)
                    dialog.dismiss()
                }
            })
    }

    private fun convertBitmapToFile(bitmap: Bitmap) : File {
        val filesDir = filesDir
        val imageFile = File(filesDir, name.toString() + ".jpg")

        val os: OutputStream
        try {
            os = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
            os.flush()
            os.close()
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "Error writing bitmap", e)
        }

        return imageFile

    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this)
        }
        return super.onOptionsItemSelected(item)
    }*/
}
