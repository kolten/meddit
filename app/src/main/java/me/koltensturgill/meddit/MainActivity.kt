package me.koltensturgill.meddit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
// Kotlin manages our view elements for us now!!
import kotlinx.android.synthetic.main.activity_main.*

// Here we have the entry point for our app
// extends --> : AppCompatActivity()
class MainActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Creating a linear layout manager (there are other types of layout managers)
        linearLayoutManager = LinearLayoutManager(this)

        // Use our recycler view with the linear layout manager
        // If you're used to doing Android, notice we're not using findViewById!!
        recyclerView.layoutManager = linearLayoutManager
    }
}
