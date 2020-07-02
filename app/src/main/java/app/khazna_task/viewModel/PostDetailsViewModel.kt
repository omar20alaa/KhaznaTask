package com.app.khazna_task.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class PostDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private var userId: MutableLiveData<String>? = MutableLiveData()
    fun getText(text: String): String {
        if (userId == null) {
            userId = MutableLiveData()
            userId!!.value = text
        }
        return text
    }
}