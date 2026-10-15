export interface UserProfile {
  id: string;
  username: string;
  fullName: string;
  email?: string;
  phone?: string;
  bio?: string;
  website?: string;
  avatarUrl?: string;
  isPrivate: boolean;
  isVerified: boolean;
  postsCount: number;
  followersCount: number;
  followingCount: number;
  likesCount: number;
  language: 'sw' | 'en';
  theme: 'system' | 'light' | 'dark';
}

export interface RegisterInput {
  target: string; // email or phone
  fullName: string;
  username: string;
  password: string;
  dob: string; // YYYY-MM-DD
}

export interface LoginInput {
  login: string; // username, email or phone
  password: string;
}

export interface OtpVerificationInput {
  target: string;
  code: string;
  purpose: 'REGISTRATION' | 'PASSWORD_RESET' | 'LOGIN';
}

export interface AuthTokens {
  accessToken: string;
  refreshToken: string;
  expiresIn: number;
}

export interface AuthResponse {
  success: boolean;
  user?: UserProfile;
  tokens?: AuthTokens;
  requiresOtp?: boolean;
  message?: string;
}

export const INTEREST_TOPICS = [
  { id: 'music', nameSw: 'Muziki', nameEn: 'Music', icon: '🎵' },
  { id: 'sports', nameSw: 'Michezo', nameEn: 'Sports', icon: '⚽' },
  { id: 'food', nameSw: 'Chakula', nameEn: 'Food', icon: '🍲' },
  { id: 'fashion', nameSw: 'Mitindo', nameEn: 'Fashion', icon: '👗' },
  { id: 'comedy', nameSw: 'Vichekesho', nameEn: 'Comedy', icon: '😂' },
  { id: 'education', nameSw: 'Elimu', nameEn: 'Education', icon: '📚' },
  { id: 'business', nameSw: 'Biashara', nameEn: 'Business', icon: '💼' },
  { id: 'tech', nameSw: 'Teknolojia', nameEn: 'Tech', icon: '📱' },
  { id: 'religion', nameSw: 'Dini & Imani', nameEn: 'Faith', icon: '🙏' },
  { id: 'travel', nameSw: 'Safari & Utalii', nameEn: 'Travel', icon: '✈️' },
] as const;
