package io.github.castrors.iddog.presentation.signup

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.google.android.material.textfield.TextInputLayout
import io.github.castrors.iddog.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignUpActivityTest{

    @Rule
    @JvmField
    val rule = ActivityTestRule<SignUpActivity>(SignUpActivity::class.java, true, true)

    @Test
    fun whenOpen_shouldStartWithEmptyEmail() {
        onView(withId(R.id.editEmail)).check(matches(isDisplayed()))
        onView(withId(R.id.editEmail)).check(matches(withText("")))
    }

    @Test
    fun whenSignUp_withInvalidEmail_shouldShowInvalidEmailMessage() {
        onView(withId(R.id.editEmail)).perform(typeText("email"))

        onView(withId(R.id.buttonSubmit)).perform(click())

        onView(withText(R.string.invalid_email)).check(matches(isDisplayed()))
        onView(withId(R.id.inputLayout)).check(matches(isTextInputLayoutWithErrorEnabled(true)))
    }

    @Test
    fun whenSignUp_withValidEmail_shouldNotShowInvalidEmailMessage() {
        onView(withId(R.id.editEmail)).perform(typeText("email@email.com"))

        onView(withId(R.id.buttonSubmit)).perform(click())

        onView(withId(R.id.inputLayout)).check(matches(isTextInputLayoutWithErrorEnabled(false)))
    }


    private fun isTextInputLayoutWithErrorEnabled(isEnabled: Boolean): Matcher<View> = object : TypeSafeMatcher<View>() {

        override fun describeTo(description: Description?) { }
        override fun matchesSafely(item: View?): Boolean {
            if (item !is TextInputLayout) return false
            return item.isErrorEnabled == isEnabled
        }
    }
}