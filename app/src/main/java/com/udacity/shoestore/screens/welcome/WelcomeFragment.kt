package com.udacity.shoestore.screens.welcome

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_welcome, container, false)
        val userNameArgs by navArgs<WelcomeFragmentArgs>()
        Log.i("WelcomeFragment", "userName:${userNameArgs.userName}")
        binding.userText.text = userNameArgs.userName
        binding.openInstructionButton.setOnClickListener{view :View ->
            view.findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment())
        }
        return binding.root
    }
}