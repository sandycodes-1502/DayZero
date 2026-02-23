package com.sandycodes.dayzero.ui.Auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.sandycodes.dayzero.Firebase.AuthRepository
import com.sandycodes.dayzero.Firebase.AuthState
import com.sandycodes.dayzero.Firebase.AuthViewModel
import com.sandycodes.dayzero.R
import com.sandycodes.dayzero.databinding.FragmentSignupBinding
import com.sandycodes.dayzero.ui.Home.HomeFragment

class SignupFragment : Fragment() {
    lateinit var binding: FragmentSignupBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        binding = FragmentSignupBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = AuthViewModel(AuthRepository())

        val emailInput = binding.email
        val passwordInput = binding.password
        val signupButton = binding.signup
        val googleLogin = binding.googlebtn

        signupButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty()) {
                binding.email.error = "Email required"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.password.error = "Password required"
                return@setOnClickListener
            }

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.signup(email, password)
            }

        }

        googleLogin.setOnClickListener {
            Toast.makeText(requireContext(), "Signing In...", Toast.LENGTH_SHORT).show()
            viewModel.signInWithGoogle(requireContext())
        }

            viewModel.authState.observe(viewLifecycleOwner) {

                when(it) {
                    is AuthState.Loading -> {
                        binding.progressbar.isVisible = true
                    }

                    is AuthState.Success -> {
                        binding.progressbar.isVisible = false
                        Toast.makeText(requireContext(), "Success!!!", Toast.LENGTH_SHORT).show()
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, HomeFragment())
                            .commit()
                    }

                    is AuthState.Error -> {
                        binding.progressbar.isVisible = false
                        Toast.makeText(requireContext(), "Signup Failed: ${it.message}", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        binding.progressbar.isVisible = false
                    }
                }

            }

        }
    }
