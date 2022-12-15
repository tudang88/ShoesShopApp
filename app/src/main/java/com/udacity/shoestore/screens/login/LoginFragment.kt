package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.models.LoginViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.lifecycleOwner = this
        // binding viewModel instance with variable in layout
        binding.loginViewModel = viewModel
        viewModel.authFinishedEvent.observe(viewLifecycleOwner, Observer { result ->
            if (result) {
                loginSuccessful()
                viewModel.clearLoginEvent()
            }
        })
        viewModel.loginFailedEvent.observe(viewLifecycleOwner, Observer { result ->
            if (result) {
                loginFailed()
                viewModel.clearLoginEvent()
            }
        })
        return binding.root
    }

    /**
     * update view in case login successful
     * transition to Welcome
     */
    private fun loginSuccessful() {
        Log.i("LoginFragment", "loginSuccessful")
        val action =
            LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(viewModel.userName)
        findNavController().navigate(action)
    }

    /**
     * Notify login failed to user
     */
    private fun loginFailed() {
        Toast.makeText(context, "Login Failed! User or Password empty", Toast.LENGTH_SHORT).show()
    }
}