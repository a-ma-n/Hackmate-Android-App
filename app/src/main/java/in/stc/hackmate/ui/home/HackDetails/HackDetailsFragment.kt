package `in`.stc.hackmate.ui.home.HackDetails

import `in`.stc.hackmate.R
import `in`.stc.hackmate.data.models.HackProfile
import `in`.stc.hackmate.databinding.FragmentDeleteAccBinding
import `in`.stc.hackmate.databinding.FragmentHackProfileBinding
import `in`.stc.hackmate.utils.loadImage
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import javax.annotation.Nullable

private const val TAG = "HackDetailsFragment"

class HackDetailsFragment:Fragment() {
    companion object{
        var hackProfile : HackProfile?=null
    }


    private lateinit var binding: FragmentHackProfileBinding

    private val args: HackDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentHackProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireContext(), args.obj.id, Toast.LENGTH_SHORT).show()


        //fetch the values of the hack
        if(requireArguments() != null )
        {
           //var args =  HackDetailsFragmentArgs.fromBundle(requireArguments())
            var hack:HackProfile = args.obj
            hackProfile=hack
            Log.d(TAG, "onViewCreated: "+hack.name)

            binding.hackTitle.setText(hack.name)
            binding.imagePoster.loadImage(hack.poster)
            //binding.organiserName.setText(hack.name)
            // parse the fetched date to the format to display
            binding.startDate.setText(hack.start)
            binding.endDate.setText(hack.end)
            binding.venueContent.setText(hack.venue)
            binding.prizeContent.setText(hack.prize)
            binding.minMem.setText(hack.min)
            binding.maxMem.setText(hack.max)
            binding.aboutContent.setText(hack.description)
            binding.participateNowButton.setOnClickListener {
                openDialog()
            }
            binding.button.setOnClickListener {
                openWebsite(hack)
            }
         }
    }

    private fun openWebsite(hack:HackProfile) {
        var link = Intent(Intent.ACTION_VIEW)
        link.setData(Uri.parse(hack.website))
        startActivity(link)
    }

    private fun openDialog() {
        var dialog =  Dialog (requireContext())
        dialog.setContentView(R.layout.dialog_participate_now)
        dialog.show()
        var add = dialog.findViewById<Button>(R.id.addExisting)
        var join = dialog.findViewById<Button>(R.id.joinExisting)
        var create = dialog.findViewById<Button>(R.id.createTeam)



        add.setOnClickListener {
            dialog.hide()
            Navigation.findNavController(requireView())
                .navigate(R.id.action_hackDetailsFragment_to_addFromExistingFragment)
        }
        join.setOnClickListener {
            dialog.hide()
            Navigation.findNavController(requireView())
                .navigate(R.id.action_hackDetailsFragment_to_joinTeamFragment)
        }
        create.setOnClickListener {
            dialog.hide()
            Navigation.findNavController(requireView())
                .navigate(R.id.action_hackDetailsFragment_to_teamCreateFragment)
        }

    }
}