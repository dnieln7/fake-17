package com.dnieln7.fake17.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dnieln7.fake17.databinding.LoginFragmentBinding
import com.dnieln7.fake17.utils.NavigationUtils.navigate

class LoginFragment : Fragment() {
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)

        with(binding) {
            toSignup.setOnClickListener {
                LoginFragmentDirections.actionLoginFragmentToSignupFragment().navigate(it)
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}