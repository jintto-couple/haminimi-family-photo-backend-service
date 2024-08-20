package com.haminime.photo.service.dto;

public final class UserAuthenticationHolder {

    private static final ThreadLocal<UserAuthentication> tlUserAuthentication = new ThreadLocal<>();

    public static UserAuthentication getUserAuthentication() {
        return tlUserAuthentication.get();
    }

    public static void setUserAuthentication(UserAuthentication userAuthentication) {
        tlUserAuthentication.set(userAuthentication);
    }

    public static void removeUserAuthentication() {
        tlUserAuthentication.remove();
    }
}
