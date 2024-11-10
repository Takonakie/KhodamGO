package com.example.khodamgo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode

class KhodamActivity : AppCompatActivity() {

    private val CAMERA_REQUEST_CODE = 100
    lateinit var sceneView: ArSceneView
    lateinit var placeButton: ExtendedFloatingActionButton
    private lateinit var modelNode: ArModelNode
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_khodam)

        score = intent.getIntExtra("score", 0) // Ambil skor dari Intent

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE)
        }

        sceneView = findViewById(R.id.sceneView)
        placeButton = findViewById(R.id.place)

        placeButton.setOnClickListener {
            placeModel()
        }

        val modelPath = getModelPathByScore(score)
        modelNode = ArModelNode().apply {
            loadModelGlbAsync(
                glbFileLocation = modelPath
            ) {
                sceneView.planeRenderer.isVisible = true
            }
            onAnchorChanged = {
                placeButton.isGone
            }
        }
        sceneView.addChild(modelNode)
    }

    private fun getModelPathByScore(score: Int): String {
        return when {
            score == 400 -> "models/"
            score >= 340 -> "models/"
            score >= 280 -> "models/"
            score >= 220 -> "models/roger_sumatera.glb"
            score >= 160 -> "models/sigit_rendang.glb"
            else -> "models/skibidi_rizz.glb"
        }
    }

    private fun placeModel() {
        modelNode.anchor()
        sceneView.planeRenderer.isVisible = false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java) // Ganti dengan activity tampilan awal
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}

