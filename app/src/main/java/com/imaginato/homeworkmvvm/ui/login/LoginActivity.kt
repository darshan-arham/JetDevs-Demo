package com.imaginato.homeworkmvvm.ui.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.imaginato.homeworkmvvm.R
import com.imaginato.homeworkmvvm.databinding.ActivityLoginBinding
import com.imaginato.homeworkmvvm.ui.base.IApp
import com.imaginato.homeworkmvvm.ui.demo.MainActivity
import org.koin.core.component.KoinApiExtension


@KoinApiExtension
class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginActivityViewModel
    private lateinit var binding: ActivityLoginBinding

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*
        val retrofit = (application as IApp).getKoin().loadModules(listOf(netModules),false)
*/
        val repository = (application as IApp).demo2Repository

        viewModel = ViewModelProvider(
            this, LoginViewModelFactory(repository)
        ).get(LoginActivityViewModel::class.java)
        binding.loginBtn.setOnClickListener {
            hideKeyboard(this)
            if (isValid()) {
                binding.pbLoading.visibility = View.VISIBLE
                viewModel.getLoginResponse(
                    binding.edtUserName.text.toString(),
                    binding.edtPassword.text.toString(),
                )
            }
        }
        initObserve()
    }

    private fun isValid(): Boolean {
        binding.layUserName.isErrorEnabled = false
        binding.layPassword.isErrorEnabled = false
        if (binding.edtUserName.text.toString().isEmpty()) {
            binding.layUserName.isErrorEnabled = true
            binding.layUserName.error =
                getString(R.string.pleaseEnter) + " " + binding.layUserName.hint
            return false
        }
        if (binding.edtPassword.text.toString().isEmpty()) {
            binding.layPassword.isErrorEnabled = true
            binding.layPassword.error =
                getString(R.string.pleaseEnter) + " " + binding.layPassword.hint
            return false
        }
        return true
    }

    private fun initObserve() {
        viewModel.loginResponse.observe(this) {
            binding.pbLoading.visibility = View.GONE
            Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (activity.getCurrentFocus() != null) {
            if (imm != null) {
                imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
            }
        }
    }
}