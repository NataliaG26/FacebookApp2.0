package co.nata.facebookapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostView(itemView:View) : RecyclerView.ViewHolder(itemView) {

    //Idenytificar los UI components
    var image_user: ImageView = itemView.findViewById(R.id.image_user)
    var text_user_name: TextView = itemView.findViewById(R.id.text_user_name)
    var txt_date: TextView = itemView.findViewById(R.id.txt_date)
    var txt_location: TextView = itemView.findViewById(R.id.txt_location)
    var txt_description: TextView = itemView.findViewById(R.id.txt_description)
    var image_post: ImageView = itemView.findViewById(R.id.image_post)




}