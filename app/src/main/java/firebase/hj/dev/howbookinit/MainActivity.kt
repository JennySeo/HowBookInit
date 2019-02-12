package firebase.hj.dev.howbookinit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    var auth : FirebaseAuth? =null
    var authListener: FirebaseAuth.AuthStateListener?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        authListener = FirebaseAuth.AuthStateListener{ FirebaseAuth ->
            val user = FirebaseAuth.currentUser
            if (user != null) {
                //login event
            } else {
                // logout event
            }
        }

    }
    override fun onStart() {
        super.onStart()
        auth?.addAuthStateListener (authListener!!)
    }

    override fun onPause() {
        super.onPause()
        auth?.removeAuthStateListener (authListener!!)
    }
    //계정생성
    fun createUserId(email: String, password: String ){
        auth?.createUserWithEmailAndPassword(email,password)
            ?.addOnCompleteListener ( this, { task ->
                if (task.isSuccessful){
                    val user = auth?.currentUser
                }
                else{

                }
            } )
    }

    //로그인
    fun loginUserId(email: String, password: String ){
        auth?.signInWithEmailAndPassword(email,password)
            ?.addOnCompleteListener ( this, { task ->
                if (task.isSuccessful){
                }
                else{

                }
            } )
    }

    //메일 유효성체크
    fun verifyEmail(email: String, password: String ){
        auth?.currentUser?.sendEmailVerification()
            ?.addOnCompleteListener (OnCompleteListener<Void>
            { task ->
                if (task.isSuccessful) {
                }
            })
    }

    //패스워드 변경
    fun updatePassword(password:String){
        auth?.currentUser?.updatePassword(password)

            ?.addOnCompleteListener(OnCompleteListener<Void>
            { task ->
                if (task.isSuccessful) {
                    //Success msg
                }
            })
    }

    //이메일 변경
    fun updateEmail(email:String){
        auth?.currentUser?.updateEmail(email)

            ?.addOnCompleteListener(OnCompleteListener<Void>
            { task ->
                if (task.isSuccessful) {
                    //Success msg
                }
            })
    }


    //패스워드 재설정
    fun sendPasswordResetEmail(email:String){
        auth?.sendPasswordResetEmail(email)

            ?.addOnCompleteListener(OnCompleteListener<Void>
            { task ->
                if (task.isSuccessful) {
                    //Success msg
                }
            })
    }

    //회원탈퇴
    fun deleteId(){
        auth?.currentUser?.delete()
            ?.addOnCompleteListener(OnCompleteListener<Void>
            { task ->
                if (task.isSuccessful) {
                    //Success msg
                }
            })
    }


    //회원재인증
    fun reauthentiate(email: String, password: String ){

        val credential = EmailAuthProvider
            .getCredential(email,password)

        auth?.currentUser?.reauthenticate(credential)
            ?.addOnCompleteListener(OnCompleteListener<Void>
            { task ->
                if (task.isSuccessful) {
                    //Success msg
                }
            })
    }


}
