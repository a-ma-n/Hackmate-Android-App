package `in`.stc.hackmate.ui.home.profile

import `in`.stc.hackmate.R
import `in`.stc.hackmate.data.models.Project
import `in`.stc.hackmate.data.models.User
import `in`.stc.hackmate.databinding.FragmentMyProfileBinding
import `in`.stc.hackmate.databinding.FragmentMyProfileEditBinding
import `in`.stc.hackmate.ui.auth.gettingStarted.GettingStartedFragment
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip

private const val TAG = "EditProfileFragment"
class EditProfileFragment: Fragment() {
    companion object{
        var checkedChips:MutableList<String> = mutableListOf()
    }
    private lateinit var binding: FragmentMyProfileEditBinding
    private lateinit var viewmodel: EditProfileViewModel
    lateinit var uri: Uri
    lateinit var pic:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMyProfileEditBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //add pic on the main profile page -> click on the image view
        binding.imageView3.setOnClickListener{
            addPic()
        }
        //todo: delete projects -> click on the card on recyclerview



       // deleteProject()

        //update the new details
        binding.savechanges.setOnClickListener{
            updateProfile()
        }
    }

    private fun addPic() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
    }

    private fun updateSkills() {
        //TODO: check the selected chips and add them in a array - hit the route to edit skills
        for (i: Int in 0..(binding.chipGroup.getChildCount()-1)) {


            var chip: Chip? = (binding.chipGroup).getChildAt(i) as Chip?

            Log.i("outside if ", i.toString() + " chip = " + chip?.getText().toString())
            Log.i("outside if ", i.toString() + " chip = " + chip?.id.toString())

            if (chip?.isChecked()!!) {
                Log.i("inside if ", i.toString() + " chip = " + chip?.getText().toString())
//                binding.textView.setText(chip.getText().toString());
                Log.d(TAG, "createUser: " + chip?.id.toString())


                if(chip?.getText().toString().equals("Backend"))
                {
                    GettingStartedFragment.checkedChips.add("backend")
                }
                if(chip?.getText().toString().equals("Frontend"))
                {
                    GettingStartedFragment.checkedChips.add("frontend")
                }
                if(chip?.getText().toString().equals("App Development"))
                {
                    GettingStartedFragment.checkedChips.add("appdev")
                }
                if(chip?.getText().toString().equals("Machine learning"))
                {
                    GettingStartedFragment.checkedChips.add("ml")
                }
                if(chip?.getText().toString().equals("UI/UX design"))
                {
                    GettingStartedFragment.checkedChips.add("ui/ux")
                }
                if(chip?.getText().toString().equals("Management"))
                {
                    GettingStartedFragment.checkedChips.add("management")
                }
                if(chip?.getText().toString().equals("CyberSecurity"))
                {
                    GettingStartedFragment.checkedChips.add("cybersecurity")
                }
                if(chip?.getText().toString().equals("Blockchain"))
                {
                    GettingStartedFragment.checkedChips.add("blockchain")
                }

            }

        }
        try {
            viewmodel.addSkills()
        }
        catch (e:Exception){
            Log.e(TAG, "createUser: " +e.toString())
        }
    }

    private fun updateProfile() {
        updateSkills()
        var user =User (
            "",
        binding.bioContent.text.toString(),
        binding.collegeContent.text.toString(),
        binding.githubContent.text.toString(),
        binding.yearOfGraduationContent.text.toString().toInt(),
        binding.linkedinContent.text.toString(),
        binding.nameTitle.text.toString(),
        pic,//todo: check if this is correct or not
        binding.usernameSubtitle.text.toString(),
        binding.personalWebsiteContent.text.toString(),
        binding.emailContent.text.toString()
        )
        viewmodel.updateUser(user)

        Navigation.findNavController(requireView())
            .navigate(R.id.action_editProfileFragment_to_profileFragment)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        uri = data!!.getData()!!
        pic=uri.toString()
        Glide.with(this)
            .load(uri)
            .into(binding.imageView3);
    }

    private fun deleteProject(project :Project) {
        viewmodel.deleteproject(project)
    }
}