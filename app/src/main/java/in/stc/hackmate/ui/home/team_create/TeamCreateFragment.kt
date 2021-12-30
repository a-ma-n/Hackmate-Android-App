package `in`.stc.hackmate.ui.home.team_create

import `in`.stc.hackmate.data.models.Team
import `in`.stc.hackmate.data.models.User
import `in`.stc.hackmate.databinding.FragmentCreateTeamBinding
import `in`.stc.hackmate.ui.auth.gettingStarted.GettingStartedFragment
import `in`.stc.hackmate.ui.home.profile.ProfileViewModel.Companion.uid
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip

private const val TAG = "TeamCreateFragment"
class TeamCreateFragment: Fragment() {
companion object{
    var checkedChips:MutableList<String> = mutableListOf()
}

    private lateinit var binding: FragmentCreateTeamBinding
    private lateinit var viewmodel: TeamCreateViewModel
    private lateinit var user: User


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCreateTeamBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(requireActivity()).get(TeamCreateViewModel::class.java)


        binding.createTeam.setOnClickListener(){

            var name :String = binding.name.text.toString()

            for (i: Int in 0..(binding.chipgp.getChildCount()-1)) {


                var chip: Chip? = (binding.chipgp).getChildAt(i) as Chip?

                Log.i("outside if ", i.toString() + " chip = " + chip?.getText().toString())
                Log.i("outside if ", i.toString() + " chip = " + chip?.id.toString())

                if (chip?.isChecked()!!) {
                    Log.i("inside if ", i.toString() + " chip = " + chip?.getText().toString())
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
            var chips:MutableList<String> = GettingStartedFragment.checkedChips

            var newTeam= uid?.let { it1 ->
                Team(name,
                    "",
                    it1,
                    "",
                    null,
                    "",
                    null
                )
            }
            if (newTeam != null) {
                createTeam(newTeam,chips)
            }
        }
    }

    private fun createTeam(team : Team,chips:  MutableList<String>) {
        viewmodel.createTeam(team,chips)
    }

}