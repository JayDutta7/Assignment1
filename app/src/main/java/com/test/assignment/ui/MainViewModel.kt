package com.test.assignment.ui

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.assignment.network.APIExceptions
import com.test.assignment.network.entity.Data
import com.test.assignment.util.InternetUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val application: Application,
    private val mainRepository: MainRepository
) : ViewModel() {

    val testResponse: MutableLiveData<Data?> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()


    fun fetchTestApi(kid_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!InternetUtil.isInternetOn()) {
                errorMessage.postValue("No internet connection")
            } else {
                try {
                    loadingVisibility.postValue(View.VISIBLE)
                    val getTestApiResponse = mainRepository.testApi(kid_id)
                    if (getTestApiResponse?.success == true)
                        testResponse.postValue(getTestApiResponse.data)
                    else
                        testResponse.postValue(null)
                } catch (e: APIExceptions) {
                    errorMessage.postValue(e.message)
                    Log.e("Exception", "----->${e}")
                } catch (e: Exception) {
                    errorMessage.postValue(e.message)
                    Log.e("Exception", "----->${e}")
                } finally {
                    loadingVisibility.postValue(View.GONE)
                }

            }
        }
    }


}