package co.nata.facebookapp

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.ceil

class PostAdapter: RecyclerView.Adapter<PostView>() {

    private val posts = ArrayList<Post>()

    init{
        posts.add(Post("A",null, "Esto es una imagen", System.currentTimeMillis(), "Cali", User("a", "Isabel", "123", "Isabel Murillo")))
        posts.add(Post("B",null, "Esto es una nueva imagen", System.currentTimeMillis(), "Cali", User("b", "Mike", "123", "Mike Murillo")))
        posts.add(Post("C",null, "Esto es otra imagen", System.currentTimeMillis(), "Popayán", User("c", "Mikee", "123", "Mikee Murillo")))
  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostView {
        //Inflater : xml --> view
        var inflater = LayoutInflater.from(parent.context)
        var row = inflater.inflate(R.layout.post, parent,false)
        val postView = PostView(row)
        return postView
    }

    override fun onBindViewHolder(skeleton: PostView, position: Int) {
        val post = posts[position]
        //skeleton.image_user.imageView = post.userImage;
        skeleton.text_user_name.text = post.user.name
        skeleton.txt_date.text = timeReference(post.date)
        skeleton.txt_description.text = post.description
        if(post.location != null){
            skeleton.txt_location.text = post.location
        }
        if(post.image != null){
            val uriImage = Uri.parse(post.image)
            uriImage?.let{
                skeleton.image_post.setImageURI(uriImage)
            }

        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    private fun timeReference(time: Long): String {

        val difference = ((System.currentTimeMillis() - time)/1000).toDouble()

        if(difference<60){
            return "Just now"
        }
        else if(difference<3600) {//Between 1 minute and 1 hour
            val minutes = ceil(difference/60)
            return "$minutes minute(s) ago"
        }
        else if(difference<86399) {//Between 1 hour and 1 day
            val hours = ceil(difference/3600)
            return "$hours hours(s) ago"
        }
        else if(difference<2592000) {//Between 1 day and 1 month
            val days = ceil(difference/86400)
            return "$days days(s) ago"
        }else {
            return Calendar.getInstance().time.toString()
        }
    }

    fun addPost(post: Post){
        posts.add(post)
    }


}