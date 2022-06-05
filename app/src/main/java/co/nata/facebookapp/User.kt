package co.nata.facebookapp

import java.io.Serializable

class User (var id: String, var username: String, var password: String, var name: String) : Serializable{
    var photo:String? = null


    @JvmName("setName1")
    fun setName(newName: String){
        name = newName
    }
}