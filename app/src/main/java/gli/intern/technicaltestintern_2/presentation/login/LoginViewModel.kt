package gli.intern.technicaltestintern_2.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun login(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            _loginResult.value = false
            return
        }

        _loginResult.value = username == "alfagift-admin" && password == "asdf"
    }
}