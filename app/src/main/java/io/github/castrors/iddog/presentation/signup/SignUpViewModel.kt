package io.github.castrors.iddog.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.castrors.iddog.domain.PersistTokenUseCase
import io.github.castrors.iddog.domain.base.Interactor
import io.github.castrors.iddog.domain.base.SuspendedInteractor
import io.github.castrors.iddog.presentation.base.CoroutineAwareViewModel
import io.github.castrors.iddog.presentation.base.CoroutinesBuilderProvider
import io.github.castrors.iddog.presentation.base.LoadingState
import io.github.castrors.iddog.presentation.base.UIState
import kotlin.coroutines.CoroutineContext

class SignUpViewModel(
    private val signUpUseCase: SuspendedInteractor<String, UIState>,
    private val persistTokenUseCase: Interactor<String, Boolean>,
    private val coroutinesBuilderProvider: CoroutinesBuilderProvider,
    coroutineContext: CoroutineContext
) : CoroutineAwareViewModel(coroutineContext) {

    private val signUpData: MutableLiveData<UIState> = MutableLiveData()

    fun signUp(email: String): LiveData<UIState>{
        signUpData.postValue(LoadingState)
        coroutinesBuilderProvider.launch(main){
            signUpData.postValue(signUpUseCase.run(email))
        }

        return signUpData
    }

    fun persistToken(token: String): Boolean{
        return persistTokenUseCase.run(token)
    }

}