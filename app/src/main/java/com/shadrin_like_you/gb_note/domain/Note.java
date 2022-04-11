package com.shadrin_like_you.gb_note.domain;

import java.util.Date;

public class Note {
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


}
