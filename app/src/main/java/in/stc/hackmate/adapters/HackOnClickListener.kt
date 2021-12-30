package `in`.stc.hackmate.adapters

import `in`.stc.hackmate.data.models.HackProfile
import android.view.View

interface HackOnClickListener {
    fun onClick(view: View, position: Int, hack: HackProfile)
}