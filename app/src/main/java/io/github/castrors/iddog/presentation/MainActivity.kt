package io.github.castrors.iddog.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.orhanobut.hawk.Hawk
import io.github.castrors.iddog.R
import io.github.castrors.iddog.data.SessionRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        if(!userIsRegistered()){
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun userIsRegistered(): Boolean {
        return SessionRepository.token.isNotEmpty()
    }
}
