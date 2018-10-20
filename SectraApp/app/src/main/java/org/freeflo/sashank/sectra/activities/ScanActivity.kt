package org.freeflo.sashank.sectra.activities

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wonderkiln.camerakit.*
import kotlinx.android.synthetic.main.activity_scan.*
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import com.androidnetworking.error.ANError
import org.json.JSONObject
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import org.freeflo.sashank.sectra.R
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class ScanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        title = "Scan Your Baggage"
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
        AndroidNetworking.upload("http://172.31.1.5:8080")
            .addMultipartFile("image", file)
            .addMultipartParameter("key", "value")
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
                    toast("Error Occured")
                    dialog.dismiss()
                }
            })


    }

    private fun convertBitmapToFile(bitmap: Bitmap) : File {
        val f = File(cacheDir,"image.png")
        f.createNewFile()

        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 , bos)
        val bitmapdata = bos.toByteArray()

        val fos = FileOutputStream(f)
        fos.write(bitmapdata)
        fos.flush()
        fos.close()

        return f
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this)
        }
        return super.onOptionsItemSelected(item)
    }*/
}
