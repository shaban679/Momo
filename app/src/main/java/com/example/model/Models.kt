package com.example.model

data class MomoUser(
    val id: String,
    val username: String,
    val fullName: String,
    val email: String? = null,
    val phone: String? = null,
    val bio: String = "",
    val website: String = "",
    val avatarUrl: String = "",
    val isPrivate: Boolean = false,
    val isVerified: Boolean = false,
    val postsCount: Int = 0,
    val followersCount: Int = 0,
    val followingCount: Int = 0,
    val selectedInterests: List<String> = emptyList()
)

data class InterestTopic(
    val id: String,
    val nameSw: String,
    val nameEn: String,
    val icon: String,
    val isSelected: Boolean = false
)

data class SuggestedCreator(
    val id: String,
    val username: String,
    val fullName: String,
    val avatarUrl: String,
    val category: String,
    val followersText: String,
    val isFollowing: Boolean = false
)

data class StoryHighlight(
    val id: String,
    val title: String,
    val coverEmojiOrUrl: String
)

data class UserPostItem(
    val id: String,
    val mediaUrl: String,
    val isVideo: Boolean = false,
    val likesCount: Int = 0,
    val commentsCount: Int = 0,
    val caption: String = ""
)

data class CommentItem(
    val id: String,
    val authorUsername: String,
    val authorAvatarUrl: String,
    val text: String,
    val timeAgo: String,
    val likesCount: Int = 0,
    val isLiked: Boolean = false
)

data class FeedPost(
    val id: String,
    val authorId: String,
    val authorUsername: String,
    val authorFullName: String,
    val authorAvatarUrl: String,
    val isAuthorVerified: Boolean = false,
    val location: String? = null,
    val mediaUrls: List<String>,
    val isVideo: Boolean = false,
    val caption: String,
    val likesCount: Int,
    val isLiked: Boolean = false,
    val isSaved: Boolean = false,
    val commentsCount: Int,
    val comments: List<CommentItem> = emptyList(),
    val timeAgo: String
)

data class StoryItem(
    val id: String,
    val authorId: String,
    val authorUsername: String,
    val authorAvatarUrl: String,
    val mediaUrl: String,
    val isSeen: Boolean = false,
    val timeAgo: String,
    val textOverlay: String? = null
)

data class ReelItem(
    val id: String,
    val authorId: String,
    val authorUsername: String,
    val authorAvatarUrl: String,
    val isVerified: Boolean = false,
    val videoMediaUrl: String,
    val caption: String,
    val musicTitle: String,
    val likesCount: Int,
    val isLiked: Boolean = false,
    val commentsCount: Int,
    val sharesCount: Int,
    val isSaved: Boolean = false,
    val isFollowing: Boolean = false
)

data class NotificationItem(
    val id: String,
    val actorUsername: String,
    val actorAvatarUrl: String,
    val messageSw: String,
    val messageEn: String,
    val timeAgo: String,
    val postPreviewUrl: String? = null,
    val isFollowRequest: Boolean = false,
    val isRead: Boolean = false
)

data class ChatMessage(
    val id: String,
    val senderId: String,
    val text: String,
    val mediaUrl: String? = null,
    val time: String,
    val isMine: Boolean
)

data class ConversationItem(
    val id: String,
    val user: MomoUser,
    val lastMessage: String,
    val lastMessageTime: String,
    val unreadCount: Int = 0,
    val isOnline: Boolean = false
)

data class ExploreItem(
    val id: String,
    val mediaUrl: String,
    val isVideo: Boolean = false,
    val category: String,
    val likesCount: Int
)

sealed class Screen {
    object Splash : Screen()
    object LanguageSelect : Screen()
    object Onboarding : Screen()
    object Login : Screen()
    object Register : Screen()
    data class OtpVerification(val target: String, val purpose: String) : Screen()
    object ForgotPassword : Screen()
    object ProfileSetup : Screen()
    object MainApp : Screen()
    object EditProfile : Screen()
    data class StoryViewer(val initialIndex: Int) : Screen()
    object Notifications : Screen()
    object DirectMessages : Screen()
    data class ChatDetail(val conversationId: String) : Screen()
}

enum class MainTab {
    HOME,
    EXPLORE,
    CREATE,
    REELS,
    PROFILE
}
