package `in`.stc.hackmate.ui.home.deleteAccount

import `in`.stc.hackmate.databinding.FragmentDeleteAccBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private const val TAG = "DeleteAccountFragment"

class DeleteAccountFragment: Fragment() {

    private lateinit var binding: FragmentDeleteAccBinding
    private lateinit var viewmodel: DeleteAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentDeleteAccBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDeleteAccBinding.bind(view)

        binding.deleteAccbutton.setOnClickListener {
            val user = Firebase.auth.currentUser!!
            val credential = EmailAuthProvider
                .getCredential(Firebase.auth.currentUser!!.email.toString(), binding.enterOtp.text.toString())
            user.reauthenticate(credential)
                .addOnCompleteListener {
                    Log.d(TAG, "User re-authenticated.")
                    //delete user acc using both fb and route
                    user.delete()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d(TAG, "User account deleted.")
                            }
                        }
                    viewmodel.deleteUser()
                 }
            }




    }

}