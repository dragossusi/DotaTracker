package com.rachierudragos.dotatracker.Wrapper.match;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 03-Nov-17.
 */

public class MatchUnit implements Parcelable {
    public String unitname;
    public int item_0;
    public int item_1;
    public int item_2;
    public int item_3;
    public int item_4;
    public int item_5;
    public int backpack_0;
    public int backpack_1;
    public int backpack_2;

    protected MatchUnit(Parcel in) {
        unitname = in.readString();
        item_0 = in.readInt();
        item_1 = in.readInt();
        item_2 = in.readInt();
        item_3 = in.readInt();
        item_4 = in.readInt();
        item_5 = in.readInt();
        backpack_0 = in.readInt();
        backpack_1 = in.readInt();
        backpack_2 = in.readInt();
    }

    public static final Creator<MatchUnit> CREATOR = new Creator<MatchUnit>() {
        @Override
        public MatchUnit createFromParcel(Parcel in) {
            return new MatchUnit(in);
        }

        @Override
        public MatchUnit[] newArray(int size) {
            return new MatchUnit[size];
        }
    };

    public List<Integer> getItems() {
        List<Integer> items = new ArrayList<>();
        items.add(item_0);
        items.add(item_1);
        items.add(item_2);
        items.add(item_3);
        items.add(item_4);
        items.add(item_5);
        items.add(backpack_0);
        items.add(backpack_1);
        items.add(backpack_2);
        return items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(unitname);
        parcel.writeInt(item_0);
        parcel.writeInt(item_1);
        parcel.writeInt(item_2);
        parcel.writeInt(item_3);
        parcel.writeInt(item_4);
        parcel.writeInt(item_5);
        parcel.writeInt(backpack_0);
        parcel.writeInt(backpack_1);
        parcel.writeInt(backpack_2);
    }
}
