package com.wrl.exercise.util

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.text.TextUtils

/**
 * @author wangrulin
 * @description:
 * @date :2019-09-23 14:45
 */
class Utils {

    companion object {
        fun checkInstall(context: Context,packageName : String) : Boolean{
            var packageInfo :PackageInfo ?= null
            try {
                packageInfo = context.packageManager.getPackageInfo(packageName,0)
            }catch (e : Exception) {
                e.printStackTrace()
            }
            return packageInfo != null
        }

        fun openApp (context: Context,packageName: String) : Boolean{
            val pkgContext = getPackageContext(context, packageName)
            val intent = getAppOpenIntentByPackageName(context, packageName)
            if (pkgContext != null && intent != null) {
                pkgContext.startActivity(intent)
                return true
            }
            return false
        }

        @SuppressLint("WrongConstant")
        fun getAppOpenIntentByPackageName(context: Context, packageName: String) : Intent? {
            var mainAct : String? = null
            var pakg = context.packageManager
            var intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.setPackage(packageName)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
            val queryIntentActivities = pakg.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES)
                    ?: return null

            for (index in 0..queryIntentActivities.size step 1) {
                val resolveInfo = queryIntentActivities[index]
                if (resolveInfo != null && resolveInfo.activityInfo.packageName == packageName) {
                    mainAct = resolveInfo.activityInfo.name
                    break
                }
            }

            if (TextUtils.isEmpty(mainAct)) {
                return null
            }
            intent.component = ComponentName(packageName,mainAct)
            return intent
        }

        fun getPackageContext(context: Context, packageName: String): Context? {
            var pkgContext: Context? = null
            if (context.packageName == packageName) {
                pkgContext = context
            } else {
                // 创建第三方应用的上下文环境
                try {
                    pkgContext = context.createPackageContext(packageName,
                            Context.CONTEXT_IGNORE_SECURITY or Context.CONTEXT_INCLUDE_CODE)
                } catch (e: PackageManager.NameNotFoundException) {
                    e.printStackTrace()
                }

            }
            return pkgContext
        }
    }


}