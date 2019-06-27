package io.github.castrors.iddog.presentation.dogslist

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.squareup.picasso.Picasso
import io.github.castrors.iddog.R
import io.github.castrors.iddog.presentation.base.ViewBinder
import io.github.castrors.iddog.presentation.model.Dog
import kotlinx.android.synthetic.main.card_dog.view.*

class ItemDogView : CardView, ViewBinder<Dog> {
    init {
        inflate(context, R.layout.card_dog, this)
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            resources.getDimension(R.dimen.card_height_size).toInt()
        )
        useCompatPadding = true
        radius = context.resources.getDimension(R.dimen.card_radius_size)
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun bind(model: Dog) {
        Picasso.get().load(model.url).into(dogImage)
    }
}