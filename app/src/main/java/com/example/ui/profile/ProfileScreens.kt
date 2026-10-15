package com.example.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.MomoRepository
import com.example.i18n.AppLanguage
import com.example.i18n.LocalMomoStrings
import com.example.model.InterestTopic
import com.example.model.MomoUser
import com.example.model.StoryHighlight
import com.example.model.SuggestedCreator
import com.example.model.UserPostItem
import com.example.ui.components.MomoGradientButton
import com.example.ui.components.MomoOutlinedButton
import com.example.ui.components.MomoStoryRingAvatar
import com.example.ui.components.MomoTextField
import com.example.ui.theme.MomoDarkBg
import com.example.ui.theme.MomoOrange
import com.example.ui.theme.MomoPink
import com.example.ui.theme.MomoPurple
import com.example.ui.theme.MomoStoryGradient
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileSetupScreen(
    onFinishSetup: () -> Unit
) {
    val strings = LocalMomoStrings.current
    val currentUser by MomoRepository.currentUser.collectAsState()
    val suggestedCreators by MomoRepository.suggestedCreators.collectAsState()

    var selectedAvatar by remember { mutableStateOf(currentUser.avatarUrl.ifEmpty { MomoRepository.presetAvatars.first() }) }
    var bioText by remember { mutableStateOf(currentUser.bio) }
    var websiteText by remember { mutableStateOf(currentUser.website) }

    val selectedInterests = remember { mutableStateListOf<String>().apply { addAll(currentUser.selectedInterests) } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
            .testTag("profile_setup_screen"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = strings.setupProfileTitle,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = strings.setupProfileSubtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Avatar selector
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(3.dp, MomoStoryGradient, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = selectedAvatar,
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = strings.chooseAvatar,
            style = MaterialTheme.typography.labelLarge,
            color = MomoPurple,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Preset Avatars Row
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            MomoRepository.presetAvatars.forEach { avatarUrl ->
                val isSelected = avatarUrl == selectedAvatar
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .border(
                            width = if (isSelected) 3.dp else 1.dp,
                            color = if (isSelected) MomoPurple else Color.Transparent,
                            shape = CircleShape
                        )
                        .clickable { selectedAvatar = avatarUrl }
                ) {
                    AsyncImage(
                        model = avatarUrl,
                        contentDescription = "Preset avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize().clip(CircleShape)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        MomoTextField(
            value = bioText,
            onValueChange = { bioText = it },
            label = strings.bio,
            placeholder = strings.bioHint,
            singleLine = false,
            modifier = Modifier.height(110.dp),
            testTag = "setup_bio_input"
        )

        Spacer(modifier = Modifier.height(12.dp))

        MomoTextField(
            value = websiteText,
            onValueChange = { websiteText = it },
            label = strings.website,
            placeholder = "https://instagram.com/jina_lako",
            testTag = "setup_website_input"
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Interests Picker
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = strings.selectInterests,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = strings.selectInterestsDesc,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(12.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                MomoRepository.defaultInterests.forEach { interest ->
                    val isSelected = selectedInterests.contains(interest.id)
                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            if (isSelected) selectedInterests.remove(interest.id)
                            else selectedInterests.add(interest.id)
                        },
                        label = {
                            Text("${interest.icon} ${interest.nameSw}")
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MomoPurple,
                            selectedLabelColor = Color.White
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Suggested Accounts Section
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = strings.suggestedCreators,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))

            suggestedCreators.forEach { creator ->
                SuggestedCreatorRow(
                    creator = creator,
                    onToggleFollow = { MomoRepository.toggleFollowCreator(creator.id) }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        MomoGradientButton(
            text = strings.finish,
            onClick = {
                MomoRepository.updateProfile(
                    fullName = currentUser.fullName,
                    username = currentUser.username,
                    bio = bioText,
                    website = websiteText,
                    isPrivate = currentUser.isPrivate,
                    avatarUrl = selectedAvatar
                )
                MomoRepository.setInterests(selectedInterests.toList())
                onFinishSetup()
            },
            testTag = "setup_finish_btn"
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SuggestedCreatorRow(
    creator: SuggestedCreator,
    onToggleFollow: () -> Unit
) {
    val strings = LocalMomoStrings.current

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MomoStoryRingAvatar(
                imageUrl = creator.avatarUrl,
                size = 50.dp,
                hasStory = true
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "@${creator.username}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "${creator.fullName} • ${creator.category}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            if (creator.isFollowing) {
                Surface(
                    onClick = onToggleFollow,
                    shape = RoundedCornerShape(18.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    color = Color.Transparent,
                    modifier = Modifier.defaultMinSize(minHeight = 36.dp)
                ) {
                    Text(
                        text = strings.following,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                    )
                }
            } else {
                Surface(
                    onClick = onToggleFollow,
                    shape = RoundedCornerShape(18.dp),
                    color = MomoPurple,
                    modifier = Modifier.defaultMinSize(minHeight = 36.dp)
                ) {
                    Text(
                        text = strings.follow,
                        color = Color.White,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateToEditProfile: () -> Unit,
    onLogout: () -> Unit,
    onLanguageChange: (AppLanguage) -> Unit,
    onThemeToggle: (Boolean) -> Unit,
    isDarkTheme: Boolean,
    currentLanguage: AppLanguage
) {
    val strings = LocalMomoStrings.current
    val currentUser by MomoRepository.currentUser.collectAsState()
    val highlights by MomoRepository.highlights.collectAsState()
    val posts by MomoRepository.userPosts.collectAsState()

    var selectedTab by remember { mutableIntStateOf(0) }
    var showSettingsSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("profile_screen")
    ) {
        // Top App Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (currentUser.isPrivate) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Private",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Text(
                    text = currentUser.username,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                if (currentUser.isVerified) {
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = "Verified",
                        tint = MomoPurple,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            IconButton(
                onClick = { showSettingsSheet = true },
                modifier = Modifier.testTag("profile_settings_menu_btn")
            ) {
                Icon(Icons.Default.MoreVert, contentDescription = "Settings")
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            // Profile Header Item
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    // Avatar & Stats Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MomoStoryRingAvatar(
                            imageUrl = currentUser.avatarUrl,
                            size = 84.dp,
                            hasStory = true
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(24.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ProfileStatColumn(count = "${currentUser.postsCount}", label = strings.posts)
                            ProfileStatColumn(count = "${currentUser.followersCount}", label = strings.followers)
                            ProfileStatColumn(count = "${currentUser.followingCount}", label = strings.following)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Bio & Info
                    Text(
                        text = currentUser.fullName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    if (currentUser.bio.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = currentUser.bio,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    if (currentUser.website.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = currentUser.website,
                            style = MaterialTheme.typography.bodySmall,
                            color = MomoPurple,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // Action Buttons (Edit Profile, Share Profile)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            onClick = onNavigateToEditProfile,
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .testTag("edit_profile_btn")
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = strings.editProfile,
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Surface(
                            onClick = { /* Share Profile Action */ },
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .testTag("share_profile_btn")
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = strings.shareProfile,
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Story Highlights Row
                    Text(
                        text = strings.highlights,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(bottom = 12.dp)
                    ) {
                        // Add Highlight Button
                        item {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.clickable { }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(62.dp)
                                        .clip(CircleShape)
                                        .border(1.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = "Add Highlight", tint = MomoPurple)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = strings.newHighlight,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }

                        items(highlights) { highlight ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.clickable { }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(62.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.surfaceVariant)
                                        .border(2.dp, MomoStoryGradient, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = highlight.coverEmojiOrUrl, fontSize = 28.sp)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = highlight.title,
                                    style = MaterialTheme.typography.labelSmall,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }

            // Tab Row (Machapisho, Reels, Uliotambulishwa)
            item {
                SecondaryTabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MomoPurple
                ) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        icon = { Icon(Icons.Default.GridOn, contentDescription = strings.tabPosts) }
                    )
                    Tab(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        icon = { Icon(Icons.Default.Movie, contentDescription = strings.tabReels) }
                    )
                    Tab(
                        selected = selectedTab == 2,
                        onClick = { selectedTab = 2 },
                        icon = { Icon(Icons.Default.Tag, contentDescription = strings.tabTagged) }
                    )
                }
            }

            // Tab Content
            when (selectedTab) {
                0 -> {
                    // Posts 3-column Grid
                    if (posts.isEmpty()) {
                        item {
                            EmptyPostsPlaceholder(strings.emptyPosts, strings.emptyPostsDesc)
                        }
                    } else {
                        val chunked = posts.chunked(3)
                        items(chunked) { rowPosts ->
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 1.dp),
                                horizontalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                rowPosts.forEach { post ->
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .aspectRatio(1f)
                                            .clip(RoundedCornerShape(4.dp))
                                            .clickable { }
                                    ) {
                                        AsyncImage(
                                            model = post.mediaUrl,
                                            contentDescription = post.caption,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                        if (post.isVideo) {
                                            Icon(
                                                imageVector = Icons.Default.PlayArrow,
                                                contentDescription = "Video",
                                                tint = Color.White,
                                                modifier = Modifier.padding(6.dp).align(Alignment.TopEnd)
                                            )
                                        }
                                    }
                                }
                                // Pad empty cells if last row has less than 3
                                repeat(3 - rowPosts.size) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
                1 -> {
                    // Reels Tab
                    val reels = posts.filter { it.isVideo || it.likesCount > 500 }
                    val chunkedReels = reels.chunked(3)
                    items(chunkedReels) { rowReels ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 1.dp),
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            rowReels.forEach { reel ->
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(0.65f)
                                        .clip(RoundedCornerShape(6.dp))
                                        .clickable { }
                                ) {
                                    AsyncImage(
                                        model = reel.mediaUrl,
                                        contentDescription = reel.caption,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                    Row(
                                        modifier = Modifier.align(Alignment.BottomStart).padding(6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.PlayArrow,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text(
                                            text = "${reel.likesCount}",
                                            color = Color.White,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                            repeat(3 - rowReels.size) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
                2 -> {
                    // Tagged Tab
                    item {
                        EmptyPostsPlaceholder(
                            title = "Picha Zilizotambulishwa",
                            desc = "Watu wanapokutambulisha kwenye picha na video zao, zitaonekana hapa."
                        )
                    }
                }
            }
        }

        // Settings Bottom Sheet
        if (showSettingsSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSettingsSheet = false },
                sheetState = sheetState
            ) {
                SettingsBottomSheetContent(
                    isDarkTheme = isDarkTheme,
                    currentLanguage = currentLanguage,
                    onThemeToggle = onThemeToggle,
                    onLanguageChange = onLanguageChange,
                    onLogout = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            showSettingsSheet = false
                            onLogout()
                        }
                    },
                    onClose = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            showSettingsSheet = false
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun ProfileStatColumn(count: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = count,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun EmptyPostsPlaceholder(title: String, desc: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(MomoPurple.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.GridOn, contentDescription = null, tint = MomoPurple, modifier = Modifier.size(36.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = desc,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SettingsBottomSheetContent(
    isDarkTheme: Boolean,
    currentLanguage: AppLanguage,
    onThemeToggle: (Boolean) -> Unit,
    onLanguageChange: (AppLanguage) -> Unit,
    onLogout: () -> Unit,
    onClose: () -> Unit
) {
    val strings = LocalMomoStrings.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .testTag("settings_bottom_sheet")
    ) {
        Text(
            text = strings.settings,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Language Switcher Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Language, contentDescription = null, tint = MomoPurple)
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(text = strings.language, fontWeight = FontWeight.SemiBold)
                    Text(
                        text = currentLanguage.displayName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(
                    onClick = { onLanguageChange(AppLanguage.SWAHILI) },
                    shape = RoundedCornerShape(16.dp),
                    color = if (currentLanguage == AppLanguage.SWAHILI) MomoPurple else MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        text = "🇹🇿 SW",
                        color = if (currentLanguage == AppLanguage.SWAHILI) Color.White else MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }

                Surface(
                    onClick = { onLanguageChange(AppLanguage.ENGLISH) },
                    shape = RoundedCornerShape(16.dp),
                    color = if (currentLanguage == AppLanguage.ENGLISH) MomoPurple else MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        text = "🌍 EN",
                        color = if (currentLanguage == AppLanguage.ENGLISH) Color.White else MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }
        }

        Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))

        // Theme Switcher Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = if (isDarkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
                    contentDescription = null,
                    tint = MomoOrange
                )
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(text = strings.theme, fontWeight = FontWeight.SemiBold)
                    Text(
                        text = if (isDarkTheme) strings.darkMode else strings.lightMode,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Switch(
                checked = isDarkTheme,
                onCheckedChange = onThemeToggle,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MomoPurple,
                    checkedTrackColor = MomoPurple.copy(alpha = 0.4f)
                )
            )
        }

        Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))

        // Account Security & Privacy info
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Security, contentDescription = null, tint = MomoPink)
            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(text = strings.security, fontWeight = FontWeight.SemiBold)
                Text(
                    text = "Vifaa 2 vimeunganishwa • Dar es Salaam, TZ",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))

        Spacer(modifier = Modifier.height(16.dp))

        // Logout Button
        Surface(
            onClick = onLogout,
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.error.copy(alpha = 0.12f),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("logout_button")
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Default.Logout, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = strings.logout,
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun EditProfileScreen(
    onBack: () -> Unit,
    onSaveSuccess: () -> Unit
) {
    val strings = LocalMomoStrings.current
    val currentUser by MomoRepository.currentUser.collectAsState()

    var fullName by remember { mutableStateOf(currentUser.fullName) }
    var username by remember { mutableStateOf(currentUser.username) }
    var bio by remember { mutableStateOf(currentUser.bio) }
    var website by remember { mutableStateOf(currentUser.website) }
    var isPrivate by remember { mutableStateOf(currentUser.isPrivate) }
    var selectedAvatar by remember { mutableStateOf(currentUser.avatarUrl) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
            .testTag("edit_profile_screen")
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
                Text(
                    text = strings.editProfile,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            TextButton(
                onClick = {
                    MomoRepository.updateProfile(
                        fullName = fullName,
                        username = username,
                        bio = bio,
                        website = website,
                        isPrivate = isPrivate,
                        avatarUrl = selectedAvatar
                    )
                    onSaveSuccess()
                },
                modifier = Modifier.testTag("save_profile_btn")
            ) {
                Text(
                    text = strings.save,
                    color = MomoPurple,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Avatar with edit badge
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .border(3.dp, MomoStoryGradient, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = selectedAvatar,
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize().clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = strings.changePhoto,
                color = MomoPurple,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )

            // Preset avatar quick chips
            Spacer(modifier = Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                MomoRepository.presetAvatars.take(4).forEach { url ->
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .border(
                                width = if (url == selectedAvatar) 2.5.dp else 1.dp,
                                color = if (url == selectedAvatar) MomoPurple else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable { selectedAvatar = url }
                    ) {
                        AsyncImage(
                            model = url,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize().clip(CircleShape)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        MomoTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = strings.fullName,
            testTag = "edit_fullname"
        )

        Spacer(modifier = Modifier.height(12.dp))

        MomoTextField(
            value = username,
            onValueChange = { username = it.lowercase().trim() },
            label = strings.username,
            leadingIcon = { Text("@", fontWeight = FontWeight.Bold, color = MomoPurple, fontSize = 16.sp, modifier = Modifier.padding(start = 12.dp)) },
            testTag = "edit_username"
        )

        Spacer(modifier = Modifier.height(12.dp))

        MomoTextField(
            value = bio,
            onValueChange = { bio = it },
            label = strings.bio,
            placeholder = strings.bioHint,
            singleLine = false,
            modifier = Modifier.height(100.dp),
            testTag = "edit_bio"
        )

        Spacer(modifier = Modifier.height(12.dp))

        MomoTextField(
            value = website,
            onValueChange = { website = it },
            label = strings.website,
            testTag = "edit_website"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Privacy Toggle
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = strings.privateAccount,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = strings.privateAccountDesc,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Switch(
                    checked = isPrivate,
                    onCheckedChange = { isPrivate = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MomoPurple,
                        checkedTrackColor = MomoPurple.copy(alpha = 0.4f)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        MomoGradientButton(
            text = strings.save,
            onClick = {
                MomoRepository.updateProfile(
                    fullName = fullName,
                    username = username,
                    bio = bio,
                    website = website,
                    isPrivate = isPrivate,
                    avatarUrl = selectedAvatar
                )
                onSaveSuccess()
            },
            testTag = "edit_profile_save_btn"
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
