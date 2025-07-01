package com.configbridge

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import android.util.Log
import java.lang.reflect.Field

class ConfigBridgeModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

    private fun resolveBuildConfig(): Class<*>? {
    return try {
        // Intenta con diferentes variantes hasta encontrar una que funcione
        val packageName = reactApplicationContext.packageName
        listOf(
            packageName,
            packageName.substringBeforeLast('.'),
            packageName.substringBeforeLast('.').substringBeforeLast('.')
        ).firstNotNullOfOrNull { pkg ->
            try {
                Class.forName("$pkg.BuildConfig")
            } catch (e: ClassNotFoundException) {
                null
            }
        }
    } catch (e: Exception) {
        Log.e(NAME, "Failed to resolve BuildConfig", e)
        null
    }
  }

  override fun getConstants(): Map<String, Any> {
    val constants = mutableMapOf<String, Any>()
    val buildConfig = resolveBuildConfig() ?: return constants

    try {
      buildConfig.declaredFields.forEach { field ->
        try {
          constants[field.name] = field.get(null) ?: ""
        } catch (e: Exception) {
          Log.w(NAME, "Cannot access field: ${field.name}", e)
        }
      }
    } catch (e: Exception) {
      Log.e(NAME, "Error processing BuildConfig", e)
    }

    return constants
  }

  companion object {
    const val NAME = "ConfigBridge"
  }
}
