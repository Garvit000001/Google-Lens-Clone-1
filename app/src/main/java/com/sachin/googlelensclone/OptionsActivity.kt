package com.sachin.googlelensclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sachin.googlelensclone.Barcode.BarcodeDetectActivity
import com.sachin.googlelensclone.ImageLabel.ImageLabelActivity
import com.sachin.googlelensclone.facedetect.FaceDetectActivity
import com.sachin.googlelensclone.text.TextRecognitionActivity
import kotlinx.android.synthetic.main.activity_options.*

class OptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)
/*btn barcode is actually for recognition*/
        btnLabeler.setOnClickListener {
            startActivity(Intent(this,ImageLabelActivity::class.java))
        }

        btnTextR.setOnClickListener {
            startActivity(Intent(this,TextRecognitionActivity::class.java))
        }
        btnFace.setOnClickListener {
            startActivity(Intent(this,FaceDetectActivity::class.java))
        }
        btnBarcode.setOnClickListener{
            startActivity(Intent(this,BarcodeDetectActivity::class.java))

        }

    }

}