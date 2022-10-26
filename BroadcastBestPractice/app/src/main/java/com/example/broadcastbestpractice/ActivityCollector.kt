package com.example.broadcastbestpractice

import android.app.Activity

object ActivityCollector {
    // 创建一个list来保存所有的Activity
    private val activities = ArrayList<Activity>()
//    添加Activity
    fun addActivity(activity: Activity) {
        activities.add(activity)
    }
    //    删除Activity
    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }
    //    关闭所有Activity
    fun finishAll() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activities.clear()
    }
}
