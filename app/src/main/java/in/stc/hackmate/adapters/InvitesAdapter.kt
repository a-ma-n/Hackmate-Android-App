package `in`.stc.hackmate.adapters

import `in`.stc.hackmate.data.models.Invites
import `in`.stc.hackmate.data.models.InvitesReceived
import `in`.stc.hackmate.databinding.CardInvitesBinding
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class InvitesAdapter(
    private val clickListener: InvitesonClickListener
) : ListAdapter<InvitesReceived, InvitesAdapter.InvitesViewHolder>(DiffUtilCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvitesViewHolder =
        InvitesViewHolder.create(parent)
    override fun onBindViewHolder(holder:InvitesViewHolder, position: Int) {
        holder.bind(getItem(position),clickListener)
    }

    companion object DiffUtilCallback : DiffUtil.ItemCallback<InvitesReceived>() {
        override fun areItemsTheSame(oldItem: InvitesReceived, newItem:  InvitesReceived): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: InvitesReceived, newItem: InvitesReceived): Boolean =
            oldItem.toString() == newItem.toString()

    }
    class InvitesViewHolder(private var binding: CardInvitesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(invite: InvitesReceived, clickListener: InvitesonClickListener) {



            binding.apply {
                name.setText(invite.participant.name)
                textView10.setText(invite.team.name)

                //accept button
                button3.setOnClickListener {
                    clickListener.onClick(binding.root, adapterPosition, invite,true)
                }
                button4.setOnClickListener{
                    clickListener.onClick(binding.root, adapterPosition, invite,false)
                }
                }
            }


        companion object {
            fun create(parent: ViewGroup): InvitesViewHolder {
                val binding =
                    CardInvitesBinding.inflate(LayoutInflater.from(parent.context))
                return InvitesViewHolder(binding)
            }
        }
    }
}