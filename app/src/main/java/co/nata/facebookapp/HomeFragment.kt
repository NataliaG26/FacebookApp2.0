package co.nata.facebookapp

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.nata.facebookapp.databinding.FragmentHomeBinding
import java.net.UnknownServiceException


class HomeFragment() : Fragment(), PublishFragment.OnNewPostListener{

    private  var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter = PostAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding. inflate(inflater, container, false)
        val view = binding.root

        //recrear estado
        var postContainer = binding.postContainer
        postContainer.setHasFixedSize(true)
        postContainer.layoutManager = LinearLayoutManager(activity)
        postContainer.adapter = adapter


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onNewPost(post: Post) {
        adapter.addPost(post)
    }

}