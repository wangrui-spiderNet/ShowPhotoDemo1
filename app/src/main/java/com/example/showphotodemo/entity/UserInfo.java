package com.example.showphotodemo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 94540 on 2015/11/21.
 */
public class UserInfo implements Parcelable {

   /* {
        "profile-image": "http://img2.findthebest.com/sites/default/files/688/media/images/Mingle_159902_i0.png",
            "avatar": "http://info.thoughtworks.com/rs/thoughtworks2/images/glyph_badge.png",
            "nick": "John Smith",
            "username": "jsmith"
    }*/

    private int user_id;
    private String profile_image;
    private String avatar;
    private String nick;
    private String username;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.user_id);
        dest.writeString(this.profile_image);
        dest.writeString(this.avatar);
        dest.writeString(this.nick);
        dest.writeString(this.username);

    }

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        this.user_id = in.readInt();
        this.profile_image = in.readString();
        this.avatar = in.readString();
        this.nick = in.readString();
        this.username = in.readString();
    }

    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
