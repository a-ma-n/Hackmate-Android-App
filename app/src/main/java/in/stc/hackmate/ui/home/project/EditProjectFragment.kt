package `in`.stc.hackmate.ui.home.project

import `in`.stc.hackmate.databinding.FragmentProjectAddBinding
import `in`.stc.hackmate.databinding.FragmentProjectEditBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

private const val TAG = "EditProjectFragment"
// Todo: can only be done by the leader > so we should check if the profile is of the leader or not and then only give the option of editing
class EditProjectFragment:Fragment() {

    private lateinit var binding: FragmentProjectEditBinding
    private lateinit var viewmodel: AddProjectViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentProjectEditBinding.inflate(layoutInflater)
        return binding.root
    }

}