package io.github.castrors.iddog.presentation.dogslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.castrors.iddog.domain.IsUserAuthenticatedUseCase
import io.github.castrors.iddog.domain.base.Interactor
import io.github.castrors.iddog.domain.base.None
import io.github.castrors.iddog.domain.base.SuspendedInteractor
import io.github.castrors.iddog.presentation.base.CoroutineAwareViewModel
import io.github.castrors.iddog.presentation.base.CoroutinesBuilderProvider
import io.github.castrors.iddog.presentation.base.LoadingState
import io.github.castrors.iddog.presentation.base.UIState
import kotlin.coroutines.CoroutineContext

class DogsViewModel(
    private val getDogsUseCase: SuspendedInteractor<String, UIState>,
    private val isUserAuthenticatedUseCase: Interactor<None, Boolean>,
    private val signOutUseCase: Interactor<None, Boolean>,
    private val coroutinesBuilderProvider: CoroutinesBuilderProvider,
    coroutineContext: CoroutineContext
) : CoroutineAwareViewModel(coroutineContext) {

    val dogsData: MutableLiveData<UIState> = MutableLiveData()
    private val shouldRequestDogs: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchDogs(category: String = "husky"){
        dogsData.postValue(LoadingState)
        coroutinesBuilderProvider.launch(main){
            dogsData.postValue(getDogsUseCase.run(category))
        }
    }

    fun shouldRequestDogs(): LiveData<Boolean>{
        shouldRequestDogs.postValue(isUserAuthenticatedUseCase.run(None))
        return shouldRequestDogs
    }

    fun signOut(){
        shouldRequestDogs.postValue(!signOutUseCase.run(None))
    }

}