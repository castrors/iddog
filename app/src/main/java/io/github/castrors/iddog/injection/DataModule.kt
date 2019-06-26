package io.github.castrors.iddog.injection

import io.github.castrors.iddog.data.DogAPI
import io.github.castrors.iddog.data.DogsGateway
import io.github.castrors.iddog.data.DogsRepository
import io.github.castrors.iddog.data.buildClient
import io.github.castrors.iddog.domain.GetDogsUseCase
import io.github.castrors.iddog.domain.SignUpUseCase
import io.github.castrors.iddog.domain.base.SuspendedInteractor
import io.github.castrors.iddog.presentation.DogsViewModel
import io.github.castrors.iddog.presentation.SignUpViewModel
import io.github.castrors.iddog.presentation.base.CoroutinesBuilderProvider
import io.github.castrors.iddog.presentation.base.DefaultBuilderProvider
import io.github.castrors.iddog.presentation.base.UIState
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext


object DataModule{

    private const val SIGN_UP_USE_CASE = "SIGN_UP_USE_CASE"
    private const val GET_DOGS_USE_CASE = "GET_DOGS_USE_CASE"

    val base = module {
        factory<CoroutinesBuilderProvider> { DefaultBuilderProvider() }
        factory<CoroutineContext> { Dispatchers.Main }
    }

    val dataModule = module{

        factory {
            buildClient()
        }

        single<DogAPI> { Retrofit.Builder()
            .baseUrl("https://api-iddog.idwall.co")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogAPI::class.java)
        }

        factory<DogsGateway> { DogsRepository(get()) }

        factory<SuspendedInteractor<String, UIState>>(named(SIGN_UP_USE_CASE)){
            SignUpUseCase(get())
        }

        factory<SuspendedInteractor<String, UIState>>(named(GET_DOGS_USE_CASE)){
            GetDogsUseCase(get())
        }

        viewModel {
            SignUpViewModel(get(named(SIGN_UP_USE_CASE)), get(), get())
        }

        viewModel {
            DogsViewModel(get(named(GET_DOGS_USE_CASE)), get(), get())
        }

    }
}