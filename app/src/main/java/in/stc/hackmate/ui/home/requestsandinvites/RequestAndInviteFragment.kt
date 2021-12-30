package `in`.stc.hackmate.ui.home.requestsandinvites

import `in`.stc.hackmate.adapters.*
import `in`.stc.hackmate.data.models.InvitesReceived
import `in`.stc.hackmate.data.models.InvitesSent
import `in`.stc.hackmate.data.models.Requests
import `in`.stc.hackmate.databinding.FragmentInvitesAndRequestsBinding
import `in`.stc.hackmate.databinding.FragmentProjectAddBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

private const val TAG = "RequestAndInviteFragmen"
class RequestAndInviteFragment: Fragment() ,RequestsOnClickListener,InvitesonClickListener {
    private lateinit var binding: FragmentInvitesAndRequestsBinding
    private lateinit var viewmodel: RequestAndInviteViewModel
    private var invitesAdapter = InvitesAdapter(this)
    private var requestsAdapter = RequestsAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentInvitesAndRequestsBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(requireActivity()).get(RequestAndInviteViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fetch the data and show it here
        viewmodel.getInvites()
        observeViewModelInvites()
        viewmodel.getRequests()
        observeViewModelRequests()


    }

    private fun observeViewModelInvites() {
        viewmodel.invites.observe(viewLifecycleOwner, { inv ->
            inv?.let {
                binding.invitesrecyclerview.visibility = View.VISIBLE
                invitesAdapter.submitList(it)
            }
        })
        viewmodel.getRequests()
    }

    private fun observeViewModelRequests() {
        viewmodel.requests.observe(viewLifecycleOwner, { inv ->
            inv?.let {
                binding.invitesrecyclerview.visibility = View.VISIBLE
                requestsAdapter.submitList(it)
            }
        })
        viewmodel.getRequests()
    }


    override fun onClick(view: View, position: Int, invite: InvitesReceived, accept: Boolean) {
        //Invite accepted ->invite, give the status woth the route
        //Invite Rejected -> give the status woth the route
        var s: String
        if (accept) {
            s = "Accepted"
        } else {
            s = "Rejected"
        }
        viewmodel.responseInvites(s)
    }


    override fun onClick(view: View, position: Int, requests: InvitesSent, accept: Boolean) {
        //Request accepted ->request , give the status woth the route
        var s: String
        if (accept) {
            s = "Accepted"
        } else {
            s = "Rejected"
        }
        viewmodel.responseRequests(s)
    }


}