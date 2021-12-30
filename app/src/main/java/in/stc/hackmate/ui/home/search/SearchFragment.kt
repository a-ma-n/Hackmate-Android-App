package `in`.stc.hackmate.ui.home.search

import `in`.stc.hackmate.R
import `in`.stc.hackmate.adapters.FindATeamAdapter
import `in`.stc.hackmate.adapters.FindATeamOnClickListener
import `in`.stc.hackmate.adapters.MyTeamsAdapter
import `in`.stc.hackmate.adapters.MyTeamsOnCLickListener
import `in`.stc.hackmate.data.models.Team
import `in`.stc.hackmate.databinding.FragmentFindATeamBinding
import `in`.stc.hackmate.databinding.FragmentMyTeamBinding
import `in`.stc.hackmate.ui.home.profile.ProfileViewModel
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

private const val TAG = "SearchFragment"
//todo fix animation effect using a drawable instead of drawable end
class SearchFragment:Fragment(),FindATeamOnClickListener {

        private lateinit var binding: FragmentFindATeamBinding
        private lateinit var viewmodel: SearchViewModel
        private var userListAdapter = FindATeamAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFindATeamBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filter.setOnClickListener(){
            //TODO implement the hiding of the menu
            binding.teamCode.setOnClickListener(){
                joinByCode()
            }
            if (binding.chipGroup.getVisibility() == View.GONE) {
                binding.chipGroup.setVisibility(View.VISIBLE)
            }
            else{
                binding.chipGroup.setVisibility(View.GONE)
            }
        }

        viewmodel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userListAdapter
        }
        viewmodel.fetchTeams()
        observeViewModel()


    }

    private fun joinByCode() {
        var dialog =  Dialog (requireContext())
        dialog.setContentView(R.layout.dialog_join_team_code)
        dialog.show()

        var code = dialog.findViewById<EditText>(R.id.code).text
        Log.d(TAG, "joinByCode: "+code)
        //todo add route to join by code


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
        Log.d(TAG, "onClick: " + team)
        val action = SearchFragmentDirections.actionSearchFragmentToParticipantViewFragment(team)
    }
}


