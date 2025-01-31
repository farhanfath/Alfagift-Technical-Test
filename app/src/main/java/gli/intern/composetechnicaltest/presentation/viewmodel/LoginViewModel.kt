package gli.intern.composetechnicaltest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    fun login(username: String, password: String) {
        when {
            username.isEmpty() || password.isEmpty() -> {
                _loginResult.value = false
                _errorMessage.value = "Please fill all fields"
            }
            username == "alfagift-admin" && password == "asdf" -> {
                _loginResult.value = true
                _errorMessage.value = null
            }
            else -> {
                _loginResult.value = false
                _errorMessage.value = "Invalid username or password"
            }
        }
    }
}