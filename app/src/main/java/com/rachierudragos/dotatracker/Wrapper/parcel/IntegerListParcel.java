package com.rachierudragos.dotatracker.Wrapper.parcel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 07-Nov-17.
 */

public class IntegerListParcel implements Parcelable {
    List<Integer> list;

    public IntegerListParcel(List<Integer> list) {
        this.list = list;
    }

    protected IntegerListParcel(Parcel in) {
        list = new ArrayList<>();
        in.readList(list,null);
    }

    public static final Creator<IntegerListParcel> CREATOR = new Creator<IntegerListParcel>() {
        @Override
        public IntegerListParcel createFromParcel(Parcel in) {
            return new IntegerListParcel(in);
        }

        @Override
        public IntegerListParcel[] newArray(int size) {
            return new IntegerListParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(list);
    }

    public List<Integer> getList() {
        return list;
    }
}
