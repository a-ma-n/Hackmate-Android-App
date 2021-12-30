package `in`.stc.hackmate.ui.home.team_add_from_existing

import `in`.stc.hackmate.R
import `in`.stc.hackmate.adapters.AddExistingTeamsAdapter
import `in`.stc.hackmate.adapters.AddTeamsOnClickListener
import `in`.stc.hackmate.adapters.HomeAdapter
import `in`.stc.hackmate.data.models.HackProfile
import `in`.stc.hackmate.data.models.Team
import `in`.stc.hackmate.databinding.CardTeamAndChipsLayoutBinding
import `in`.stc.hackmate.databinding.FragmentAddTeamToHackBinding
import `in`.stc.hackmate.databinding.FragmentHackProfileBinding
import `in`.stc.hackmate.ui.home.HackDetails.HackDetailsFragment.Companion.hackProfile
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

private const val TAG = "AddFromExistingFragment"

class AddFromExistingFragment:Fragment(),AddTeamsOnClickListener {
    private lateinit var binding:FragmentAddTeamToHackBinding
    private lateinit var viewmodel: AddFromExistingViewModel
     private var teamsAdapter = AddExistingTeamsAdapter(this)

//todo: call the my teams function and then bind it with the recyler view



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentAddTeamToHackBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    
    }

    private fun observeViewModel() {
        viewmodel.teams.observe(viewLifecycleOwner, { hacks ->
            hacks?.let {
                binding.addTeamsRecylerView.visibility = View.VISIBLE
                //todo: uncomment
             teamsAdapter.submitList(it)
            }
        })

    }


    @SuppressLint("ResourceAsColor")
    override fun onClick(view: View, position: Int, team: Team) {
        Log.d(TAG, "onClick: "+team)
        //todo implement route to add team to hack
         var binding2 = CardTeamAndChipsLayoutBinding.inflate(layoutInflater)
        binding2.addHack.setBackgroundColor(R.color.pill_button)
        //val action = HomeFragmentDirections.actionHomeFragmentToHackDetailsFragment(hack)
        viewmodel.addTeam(team,hackProfile!!)
//        Navigation.findNavController(requireView())
//            .navigate(action)
    }


}