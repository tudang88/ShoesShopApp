package com.udacity.shoestore.screens.welcome

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.models.LoginViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {

    private val shareLoginViewModel: LoginViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_welcome, container, false)
        Log.i("WelcomeFragment", "userName:${shareLoginViewModel.userName}")
        binding.userText.text = shareLoginViewModel.userName
        binding.openInstructionButton.setOnClickListener{view :View ->
            view.findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment())
        }
        return binding.root
    }
}