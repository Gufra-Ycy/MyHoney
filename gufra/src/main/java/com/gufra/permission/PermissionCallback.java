package com.gufra.permission;

public interface PermissionCallback <T> {
    void permissionAllowed(int requestCode);
    void permissionCancled(int requestCode);

    T getSource();
}
