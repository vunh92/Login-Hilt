package com.vunh.login_hilt.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.vunh.login_hilt.BaseApp
import com.vunh.login_hilt.databinding.ActivityLoginBinding
import com.vunh.login_hilt.utils.AppUtils.afterTextChanged
import com.vunh.login_hilt.utils.AppUtils.isEnable
import com.vunh.login_hilt.utils.AppUtils.validateEmail
import com.vunh.login_hilt.viewModel.LoginViewModel
import com.vunh.login_hilt.viewModel.LoginViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {
    lateinit var viewModel: LoginViewModel
    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory
    private lateinit var binding: ActivityLoginBinding
//    private lateinit var loginRepositoryImpl: LoginRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
//        (application as BaseApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        loginRepositoryImpl = LoginRepositoryImpl(loginService)
//        viewModelFactory = LoginViewModelFactory(loginRepositoryImpl)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        initializeView()
        initializeViewModel()
    }

    private fun initializeView(){
        binding.btnLogin.setOnClickListener {
            if(viewModel.checkValidate(binding.edtEmail.text.toString(),binding.edtPassword.text.toString())){
                viewModel.getUser(username = binding.edtEmail.text.toString(), password = binding.edtPassword.text.toString())
            }

        }
        binding.edtEmail.afterTextChanged{
            binding.btnLogin.isEnable(this,it.validateEmail())
        }
    }

    private fun initializeViewModel(){
        viewModel.userResult.observe(this, Observer {
            binding.lblEmailAnswer.text = it.username
            Snackbar.make(binding.root, "Success", Snackbar.LENGTH_LONG).show()
            startActivity(newTaskIntent(this@LoginActivity))
            finish()
        })
        viewModel.showLoading.observe(this, Observer {
            if (it) binding.progressLoading.visibility =
                View.VISIBLE else binding.progressLoading.visibility = View.GONE
        })
        viewModel.showError.observe(this, Observer {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        })

    }

    companion object {
        fun newTaskIntent(context: Context): Intent {
            //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return Intent(context, MainActivity::class.java)
        }
    }

}