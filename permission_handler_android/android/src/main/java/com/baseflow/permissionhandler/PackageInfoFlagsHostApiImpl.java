package com.baseflow.permissionhandler;

import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.baseflow.instancemanager.InstanceManager;
import com.baseflow.permissionhandler.PermissionHandlerPigeon.PackageInfoFlagsHostApi;

/**
 * Host API implementation for `PackageInfoFlags`.
 *
 * <p>This class may handle instantiating and adding native object instances that are attached to a
 * Dart instance or handle method calls on the associated native class or an instance of the class.
 */
public class PackageInfoFlagsHostApiImpl implements PackageInfoFlagsHostApi {
    private final InstanceManager instanceManager;

    private final PackageInfoFlagsFlutterApiImpl flutterApi;

    /**
     * Constructs an {@link PackageInfoFlagsHostApiImpl}.
     *
     * @param instanceManager maintains instances stored to communicate with attached Dart objects
     */
    public PackageInfoFlagsHostApiImpl(
        @NonNull PackageInfoFlagsFlutterApiImpl flutterApi,
        @NonNull InstanceManager instanceManager
    ) {
        this.flutterApi = flutterApi;
        this.instanceManager = instanceManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @NonNull
    @Override
    public String of(@NonNull Long value) {
        final PackageManager.PackageInfoFlags flags = PackageManager.PackageInfoFlags.of(value);
        flutterApi.create(flags);
        return instanceManager.getIdentifierForStrongReference(flags).toString();
    }
}