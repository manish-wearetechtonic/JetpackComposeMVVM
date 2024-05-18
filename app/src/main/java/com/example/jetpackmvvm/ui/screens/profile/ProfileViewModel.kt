package com.example.jetpackmvvm.ui.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackmvvm.data.preference.PreferenceHelper
import com.example.jetpackmvvm.data.repository.auth.AuthRepository
import com.example.jetpackmvvm.data.repository.profile.ProfileRepository
import com.example.jetpackmvvm.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val preferenceHelper: PreferenceHelper
) : ViewModel() {

    private val _profileState = MutableStateFlow(ProfileState(null))
    val profileState = _profileState.asStateFlow()

    fun getUserProfile() {
        viewModelScope.launch {
            val userId = preferenceHelper.getLoggedInUserId()
            if (userId != null) {
                profileRepository.getUserProfile(userId.toInt()).collectLatest { userProfile ->
                    when (userProfile) {
                        is DataState.Error -> {
                            // Handle error
                        }
                        DataState.Loading -> {
                            // Handle loading state
                        }
                        is DataState.Success -> {
                            _profileState.value = ProfileState(userProfile.data)
                        }
                    }
                }
            } else {
                // Handle case where userId is null
                Log.d("ProfileViewModel", "User ID is null")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                profileRepository.logout().collectLatest {
                    when (it) {
                        is DataState.Error -> {
                            Log.d("error@@@", it.exception.toString())
                        }

                        DataState.Loading -> {

                        }

                        is DataState.Success -> {
                            it.data.let { logoutRes ->
                                Log.d("Success Result", logoutRes.toString())
                            }
                        }
                    }
                }

            } catch (e: Exception) {
                e.localizedMessage?.let { Log.d("Error On Logout", it) }
            }

        }
    }
}