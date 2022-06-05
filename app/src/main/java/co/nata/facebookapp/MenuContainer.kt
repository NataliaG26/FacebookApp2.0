package co.nata.facebookapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import co.nata.facebookapp.databinding.ActivityMenuContainerBinding

class MenuContainer : AppCompatActivity() {

    private lateinit var binding: ActivityMenuContainerBinding

    private lateinit var profileFragment: ProfileFragment
    private lateinit var homeFragment: HomeFragment
    private lateinit var publishFragment: PublishFragment

    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuContainerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        user = intent.getSerializableExtra("user") as User

        profileFragment = ProfileFragment.newInstance(user)
        homeFragment = HomeFragment.newInstance()
        publishFragment = PublishFragment.newInstance(user)

        //Suscripcion
        publishFragment.listener = homeFragment

        binding.navBar.setOnItemSelectedListener { menu->
            if(menu.itemId == R.id.homeItem){
                showFragment(homeFragment)
            }else if (menu.itemId == R.id.profileItem) {
                showFragment(profileFragment)
            }else if (menu.itemId == R.id.pusblishItem){
                showFragment(publishFragment)
            }
            true
        }
    }

    private fun showFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }
}