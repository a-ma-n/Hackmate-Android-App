package `in`.stc.hackmate.adapters

import `in`.stc.hackmate.data.models.HackProfile
import `in`.stc.hackmate.databinding.CardHomePgBinding
import `in`.stc.hackmate.utils.loadImage
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class HomeAdapter(
    private val clickListener: HackOnClickListener,
) : ListAdapter<HackProfile, HomeAdapter.HackProfileViewHolder>(DiffUtilCallback) {

    //todo add notify dataset changed prop

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HackProfileViewHolder =
        HackProfileViewHolder.create(parent)

    override fun onBindViewHolder(holder: HackProfileViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }


    companion object DiffUtilCallback : DiffUtil.ItemCallback<HackProfile>() {
        override fun areItemsTheSame(oldItem: HackProfile, newItem: HackProfile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HackProfile, newItem: HackProfile): Boolean =
            oldItem.toString() == newItem.toString()
    }


    class HackProfileViewHolder(private var binding: CardHomePgBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hack: HackProfile, clickListener: HackOnClickListener) {
            binding.apply {
                titleMain.text = hack.name
                teamSizeContent.text = hack.min + " to " + hack.max
                startsContent.text = hack.start
                endsContent.text = hack.end
                imagePoster.loadImage(hack.poster)

                knowmore.setOnClickListener {
                    clickListener.onClick(binding.root, adapterPosition, hack)
                }
            }

        }

        companion object {
            fun create(parent: ViewGroup): HackProfileViewHolder {
                val binding = CardHomePgBinding.inflate(LayoutInflater.from(parent.context))
                return HackProfileViewHolder(binding)
            }
        }
    }

}