package `in`.stc.hackmate.ui.home

import `in`.stc.hackmate.R
import `in`.stc.hackmate.databinding.ActivityHomeBinding
import `in`.stc.hackmate.ui.auth.AuthActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "HomeActivity"
class HomeActivity:AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        idToken = intent.getStringExtra("idToken").toString()
        if (idToken.isNullOrEmpty()) {
            fetchIdToken()
        }
         val navController= findNavController(R.id.fragmentContainerView)
//        val appBarConfig = AppBarConfiguration(setOf(R.id.homeFragment,R.id.searchFragment,R.id.myTeamsFragment,R.id.profileFragment))
//        setupActionBarWithNavController(navController,appBarConfig)
        binding.bottomNavigation.setupWithNavController(navController)

    }
    companion object{
        var idToken = ""
        fun fetchIdToken(){
            FirebaseAuth.getInstance().currentUser!!.getIdToken(false)
                .addOnSuccessListener { token ->
                    Log.e(TAG, "fetchIdToken: New Token fetched -> ${token.token}", )
                    idToken= token.token!!
            }
        }
    }



}