package io.github.castrors.iddog.presentation.dogslist

import android.app.Application
import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.github.castrors.iddog.domain.base.SuspendedInteractor
import io.github.castrors.iddog.injection.DataModule
import io.github.castrors.iddog.presentation.base.ContentState
import io.github.castrors.iddog.presentation.base.UIState
import io.github.castrors.iddog.presentation.model.Dog
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock

@RunWith(AndroidJUnit4::class)
class MainActivityTest : KoinTest {

    private val suspendedInteractor: SuspendedInteractor<String, UIState> by inject(named("GET_DOGS_USE_CASE"))

    @Rule
    @JvmField
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java, true, false)

    @Before
    fun setup() {
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().context)
            modules(listOf(DataModule.base, DataModule.dataModule))
        }
        declareMock<SuspendedInteractor<String, UIState>>(named("GET_DOGS_USE_CASE"))
        runBlocking {
            whenever(suspendedInteractor.run(any())).thenReturn(ContentState(listOf(Dog("https://i.ytimg.com/vi/cQtLsRyU05E/maxresdefault.jpg"))))
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun wtf(){
        rule.launchActivity(Intent())
        Thread.sleep(20000)
    }
}