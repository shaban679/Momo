package com.example.data

import com.example.model.ChatMessage
import com.example.model.CommentItem
import com.example.model.ConversationItem
import com.example.model.ExploreItem
import com.example.model.FeedPost
import com.example.model.InterestTopic
import com.example.model.MomoUser
import com.example.model.NotificationItem
import com.example.model.ReelItem
import com.example.model.StoryHighlight
import com.example.model.StoryItem
import com.example.model.SuggestedCreator
import com.example.model.UserPostItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

object MomoRepository {

    // Default East African Interests
    val defaultInterests = listOf(
        InterestTopic("music", "Muziki", "Music", "🎵"),
        InterestTopic("sports", "Michezo", "Sports", "⚽"),
        InterestTopic("food", "Chakula", "Food", "🍲"),
        InterestTopic("fashion", "Mitindo", "Fashion", "👗"),
        InterestTopic("comedy", "Vichekesho", "Comedy", "😂"),
        InterestTopic("education", "Elimu", "Education", "📚"),
        InterestTopic("business", "Biashara", "Business", "💼"),
        InterestTopic("tech", "Teknolojia", "Tech", "📱"),
        InterestTopic("religion", "Dini & Imani", "Faith", "🙏"),
        InterestTopic("travel", "Safari & Utalii", "Travel", "✈️")
    )

    // Preset Avatars for profile setup
    val presetAvatars = listOf(
        "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=400&auto=format&fit=crop&q=80",
        "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&auto=format&fit=crop&q=80",
        "https://images.unsplash.com/photo-1517841905240-472988babdf9?w=400&auto=format&fit=crop&q=80",
        "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?w=400&auto=format&fit=crop&q=80",
        "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?w=400&auto=format&fit=crop&q=80",
        "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=400&auto=format&fit=crop&q=80"
    )

    // Current Logged-in User
    private val _currentUser = MutableStateFlow(
        MomoUser(
            id = "user_me",
            username = "baraka_tanzania",
            fullName = "Baraka Eliakim",
            email = "baraka@momo.africa",
            phone = "+255 754 123 456",
            bio = "Mbunifu wa dijitali na mpenda safari 🇹🇿 | Karibu kwenye dunia yangu ya picha na video! 📸✨",
            website = "https://momo.africa/@baraka",
            avatarUrl = "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?w=400&auto=format&fit=crop&q=80",
            isPrivate = false,
            isVerified = true,
            postsCount = 12,
            followersCount = 1420,
            followingCount = 385,
            selectedInterests = listOf("music", "travel", "tech")
        )
    )
    val currentUser: StateFlow<MomoUser> = _currentUser.asStateFlow()

    // Suggested Creators
    private val _suggestedCreators = MutableStateFlow(
        listOf(
            SuggestedCreator(
                id = "c1",
                username = "juma_safari",
                fullName = "Juma Swahili Vibes",
                avatarUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&auto=format&fit=crop&q=80",
                category = "Safari & Utalii",
                followersText = "124K wafuasi",
                isFollowing = false
            ),
            SuggestedCreator(
                id = "c2",
                username = "amina_style",
                fullName = "Amina Hassan",
                avatarUrl = "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=400&auto=format&fit=crop&q=80",
                category = "Mitindo & Urembo",
                followersText = "89K wafuasi",
                isFollowing = false
            ),
            SuggestedCreator(
                id = "c3",
                username = "doreen_tech",
                fullName = "Doreen Mushi",
                avatarUrl = "https://images.unsplash.com/photo-1517841905240-472988babdf9?w=400&auto=format&fit=crop&q=80",
                category = "Teknolojia & AI",
                followersText = "45K wafuasi",
                isFollowing = false
            ),
            SuggestedCreator(
                id = "c4",
                username = "zanzibar_flavors",
                fullName = "Mpishi Salum",
                avatarUrl = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=400&auto=format&fit=crop&q=80",
                category = "Chakula & Biriani",
                followersText = "210K wafuasi",
                isFollowing = false
            )
        )
    )
    val suggestedCreators: StateFlow<List<SuggestedCreator>> = _suggestedCreators.asStateFlow()

