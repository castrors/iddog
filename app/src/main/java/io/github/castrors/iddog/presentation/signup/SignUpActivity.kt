package io.github.castrors.iddog.presentation.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
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
            if(editEmail.isValidEmail()){
                signUpViewModel.signUp(editEmail.text.toString()).observe(this, Observer<UIState> {
                    when(it){
                        is ContentState -> {
                            saveToken(it.content as User)
                            finish()
                        }
                    }
                })
            } else {
                inputLayout.error = getString(R.string.invalid_email)
            }
        }
    }

    private fun saveToken(user: User) {
        SessionRepository.token = user.token
    }
}

private fun TextInputEditText.isValidEmail() : Boolean{
    return (!TextUtils.isEmpty(this.text) && Patterns.EMAIL_ADDRESS.matcher(this.text).matches())
}
