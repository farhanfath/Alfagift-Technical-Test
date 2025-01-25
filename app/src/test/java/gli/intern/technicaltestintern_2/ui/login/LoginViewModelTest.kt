package gli.intern.technicaltestintern_2.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import gli.intern.technicaltestintern_2.utils.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName

class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        viewModel = LoginViewModel()
    }

    @Test
    @DisplayName("Login with correct credentials should return true")
    fun loginWithCorrectCredentials() {
        viewModel.login("alfagift-admin", "asdf")

        val result = viewModel.loginResult.getOrAwaitValue()
        assertTrue(result)
    }

    @Test
    @DisplayName("Login with wrong username should return false")
    fun loginWithWrongUsername() {
        viewModel.login("wrong-username", "asdf")

        val result = viewModel.loginResult.getOrAwaitValue()
        assertFalse(result)
    }

    @Test
    @DisplayName("Login with wrong password should return false")
    fun loginWithWrongPassword() {
        viewModel.login("alfagift-admin", "wrong-password")

        val result = viewModel.loginResult.getOrAwaitValue()
        assertFalse(result)
    }

    @Test
    @DisplayName("Login with empty username should return false")
    fun loginWithEmptyUsername() {
        viewModel.login("", "asdf")

        val result = viewModel.loginResult.getOrAwaitValue()
        assertFalse(result)
    }

    @Test
    @DisplayName("Login with empty password should return false")
    fun loginWithEmptyPassword() {
        viewModel.login("alfagift-admin", "")

        val result = viewModel.loginResult.getOrAwaitValue()
        assertFalse(result)
    }

    @Test
    @DisplayName("Login with empty credentials should return false")
    fun loginWithEmptyCredentials() {
        viewModel.login("", "")

        val result = viewModel.loginResult.getOrAwaitValue()
        assertFalse(result)
    }
}