package gli.intern.technicaltestintern_2.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import gli.intern.technicaltestintern_2.R
import gli.intern.technicaltestintern_2.databinding.ActivityLoginBinding
import gli.intern.technicaltestintern_2.ui.student.StudentListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private val loginViewModel : LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.emailEt.text.toString()
            val password = binding.passEt.text.toString()
            loginViewModel.login(username, password)
        }

        loginViewModel.loginResult.observe(this) { loginSuccess ->
            if (loginSuccess) {
                startActivity(Intent(this, StudentListActivity::class.java))
            } else {
                Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show()
            }
        }
    }
}