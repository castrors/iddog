package io.github.castrors.iddog.presentation.dogslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.castrors.iddog.domain.base.SuspendedInteractor
import io.github.castrors.iddog.presentation.base.CoroutineAwareViewModel
import io.github.castrors.iddog.presentation.base.CoroutinesBuilderProvider
import io.github.castrors.iddog.presentation.base.LoadingState
import io.github.castrors.iddog.presentation.base.UIState
import kotlin.coroutines.CoroutineContext

class DogsViewModel(
    private val getDogsUseCase: SuspendedInteractor<String, UIState>,
    private val coroutinesBuilderProvider: CoroutinesBuilderProvider,
    coroutineContext: CoroutineContext
) : CoroutineAwareViewModel(coroutineContext) {

    private val dogsData: MutableLiveData<UIState> = MutableLiveData()

    fun fetchDogs(category: String): LiveData<UIState> {
        dogsData.postValue(LoadingState)
        coroutinesBuilderProvider.launch(main){
            dogsData.postValue(getDogsUseCase.run(category))
        }

        return dogsData
    }

}