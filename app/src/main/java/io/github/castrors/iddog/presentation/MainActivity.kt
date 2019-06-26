package io.github.castrors.iddog.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import io.github.castrors.iddog.R
import io.github.castrors.iddog.data.SessionRepository
import io.github.castrors.iddog.presentation.base.ContentState
import io.github.castrors.iddog.presentation.base.UIState
import io.github.castrors.iddog.presentation.model.Dog
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val dogsViewModel: DogsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        if (userIsRegistered()) {
            observeDogs()
        } else {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun observeDogs() {
        dogsViewModel.fetchDogs("husky").observe(this, Observer<UIState> {
            when (it) {
                is ContentState -> {
                    sampleText.text = (it.content as List<Dog>).toString()
                }
            }
        })
    }

    private fun userIsRegistered(): Boolean {
        return SessionRepository.token.isNotEmpty()
    }
}
