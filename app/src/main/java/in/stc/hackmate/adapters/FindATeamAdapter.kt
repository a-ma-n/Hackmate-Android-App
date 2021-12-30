package `in`.stc.hackmate.adapters

import `in`.stc.hackmate.R
import `in`.stc.hackmate.data.models.Team
import `in`.stc.hackmate.databinding.CardTeamsAndChipsLayoutSidearrowBinding
import `in`.stc.hackmate.ui.home.profile.ProfileViewModel
import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "FindATeamAdapter"
//todo write content for adapter
class FindATeamAdapter(
    private val clickListener: FindATeamOnClickListener,
) : ListAdapter<Team, FindATeamAdapter.TeamsViewHolder>(DiffUtilCallback){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindATeamAdapter.TeamsViewHolder =
        FindATeamAdapter.TeamsViewHolder.create(parent)

    override fun onBindViewHolder(holder:FindATeamAdapter.TeamsViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    companion object DiffUtilCallback : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean =
            oldItem.toString() == newItem.toString()

    }
    class TeamsViewHolder(private var binding: CardTeamsAndChipsLayoutSidearrowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(team: Team, clickListener: FindATeamOnClickListener) {

            var skills:MutableList<String> = mutableListOf()

            var uid: String? = ProfileViewModel.uid
            if (team.participants!= null){
                val iterator = team?.participants?.listIterator()
                for (i in iterator){
                    skills.add(i.skills.skill)
                }
            }
            Log.d(TAG, "skills: "+skills)

            binding.apply {
                nameTeam.setText(team.name)
                teamHack.setText(team.hackName)

                if(uid.equals(team.a_id)){
                    teamPosn.setText("Leader")
                }
                else
                {
                    teamPosn.setText("Member")
                }

                for (i in skills){
                    if (i == "ml")
                        mlChip.setBackgroundColor(R.color.pill_button)
                    if (i == "backend")
                        backendChip.setBackgroundColor(R.color.pill_button)
                    if (i == "frontend")
                        frontendChip.setBackgroundColor(R.color.pill_button)
                    //todo is this ui/ux?
                    if (i == "ui/ux")
                        uiChip.setBackgroundColor(R.color.pill_button)
                    if (i == "blockchain")
                        blockchainChip.setBackgroundColor(R.color.pill_button)
                    if (i == "cybersecurity")
                        cybersecurityChip.setBackgroundColor(R.color.pill_button)
                    if (i == "management")
                        managementChip.setBackgroundColor(R.color.pill_button)
                    if (i == "appdev")
                        appChip.setBackgroundColor(R.color.pill_button)
                }

                arrow.setOnClickListener {
                    clickListener.onClick(binding.root, adapterPosition, team)
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): FindATeamAdapter.TeamsViewHolder {
                val binding =
                    CardTeamsAndChipsLayoutSidearrowBinding.inflate(LayoutInflater.from(parent.context))
                return FindATeamAdapter.TeamsViewHolder(binding)
            }
        }
    }
}