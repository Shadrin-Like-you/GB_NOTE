package com.shadrin_like_you.gb_note.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsVM extends ViewModel {

    private MutableLiveData<String> mText;

    public SettingsVM() {
        mText = new MutableLiveData<>();
        mText.setValue("Настройки фрагмента");
    }

    public LiveData<String> getText() {

          /*
        LiveData хранилище данных, работающее по принципу паттерна Observer (наблюдатель).
        Это хранилище умеет делать две вещи:

        1) В него можно поместить какой-либо объект

        2) На него можно подписаться и получать объекты, которые в него помещают.

        Т.е. с одной стороны кто-то помещает объект в хранилище, а с другой стороны кто-то
        подписывается и получает этот объект.

         В качестве аналогии можно привести, например, каналы в Telegram. Автор пишет пост и
          отправляет его в канал, а все подписчики получают этот пост.

       */

        return mText;
    }
}
