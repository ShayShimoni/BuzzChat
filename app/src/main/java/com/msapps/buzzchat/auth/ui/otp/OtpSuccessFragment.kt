package com.msapps.buzzchat.auth.ui.otp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.msapps.buzzchat.MainActivity
import com.msapps.buzzchat.databinding.FragmentOtpSuccessBinding

class OtpSuccessFragment: Fragment() {

    private var _binding: FragmentOtpSuccessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtpSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            Intent(requireContext(), MainActivity::class.java).apply {
                startActivity(this)
                requireActivity().finish()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() { /*Do nothing*/}
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}