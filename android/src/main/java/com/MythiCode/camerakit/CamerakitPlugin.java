package com.MythiCode.camerakit;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.platform.PlatformViewRegistry;

/**
 * CamerakitPlugin
 */
public class CamerakitPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
//  private MethodChannel channel;
    private PlatformViewRegistry registery;
    private DartExecutor dartExecuter;


    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
//    channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "plugins/camera_kit");
        registery = flutterPluginBinding.getFlutterEngine().getPlatformViewsController().getRegistry();
        dartExecuter = flutterPluginBinding.getFlutterEngine().getDartExecutor();

//    System.out.println("onAttachedToEngine");


    }

    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
//    public static void registerWith(Registrar registrar) {
////    final MethodChannel channel = new MethodChannel(registrar.messenger(), "camerakit");
////    channel.setMethodCallHandler(new CamerakitPlugin());
//
////    if (registrar.activity() != null) {
////      registrar
////              .platformViewRegistry()
////              .registerViewFactory(
////                      "plugins/camera_kit"
////                      , new CameraKitFactory(registrar));
////    }
//    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {

    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        System.out.println("onAttachedToActivity");
        if (binding.getActivity() != null) {
            registery
                    .registerViewFactory(
                            "plugins/camera_kit"
                            , new CameraKitFactory(binding, dartExecuter));
        }
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {

    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {

    }

    @Override
    public void onDetachedFromActivity() {

    }
}
