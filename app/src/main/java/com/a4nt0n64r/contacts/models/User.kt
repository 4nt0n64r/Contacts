package com.a4nt0n64r.contacts.models

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserDataFromApi(
    @SerializedName("results")
    val users: List<User>
)

//@TypeConverters(NameTypeConverter::class,PictureTypeConverter::class)
data class User(
    @SerializedName("name")
    val name: Name,
    @SerializedName("email")
    @ColumnInfo(name = "email_field") val email: String,
    @SerializedName("picture")
    @ColumnInfo(name = "picture_field") val picture: Picture
)

data class Picture(
    @SerializedName("thumbnail")
    val thumbnailLink: String,
    @SerializedName("medium")
    val mediumLink: String
)

data class Name(
    @SerializedName("first")
    val name: String,
    @SerializedName("last")
    val surname: String
)


fun User.toSimpleUser(): SimpleUser {
    return SimpleUser(
        id = hashCode(),
        name = this.name.name,
        surname = this.name.surname,
        email = this.email,
        smallImgLink = this.picture.thumbnailLink,
        bigImgLink = this.picture.mediumLink
    )
}


@Entity(tableName = "users_table")
data class SimpleUser(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name_field")
    val name: String,
    @ColumnInfo(name = "surname_field")
    val surname: String,
    @ColumnInfo(name = "email_field")
    val email: String,
    @ColumnInfo(name = "small_img_field")
    val smallImgLink: String,
    @ColumnInfo(name = "big_img_field")
    val bigImgLink: String
): Serializable


class NameTypeConverter {
    @TypeConverter
    fun fromName(name: Name): String {
        return name.name + "," + name.surname
    }

    @TypeConverter
    fun toName(data: String): Name {
        val namePlusSurname = data.split(",")
        return Name(namePlusSurname[0],namePlusSurname[1])
    }
}

class PictureTypeConverter {
    @TypeConverter
    fun fromPicture(pic: Picture): String {
        return pic.thumbnailLink + "," + pic.mediumLink
    }

    @TypeConverter
    fun toPicture(data: String): Picture {
        val pic = data.split(",")
        return Picture(pic[0],pic[1])
    }
}

