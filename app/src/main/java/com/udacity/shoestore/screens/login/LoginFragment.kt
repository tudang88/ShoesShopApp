package com.udacity.shoestore.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.authFinishedEvent.observe(viewLifecycleOwner, Observer { result ->
            if (result) {
                loginSuccessful()
            } else {
                loginFailed()
            }
        })
        // binding OnClick
        binding.loginButton.setOnClickListener {
            viewModel.authentication(
                binding.emailEditText.text.toString(),
                binding.pwEditText.text.toString()
            )
        }
        binding.registerButton.setOnClickListener {
            viewModel.authentication(
                binding.emailEditText.text.toString(),
                binding.pwEditText.text.toString()
            )
        }
        return binding.root
    }

    /**
     * update view in case login successful
     * transition to Welcome
     */
    private fun loginSuccessful() {
        val action =
            LoginFragmentDirections.actionLoginFragmentToWelcomeFragment2(viewModel.userName.value?:"Anonymous")
        findNavController().navigate(action)
    }

    /**
     * Notify login failed to user
     */
    private fun loginFailed() {
        Toast.makeText(context, "Login Failed! User or Password empty", Toast.LENGTH_SHORT).show()
    }
}