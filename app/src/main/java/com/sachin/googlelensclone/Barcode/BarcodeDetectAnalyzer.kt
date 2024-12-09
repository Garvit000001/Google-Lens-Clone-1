package com.sachin.googlelensclone.Barcode

import android.annotation.SuppressLint
import android.renderscript.Sampler
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.sachin.googlelensclone.ImageLabel.labelAcc
import com.sachin.googlelensclone.ImageLabel.labelDone
var barcodetext="unknown"
class BarcodeDetectAnalyzer : ImageAnalysis.Analyzer {

    private val scanner = BarcodeScanning.getClient()

    @SuppressLint("UnsafeExperimentalUsageError", "UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
111
        Log.d("BARCODE", "barcode analysed")

        val mediaImage = imageProxy.image
        mediaImage?.let {
            val image = InputImage.fromMediaImage(it, imageProxy.imageInfo.rotationDegrees)


            // Passing image to an ML Kit Vision API

            scanner.process(image)
                .addOnSuccessListener { codes ->
                    codes.forEach { barcode ->
                        Log.d(
                            "BARCODE", """
                            Format = ${barcode.format}
                            Value = ${barcode.rawValue}
                        """.trimIndent()
                        )
                        barcodetext= barcode.rawValue.toString()
                    }

                }

                .addOnFailureListener { e ->
                    Log.e("Barcode", "Detection failed", e)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }

        } ?: imageProxy.close()  // close if image not found either
    }
}