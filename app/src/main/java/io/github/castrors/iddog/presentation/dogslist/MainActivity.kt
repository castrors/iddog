package io.github.castrors.iddog.presentation.dogslist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import io.github.castrors.iddog.R
import io.github.castrors.iddog.data.SessionRepository
import io.github.castrors.iddog.presentation.signup.SignUpActivity
import io.github.castrors.iddog.presentation.base.ContentState
import io.github.castrors.iddog.presentation.base.UIState
import io.github.castrors.iddog.presentation.dogdetail.DogDetailActivity
import io.github.castrors.iddog.presentation.model.Dog
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val dogsViewModel: DogsViewModel by viewModel()
    private val adapter: DogsListAdapter<Dog, ItemDogView> by lazy {
        DogsListAdapter {
            ItemDogView(
                it
            )
        }.apply {
            withListener {
                DogDetailActivity.navigateToDogDetail(this@MainActivity, it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@MainActivity.adapter
        }
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
                    adapter.setList( (it.content as List<Dog>))
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun userIsRegistered(): Boolean {
        return SessionRepository.token.isNotEmpty()
    }
}
