package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.i18n.AppLanguage
import com.example.i18n.EnglishStrings
import com.example.i18n.LocalMomoStrings
import com.example.i18n.SwahiliStrings
import com.example.model.MainTab
import com.example.model.Screen
import com.example.ui.auth.ForgotPasswordScreen
import com.example.ui.auth.LanguageSelectScreen
import com.example.ui.auth.LoginScreen
import com.example.ui.auth.OnboardingCarouselScreen
import com.example.ui.auth.OtpVerificationScreen
import com.example.ui.auth.RegisterScreen
import com.example.ui.auth.SplashScreen
import com.example.ui.chat.ChatDetailScreen
import com.example.ui.chat.MessagesInboxScreen
import com.example.ui.components.MomoBottomNav
import com.example.ui.create.CreatePostScreen
import com.example.ui.explore.ExploreScreen
import com.example.ui.feed.FeedScreen
import com.example.ui.notifications.NotificationsScreen
import com.example.ui.profile.EditProfileScreen
import com.example.ui.profile.ProfileScreen
import com.example.ui.profile.ProfileSetupScreen
import com.example.ui.reels.ReelsScreen
import com.example.ui.stories.StoryViewerScreen
import com.example.ui.theme.MomoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var currentLanguage by remember { mutableStateOf(AppLanguage.SWAHILI) }
            var isDarkTheme by remember { mutableStateOf(true) }
            var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }
            var currentTab by remember { mutableStateOf(MainTab.HOME) }

            val strings = if (currentLanguage == AppLanguage.SWAHILI) SwahiliStrings else EnglishStrings

            CompositionLocalProvider(LocalMomoStrings provides strings) {
                MomoTheme(darkTheme = isDarkTheme) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        when (val screen = currentScreen) {
                            is Screen.Splash -> {
                                SplashScreen(
                                    onTimeout = { currentScreen = Screen.LanguageSelect }
                                )
                            }

                            is Screen.LanguageSelect -> {
                                LanguageSelectScreen(
                                    currentLanguage = currentLanguage,
                                    onLanguageSelected = { currentLanguage = it },
                                    onContinue = { currentScreen = Screen.Onboarding }
                                )
                            }

                            is Screen.Onboarding -> {
                                BackHandler { currentScreen = Screen.LanguageSelect }
                                OnboardingCarouselScreen(
                                    onFinish = { currentScreen = Screen.Register },
                                    onLoginClick = { currentScreen = Screen.Login }
                                )
                            }

                            is Screen.Login -> {
                                BackHandler { currentScreen = Screen.Onboarding }
                                LoginScreen(
                                    onLoginSuccess = {
                                        currentTab = MainTab.HOME
                                        currentScreen = Screen.MainApp
                                    },
                                    onRegisterClick = { currentScreen = Screen.Register },
                                    onForgotPasswordClick = { currentScreen = Screen.ForgotPassword }
                                )
                            }

                            is Screen.Register -> {
                                BackHandler { currentScreen = Screen.Login }
                                RegisterScreen(
                                    onNavigateToOtp = { target ->
                                        currentScreen = Screen.OtpVerification(target, "REGISTRATION")
                                    },
                                    onLoginClick = { currentScreen = Screen.Login }
                                )
                            }

                            is Screen.OtpVerification -> {
                                BackHandler { currentScreen = Screen.Register }
                                OtpVerificationScreen(
                                    target = screen.target,
                                    onVerified = { currentScreen = Screen.ProfileSetup },
                                    onBack = { currentScreen = Screen.Register }
                                )
                            }

                            is Screen.ForgotPassword -> {
                                BackHandler { currentScreen = Screen.Login }
                                ForgotPasswordScreen(
                                    onBack = { currentScreen = Screen.Login },
                                    onResetSent = { currentScreen = Screen.Login }
                                )
                            }

                            is Screen.ProfileSetup -> {
                                BackHandler { currentScreen = Screen.Login }
                                ProfileSetupScreen(
                                    onFinishSetup = {
                                        currentTab = MainTab.HOME
                                        currentScreen = Screen.MainApp
                                    }
                                )
                            }

                            is Screen.StoryViewer -> {
                                BackHandler { currentScreen = Screen.MainApp }
                                StoryViewerScreen(
                                    initialIndex = screen.initialIndex,
                                    onClose = { currentScreen = Screen.MainApp }
                                )
                            }

                            is Screen.Notifications -> {
                                BackHandler { currentScreen = Screen.MainApp }
                                NotificationsScreen(
                                    onBack = { currentScreen = Screen.MainApp }
                                )
                            }

                            is Screen.DirectMessages -> {
                                BackHandler { currentScreen = Screen.MainApp }
                                MessagesInboxScreen(
                                    onBack = { currentScreen = Screen.MainApp },
                                    onOpenChat = { convId ->
                                        currentScreen = Screen.ChatDetail(convId)
                                    }
                                )
                            }

                            is Screen.ChatDetail -> {
                                BackHandler { currentScreen = Screen.DirectMessages }
                                ChatDetailScreen(
                                    conversationId = screen.conversationId,
                                    onBack = { currentScreen = Screen.DirectMessages }
                                )
                            }

                            is Screen.EditProfile -> {
                                BackHandler { currentScreen = Screen.MainApp }
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .statusBarsPadding()
                                        .navigationBarsPadding()
                                ) {
                                    EditProfileScreen(
                                        onBack = { currentScreen = Screen.MainApp },
                                        onSaveSuccess = { currentScreen = Screen.MainApp }
                                    )
                                }
                            }

                            is Screen.MainApp -> {
                                Scaffold(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .statusBarsPadding(),
                                    bottomBar = {
                                        MomoBottomNav(
                                            currentTab = currentTab,
                                            onTabSelected = { currentTab = it },
                                            modifier = Modifier.navigationBarsPadding()
                                        )
                                    }
                                ) { innerPadding ->
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(innerPadding)
                                    ) {
                                        when (currentTab) {
                                            MainTab.HOME -> {
                                                FeedScreen(
                                                    onOpenStory = { index ->
                                                        currentScreen = Screen.StoryViewer(index)
                                                    },
                                                    onNavigateToNotifications = {
                                                        currentScreen = Screen.Notifications
                                                    },
                                                    onNavigateToMessages = {
                                                        currentScreen = Screen.DirectMessages
                                                    }
                                                )
                                            }

                                            MainTab.EXPLORE -> {
                                                ExploreScreen()
                                            }

                                            MainTab.CREATE -> {
                                                CreatePostScreen(
                                                    onPostCreated = {
                                                        currentTab = MainTab.HOME
                                                    }
                                                )
                                            }

                                            MainTab.REELS -> {
                                                ReelsScreen()
                                            }

                                            MainTab.PROFILE -> {
                                                ProfileScreen(
                                                    onNavigateToEditProfile = {
                                                        currentScreen = Screen.EditProfile
                                                    },
                                                    onLogout = {
                                                        currentScreen = Screen.Login
                                                    },
                                                    onLanguageChange = {
                                                        currentLanguage = it
                                                    },
                                                    onThemeToggle = {
                                                        isDarkTheme = it
                                                    },
                                                    isDarkTheme = isDarkTheme,
                                                    currentLanguage = currentLanguage
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
