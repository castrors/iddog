package io.github.castrors.iddog.presentation.dogslist

import android.content.Context
import android.view.View
import io.github.castrors.iddog.presentation.base.BaseRecyclerAdapter
import io.github.castrors.iddog.presentation.base.ViewBinder

open class DogsListAdapter<MODEL, VIEW>(creator: (context: Context) -> VIEW) :
    BaseRecyclerAdapter<MODEL>({ context, _ -> creator.invoke(context) })
        where VIEW : View, VIEW : ViewBinder<MODEL> {

}