package com.example.ui.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MomoRepository
import com.example.i18n.AppLanguage
import com.example.i18n.LocalMomoStrings
import com.example.ui.components.MomoAppWordmark
import com.example.ui.components.MomoGradientButton
import com.example.ui.components.MomoOutlinedButton
import com.example.ui.components.MomoTextField
import com.example.ui.theme.MomoError
import com.example.ui.theme.MomoOrange
import com.example.ui.theme.MomoPink
import com.example.ui.theme.MomoPurple
import com.example.ui.theme.MomoStoryGradient
import com.example.ui.theme.MomoSuccess
import com.example.ui.theme.MomoWarning
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onTimeout: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.94f,
        targetValue = 1.06f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    LaunchedEffect(Unit) {
        delay(2200)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("splash_screen"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .scale(scale)
                    .clip(CircleShape)
                    .background(MomoStoryGradient)
                    .padding(6.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Momo",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Ungana. Shiriki. Furahia.",
                style = MaterialTheme.typography.bodyLarge,
                color = MomoPurple,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun LanguageSelectScreen(
    currentLanguage: AppLanguage,
    onLanguageSelected: (AppLanguage) -> Unit,
    onContinue: () -> Unit
) {
    val strings = LocalMomoStrings.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
            .testTag("language_select_screen"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(32.dp))
            MomoAppWordmark()
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = strings.chooseLanguage,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Chagua lugha unayopendelea kutumia kwenye Momo",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Swahili Option
            LanguageCard(
                title = "Kiswahili (Chaguo-msingi)",
                subtitle = "Lugha adhimu ya Afrika Mashariki",
                flag = "🇹🇿",
                isSelected = currentLanguage == AppLanguage.SWAHILI,
                onClick = { onLanguageSelected(AppLanguage.SWAHILI) },
                testTag = "lang_swahili"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // English Option
            LanguageCard(
                title = "English (International)",
                subtitle = "Global language for creators worldwide",
                flag = "🌍",
                isSelected = currentLanguage == AppLanguage.ENGLISH,
                onClick = { onLanguageSelected(AppLanguage.ENGLISH) },
                testTag = "lang_english"
            )
        }

        MomoGradientButton(
            text = strings.next,
            onClick = onContinue,
            modifier = Modifier.padding(bottom = 16.dp),
            testTag = "continue_from_language"
        )
    }
}

@Composable
private fun LanguageCard(
    title: String,
    subtitle: String,
    flag: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    testTag: String
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MomoPurple.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surfaceVariant
        ),
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) MomoPurple else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .testTag(testTag)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = flag, fontSize = 32.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(MomoPurple),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun OnboardingCarouselScreen(
    onFinish: () -> Unit,
    onLoginClick: () -> Unit
) {
    val strings = LocalMomoStrings.current
    var currentPage by remember { mutableIntStateOf(0) }

    val slides = listOf(
        Triple(
            "📸",
            strings.slide1Title,
            strings.slide1Desc
        ),
        Triple(
            "✨",
            strings.slide2Title,
            strings.slide2Desc
        ),
        Triple(
            "💬",
            strings.slide3Title,
            strings.slide3Desc
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
            .testTag("onboarding_screen"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MomoAppWordmark()
            TextButton(
                onClick = onFinish,
                modifier = Modifier.testTag("skip_onboarding")
            ) {
                Text(text = strings.skip, color = MomoPurple, fontWeight = FontWeight.SemiBold)
            }
        }

        // Slide Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
                    .background(MomoPurple.copy(alpha = 0.15f))
                    .border(2.dp, MomoStoryGradient, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = slides[currentPage].first,
                    fontSize = 72.sp
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = slides[currentPage].second,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = slides[currentPage].third,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )
        }

        // Bottom Controls
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Indicators
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                slides.indices.forEach { index ->
                    val isSelected = index == currentPage
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .height(8.dp)
                            .width(if (isSelected) 28.dp else 8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (isSelected) MomoPurple else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                    )
                }
            }

            if (currentPage < slides.size - 1) {
                MomoGradientButton(
                    text = strings.next,
                    onClick = { currentPage++ },
                    testTag = "onboarding_next"
                )
            } else {
                MomoGradientButton(
                    text = strings.getStarted,
                    onClick = onFinish,
                    testTag = "onboarding_get_started"
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            TextButton(
                onClick = onLoginClick,
                modifier = Modifier.testTag("onboarding_login_button")
            ) {
                Text(
                    text = strings.alreadyHaveAccount,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    val strings = LocalMomoStrings.current
    var loginInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
            .testTag("login_screen"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(36.dp))
        MomoAppWordmark()
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = strings.welcomeBack,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = strings.loginSubtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        MomoTextField(
            value = loginInput,
            onValueChange = {
                loginInput = it
                errorMessage = null
            },
            label = strings.emailOrPhone,
            placeholder = "baraka / baraka@momo.africa / +255...",
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = null, tint = MomoPurple)
            },
            testTag = "login_username_input"
        )

        Spacer(modifier = Modifier.height(16.dp))

        MomoTextField(
            value = passwordInput,
            onValueChange = {
                passwordInput = it
                errorMessage = null
            },
            label = strings.password,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = null, tint = MomoPurple)
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = "Toggle password visibility"
                    )
                }
            },
            isError = errorMessage != null,
            errorMessage = errorMessage,
            testTag = "login_password_input"
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = onForgotPasswordClick,
                modifier = Modifier.testTag("forgot_password_btn")
            ) {
                Text(
                    text = strings.forgotPassword,
                    color = MomoPurple,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        MomoGradientButton(
            text = strings.login,
            isLoading = isLoading,
            onClick = {
                if (loginInput.isBlank()) {
                    errorMessage = strings.invalidEmailOrPhone
                    return@MomoGradientButton
                }
                if (passwordInput.length < 6) {
                    errorMessage = strings.passwordTooShort
                    return@MomoGradientButton
                }
                isLoading = true
                MomoRepository.loginUser(loginInput)
                onLoginSuccess()
            },
            testTag = "login_submit_btn"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Demo Quick Login pill for quick testing
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            modifier = Modifier.clickable {
                loginInput = "baraka_tanzania"
                passwordInput = "12345678"
                MomoRepository.loginUser("baraka_tanzania")
                onLoginSuccess()
            }
        ) {
            Text(
                text = "⚡ Ingia Haraka (Demo): @baraka_tanzania",
                style = MaterialTheme.typography.bodySmall,
                color = MomoPurple,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(
                text = strings.dontHaveAccount,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            TextButton(onClick = onRegisterClick) {
                Text(
                    text = strings.register,
                    color = MomoPurple,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun RegisterScreen(
    onNavigateToOtp: (target: String) -> Unit,
    onLoginClick: () -> Unit
) {
    val strings = LocalMomoStrings.current
    var fullName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var target by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val passwordStrength = when {
        password.length < 6 -> 0.2f to strings.passwordStrengthWeak
        password.length < 10 -> 0.6f to strings.passwordStrengthGood
        else -> 1.0f to strings.passwordStrengthStrong
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
            .testTag("register_screen"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        MomoAppWordmark()
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = strings.createAccountTitle,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = strings.createAccountDesc,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        MomoTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = strings.fullName,
            placeholder = "Mfano: Baraka Eliakim",
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = MomoPurple) },
            testTag = "reg_fullname_input"
        )

        Spacer(modifier = Modifier.height(12.dp))

        MomoTextField(
            value = username,
            onValueChange = { username = it.lowercase().trim() },
            label = strings.username,
            placeholder = "mfano: baraka_99",
            leadingIcon = { Text("@", fontWeight = FontWeight.Bold, color = MomoPurple, fontSize = 18.sp, modifier = Modifier.padding(start = 14.dp)) },
            testTag = "reg_username_input"
        )

        Spacer(modifier = Modifier.height(12.dp))

        MomoTextField(
            value = target,
            onValueChange = { target = it },
            label = strings.emailOrPhone,
            placeholder = "+255... au email@example.com",
            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = MomoPurple) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            testTag = "reg_target_input"
        )

        Spacer(modifier = Modifier.height(12.dp))

        MomoTextField(
            value = password,
            onValueChange = { password = it },
            label = strings.password,
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = MomoPurple) },
            testTag = "reg_password_input"
        )

        if (password.isNotEmpty()) {
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)
            ) {
                LinearProgressIndicator(
                    progress = { passwordStrength.first },
                    modifier = Modifier.weight(1f).height(6.dp).clip(RoundedCornerShape(3.dp)),
                    color = when {
                        passwordStrength.first <= 0.3f -> MomoError
                        passwordStrength.first <= 0.7f -> MomoWarning
                        else -> MomoSuccess
                    }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = passwordStrength.second,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        MomoTextField(
            value = dob,
            onValueChange = { dob = it },
            label = strings.dateOfBirth,
            placeholder = "2002-05-18",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            testTag = "reg_dob_input"
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        MomoGradientButton(
            text = strings.sendOtp,
            onClick = {
                if (fullName.isBlank() || username.length < 3 || target.isBlank() || password.length < 8) {
                    errorMessage = "Tafadhali jaza taarifa zote kwa usahihi (Nenosiri liwe na herufi 8+)"
                    return@MomoGradientButton
                }
                MomoRepository.registerUser(fullName, username, target, dob)
                onNavigateToOtp(target)
            },
            testTag = "reg_submit_btn"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = strings.alreadyHaveAccount,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            TextButton(onClick = onLoginClick) {
                Text(
                    text = strings.login,
                    color = MomoPurple,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun OtpVerificationScreen(
    target: String,
    onVerified: () -> Unit,
    onBack: () -> Unit
) {
    val strings = LocalMomoStrings.current
    var otpDigits by remember { mutableStateOf(listOf("5", "4", "2", "8", "1", "9")) }
    var enteredOtp by remember { mutableStateOf("542819") }
    var countdown by remember { mutableIntStateOf(58) }

    LaunchedEffect(Unit) {
        while (countdown > 0) {
            delay(1000)
            countdown--
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
            .testTag("otp_screen"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = strings.verify,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "🔢",
            fontSize = 54.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = strings.enterOtp,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${strings.otpSentTo}\n$target",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(36.dp))

        // OTP 6-Digit visual row
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            otpDigits.forEachIndexed { index, digit ->
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .border(1.5.dp, MomoPurple, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = digit,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        MomoGradientButton(
            text = strings.verify,
            onClick = onVerified,
            testTag = "otp_verify_btn"
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (countdown > 0) {
            Text(
                text = "Tuma tena msimbo baada ya sekunde ${countdown}s",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            TextButton(onClick = { countdown = 60 }) {
                Text(
                    text = strings.resendOtp,
                    color = MomoPurple,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ForgotPasswordScreen(
    onBack: () -> Unit,
    onResetSent: () -> Unit
) {
    val strings = LocalMomoStrings.current
    var input by remember { mutableStateOf("") }
    var sent by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = strings.forgotPassword,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "🔐",
            fontSize = 54.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = strings.resetPassword,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Weka barua pepe au namba yako ya simu ili tukutumie msimbo wa kubadilisha nenosiri.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        MomoTextField(
            value = input,
            onValueChange = { input = it },
            label = strings.emailOrPhone,
            placeholder = "baraka@example.com / +255...",
            leadingIcon = { Icon(Icons.Default.Mail, contentDescription = null, tint = MomoPurple) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        MomoGradientButton(
            text = strings.sendOtp,
            onClick = {
                sent = true
                onResetSent()
            }
        )
    }
}
