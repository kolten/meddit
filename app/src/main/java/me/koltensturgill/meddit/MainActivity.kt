package me.koltensturgill.meddit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.ListAdapter
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
// Kotlin manages our view elements for us now!!
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

// Here we have the entry point for our app
// extends --> : AppCompatActivity()
class MainActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var redditPosts: ArrayList<Post> = ArrayList()
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting our base path, this is great for versioning apis in apps
        FuelManager.instance.basePath = "https://www.reddit.com"

        // Creating a linear layout manager (there are other types of layout managers)
        linearLayoutManager = LinearLayoutManager(this)

        // Use our recycler view with the linear layout manager
        // If you're used to doing Android, notice we're not using findViewById!!
        recyclerView.layoutManager = linearLayoutManager

        loadRedditPosts()

        // Give our adapter an array list
        adapter = PostAdapter(redditPosts)
        recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        // If our arraylist doesn't contain anything, populate it
        if (redditPosts.size == 0) {
            loadRedditPosts()
        }
    }

    fun loadRedditPosts () {
        "/r/popular.json".httpGet().responseJson { request, response, result ->
            result.fold(success = { json ->

                val data = json.obj().getJSONObject("data")

                val children = data.getJSONArray("children")

                for (i in 0..(children.length() - 1)) {

                    val item = children.getJSONObject(i)
                    val childData = item.get("data") as JSONObject
                    Log.d("data", childData.toString())
                    redditPosts.add(Post(childData.get("thumbnail") as String , childData.get("title") as String , childData.getInt("ups")))
                    adapter.notifyDataSetChanged()

                }
                //Log.d("children", children.toString())
            }, failure = { err ->
                Log.e("request err", err.toString())
            })
        }
    }
}