    // Highlights
    private val _highlights = MutableStateFlow(
        listOf(
            StoryHighlight("h1", "Zanzibar 🌊", "🏝️"),
            StoryHighlight("h2", "Serengeti 🦁", "🦁"),
            StoryHighlight("h3", "Muziki 🎶", "🎧"),
            StoryHighlight("h4", "Vibes ✨", "🔥")
        )
    )
    val highlights: StateFlow<List<StoryHighlight>> = _highlights.asStateFlow()

    // User Posts Grid
    private val _userPosts = MutableStateFlow(
        listOf(
            UserPostItem(
                id = "p1",
                mediaUrl = "https://images.unsplash.com/photo-1516426122078-c23e76319801?w=600&auto=format&fit=crop&q=80",
                likesCount = 482,
                commentsCount = 36,
                caption = "Mchana mwema kutoka pwani ya Nungwi Zanzibar! Maji ni safi na upepo ni mwanana 🌴🌊"
            ),
            UserPostItem(
                id = "p2",
                mediaUrl = "https://images.unsplash.com/photo-1547471080-7cc2caa01a7e?w=600&auto=format&fit=crop&q=80",
                likesCount = 920,
                commentsCount = 64,
                caption = "Mbuga ya Serengeti wakati wa machweo. Uzuri wa asili ya nchi yetu Tanzania 🇹🇿🦁"
            ),
            UserPostItem(
                id = "p3",
                mediaUrl = "https://images.unsplash.com/photo-1492684223066-81342ee5ff30?w=600&auto=format&fit=crop&q=80",
                likesCount = 310,
                commentsCount = 19,
                caption = "Usiku wa burudani na marafiki Dar es Salaam! Muziki mzuri na vicheko tele 🎶"
            ),
            UserPostItem(
                id = "p4",
                mediaUrl = "https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=600&auto=format&fit=crop&q=80",
                likesCount = 540,
                commentsCount = 42,
                caption = "Biriani ya Zanzibar yenye viungo vya karafuu na iliki. Hakuna kama ladha hii! 🍛"
            ),
            UserPostItem(
                id = "p5",
                mediaUrl = "https://images.unsplash.com/photo-1498050108023-c5249f4df085?w=600&auto=format&fit=crop&q=80",
                likesCount = 275,
                commentsCount = 15,
                caption = "Kujenga teknolojia mpya ya Kiafrika kwa ajili ya vijana wetu. Future is bright! 💻✨"
            ),
            UserPostItem(
                id = "p6",
                mediaUrl = "https://images.unsplash.com/photo-1506744038136-46273834b3fb?w=600&auto=format&fit=crop&q=80",
                likesCount = 612,
                commentsCount = 28,
                caption = "Maporomoko ya maji mkoani Arusha. Pumzika na ufurahie uumbaji! ⛰️"
            )
        )
    )
    val userPosts: StateFlow<List<UserPostItem>> = _userPosts.asStateFlow()

    // Stories Tray Data
    private val _stories = MutableStateFlow(
        listOf(
            StoryItem(
                id = "s_me",
                authorId = "user_me",
                authorUsername = "Hadithi Yako",
                authorAvatarUrl = "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?w=400&auto=format&fit=crop&q=80",
                mediaUrl = "https://images.unsplash.com/photo-1516426122078-c23e76319801?w=800&auto=format&fit=crop&q=80",
                isSeen = false,
                timeAgo = "dakika 15",
                textOverlay = "Habari za asubuhi Momo fam! ☀️🌊"
            ),
            StoryItem(
                id = "s1",
                authorId = "c1",
                authorUsername = "juma_safari",
                authorAvatarUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&auto=format&fit=crop&q=80",
                mediaUrl = "https://images.unsplash.com/photo-1547471080-7cc2caa01a7e?w=800&auto=format&fit=crop&q=80",
                isSeen = false,
                timeAgo = "saa 1",
                textOverlay = "Serengeti leo ni moto sana! 🦁🇹🇿"
            ),
            StoryItem(
                id = "s2",
                authorId = "c2",
                authorUsername = "amina_style",
                authorAvatarUrl = "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=400&auto=format&fit=crop&q=80",
                mediaUrl = "https://images.unsplash.com/photo-1492684223066-81342ee5ff30?w=800&auto=format&fit=crop&q=80",
                isSeen = false,
                timeAgo = "saa 3",
                textOverlay = "Mitindo ya Kitenge Dar Fashion Week 👗✨"
            ),
            StoryItem(
                id = "s3",
                authorId = "c3",
                authorUsername = "doreen_tech",
                authorAvatarUrl = "https://images.unsplash.com/photo-1517841905240-472988babdf9?w=400&auto=format&fit=crop&q=80",
                mediaUrl = "https://images.unsplash.com/photo-1498050108023-c5249f4df085?w=800&auto=format&fit=crop&q=80",
                isSeen = true,
                timeAgo = "saa 5",
                textOverlay = "Coding my new app with Jetpack Compose 🚀"
            )
        )
    )
    val stories: StateFlow<List<StoryItem>> = _stories.asStateFlow()

