package co.nata.facebookapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.nata.facebookapp.databinding.FragmentProfileBinding
import co.nata.facebookapp.databinding.FragmentPublishBinding

class ProfileFragment(user:User) : Fragment() {

    private  var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    var currentUser = user


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding. inflate(inflater, container, false)
        binding.textVName.text = currentUser.name
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }

    companion object {

        @JvmStatic
        fun newInstance(user:User) = ProfileFragment(user)
    }
}