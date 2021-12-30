package `in`.stc.hackmate.ui.auth.login

import `in`.stc.hackmate.R
import `in`.stc.hackmate.data.repositories.ProfileRepoImpl
import `in`.stc.hackmate.databinding.FragmentLoginBinding
import `in`.stc.hackmate.ui.auth.signin.SignupFragment
import `in`.stc.hackmate.ui.home.HomeActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "LoginFragment"

class LoginFragment:  Fragment(){
    companion object{
        var idTokenLogin: String="---"
    }

    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewmodel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewmodel= ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_login, container, false)


        binding = FragmentLoginBinding.bind(view)

        firebaseAuth = FirebaseAuth.getInstance()


        binding.loginBtn.setOnClickListener() {
            login()
        }
        val currentUser = firebaseAuth.currentUser

        if(currentUser != null){
            toHome()
        }

        binding.Signup.setOnClickListener(){
            toSignUp()
        }
        binding.ForgotPass.setOnClickListener()
        {
            forgotPass()
        }

        return binding.root
    }

    private fun login() {
            FirebaseAuth.getInstance().signOut()
            val email: String = binding.emailField.text.toString()
            val password: String = binding.passField.text.toString()
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(context , "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            }
        else{


            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity())
            {
                if (it.isSuccessful)
                {
                    Log.d(TAG,"ye hai user: "+ firebaseAuth.currentUser.toString() )
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    viewmodel.loginViewmodel()
                    if(ProfileRepoImpl.responseCodeLogin==200)
                    {
                        toHome()
                    }

                    else
                    {
                        Log.d(TAG, "user ?: "+firebaseAuth.currentUser!!.toString())
                        Log.d(TAG, "is mail verified: "+ firebaseAuth.currentUser!!.isEmailVerified.toString())
                        toGettingStarted()
                    }


                } else
                {
                    Toast.makeText(context, "Authentication Failed ", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }


    private fun forgotPass()
    {

        val email: String = binding.emailField.text.toString()
        if(email=="")
        {
            Toast.makeText(context, "Enter Your Mail ID", Toast.LENGTH_SHORT).show()
        }
        else
        {
            firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "Mail sent to you , recover your password", Toast.LENGTH_LONG).show()
                        Log.d("forgotpass-", "Email sent.")
                    }
                }
        }
    }

    private fun toSignUp()
    {//navigate to home
        Navigation.findNavController(requireView())
            .navigate(R.id.action_loginFragment_to_signupFragment)
    }

    private fun toHome()
    {
        firebaseAuth.currentUser!!.getIdToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    idTokenLogin = task.result!!.token!!
                    Log.e(TAG, "token is " +idTokenLogin)
                    Log.d(SignupFragment.TAG, "Token successful"+task.result.toString())
                    Intent(requireActivity(), HomeActivity::class.java)
                        .also {
                            it.putExtra("idToken", task.result!!.token!!)
                            startActivity(it)
                            requireActivity().finish()
                        }
                }
                else{
                    Log.e(TAG, "nahi mila token: ", )
                }
            }

    }

    private fun toGettingStarted() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_loginFragment_to_gettingStartedFragment)
    }

}