    // Feed Posts Data
    private val _feedPosts = MutableStateFlow(
        listOf(
            FeedPost(
                id = "feed_1",
                authorId = "c1",
                authorUsername = "juma_safari",
                authorFullName = "Juma Swahili Vibes",
                authorAvatarUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&auto=format&fit=crop&q=80",
                isAuthorVerified = true,
                location = "Serengeti National Park, Tanzania",
                mediaUrls = listOf("https://images.unsplash.com/photo-1547471080-7cc2caa01a7e?w=800&auto=format&fit=crop&q=80"),
                caption = "Leo tumebahatika kushuhudia familia ya simba wakipumzika chini ya mti wa mwavuli. Asili yetu haina mbadala! 🦁🇹🇿 #Safari #Tanzania #Serengeti #MomoVibes",
                likesCount = 1420,
                isLiked = true,
                isSaved = false,
                commentsCount = 58,
                comments = listOf(
                    CommentItem("cm1", "baraka_tanzania", "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?w=400&auto=format&fit=crop&q=80", "Picha nzuri sana kaka! Lazima nije msimu ujao 🔥", "dakika 12"),
                    CommentItem("cm2", "amina_style", "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=400&auto=format&fit=crop&q=80", "Wow! Amazing wildlife view 🌿", "dakika 40")
                ),
                timeAgo = "dakika 25 zilizopita"
            ),
            FeedPost(
                id = "feed_2",
                authorId = "c2",
                authorUsername = "amina_style",
                authorFullName = "Amina Hassan",
                authorAvatarUrl = "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=400&auto=format&fit=crop&q=80",
                isAuthorVerified = false,
                location = "Masaki, Dar es Salaam",
                mediaUrls = listOf("https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=800&auto=format&fit=crop&q=80"),
                caption = "Ijumaa Kareem kutoka Dar es Salaam! Je, mko tayari kwa wikendi hii? Tuambie unakwenda wapi! ✨💄👗 #DarEsSalaam #Fashion #Mitindo #MomoVibes",
                likesCount = 890,
                isLiked = false,
                isSaved = true,
                commentsCount = 32,
                comments = listOf(
                    CommentItem("cm3", "doreen_tech", "https://images.unsplash.com/photo-1517841905240-472988babdf9?w=400&auto=format&fit=crop&q=80", "Umetokelezea sana Amina! ❤️", "saa 1")
                ),
                timeAgo = "saa 2 zilizopita"
            ),
            FeedPost(
                id = "feed_3",
                authorId = "c4",
                authorUsername = "zanzibar_flavors",
                authorFullName = "Mpishi Salum",
                authorAvatarUrl = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=400&auto=format&fit=crop&q=80",
                isAuthorVerified = true,
                location = "Stone Town, Zanzibar",
                mediaUrls = listOf("https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800&auto=format&fit=crop&q=80"),
                caption = "Biriani ya kuku wa kienyeji yenye ladha ya karafuu halisi ya Zanzibar. Karibuni wote mlo wa mchana! 🍛🥥🌶️ #ZanzibarFood #Biriani #StoneTown",
                likesCount = 2150,
                isLiked = false,
                isSaved = false,
                commentsCount = 94,
                comments = listOf(),
                timeAgo = "saa 4 zilizopita"
            )
        )
    )
    val feedPosts: StateFlow<List<FeedPost>> = _feedPosts.asStateFlow()

