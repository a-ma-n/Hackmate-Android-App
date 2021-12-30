package `in`.stc.hackmate.ui.home.teams

import `in`.stc.hackmate.adapters.HackOnClickListener
import `in`.stc.hackmate.adapters.HomeAdapter
import `in`.stc.hackmate.adapters.MyTeamsAdapter
import `in`.stc.hackmate.adapters.MyTeamsOnCLickListener
import `in`.stc.hackmate.data.models.HackProfile
import `in`.stc.hackmate.data.models.Team
import `in`.stc.hackmate.databinding.FragmentHomeBinding
import `in`.stc.hackmate.databinding.FragmentMyTeamBinding
import `in`.stc.hackmate.ui.home.profile.ProfileViewModel.Companion.uid
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip

private const val TAG = "MyTeamsFragment"

class MyTeamsFragment:Fragment(), MyTeamsOnCLickListener {
    private lateinit var binding: FragmentMyTeamBinding
    private lateinit var viewmodel: MyTeamsViewModel
    private var userListAdapter = MyTeamsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMyTeamBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createTeam.setOnClickListener(){
            Navigation.findNavController(requireView()).navigate(MyTeamsFragmentDirections.actionMyTeamsFragmentToTeamCreateFragment())
        }
        binding.bell.setOnClickListener(){
            Navigation.findNavController(requireView()).navigate(MyTeamsFragmentDirections.actionMyTeamsFragmentToRequestAndInviteFragment())
        }


        viewmodel = ViewModelProvider(requireActivity()).get(MyTeamsViewModel::class.java)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userListAdapter
        }
        viewmodel.fetchTeams()
        observeViewModel()

    }
    private fun observeViewModel() {
        viewmodel.teams.observe(viewLifecycleOwner, { hacks ->
            hacks?.let {
                binding.recyclerView.visibility = View.VISIBLE
                userListAdapter.submitList(it)
            }
        })

    }

    override fun onClick(view: View, position: Int, team: Team) {
        Log.d(TAG, "onClick: "+team)
        if (uid == team.a_id){
        val action = MyTeamsFragmentDirections.actionMyTeamsFragmentToTeamProfileLeaderFragment(team)
            Navigation.findNavController(requireView())
                .navigate(action)
        }
        else{
        val action = MyTeamsFragmentDirections.actionMyTeamsFragmentToTeamProfileMemberFragment(team)
            Navigation.findNavController(requireView())
                .navigate(action)
        }


    }
}