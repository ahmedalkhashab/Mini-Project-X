package io.android.projectx.androidextensions.maps

import android.content.Context
import android.content.Intent
import android.net.Uri


fun Context.openGoogleMaps(lat: Double, long: Double) {
    // Create a Uri from an intent string. Use the result to create an Intent.
    //val uri: Uri = Uri.parse("geo:$lat,$long")
    val uri: Uri = Uri.parse("google.navigation:q=$lat,$long")
    // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
    val mapIntent = Intent(Intent.ACTION_VIEW, uri)
    // Make the Intent explicit by setting the Google Maps package
    mapIntent.setPackage("com.google.android.apps.maps")
    // Attempt to start an activity that can handle the Intent
    if (mapIntent.resolveActivity(packageManager) != null) startActivity(mapIntent)
}


fun Context.openMaps(mapsUri: Uri) {
    val mapsIntent = Intent(Intent.ACTION_VIEW, mapsUri)
    mapsIntent.setPackage("com.google.android.apps.maps")
    if (mapsIntent.resolveActivity(packageManager) != null) startActivity(mapsIntent)
}