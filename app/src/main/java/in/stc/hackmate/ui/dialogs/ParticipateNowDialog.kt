package `in`.stc.hackmate.ui.dialogs

import `in`.stc.hackmate.R
import `in`.stc.hackmate.databinding.DialogParticipateNowBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class ParticipateNowDialog: DialogFragment() {

    private lateinit var binding: DialogParticipateNowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.round_corner);
        binding = DialogParticipateNowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}