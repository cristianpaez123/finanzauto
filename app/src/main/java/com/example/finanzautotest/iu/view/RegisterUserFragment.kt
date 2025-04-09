package com.example.myapplication.iu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.example.finanzautotest.databinding.FragmentRegisterUserBinding
import com.example.myapplication.iu.model.UserUI
import com.example.myapplication.iu.viewModel.RegisterUserViewModel
import com.example.myapplication.utils.InputValidator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


const val ERROR = "ERROR"

@AndroidEntryPoint
class RegisterUserFragment : Fragment() {

    private lateinit var binding: FragmentRegisterUserBinding
    private val viewModel: RegisterUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.registerUserStateUserState().observe(viewLifecycleOwner) { state ->
            when (state) {
                is RegisterUserViewModel.RegisterUserState.DataLoaded -> {
                    var user = UserUI(
                        id = state.userResponseResult.id,
                        title = state.userResponseResult.title,
                        firstName = state.userResponseResult.firstName,
                        lastName = state.userResponseResult.lastName,
                        picture = state.userResponseResult.picture
                    )

                    val resultBundle = Bundle()
                    resultBundle.putParcelable("user", user)
                    setFragmentResult("userKey", resultBundle)

                    parentFragmentManager.popBackStack()
                }

                is RegisterUserViewModel.RegisterUserState.Error -> {
                    Toast.makeText(requireContext(), ERROR, Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    private fun initListener() {
        binding.apply {
            btnRegister.setOnClickListener {
                val fieldsValid = InputValidator.validateRequiredFields(
                    etTitle,
                    etFirstName,
                    etLastName,
                    etGender,
                    etEmail,
                    etDateOfBirth,
                    etPhone
                )
                if (!InputValidator.isValidEmail(etEmail.text.toString())) {
                    etEmail.error = "Correo no válido"
                    return@setOnClickListener
                }

                if (fieldsValid) {
                    val title = etTitle.text.toString().trim()
                    val firstName = etFirstName.text.toString().trim()
                    val lastName = etLastName.text.toString().trim()
                    val gender = etGender.text.toString().trim()
                    val email = etEmail.text.toString().trim()
                    val dateOfBirth = etDateOfBirth.text.toString().trim()
                    val phone = etPhone.text.toString().trim()

                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.createUser(
                            title = title,
                            firstName = firstName,
                            lastName = lastName,
                            gender = gender,
                            email = email,
                            dateOfBirth = dateOfBirth,
                            phone = phone
                        )
                    }
                }
            }
        }
    }
}