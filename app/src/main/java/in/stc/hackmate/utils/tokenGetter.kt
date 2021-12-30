package `in`.stc.hackmate.utils

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "tokenGetter"
fun tokenGetter():String {
     var token :String=""
    FirebaseAuth.getInstance().currentUser!!.getIdToken(false).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            token=task.result!!.token!!
            Log.d(TAG, "token found ")
            Log.d(TAG, "tokenGetter: "+token)

        }
        else
        {
            Log.d(TAG, "some error occured ")
            token=""
        }
    }
    return token
}