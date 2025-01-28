package com.exammanager.common.security;

public class UserInfoProvider {

    private static final ThreadLocal<UserInfo> userInfo = new ThreadLocal<>();

    public static UserInfo getUserInfo() {
        return userInfo.get();
    }

    static void setUserInfo(UserInfo userInfo) {
        UserInfoProvider.userInfo.set(userInfo);
    }
}
