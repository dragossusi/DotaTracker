package com.rachierudragos.dotatracker.Wrapper.parcel;

import android.os.Parcel;
import android.os.Parcelable;

import com.rachierudragos.dotatracker.Wrapper.match.Chat;

import java.util.List;

/**
 * Created by Dragos on 07-Nov-17.
 */

public class ChatListParcel implements Parcelable {
    List<Chat> chats;

    public ChatListParcel(List<Chat> chats) {
        this.chats = chats;
    }

    protected ChatListParcel(Parcel in) {
        chats = in.createTypedArrayList(Chat.CREATOR);
    }

    public static final Creator<ChatListParcel> CREATOR = new Creator<ChatListParcel>() {
        @Override
        public ChatListParcel createFromParcel(Parcel in) {
            return new ChatListParcel(in);
        }

        @Override
        public ChatListParcel[] newArray(int size) {
            return new ChatListParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(chats);
    }

    public List<Chat> getList() {
        return chats;
    }
}
