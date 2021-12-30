package `in`.stc.hackmate.adapters

import `in`.stc.hackmate.data.models.InvitesSent
import `in`.stc.hackmate.data.models.Requests
import `in`.stc.hackmate.databinding.CardInvitesBinding
import `in`.stc.hackmate.databinding.CardRequestsBinding
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter


class RequestsAdapter(
    private val clickListener: RequestsOnClickListener

): ListAdapter<InvitesSent, RequestsAdapter.RequestsViewHolder>(DiffUtilCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestsViewHolder =
        RequestsViewHolder.create(parent)
    override fun onBindViewHolder(holder:RequestsViewHolder, position: Int) {
        holder.bind(getItem(position),clickListener)
    }

    companion object DiffUtilCallback : DiffUtil.ItemCallback<InvitesSent>() {
        override fun areItemsTheSame(oldItem: InvitesSent, newItem: InvitesSent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: InvitesSent, newItem: InvitesSent): Boolean =
            oldItem.toString() == newItem.toString()

    }
    class RequestsViewHolder(private var binding: CardRequestsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(invite: InvitesSent, clickListener: RequestsOnClickListener) {


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
            fun create(parent: ViewGroup): RequestsViewHolder {
                val binding =
                    CardRequestsBinding.inflate(LayoutInflater.from(parent.context))
                return RequestsViewHolder(binding)
            }
        }
    }
}