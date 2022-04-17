package com.shadrin_like_you.gb_note.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {
    private String title;           //заголовок
    private String content;         //содержимое
    private Date createDate;        //дата и время создания
    private boolean state;          //состояние (отметка о выполнении)
    int icon;                       //иконка

    public Note(String title, String content, Date createDate, boolean state, int icon) {
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.state = state;
        this.icon = icon;

    }

    public Note(String string) {
    }

    protected Note(Parcel in) {
        title = in.readString();
        content = in.readString();
        state = in.readByte() != 0;
        icon = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public boolean isState() {
        return state;
    }

    public int getIcon() {
        return icon;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeByte((byte) (state ? 1 : 0));
        parcel.writeInt(icon);
    }
}
