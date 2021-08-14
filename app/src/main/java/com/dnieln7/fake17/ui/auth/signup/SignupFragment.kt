package com.dnieln7.fake17.ui.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dnieln7.fake17.databinding.SignupFragmentBinding

class SignupFragment : Fragment() {
    private var _binding: SignupFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = SignupFragmentBinding.inflate(inflater, container, false)

        with(binding) {
            toLogin.setOnClickListener { findNavController().popBackStack() }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}