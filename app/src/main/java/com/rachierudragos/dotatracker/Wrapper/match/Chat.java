package com.rachierudragos.dotatracker.Wrapper.match;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dragos on 03-Nov-17.
 */
public class Chat implements Parcelable {
    public int time;
    public String type;
    public String unit;
    public String key;
    public int slot;
    public int player_slot;

    protected Chat(Parcel in) {
        time = in.readInt();
        type = in.readString();
        unit = in.readString();
        key = in.readString();
        slot = in.readInt();
        player_slot = in.readInt();
    }

    public static final Creator<Chat> CREATOR = new Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel in) {
            return new Chat(in);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };

    public int getTime() {
        return time;
    }

    public String getName() {
        return unit;
    }

    public String getMessage() {
        return key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(time);
        parcel.writeString(type);
        parcel.writeString(unit);
        parcel.writeString(key);
        parcel.writeInt(slot);
        parcel.writeInt(player_slot);
    }
}
