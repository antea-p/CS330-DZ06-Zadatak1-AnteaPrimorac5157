package rs.ac.metropolitan.cs330_dz06_anteaprimorac_5157

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.content.res.Configuration
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupButtons()
        displayOrientationToast()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        displayOrientationToast()
    }
    private fun setupButtons() {
        val topButton = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.name)
        topButton.setOnClickListener {
            textView.setText(R.string.university_name)
            Toast.makeText(this, R.string.university_name, Toast.LENGTH_SHORT).show()
        }

        val bottomButton = findViewById<Button>(R.id.button2)
        bottomButton.setOnClickListener {
            val locationUri = Uri.parse(getString(R.string.university_location_url))
            val mapIntent = Intent(Intent.ACTION_VIEW, locationUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            } else {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q=" + getString(R.string.university_location_url)))
                startActivity(browserIntent)
            }
        }
    }

    private fun displayOrientationToast() {
        val orientationMessage = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> getString(R.string.orientation_portrait)
            Configuration.ORIENTATION_LANDSCAPE -> getString(R.string.orientation_landscape)
            else -> ""
        }
        if (orientationMessage.isNotEmpty()) {
            Toast.makeText(this, orientationMessage, Toast.LENGTH_SHORT).show()
        }
    }
}