    // Reels Feed Data
    private val _reels = MutableStateFlow(
        listOf(
            ReelItem(
                id = "reel_1",
                authorId = "c1",
                authorUsername = "juma_safari",
                authorAvatarUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&auto=format&fit=crop&q=80",
                isVerified = true,
                videoMediaUrl = "https://images.unsplash.com/photo-1516426122078-c23e76319801?w=800&auto=format&fit=crop&q=80",
                caption = "Kuteleza kwenye mawimbi ya bahari ya Hindi Zanzibar! Nani anataka safari kama hii? 🌊🏄‍♂️ #ZanzibarReels #MomoTanzania",
                musicTitle = "Marioo • Hakuna Matata (Official Audio)",
                likesCount = 5420,
                isLiked = false,
                commentsCount = 312,
                sharesCount = 189,
                isFollowing = true
            ),
            ReelItem(
                id = "reel_2",
                authorId = "c2",
                authorUsername = "amina_style",
                authorAvatarUrl = "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=400&auto=format&fit=crop&q=80",
                isVerified = true,
                videoMediaUrl = "https://images.unsplash.com/photo-1492684223066-81342ee5ff30?w=800&auto=format&fit=crop&q=80",
                caption = "Muonekano wa gauni jipya la kitenge lililobuniwa hapa Tanzania! Karibu upate lako 💃🏽✨ #SwahiliFashion",
                musicTitle = "Zuchu • Utaniua (Instrumental)",
                likesCount = 8910,
                isLiked = true,
                commentsCount = 540,
                sharesCount = 420,
                isFollowing = false
            ),
            ReelItem(
                id = "reel_3",
                authorId = "c3",
                authorUsername = "doreen_tech",
                authorAvatarUrl = "https://images.unsplash.com/photo-1517841905240-472988babdf9?w=400&auto=format&fit=crop&q=80",
                isVerified = false,
                videoMediaUrl = "https://images.unsplash.com/photo-1498050108023-c5249f4df085?w=800&auto=format&fit=crop&q=80",
                caption = "Vidokezo 3 vya kujifunza programu (coding) kwa simu yako ya mkononi ukiwa nyumbani 📱💻 #TechTanzania",
                musicTitle = "Alikiba • Mahaba (Afrobeats Remix)",
                likesCount = 3120,
                isLiked = false,
                commentsCount = 194,
                sharesCount = 105,
                isFollowing = false
            )
        )
    )
    val reels: StateFlow<List<ReelItem>> = _reels.asStateFlow()

    // Explore Items
    private val _exploreItems = MutableStateFlow(
        listOf(
            ExploreItem("e1", "https://images.unsplash.com/photo-1516426122078-c23e76319801?w=600&auto=format&fit=crop&q=80", true, "Safari", 1204),
            ExploreItem("e2", "https://images.unsplash.com/photo-1547471080-7cc2caa01a7e?w=600&auto=format&fit=crop&q=80", false, "Safari", 850),
            ExploreItem("e3", "https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=600&auto=format&fit=crop&q=80", false, "Chakula", 640),
            ExploreItem("e4", "https://images.unsplash.com/photo-1492684223066-81342ee5ff30?w=600&auto=format&fit=crop&q=80", true, "Muziki", 2400),
            ExploreItem("e5", "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=600&auto=format&fit=crop&q=80", false, "Mitindo", 950),
            ExploreItem("e6", "https://images.unsplash.com/photo-1498050108023-c5249f4df085?w=600&auto=format&fit=crop&q=80", false, "Teknolojia", 430),
            ExploreItem("e7", "https://images.unsplash.com/photo-1506744038136-46273834b3fb?w=600&auto=format&fit=crop&q=80", true, "Safari", 1520),
            ExploreItem("e8", "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?w=600&auto=format&fit=crop&q=80", false, "Vichekesho", 310)
        )
    )
    val exploreItems: StateFlow<List<ExploreItem>> = _exploreItems.asStateFlow()

