package gli.intern.technicaltestintern_2.presentation.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import gli.intern.technicaltestintern_2.utils.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue

class LoginViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        viewModel = LoginViewModel()
    }

    @Test
    fun `Login with correct credentials should return true`() {
        viewModel.login("alfagift-admin", "asdf")

        val result = viewModel.loginResult.getOrAwaitValue()
        assertTrue(result)
    }

    @Test
    fun `login with wrong username should return false`() {
        viewModel.login("wrong-username", "asdf")

        val result = viewModel.loginResult.getOrAwaitValue()
        assertFalse(result)
    }

    @Test
    fun `login with wrong password should return false`() {
        viewModel.login("alfagift-admin", "wrong-password")

        val result = viewModel.loginResult.getOrAwaitValue()
        assertFalse(result)
    }

    @Test
    fun `Login with empty username should return false`() {
        viewModel.login("", "asdf")

        val result = viewModel.loginResult.getOrAwaitValue()
        assertFalse(result)
    }

    @Test
    fun `Login with empty password should return false`() {
        viewModel.login("alfagift-admin", "")

        val result = viewModel.loginResult.getOrAwaitValue()
        assertFalse(result)
    }
}