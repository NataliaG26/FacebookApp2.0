package co.nata.facebookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import co.nata.facebookapp.databinding.ActivityLoginBinding
import java.util.*
import kotlin.collections.ArrayList

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        users.add(User("a","beta@gmail.com", "aplicacionesmoviles", "beta"))
        users.add(User("b", "alfa@gmail.com", "aplicacionesmoviles", "Alfa"))
        users.add(User("c", "nat", "123", "Natalia Gonzalez"))

        binding.btnLogin.setOnClickListener{
            var username = binding.userET.text.toString()
            var password = binding.passwordET.text.toString()


            //Toast.makeText(this, "El usuario y contraseña son incorrectos", Toast.LENGTH_LONG).show()

            //val intent = Intent(this, MenuContainer::class.java)
            //startActivity(intent)


            var user = User(UUID.randomUUID().toString(), username , password, "")

            var temp = false
            for (i in users){
                if(user.username.equals(i.username) && user.password.equals(i.password)){
                    //print(temp)
                    temp = true
                    user = i
                }
            }

            if(temp){
                val intent = Intent(this, MenuContainer::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Usuario y contraseña incorrectos", Toast.LENGTH_LONG).show()
            }

        }
    }
}