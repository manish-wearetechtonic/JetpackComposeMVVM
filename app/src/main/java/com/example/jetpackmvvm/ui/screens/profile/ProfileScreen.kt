package com.example.jetpackmvvm.ui.screens.profile


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackmvvm.data.model.profile.UserProfile
import com.example.jetpackmvvm.ui.theme.appTypography

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onLogout: () -> Unit,
) {

    val profileState by profileViewModel.profileState.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.getUserProfile();
    }

    if(profileState.profileEntity != null){
        ProfileContent(
            profileEntity = profileState.profileEntity!!,
            onLogout = onLogout,
            onLogoutTap = { profileViewModel.logout() }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    profileEntity: UserProfile,
    onLogoutTap: () -> Unit,
    onLogout: () -> Unit,
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(

        ) {
            Text(text = "First Name",
                style = appTypography.titleLarge,
                )
            Spacer(modifier = Modifier.size(15.dp))
            Text(text = profileEntity.firstName, style = appTypography.titleMedium,)
        }
        Button(
            onClick = {
                onLogoutTap()
                onLogout()
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Logout")
       
        }
    }
}
