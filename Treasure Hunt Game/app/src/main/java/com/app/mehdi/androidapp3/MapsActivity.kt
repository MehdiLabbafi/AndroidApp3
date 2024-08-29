package com.app.mehdi.androidapp3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MapsActivity : AppCompatActivity() {

    private val locations = listOf(
        "City Hall",
        "Local Bakery",
        "Coffee Shop",
        "Museum",
        "Library",
        "Bookstore",
        "Park",
        "Gym",
        "Shopping Mall",
        "Cinema",
        "Restaurant",
        "Hotel",
        "Music Store",
        "Art Gallery",
        "Theater",
        "Supermarket",
        "Florist",
        "Pharmacy",
        "Bank",
        "Train Station"
    )

    private val locationImages = mapOf(
        "City Hall" to "https://s3.distam.site/colege/City%20Hall_11zon.png",
        "Local Bakery" to "https://s3.distam.site/colege/Local%20Bakery.png",
        "Coffee Shop" to "https://s3.distam.site/colege/Coffee%20Shop.png",
        "Museum" to "https://s3.distam.site/colege/Museum.png",
        "Library" to "https://s3.distam.site/colege/1.png",
        "Bookstore" to "https://s3.distam.site/colege/Bookstore.png",
        "Park" to "https://s3.distam.site/colege/Park.png",
        "Gym" to "https://s3.distam.site/colege/Gym.png",
        "Shopping Mall" to "https://s3.distam.site/colege/Shopping%20Mall.png",
        "Cinema" to "https://s3.distam.site/colege/Cinema.png",
        "Restaurant" to "https://s3.distam.site/colege/Restaurant.png",
        "Hotel" to "https://s3.distam.site/colege/Hotel.png",
        "Music Store" to "https://s3.distam.site/colege/Music%20Store.png",
        "Art Gallery" to "https://s3.distam.site/colege/Art%20Gallery.png",
        "Theater" to "https://s3.distam.site/colege/Theater.png",
        "Supermarket" to "https://s3.distam.site/colege/Supermarket.png",
        "Florist" to "https://s3.distam.site/colege/Florist.png",
        "Pharmacy" to "https://s3.distam.site/colege/Pharmacy.png",
        "Bank" to "https://s3.distam.site/colege/Bank.png",
        "Train Station" to "https://s3.distam.site/colege/Train%20Station.png"
    )

    private var currentLocationIndex = 0
    private var isShowingImage = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Get user information from Intent and display it at the top of the page
        val userName = intent.getStringExtra("USER_NAME")
        val userPhone = intent.getStringExtra("USER_PHONE")
        findViewById<TextView>(R.id.user_info_text).text = "User: $userName - Mobail: $userPhone"

        // Show primary location
        showLocation()

        // button to go to the next location
        findViewById<Button>(R.id.next_button).setOnClickListener {
            if (isShowingImage) {
                // If the image is showing, go to the next location
                goToNextLocation()
            } else {
                // If the place name is showing, show the image
                showLocationImage()
            }
        }

        // button to display the name of the next location
        findViewById<Button>(R.id.show_name_button).setOnClickListener {
            showLocation()
        }

        // Button to return to the first page
        findViewById<Button>(R.id.back_button).setOnClickListener {
            val intent = Intent(this, UserInfoActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showLocation() {
        val locationTextView = findViewById<TextView>(R.id.location_text)
        val locationImageView = findViewById<ImageView>(R.id.location_image)
        val nextButton = findViewById<Button>(R.id.next_button)
        val showNameButton = findViewById<Button>(R.id.show_name_button)
        val backButton = findViewById<Button>(R.id.back_button)

        if (currentLocationIndex < locations.size) {
            // Display the name of the current location
            val currentLocation = locations[currentLocationIndex]
            locationTextView.text = "Next location: $currentLocation"
            locationImageView.visibility = View.INVISIBLE
            nextButton.text = "Show the location on the map"
            showNameButton.visibility = View.INVISIBLE
            backButton.visibility = View.GONE
            isShowingImage = false
        } else {
            // When all locations have been visited
            locationTextView.text = "Congratulations! You have visited all locations. You are now eligible for the free vacation draw!"
            nextButton.visibility = View.GONE
            showNameButton.visibility = View.GONE
            backButton.visibility = View.VISIBLE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showLocationImage() {
        val locationTextView = findViewById<TextView>(R.id.location_text)
        val locationImageView = findViewById<ImageView>(R.id.location_image)
        val nextButton = findViewById<Button>(R.id.next_button)
        val showNameButton = findViewById<Button>(R.id.show_name_button)

        val currentLocation = locations[currentLocationIndex]
        val imageUrl = locationImages[currentLocation]

        if (imageUrl != null) {
            Glide.with(this)
                .load(imageUrl)
                .into(locationImageView)
        } else {
            locationImageView.setImageResource(R.drawable.default_image) // Default image if no URL exists
        }

        locationTextView.text = "Location: $currentLocation"
        locationImageView.visibility = View.VISIBLE
        nextButton.text = "Next Location"
        showNameButton.visibility = View.VISIBLE
        isShowingImage = true
    }

    private fun goToNextLocation() {
        currentLocationIndex++
        showLocation()
    }
}
