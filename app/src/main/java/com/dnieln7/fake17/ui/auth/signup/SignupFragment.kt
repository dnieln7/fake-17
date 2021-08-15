package com.dnieln7.fake17.ui.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dnieln7.fake17.Fake17Application
import com.dnieln7.fake17.R
import com.dnieln7.fake17.databinding.SignupFragmentBinding
import com.dnieln7.fake17.domain.User
import com.dnieln7.fake17.ui.auth.AuthState
import com.dnieln7.fake17.utils.Printer
import com.dnieln7.fake17.utils.TextValidation.isEmail
import com.dnieln7.fake17.utils.TextValidation.isPassword

class SignupFragment : Fragment() {
    private var _binding: SignupFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = SignupFragmentBinding.inflate(inflater, container, false)

        val serviceLocator = (requireActivity().application as Fake17Application).serviceLocator

        viewModel = ViewModelProvider(
            this,
            SignupViewModel.Factory(serviceLocator.userAuthSource)
        ).get(SignupViewModel::class.java)

        with(binding) {
            signup.setOnClickListener {
                validateForm()
            }
            toLogin.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        viewModel.state.observe(viewLifecycleOwner, {
            when (it) {
                is AuthState.Failure -> {
                    Printer.snackBar(binding.root, it.error)
                    binding.progress.visibility = View.GONE
                    binding.signup.isEnabled = true
                }
                AuthState.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.signup.isEnabled = false
                }
                AuthState.Success -> {
                    Printer.toast(requireContext(), getString(R.string.signup_thanks))
                    findNavController().popBackStack()
                }
                AuthState.Nothing -> {
                    binding.name.text?.clear()
                    binding.lastName.text?.clear()
                    binding.email.text?.clear()
                    binding.password.text?.clear()
                    binding.passwordConfirm.text?.clear()
                }
            }
        })

        return binding.root
    }

    private fun validateForm() {
        with(binding) {
            nameContainer.error = if (name.text.toString().isEmpty())
                getString(R.string.field_required) else null

            lastNameContainer.error = if (lastName.text.toString().isEmpty())
                getString(R.string.field_required) else null

            emailContainer.error = if (email.text.toString().isEmpty())
                getString(R.string.field_required) else if (!email.text.toString().isEmail())
                getString(R.string.invalid_email) else null

            passwordContainer.error = if (password.text.toString().isEmpty())
                getString(R.string.field_required) else if (!password.text.toString().isPassword())
                getString(R.string.invalid_password) else null

            passwordConfirmContainer.error =
                if (password.text.toString() != passwordConfirm.text.toString())
                    getString(R.string.password_not_equal) else null

            if (
                nameContainer.error == null &&
                lastNameContainer.error == null &&
                emailContainer.error == null &&
                passwordContainer.error == null &&
                passwordConfirmContainer.error == null
            ) {
                viewModel.signUp(
                    User(
                        0,
                        name.text.toString(),
                        lastName.text.toString(),
                        email.text.toString(),
                        password.text.toString(),
                        emptyList<String>(),
                        "estudiante",
                    )
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}