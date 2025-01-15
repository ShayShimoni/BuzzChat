package com.msapps.buzzchat.auth.ui.login

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
import com.msapps.buzzchat.BuildConfig
import com.msapps.buzzchat.R
import com.msapps.buzzchat.auth.models.requests.SendOtpRequest
import com.msapps.buzzchat.databinding.FragmentLoginBinding
import com.msapps.buzzchat.extensions.showDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment: Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.root.viewTreeObserver.removeOnGlobalLayoutListener(null)
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.countryCodePicker.resetToDefaultCountry()
        setupListeners()
        addObservers()

        if (BuildConfig.DEBUG) {
            binding.tfPhoneNumber.editText?.setText("0511111111")
        }
    }

    private fun addObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sendOtpStatus.collectLatest {
                    withContext(Dispatchers.Main) {
                        if (findNavController().currentDestination?.id == R.id.LoginFragment) {
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToOtpFragment(it.sessionInfo))
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sharedFlow.collectLatest {
                    withContext(Dispatchers.Main) {
                        showErrorDialog(it)
                    }
                }
            }
        }
    }

    private fun setupListeners() {
        binding.btnSend.setOnClickListener {
            val phoneNumber = binding.tfPhoneNumber.editText?.text.toString().trim()
            if (viewModel.verifyPhoneNumber(phoneNumber)) {
                val fullPhoneNumber = "+${binding.countryCodePicker.selectedCountryCode}${viewModel.checkForNumberPrefix(phoneNumber)}"
                viewModel.sendOtp(SendOtpRequest(fullPhoneNumber))
            } else {
                if (phoneNumber.isNotEmpty()) {
                    binding.tfPhoneNumber.error = getString(R.string.error_phone_number)
                } else {
                    binding.tfPhoneNumber.error = getString(R.string.error_empty_phone_number)
                }
            }
        }

        binding.tfPhoneNumber.editText?.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    binding.btnSend.callOnClick()
                    true
                }
                else -> false
            }
        }

        binding.tfPhoneNumber.editText?.doOnTextChanged { _, _, _, _ ->
            binding.tfPhoneNumber.error = null
        }

        // Scroll down when keyboard opens.
        binding.root.viewTreeObserver.addOnGlobalLayoutListener {
            if (_binding?.tfPhoneNumber?.editText?.isFocused == true) {
                _binding?.root?.arrowScroll(View.FOCUS_DOWN)
            }
        }
    }

    private fun showErrorDialog(t: Throwable) {
        showDialog(
            title = getString(R.string.error),
            message = getString(R.string.error_msg, t.message),
            positiveBtnText = getString(R.string.ok)
        )
    }
}