package `in`.stc.hackmate.ui.auth.gettingStarted

import `in`.stc.hackmate.R
import `in`.stc.hackmate.data.models.User
import `in`.stc.hackmate.databinding.FragmentGettingStartedBinding
import `in`.stc.hackmate.ui.home.HomeActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log

import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.chip.Chip


class GettingStartedFragment:Fragment() {

    companion object{
         var checkedChips:MutableList<String> = mutableListOf()
    }
    val TAG="GettingStartedFragment"

    private lateinit var binding:FragmentGettingStartedBinding
    private lateinit var viewmodel: GettingStartedViewModel
    lateinit var uri: Uri
    lateinit var pic:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewmodel=   ViewModelProvider(requireActivity()).get(GettingStartedViewModel::class.java)
        binding = FragmentGettingStartedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            context?.resources?.getStringArray(R.array.year)?.let {
                ArrayAdapter(requireContext(),
                    R.layout.item_dropdown,
                    it.toList()).also { adapter ->
                    yearGraduation.setAdapter(adapter)
                }
            }
        }
        binding.imageView.setOnClickListener()
        {
            changePic()
        }
        chipSelect()
        binding.createProfile.setOnClickListener() {
            if (binding.description.text.toString() != ""
                && binding.description.text.toString() != ""
                && binding.college.text.toString() != ""
                && binding.GithubLink.text.toString() != ""
                && binding.yearGraduation.text.toString() != ""
                && binding.linkedin.text.toString() != ""
                && binding.FirstName.text.toString() != ""
                && binding.LastName.text.toString() != ""
                && binding.username.text.toString() != ""
            ) {
                createUser()
            } else {
                Toast.makeText(context, "Enter the required details to proceed", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun createUser() {

        if (binding.YourWebsite.text.toString() == "") {
            binding.YourWebsite.setText("--")
        }

        if (uri == null) {
            pic = "--"
        }
        var user = User(
            "",
            binding.description.text.toString(),
            binding.college.text.toString(),
            binding.GithubLink.text.toString(),
            binding.yearGraduation.text.toString().toInt(),
            binding.linkedin.text.toString(),
            (binding.FirstName.text.toString() + binding.LastName.text.toString()),
            "",
            binding.username.text.toString(),
            "",
            " "
        )
        //todo: pass user mail

        //todo check return context+
        var c=0
        try {
            for (i: Int in 0..(binding.chipgp.getChildCount()-1)) {


                var chip: Chip? = (binding.chipgp).getChildAt(i) as Chip?

                Log.i("outside if ", i.toString() + " chip = " + chip?.getText().toString())
                Log.i("outside if ", i.toString() + " chip = " + chip?.id.toString())

                if (chip?.isChecked()!!) {
                    Log.i("inside if ", i.toString() + " chip = " + chip?.getText().toString())
//                binding.textView.setText(chip.getText().toString());
                    Log.d(TAG, "createUser: " + chip?.id.toString())


                        if(chip?.getText().toString().equals("Backend"))
                        {
                            checkedChips.add("backend")
                        }
                        if(chip?.getText().toString().equals("Frontend"))
                        {
                            checkedChips.add("frontend")
                        }
                        if(chip?.getText().toString().equals("App Development"))
                        {
                            checkedChips.add("appdev")
                        }
                        if(chip?.getText().toString().equals("Machine learning"))
                        {
                            checkedChips.add("ml")
                        }
                        if(chip?.getText().toString().equals("UI/UX design"))
                        {
                            checkedChips.add("ui/ux")
                        }
                        if(chip?.getText().toString().equals("Management"))
                        {
                            checkedChips.add("management")
                        }
                        if(chip?.getText().toString().equals("CyberSecurity"))
                        {
                            checkedChips.add("cybersecurity")
                        }
                        if(chip?.getText().toString().equals("Blockchain"))
                        {
                            checkedChips.add("blockchain")
                        }

                    }

                }

            Log.d(TAG, "createUser value of the array:" + checkedChips)

            try {
                viewmodel.addSkillsViewModel()
            }
            catch (e :Exception){
                print("add skills exception")
            }

            finally {


                viewmodel.checkUserNameViewModel(binding.username.text.toString())

                viewmodel.createUserViewModel(user)


                Intent(requireActivity(), HomeActivity::class.java)
                    .also {
                        startActivity(it)
                        requireActivity().finish()
                    }


            }

        }
        catch(e :Exception)
        {
            Log.d(TAG, "createUser: "+e.toString())
        }
    }

    private fun changePic()
    {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        uri = data!!.getData()!!
        pic=uri.toString()
        Glide.with(this)
            .load(uri)
            .into(binding.imageView);
    }

    private fun chipSelect()
    {

        binding.backend.setOnCheckedChangeListener { chip, isChecked ->
            if(isChecked==true)
            {
                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pill_button))
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            else
            {
                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pill_fill))
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.pill_button))
            }
        }
        binding.frontend.setOnCheckedChangeListener { chip, isChecked ->
            // Responds to chip checked/unchecked
            if(isChecked==true)
            {
                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pill_button))
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            else
            {
                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pill_fill))
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.pill_button))
            }
        }
        binding.ml.setOnCheckedChangeListener { chip, isChecked ->
            // Responds to chip checked/unchecked
            if(isChecked==true)
            {
                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pill_button))
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            else
            {
                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pill_fill))
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.pill_button))
            }
        }
        binding.design.setOnCheckedChangeListener { chip, isChecked ->
            // Responds to chip checked/unchecked
            if(isChecked==true)
            {
                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pill_button))
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            else
            {
                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pill_fill))
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.pill_button))
            }
        }
        binding.appdev.setOnCheckedChangeListener { chip, isChecked ->
            // Responds to chip checked/unchecked
            if(isChecked==true)
            {
                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pill_button))
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            else
            {
                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pill_fill))
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.pill_button))
            }
        }
        binding.management.setOnCheckedChangeListener { chip, isChecked ->
            // Responds to chip checked/unchecked
            if(isChecked==true)
            {
                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pill_button))
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            else
            {
                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pill_fill))
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.pill_button))
            }
        }
        binding.blockchain.setOnCheckedChangeListener { chip, isChecked ->
            // Responds to chip checked/unchecked
            if(isChecked==true)
            {
                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pill_button))
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            else
            {
                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pill_fill))
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.pill_button))
            }
        }
        binding.cybersecurity.setOnCheckedChangeListener { chip, isChecked ->
            // Responds to chip checked/unchecked
            if(isChecked==true)
            {
                chip.setBackgroundResource( R.color.pill_button)
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            else
            {
                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.pill_fill))
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.pill_button))
            }
        }
    }
}
