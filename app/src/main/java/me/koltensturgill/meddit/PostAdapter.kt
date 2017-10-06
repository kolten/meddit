package me.koltensturgill.meddit

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

/**
 * Created by kolten on 10/6/17.
 */

class PostAdapter(private val posts: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.PostHolder>() {

    override fun getItemCount() = posts.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return PostHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PostAdapter.PostHolder, position: Int) {
        val redditPost = posts[position]
        holder.bindPost(redditPost)
    }

    class PostHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var post: Post? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            Log.d("RecyclerView", "CLICK!")
        }

        fun bindPost(post: Post){
            this.post = post
            Picasso.with(view.context).load(post.imageUrl).into(view.post_img)
            view.post_title.text = post.title
            view.post_ups.text = post.numOfUps.toString()
        }

    }

}