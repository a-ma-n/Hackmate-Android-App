package `in`.stc.hackmate.ui.home.settings

import `in`.stc.hackmate.R
import `in`.stc.hackmate.databinding.ActivityHomeBinding.inflate
import `in`.stc.hackmate.databinding.FragmentHomeBinding
import `in`.stc.hackmate.databinding.FragmentSettingsBinding
import `in`.stc.hackmate.ui.auth.signin.SignupFragment
import `in`.stc.hackmate.ui.dialogs.LogoutDialog
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private const val TAG = "SettingsFragment"
class SettingsFragment: Fragment(){


    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSettingsBinding.bind(view)

        binding.passwordReset.setOnClickListener(){
            passreset()
        }

        binding.deleteAcc.setOnClickListener(){
            accDel()
        }
        binding.logout.setOnClickListener()
        {

            //val bind :LogoutDialog = LogoutDialog .inflate(inflater)
            //val builder = AlertDialog.Builder()

            logout()
        }
    }

    private fun passreset() {

        val emailAddress = Firebase.auth.currentUser!!.email
        Firebase.auth.sendPasswordResetEmail(emailAddress!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                }
            }
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        Navigation.findNavController(requireView()).navigate(R.id.action_settingsFragment2_to_homeFragment)
    }

    private fun accDel() {
        Navigation.findNavController(requireView()).navigate(R.id.action_settingsFragment2_to_deleteAccountFragment2)
    }


}