package com.vunh.login_hilt.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.vunh.login_hilt.BaseApp
import com.vunh.login_hilt.databinding.ActivityMainBinding
import com.vunh.login_hilt.viewModel.MainViewModel
import com.vunh.login_hilt.viewModel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val userManager = (application as BaseApp).appComponent.userManager()
        super.onCreate(savedInstanceState)
        if (!userManager.isUserLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }else {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            (application as BaseApp).appComponent.inject(this)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
            initializeView()
            initializeViewModel()
        }

    }

    private fun initializeView() {
        binding.logoutBtn.setOnClickListener {
            viewModel.logout()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun initializeViewModel() {}

}