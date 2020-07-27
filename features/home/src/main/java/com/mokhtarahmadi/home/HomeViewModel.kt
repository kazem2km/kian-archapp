package com.mokhtarahmadi.home

import androidx.lifecycle.*
import com.mokhtarahmadi.common.base.BaseViewModel
import com.mokhtarahmadi.common.utils.Event
import com.mokhtarahmadi.domain.usecase.home.GetTopUsersUseCase
import com.mokhtarahmadi.domain.utils.AppDispatchers
import com.mokhtarahmadi.domain.utils.Resource
import com.mokhtarahmadi.model.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [BaseViewModel] that provide the data and handle logic to communicate with the model
 * for [HomeFragment].
 */
class HomeViewModel(private val getTopUsersUseCase: GetTopUsersUseCase,
                    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    // FOR DATA
    private val _users = MediatorLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>> get() = _users
    private var usersSource: LiveData<Resource<List<User>>> = MutableLiveData()

    init {
        getUsers()
    }

    // PUBLIC ACTIONS ---
    fun userClicksOnItem(user: User)
            = navigate(
        HomeFragmentDirections.actionHomeFragmentToDetailFragment(
            user.login
        )
    )

    fun userRefreshesItems() = getUsers()

    // ---

    private fun getUsers() = viewModelScope.launch(dispatchers.main) {
        _users.removeSource(usersSource) // We make sure there is only one source of livedata (allowing us properly refresh)
        withContext(dispatchers.io) { usersSource = getTopUsersUseCase() }
        _users.addSource(usersSource) {
            _users.value = it
            if (it.status == Resource.Status.ERROR) _snackbarError.value = Event(R.string.an_error_happened)
        }
    }
}