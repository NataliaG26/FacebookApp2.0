package co.nata.facebookapp

import android.widget.ImageView

class Post {

    var id: String
    var image: String?
    var description: String
    var date: Long
    var location: String?
    var user: User

    constructor(
        id: String, image: String?,
        description: String, date: Long, location: String?, user: User){
        this.id = id
        this.image = image
        this.description = description
        this.date = date
        this.location = location
        this.user = user
    }

}