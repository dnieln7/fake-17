package com.dnieln7.fake17.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dnieln7.fake17.Fake17Application
import com.dnieln7.fake17.R
import com.dnieln7.fake17.databinding.LoginFragmentBinding
import com.dnieln7.fake17.ui.auth.AuthState
import com.dnieln7.fake17.ui.home.HomeActivity
import com.dnieln7.fake17.utils.NavigationUtils.navigate
import com.dnieln7.fake17.utils.Printer
import com.dnieln7.fake17.utils.TextValidation.isEmail

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
            login.setOnClickListener { validateForm() }
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
                AuthState.Success -> {
                    Printer.toast(requireContext(), getString(R.string.welcome))
                    binding.progress.visibility = View.GONE
                    binding.login.isEnabled = true

                    requireActivity().startActivity(
                        Intent(requireContext(), HomeActivity::class.java)
                    )
                }
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

    private fun validateForm() {
        with(binding) {
            emailContainer.error = if (email.text.toString().isEmpty())
                getString(R.string.field_required) else if (!email.text.toString().isEmail())
                getString(R.string.invalid_email) else null

            passwordContainer.error = if (password.text.toString().isEmpty())
                getString(R.string.field_required) else null

            if (emailContainer.error == null && passwordContainer.error == null) {
                viewModel.login(email.text.toString(), password.text.toString())
            }
        }
    }
}