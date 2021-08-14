package com.dnieln7.fake17.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dnieln7.fake17.Fake17Application
import com.dnieln7.fake17.data.source.user.UserInMemoryAuthSource
import com.dnieln7.fake17.data.source.user.UserInMemoryDataSource
import com.dnieln7.fake17.databinding.LoginFragmentBinding
import com.dnieln7.fake17.ui.auth.AuthState
import com.dnieln7.fake17.utils.NavigationUtils.navigate
import com.dnieln7.fake17.utils.Printer

class LoginFragment : Fragment() {
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)

        val serviceLocator = (requireActivity().application as Fake17Application).serviceLocator

        viewModel = ViewModelProvider(
            this,
            LoginViewModel.Factory(
                serviceLocator.userAuthSource,
                serviceLocator.userDataSource
            )
        ).get(LoginViewModel::class.java)

        with(binding) {
            login.setOnClickListener {
                viewModel.login(
                    email.text.toString(),
                    password.text.toString()
                )
            }
            toSignup.setOnClickListener {
                LoginFragmentDirections.actionLoginFragmentToSignupFragment().navigate(it)
                viewModel.clearState()
            }
        }

        viewModel.state.observe(viewLifecycleOwner, {
            when (it) {
                is AuthState.Failure -> {
                    Printer.snackBar(binding.root, it.error)
                    binding.progress.visibility = View.GONE
                    binding.login.isEnabled = true
                }
                AuthState.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.login.isEnabled = false
                }
                AuthState.Success -> TODO()
                AuthState.Nothing -> {
                    binding.email.text?.clear()
                    binding.password.text?.clear()
                }
            }
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}