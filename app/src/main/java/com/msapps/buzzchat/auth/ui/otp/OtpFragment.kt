package com.msapps.buzzchat.auth.ui.otp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.msapps.buzzchat.databinding.FragmentOtpBinding

class OtpFragment: Fragment() {

    private var _binding: FragmentOtpBinding? = null
    private val binding get() = _binding!!
    private val args: OtpFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(this::class.simpleName, "SessionInfo: ${args.sessionInfo}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}