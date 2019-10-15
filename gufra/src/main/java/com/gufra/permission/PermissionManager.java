package com.gufra.permission;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;


/**
 * 权限申请
 * @author yinchaoyin
 *
 */
public abstract class PermissionManager {

    private static String TAG = "FeaturePermissionUtil";

    private static int requestStorage = 11;
    private static int ACTIVITY_PERMISSION_REQUEST = 1000;

    /**检查权限*/
    public static void requestPermissions(final Activity context, final PermissionCallback callback) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            callback.permissionAllowed(requestStorage);
        }else if(ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            showPermissionDialog(context,callback,"进行以后的便宜，需要授予以下权限");
        }else{
            callback.permissionAllowed(requestStorage);
        }
    }

    /**权限说明对话框*/
    protected static void showPermissionDialog(final Activity context,final PermissionCallback callback, String description){
        AlertDialog builder = new  AlertDialog.Builder(context)
                .setTitle("权限申请")
                .setCancelable(false)
                .setMessage(description)
                .setPositiveButton("继续", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //申请权限
                        if (ActivityCompat.shouldShowRequestPermissionRationale(context,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                            Log.d(TAG,"需要允许权限~");
                            ActivityCompat.requestPermissions(context,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},requestStorage);
                        }else{
                            Log.d(TAG,"已允许/拒绝权限~");
                            ActivityCompat.requestPermissions(context,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},requestStorage);
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG,"取消权限授予");
                        callback.permissionCancled(requestStorage);
                    }
                })
                .create();
        builder.show();
    }



    /**设置返回值后检查权限
     * activityd的result里接收跳转设置返回的requestCode进行再次_requestPermissions
     * */
    public static void _requestPermissions(final Activity context, final PermissionCallback callback){
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            showPermissionDialog(context,callback,"对以后操作便宜，需要授予以下权限");
        }else{
            ActivityCompat.requestPermissions(context,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},requestStorage);
        }
    }

    /**权限回调*/
    public static void onRequestPermissionsResult(PermissionCallback callback, int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == requestStorage){
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "permission has now been granted. Showing preview.");
                AlertDialog builder = new  AlertDialog.Builder(getActivity(callback.getSource()))
                        .setMessage("恭喜您，已授予所需要的权限。")
                        .setPositiveButton("继续",null)
                        .show();
                callback.permissionAllowed(requestStorage);
            } else {
                /**跳转到设置*/
                Log.d(TAG, "permission was NOT granted.");
                Intent intent = new Intent();  intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getActivity(callback.getSource()).getPackageName(), null);
                intent.setData(uri);

                getActivity(callback.getSource()).startActivityForResult(intent,ACTIVITY_PERMISSION_REQUEST);

            }
        }
    }
    /**获取activity*/
    public static Activity getActivity(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        } else if (object instanceof Activity) {
            return (Activity) object;
        }
        return null;
    }

}
