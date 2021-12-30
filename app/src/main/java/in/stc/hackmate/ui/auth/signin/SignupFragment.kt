package `in`.stc.hackmate.ui.auth.signin

import `in`.stc.hackmate.R
import `in`.stc.hackmate.databinding.FragmentSignupBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth

import androidx.lifecycle.ViewModelProvider

class SignupFragment:  Fragment() {
    companion object {
        lateinit var firebaseAuth: FirebaseAuth
        private lateinit var binding: FragmentSignupBinding
         var idToken: String="--"
        val TAG="SignupFrag"
    }



        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
        ): View? {
            super.onCreateView(inflater, container, savedInstanceState)
            var view= inflater.inflate(R.layout.fragment_signup, container, false)
            binding = FragmentSignupBinding.bind(view)
            firebaseAuth = FirebaseAuth.getInstance()
            binding.SignupButton.setOnClickListener {
                signUp()
            }
            return view
        }

        private fun signUp() {

            val email: String = binding.Email.text.toString()
            val password: String = binding.Password.text.toString()
            val Cpassword: String =binding.ConfirmPassword.text.toString()

            var viewmodel= ViewModelProvider(requireActivity()).get(SignupViewModel::class.java)

            if (email=="") {
                Toast.makeText(context , "Email can't be blank", Toast.LENGTH_SHORT).show()
            }

            if (password=="") {
                Toast.makeText(context , "Password can't be blank", Toast.LENGTH_SHORT).show()
            }

            if (!(password.equals(Cpassword))) {
                Toast.makeText(context, "Passwords do not match.",
                    Toast.LENGTH_SHORT).show()
            }

            if(email.indexOf('@')<0 && email.indexOf('.')<0)
            {
                Toast.makeText(context, "Email is not formatted properly.",
                    Toast.LENGTH_SHORT).show()
            }
            if(password.length<6)
            {
                Toast.makeText(context, "Password legth should be greater than 6",
                    Toast.LENGTH_SHORT).show()
            }


            //CREATING USER WITH EMAIL AND PASSWORD
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        Log.d("Signup-", "createUserWithEmail:success")


                        //SENDING VERIFICATION MAIL
                        firebaseAuth.currentUser!!.sendEmailVerification()
                            .addOnCompleteListener(requireActivity()) { work ->

                                if (work.isSuccessful) {
                                    Log.d(TAG, "Mail sent to the user ")


                                    //GETTING TOKEN


//                                    Log.e(TAG, "getToken: " + mUser)
                                    FirebaseAuth.getInstance().currentUser!!.getIdToken(false)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                SignupFragment.idToken = task.result!!.getToken()!!
                                                Log.d(TAG, "Token successful" + task.result.toString())

                                                Log.d(TAG, "signUp neww: "+ idToken)

                                                viewmodel.setclaimViewmodel()
                                                FirebaseAuth.getInstance().signOut()
                                                toLogin()

                                            }

                                             else {
                                                Log.e(TAG, "Token creation error")
                                            }
                                        }


                                }
                            }
                    }
                    else{


                        Log.e(TAG, "Mail could not be sent " )
                    }
                }
        }


    private fun toLogin()
    {

        Navigation.findNavController(requireView()).navigate(R.id.action_signupFragment_to_loginFragment)
    }
}
