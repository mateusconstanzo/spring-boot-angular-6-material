package br.com.events.auth;

import br.com.events.auth.entity.UserEntity;

import java.util.Optional;

public class SecurityHolder {

    private static ThreadLocal<String> tenantHolder = new ThreadLocal<>();

    public static void setUserId(String userId) {
        tenantHolder.set(userId);
    }

    public static Optional<Integer> getUserId() {
        return Optional.of(Integer.valueOf(tenantHolder.get()));
    }

    public static UserEntity getUser() {

        Optional<Integer> userId = getUserId();

        if (userId.isPresent()) {
            return new UserEntity(userId.get());
        }

        return null;
    }

    public static void clear() {
        tenantHolder.remove();
    }

}