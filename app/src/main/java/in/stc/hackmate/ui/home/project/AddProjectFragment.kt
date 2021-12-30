package `in`.stc.hackmate.ui.home.project

import `in`.stc.hackmate.R
import `in`.stc.hackmate.data.models.Project
import `in`.stc.hackmate.data.models.User
import `in`.stc.hackmate.databinding.FragmentHackProfileBinding
import `in`.stc.hackmate.databinding.FragmentProjectAddBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.*

class AddProjectFragment:Fragment() {

    private lateinit var binding: FragmentProjectAddBinding
    private lateinit var viewmodel: AddProjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentProjectAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // on submit post the details to the server

        binding.submit.setOnClickListener{

            // GIVE projeft id?
            var project = Project(
                binding.projectName.text.toString(),
                        binding.projectDescription.text.toString(),
                        binding.githubLink.text.toString(),
                        binding.designLink.text.toString(),
                        binding.demoLink.text.toString()
            )

            viewmodel.addProject(project)

            findNavController(requireView())
                    .navigate(R.id.action_addProjectFragment_to_profileFragment)


        }

    }


}