    // Conversations / Direct Messages
    private val _conversations = MutableStateFlow(
        listOf(
            ConversationItem(
                id = "conv_1",
                user = MomoUser("c1", "juma_safari", "Juma Swahili Vibes", avatarUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&auto=format&fit=crop&q=80", isVerified = true),
                lastMessage = "Umeona ile video mpya ya Serengeti niliyopandisha? 🦁",
                lastMessageTime = "10:45",
                unreadCount = 2,
                isOnline = true
            ),
            ConversationItem(
                id = "conv_2",
                user = MomoUser("c2", "amina_style", "Amina Hassan", avatarUrl = "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=400&auto=format&fit=crop&q=80"),
                lastMessage = "Asante sana kwa kushiriki post yangu Baraka! ❤️",
                lastMessageTime = "Jana",
                unreadCount = 0,
                isOnline = false
            ),
            ConversationItem(
                id = "conv_3",
                user = MomoUser("c3", "doreen_tech", "Doreen Mushi", avatarUrl = "https://images.unsplash.com/photo-1517841905240-472988babdf9?w=400&auto=format&fit=crop&q=80"),
                lastMessage = "Tutaonana kwenye meetup ya tech kesho Dar!",
                lastMessageTime = "Jumatano",
                unreadCount = 0,
                isOnline = true
            )
        )
    )
    val conversations: StateFlow<List<ConversationItem>> = _conversations.asStateFlow()

    // Messages per conversation
    private val _messagesMap = MutableStateFlow(
        mapOf(
            "conv_1" to listOf(
                ChatMessage("m1", "c1", "Habari Baraka! Mambo vipi?", null, "10:30", false),
                ChatMessage("m2", "user_me", "Poa sana kaka Juma! Vipi safari za Arusha?", null, "10:32", true),
                ChatMessage("m3", "c1", "Huku ni pazuri mno, watalii wamejaa!", null, "10:35", false),
                ChatMessage("m4", "c1", "Umeona ile video mpya ya Serengeti niliyopandisha? 🦁", null, "10:45", false)
            )
        )
    )
    val messagesMap: StateFlow<Map<String, List<ChatMessage>>> = _messagesMap.asStateFlow()

    // Notifications
    private val _notifications = MutableStateFlow(
        listOf(
            NotificationItem(
                id = "n1",
                actorUsername = "amina_style",
                actorAvatarUrl = "https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=400&auto=format&fit=crop&q=80",
                messageSw = "amependa picha yako ya Zanzibar 🌊",
                messageEn = "liked your photo from Zanzibar 🌊",
                timeAgo = "dakika 5",
                postPreviewUrl = "https://images.unsplash.com/photo-1516426122078-c23e76319801?w=200&auto=format&fit=crop&q=80",
                isRead = false
            ),
            NotificationItem(
                id = "n2",
                actorUsername = "juma_safari",
                actorAvatarUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400&auto=format&fit=crop&q=80",
                messageSw = "ametoa maoni: 'Kazi nzuri sana Baraka! 👏'",
                messageEn = "commented: 'Great shot Baraka! 👏'",
                timeAgo = "saa 1",
                postPreviewUrl = "https://images.unsplash.com/photo-1547471080-7cc2caa01a7e?w=200&auto=format&fit=crop&q=80",
                isRead = false
            ),
            NotificationItem(
                id = "n3",
                actorUsername = "doreen_tech",
                actorAvatarUrl = "https://images.unsplash.com/photo-1517841905240-472988babdf9?w=400&auto=format&fit=crop&q=80",
                messageSw = "ameanza kukufuata",
                messageEn = "started following you",
                timeAgo = "saa 3",
                isFollowRequest = true,
                isRead = true
            )
        )
    )
    val notifications: StateFlow<List<NotificationItem>> = _notifications.asStateFlow()

    // Interaction functions
    fun toggleLikePost(postId: String) {
        _feedPosts.value = _feedPosts.value.map { post ->
            if (post.id == postId) {
                val newLiked = !post.isLiked
                post.copy(
                    isLiked = newLiked,
                    likesCount = post.likesCount + if (newLiked) 1 else -1
                )
            } else post
        }
    }

    fun toggleSavePost(postId: String) {
        _feedPosts.value = _feedPosts.value.map { post ->
            if (post.id == postId) post.copy(isSaved = !post.isSaved) else post
        }
    }

    fun addComment(postId: String, text: String) {
        val user = _currentUser.value
        val newComment = CommentItem(
            id = UUID.randomUUID().toString(),
            authorUsername = user.username,
            authorAvatarUrl = user.avatarUrl,
            text = text,
            timeAgo = "sasa hivi",
            likesCount = 0
        )
        _feedPosts.value = _feedPosts.value.map { post ->
            if (post.id == postId) {
                post.copy(
                    commentsCount = post.commentsCount + 1,
                    comments = listOf(newComment) + post.comments
                )
            } else post
        }
    }

    fun createPost(caption: String, mediaUrl: String, location: String? = null) {
        val user = _currentUser.value
        val newPost = FeedPost(
            id = "feed_${System.currentTimeMillis()}",
            authorId = user.id,
            authorUsername = user.username,
            authorFullName = user.fullName,
            authorAvatarUrl = user.avatarUrl,
            isAuthorVerified = user.isVerified,
            location = location,
            mediaUrls = listOf(mediaUrl),
            caption = caption,
            likesCount = 0,
            isLiked = false,
            isSaved = false,
            commentsCount = 0,
            timeAgo = "sasa hivi"
        )
        _feedPosts.value = listOf(newPost) + _feedPosts.value
        _userPosts.value = listOf(
            UserPostItem(
                id = newPost.id,
                mediaUrl = mediaUrl,
                likesCount = 0,
                commentsCount = 0,
                caption = caption
            )
        ) + _userPosts.value
        _currentUser.value = _currentUser.value.copy(
            postsCount = _currentUser.value.postsCount + 1
        )
    }

    fun toggleLikeReel(reelId: String) {
        _reels.value = _reels.value.map { reel ->
            if (reel.id == reelId) {
                val newLiked = !reel.isLiked
                reel.copy(
                    isLiked = newLiked,
                    likesCount = reel.likesCount + if (newLiked) 1 else -1
                )
            } else reel
        }
    }

    fun sendMessage(conversationId: String, text: String) {
        val currentList = _messagesMap.value[conversationId] ?: emptyList()
        val newMessage = ChatMessage(
            id = UUID.randomUUID().toString(),
            senderId = "user_me",
            text = text,
            time = "10:52",
            isMine = true
        )
        val updatedMap = _messagesMap.value.toMutableMap()
        updatedMap[conversationId] = currentList + newMessage
        _messagesMap.value = updatedMap

        _conversations.value = _conversations.value.map { conv ->
            if (conv.id == conversationId) {
                conv.copy(lastMessage = text, lastMessageTime = "10:52", unreadCount = 0)
            } else conv
        }
    }

    fun toggleFollowCreator(id: String) {
        _suggestedCreators.value = _suggestedCreators.value.map { creator ->
            if (creator.id == id) {
                val newStatus = !creator.isFollowing
                _currentUser.value = _currentUser.value.copy(
                    followingCount = _currentUser.value.followingCount + if (newStatus) 1 else -1
                )
                creator.copy(isFollowing = newStatus)
            } else creator
        }
    }

    fun updateProfile(
        fullName: String,
        username: String,
        bio: String,
        website: String,
        isPrivate: Boolean,
        avatarUrl: String? = null
    ) {
        _currentUser.value = _currentUser.value.copy(
            fullName = fullName,
            username = username,
            bio = bio,
            website = website,
            isPrivate = isPrivate,
            avatarUrl = avatarUrl ?: _currentUser.value.avatarUrl
        )
    }

    fun setInterests(selectedIds: List<String>) {
        _currentUser.value = _currentUser.value.copy(selectedInterests = selectedIds)
    }

    fun registerUser(fullName: String, username: String, target: String, dob: String): MomoUser {
        val isEmail = target.contains("@")
        val newUser = MomoUser(
            id = UUID.randomUUID().toString(),
            username = username,
            fullName = fullName,
            email = if (isEmail) target else null,
            phone = if (!isEmail) target else null,
            bio = "Habari, nimejiunga na Momo hivi punde! 🎉",
            avatarUrl = presetAvatars.first(),
            postsCount = 0,
            followersCount = 0,
            followingCount = 0
        )
        _currentUser.value = newUser
        return newUser
    }

    fun loginUser(login: String): MomoUser {
        val user = _currentUser.value
        val updated = if (login.contains("@")) user.copy(email = login) else user.copy(username = login)
        _currentUser.value = updated
        return updated
    }
}
