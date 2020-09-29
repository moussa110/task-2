package com.example.task2.ui.MainActivity

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.task2.api.model.ReposResponse
import kotlinx.coroutines.launch
import mahmoud.moussa.newsapp.repos.impl.sources.ReposRepositoryImpl
import mahmoud.moussa.newsapp.repos.interfaces.sources.ReposRepository

class MainActivityViewModel @ViewModelInject constructor(
    application: Application,
    val reposRepository: ReposRepositoryImpl,
    val reposesRepository: ReposRepository
) : AndroidViewModel(application) {
    val reposResponseLiveData = MutableLiveData<List<ReposResponse.ReposResponseItem>?>()
    val reposErrLiveData = MutableLiveData<String?>();

    fun getReposFromApi() {

        /*   viewModelScope.launch {
               try {
                   val data=ApiManager(getApplication())
                       .getWebServices()
                       .getAllRepos();

                   reposResponseLiveData.value=data;
               }catch (t:Throwable){
                   reposErrLiveData.value=t.localizedMessage;
               }
           }*/

        viewModelScope.launch {
            try {
                val list = reposRepository.getReposes()
                reposResponseLiveData.value = list
            } catch (t: Throwable) {
                reposErrLiveData.value = t.localizedMessage;
            }
        }
    }
}