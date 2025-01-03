package com.sachin.googlelensclone.ImageLabel

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

var labelDone ="unknown"
var labelAcc:Float=1.0f

class ImageLabelAnalyzer: ImageAnalysis.Analyzer {



    private val labeler = ImageLabeling.getClient(
        ImageLabelerOptions.Builder()
            .setConfidenceThreshold(0.7F)
            .build()
    )

    @SuppressLint("UnsafeExperimentalUsageError", "UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {

        Log.d("LABEL", "image analysed")

        val mediaImage = imageProxy.image
        mediaImage?.let {
            val image = InputImage.fromMediaImage(it, imageProxy.imageInfo.rotationDegrees)


            // Passing image to an ML Kit Vision API

            labeler.process(image)
                .addOnSuccessListener { labels ->
                    labels.forEach { label ->
                        Log.d(
                            "LABEL", """
                            Format = ${label.text}
                            Value = ${label.confidence}
                        """.trimIndent()
                        )
                        labelDone =label.text.toString()
                        labelAcc=label.confidence
                    }
                }
                    .addOnFailureListener { e ->
                        Log.e("LABEL", "Detection failed", e)
                    }
                    .addOnCompleteListener {
                        imageProxy.close()
                    }

        } ?: imageProxy.close()  // close if image not found either
    }
}
