package io.github.castrors.iddog.presentation.dogslist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.github.castrors.iddog.R
import io.github.castrors.iddog.presentation.base.ContentState
import io.github.castrors.iddog.presentation.base.UIState
import io.github.castrors.iddog.presentation.dogdetail.DogDetailActivity
import io.github.castrors.iddog.presentation.model.Dog
import io.github.castrors.iddog.presentation.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        dogsViewModel.fetchDogs(menuItem.title.toString().toLowerCase())
        return true
    }

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
        observeDogs()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sign_out -> {
                dogsViewModel.signOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupView() {
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@MainActivity.adapter
        }
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onResume() {
        super.onResume()

        if(isListEmpty()){
            onNavigationItemSelected(bottomNavigation.menu.findItem(bottomNavigation.selectedItemId))
        }
    }

    private fun isListEmpty() = adapter.itemCount == 0

    private fun observeDogs() {
        dogsViewModel.shouldRequestDogs().observe(this, Observer<Boolean>{isAuthenticated ->
            if(!isAuthenticated){
                startActivity(Intent(this, SignUpActivity::class.java))
            }
        })

        dogsViewModel.dogsData.observe(this, Observer<UIState> {
            when (it) {
                is ContentState -> {
                    adapter.setList( (it.content as List<Dog>))
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }
}
