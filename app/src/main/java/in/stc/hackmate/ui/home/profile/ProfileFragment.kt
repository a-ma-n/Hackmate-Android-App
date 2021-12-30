package `in`.stc.hackmate.ui.home.profile

import `in`.stc.hackmate.R
import `in`.stc.hackmate.data.models.User
import `in`.stc.hackmate.databinding.FragmentMyProfileBinding
import `in`.stc.hackmate.databinding.FragmentSettingsBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

class ProfileFragment: Fragment() {

    private lateinit var binding: FragmentMyProfileBinding
    private lateinit var viewmodel: ProfileViewModel
    private var user:User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMyProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //fetch details
        viewmodel= ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)
        user  = viewmodel.fetchUserDetails()

        //details set
        user?.let{
            binding.nameTitle.text = it.name
            binding.bioContent.text = it.bio
            binding.collegeContent.text = it.college
            binding.emailContent.text = it.mail
            binding.githubContent.text = it.github
            binding.linkedinContent.text = it.linkedIn
            binding.yearOfGraduationContent.text = it.graduation_year.toString()
            binding.usernameSubtitle.text = it.username
        }

        //todo: get & display skills

        binding.addproject.setOnClickListener(){
            addproject()
        }

        binding.editProfile.setOnClickListener(){
            editProfile()
        }

    }

    private fun editProfile() {
        Navigation.findNavController(requireView())
                   .navigate(R.id.action_profileFragment_to_editProfileFragment)
    }

    private fun addproject() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_profileFragment_to_addProjectFragment)
    }
}