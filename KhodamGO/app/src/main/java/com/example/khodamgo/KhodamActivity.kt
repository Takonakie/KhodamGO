package com.example.khodamgo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isGone
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode

class KhodamActivity : AppCompatActivity() {
    private val CAMERA_REQUEST_CODE = 100
    lateinit var sceneView: ArSceneView
    lateinit var placeButton: ExtendedFloatingActionButton
    private lateinit var modelNode: ArModelNode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_khodam)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE)
        }
        sceneView = findViewById(R.id.sceneView)
        val khodam = intent.getStringExtra("KHODAM_RESULT")
        val modelPath = getModelPathByScore(khodam)
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

    private fun getModelPathByScore(khodam:String?): String {
        return when (khodam) {
            "Khodam IL"-> "models/ksatria.glb"
            "Khodam EL"-> "models/ambatron.glb"
            "Khodam EN"-> "models/mothertron.glb"
            "Khodam EL2"-> "models/iblis_petarung.glb"
            "Khodam IN"-> "models/dewa_19.glb"
            "Khodam IN2"-> "models/dewa_mesir.glb"
            "Khodam EN2"-> "models/legenda_ayam.glb"
            "Khodam IL2"-> "models/dewi_api.glb"
            else -> "models/kepala_tiga.glb"
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java) // Ganti dengan activity tampilan awal
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

}

