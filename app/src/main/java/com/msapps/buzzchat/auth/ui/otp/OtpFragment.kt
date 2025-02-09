package com.msapps.buzzchat.auth.ui.otp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.msapps.buzzchat.BuildConfig
import com.msapps.buzzchat.R
import com.msapps.buzzchat.auth.models.requests.VerifyOtpRequest
import com.msapps.buzzchat.databinding.FragmentOtpBinding
import com.msapps.buzzchat.extensions.safeNavigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class OtpFragment: Fragment() {

    private var _binding: FragmentOtpBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OtpFragmentViewModel by viewModel()
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

        setupListeners()
        addObservers()

        if (BuildConfig.DEBUG) {
            binding.tiOtpCode.editText?.setText("223311")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.root.viewTreeObserver.removeOnGlobalLayoutListener(null)
        _binding = null
    }

    private fun addObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.verifyOtpStatus.collectLatest {
                    withContext(Dispatchers.Main) {
                        viewModel.saveCredentials(
                            idToken = it.idToken,
                            refreshToken = it.refreshToken,
                            expiredIn = it.expiresIn,
                            phoneNumber = it.phoneNumber,
                            isNewUser = it.isNewUser
                        )

                        navigateToNextScreen(true)


                    }
                }
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventsSharedFlow.collectLatest {
                    withContext(Dispatchers.Main) {
                        navigateToNextScreen(false)
                    }
                }
            }
        }
    }

    private fun setupListeners() {
        binding.btnSend.setOnClickListener {
            val otpCode = binding.tiOtpCode.editText?.text.toString().trim()
            viewModel.verifyOtp(VerifyOtpRequest(args.sessionInfo, otpCode))
        }

        binding.tiOtpCode.editText?.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    binding.btnSend.callOnClick()
                    true
                }

                else -> false
            }
        }

        binding.tiOtpCode.editText?.doOnTextChanged { _, _, _, _ ->
            binding.tiOtpCode.error = null
        }

        // Scroll down when keyboard opens.
        binding.root.viewTreeObserver.addOnGlobalLayoutListener {
            if (_binding?.tiOtpCode?.editText?.isFocused == true) {
                _binding?.root?.arrowScroll(View.FOCUS_DOWN)
            }
        }
    }

    private fun navigateToNextScreen(isSuccess: Boolean) {
        if (isSuccess) {
          safeNavigation(R.id.OtpFragment) {
              findNavController().navigate(R.id.action_OtpFragment_to_OtpSuccessFragment)
          }
        } else {
            safeNavigation(R.id.OtpFragment) {
                findNavController().navigate(R.id.action_OtpFragment_to_OtpFailureFragment)
            }
        }
    }
}