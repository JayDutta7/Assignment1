package com.test.assignment.network.entity

import com.google.gson.annotations.SerializedName

data class TestResponse(
    @SerializedName("success")
    val success: Boolean? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    val data: Data? = null,
)

data class Data(
    @SerializedName("app_list")
    val appList: ArrayList<AppList>? = null
)


data class AppList(
    @SerializedName("app_id")
    val appId: Int? = null,
    @SerializedName("fk_kid_id")
    val fkKidId: Int? = null,
    @SerializedName("kid_profile_image")
    val kidProfileImage: String? = null,
    @SerializedName("app_name")
    val appName: String? = null,
    @SerializedName("app_icon")
    val appIcon: String? = null,
    @SerializedName("app_package_name")
    val appPackageName: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("is_select")
    var isSelect: Boolean = false,
) {
    @JvmName("setSelected1")
    fun setSelected(_selected: Boolean) {
        isSelect = _selected
    }

    @JvmName("getSelected1")
    fun getSelected(): Boolean {
        return isSelect
    }
}

