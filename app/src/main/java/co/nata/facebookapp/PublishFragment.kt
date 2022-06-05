package co.nata.facebookapp

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import co.nata.facebookapp.databinding.FragmentPublishBinding
import java.io.File
import java.util.*

class PublishFragment(user:User) : Fragment() {

    private  var _binding: FragmentPublishBinding? = null
    private val binding get() = _binding!!
    private lateinit var postAdapter: PostAdapter

    private var file:File? = null
    private var photo: String? = null

    var currentUser = user

    var listener: OnNewPostListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPublishBinding. inflate(inflater, container, false)
        val view = binding.root
        postAdapter = PostAdapter()

        binding.txtUserName.text = currentUser.name

        binding.btnPost.setOnClickListener{
            createPost()
        }

        val cameralauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onCameraResult)
        val gallerylauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onGalleryResult)

        binding.btnCamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val fileName = "photo_${Calendar.getInstance().time}.png"
            file = File("${activity?.getExternalFilesDir(null)}/${fileName}")
            val uri = activity?.let { it1 -> FileProvider.getUriForFile(it1, it1.packageName, file!!) }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            Log.e(">>>", file?.path.toString())

            cameralauncher.launch(intent)
        }

        binding.btnGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            gallerylauncher.launch(intent)
        }

        requestPermissions(arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.CALL_PHONE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ), 1 )

        return view
    }

    private fun onCameraResult(result: ActivityResult){
        //val bitmap  = result.data?.extras?.get("data") as Bitmap
        //binding.image.setImageBitmap(bitmap)
        if(result.resultCode == AppCompatActivity.RESULT_OK){
            val bitmap = BitmapFactory.decodeFile(file?.path)
            val thumbnail = Bitmap.createScaledBitmap(bitmap, bitmap.width/4, bitmap.height/4, true)
            binding.image.setImageBitmap(thumbnail)
            photo = file?.path.toString()
        }else if(result.resultCode == AppCompatActivity.RESULT_CANCELED){
            Toast.makeText(activity, "No tomo foto", Toast.LENGTH_SHORT).show()

        }
    }

    private fun onGalleryResult(result: ActivityResult){
        if(result.resultCode == AppCompatActivity.RESULT_OK){
            val uriImage = result.data?.data
            uriImage?.let{
                binding.image.setImageURI(uriImage)
            }
            photo = uriImage.toString()
        }
    }

    private fun createPost(){

        var description = binding.editTextDescription.text.toString()

        if(description.isNotBlank()){
            var id = UUID.randomUUID().toString()
            var time = System.currentTimeMillis()
            var city = binding.txtCity.text.toString()
            val post = Post(id, photo, description, time, city, currentUser)
            Log.e(">>>>" , photo+" Create post: ")
            listener?.let {
                it.onNewPost(post)//-----------editar parametros

            }
            Toast.makeText(getActivity(), "Publicación realizada", Toast.LENGTH_LONG).show()
            binding.editTextDescription.setText("")

        }else{
            Toast.makeText(getActivity(), "Agregue algo a la publicación", Toast.LENGTH_LONG).show()
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1){
            var allGrant = true
            for (result in grantResults){
                Log.e(">>>",""+result)
                if (result == PackageManager.PERMISSION_DENIED) allGrant = false
            }

            if (allGrant){

                //finish()


            }else{
                Toast.makeText(activity, "Tiene que aceptar todos los permisos para continuar", Toast.LENGTH_SHORT).show()
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }

    interface OnNewPostListener{
        fun onNewPost(post: Post)
    }

    companion object {
        @JvmStatic
        fun newInstance(user:User) = PublishFragment(user)
    }
}