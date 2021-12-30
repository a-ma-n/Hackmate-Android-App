package `in`.stc.hackmate.ui.home.home_main

import `in`.stc.hackmate.adapters.HackOnClickListener
import `in`.stc.hackmate.adapters.HomeAdapter
import `in`.stc.hackmate.data.models.HackProfile
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.stc.hackmate.databinding.FragmentHomeBinding
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.chip.Chip

private const val TAG = "HomeFragment"
class HomeFragment : Fragment(), HackOnClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewmodel: HomeViewModel
    private var userListAdapter = HomeAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userListAdapter
        }
        viewmodel.fetchHacks()
        observeViewModel()
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->

            val chip: Chip? = group.findViewById(checkedId)

            chip?.let { chipView ->
                viewmodel.apply {
                    when (chip.text) {
                        "All" -> fetchHacks()
                        "Upcoming" -> fetchHacksByUpcoming()
                        "Ongoing" -> fetchHacksByOngoing()
                        "Popularity" -> fetchHacksByPopularity()
                    }
                }
                observeViewModel()

                //Toast.makeText(context, chip.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModel() {
        viewmodel.hacks.observe(viewLifecycleOwner, { hacks ->
            hacks?.let {
                binding.homeRecyclerView.visibility = View.VISIBLE
                userListAdapter.submitList(it)
            }
        })

    }

    override fun onClick(view: View, position: Int, hack: HackProfile) {
        Log.d(TAG, "onClick: "+hack)
       val action = HomeFragmentDirections.actionHomeFragmentToHackDetailsFragment(hack)
        Navigation.findNavController(requireView())
            .navigate(action)
    }
}