package com.shadrin_like_you.gb_note.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Objects;

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
    int icon;                       //иконка
    private String id;
    private String title;           //заголовок
    private String content;         //содержимое
    private Date createDate;        //дата и время создания
    private boolean state;          //состояние (отметка о выполнении)

    public Note(String id, String title, String content, Date createDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.icon = icon;
        this.createDate = createDate;

    }

    protected Note(Parcel in) {
        id = in.readString();
        title = in.readString();
        content = in.readString();
        state = in.readByte() != 0;
        icon = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(icon);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id) && Objects.equals(title, note.title) && Objects.equals(content, note.content) && Objects.equals(createDate, note.createDate) && Objects.equals(icon, note.icon) && Objects.equals(state, note.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createDate, icon, state);
    }


}
