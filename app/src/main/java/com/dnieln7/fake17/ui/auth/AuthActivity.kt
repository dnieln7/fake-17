package com.dnieln7.fake17.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.dnieln7.fake17.R
import com.dnieln7.fake17.databinding.AuthMainBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: AuthMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val host = supportFragmentManager.findFragmentById(R.id.auth_nav_host) as NavHostFragment
        navController = host.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}