package io.github.castrors.iddog.injection

import io.github.castrors.iddog.data.*
import io.github.castrors.iddog.domain.*
import io.github.castrors.iddog.domain.base.Interactor
import io.github.castrors.iddog.domain.base.None
import io.github.castrors.iddog.domain.base.SuspendedInteractor
import io.github.castrors.iddog.presentation.dogslist.DogsViewModel
import io.github.castrors.iddog.presentation.signup.SignUpViewModel
import io.github.castrors.iddog.presentation.base.CoroutinesBuilderProvider
import io.github.castrors.iddog.presentation.base.DefaultBuilderProvider
import io.github.castrors.iddog.presentation.base.UIState
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext


object DataModule {

    private const val SIGN_UP_USE_CASE = "SIGN_UP_USE_CASE"
    private const val GET_DOGS_USE_CASE = "GET_DOGS_USE_CASE"
    private const val IS_USER_AUTHENTICATED_USE_CASE = "IS_USER_AUTHENTICATED_USE_CASE"
    private const val PERSIST_TOKEN_USE_CASE = "PERSIST_TOKEN_USE_CASE"
    private const val PROVIDE_TOKEN_USE_CASE = "PROVIDE_TOKEN_USE_CASE"


    val base = module {
        factory<CoroutinesBuilderProvider> { DefaultBuilderProvider() }
        factory<CoroutineContext> { Dispatchers.Main }
    }

    val dataModule = module {

        single<SessionGateway> { SessionRepository() }

        factory {
            buildClient(androidApplication(), get())
        }

        single<DogAPI> {
            Retrofit.Builder()
                .baseUrl("https://api-iddog.idwall.co")
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DogAPI::class.java)
        }

        factory<DogsGateway> { DogsRepository(get()) }

        factory<SuspendedInteractor<String, UIState>>(named(SIGN_UP_USE_CASE)) {
            SignUpUseCase(get())
        }

        factory<SuspendedInteractor<String, UIState>>(named(GET_DOGS_USE_CASE)) {
            GetDogsUseCase(get())
        }

        factory<Interactor<None, Boolean>>(named(IS_USER_AUTHENTICATED_USE_CASE)) {
            IsUserAuthenticatedUseCase(get())
        }

        factory<Interactor<String, Boolean>>(named(PERSIST_TOKEN_USE_CASE)) {
            PersistTokenUseCase(get())
        }

        viewModel {
            SignUpViewModel(get(named(SIGN_UP_USE_CASE)), get(named(PERSIST_TOKEN_USE_CASE)), get(), get())
        }

        viewModel {
            DogsViewModel(get(named(GET_DOGS_USE_CASE)), get(named(IS_USER_AUTHENTICATED_USE_CASE)), get(), get())
        }

    }
}