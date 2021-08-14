package com.dnieln7.fake17.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dnieln7.fake17.databinding.AuthMainBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: AuthMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}