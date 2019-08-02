package com.firehook.doittask.network.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Vladyslav Bondar on 19.07.2019
 * Skype: diginital
 */

@Entity(tableName = "taskStore")
data class Task(

    @SerializedName("id")       @ColumnInfo(name = "_id")         @Expose var id: Int,
    @SerializedName("title")    @ColumnInfo(name = "name")        @Expose var name: String?,
    @SerializedName("priority") @ColumnInfo(name = "priority")    @Expose var priority: String?,
    @SerializedName("dueBy")    @ColumnInfo(name = "dueBy")       @Expose var expiration: Long,
                                     @ColumnInfo(name = "action")      var action: Int,
                                     @ColumnInfo(name = "is_reminder") var isReminder: Boolean) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readInt(),
        parcel.readValue(Boolean::class.java.classLoader) as Boolean
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(priority)
        parcel.writeLong(expiration)
        parcel.writeInt(action)
        parcel.writeValue(isReminder)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }
}