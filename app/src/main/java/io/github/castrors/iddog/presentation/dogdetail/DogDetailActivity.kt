package io.github.castrors.iddog.presentation.dogdetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.squareup.picasso.Picasso
import io.github.castrors.iddog.R
import io.github.castrors.iddog.presentation.model.Dog
import kotlinx.android.synthetic.main.activity_dog_detail.*

class DogDetailActivity : AppCompatActivity() {
    private val hideHandler = Handler()
    private val hideRunnable = Runnable { supportActionBar?.hide() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_detail)

        val dog: Dog = intent.extras?.get(DOG_EXTRA) as Dog? ?: Dog("")
        Picasso.get().load(dog.url).into(fullscreenImageView)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        hideHandler.removeCallbacks(hideRunnable)
        hideHandler.postDelayed(hideRunnable, 100L)
    }

    companion object {
        private const val DOG_EXTRA = "DOG_EXTRA"

        fun navigateToDogDetail(context: Context, dog: Dog) {
            val intent = Intent(context, DogDetailActivity::class.java)
            intent.putExtra(DOG_EXTRA, dog)
            context.startActivity(intent)
        }
    }
}
