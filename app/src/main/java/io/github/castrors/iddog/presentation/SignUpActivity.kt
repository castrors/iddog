package io.github.castrors.iddog.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.orhanobut.hawk.Hawk
import io.github.castrors.iddog.R
import io.github.castrors.iddog.data.SessionRepository
import io.github.castrors.iddog.presentation.base.ContentState
import io.github.castrors.iddog.presentation.base.UIState
import io.github.castrors.iddog.presentation.model.User
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : AppCompatActivity() {

    private val signUpViewModel: SignUpViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)



        buttonSubmit.setOnClickListener {
            signUpViewModel.signUp(editEmail.text.toString()).observe(this, Observer<UIState> {
                when(it){
                    is ContentState -> {
                        saveToken(it.content as User)
                        finish()
                    }
                }
            })
        }
    }

    private fun saveToken(user: User) {
        SessionRepository.token = user.token
    }
}
