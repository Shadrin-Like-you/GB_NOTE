package com.shadrin_like_you.gb_note.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {

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
    private final String name;
    int icon;                       //иконка
    private String title;           //заголовок
    private String content;         //содержимое
    private Date createDate;        //дата и время создания
    private boolean state;          //состояние (отметка о выполнении)

    public Note(String name, int icon) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.icon = icon;

    }

    protected Note(Parcel in) {
        name = in.readString();
//        title = in.readString();
//        content = in.readString();
//        state = in.readByte() != 0;
        icon = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
//        dest.writeString(title);
//        dest.writeString(content);
        dest.writeInt(icon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getIcon() {
        return icon;
    }
    public String getName() {
        return name;
    }

}
