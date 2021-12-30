package `in`.stc.hackmate.ui.home.project

import `in`.stc.hackmate.data.models.Project
import `in`.stc.hackmate.data.repositories.ProjectRepoImpl
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

private const val TAG = "AddProjectViewModel"

class AddProjectViewModel:ViewModel() {

    private val repo: ProjectRepoImpl = ProjectRepoImpl.getInstanceRepo()

    fun addProject(project : Project) {

        viewModelScope.launch {

            //todo add executed
            if(repo.addProject(project).isSuccess)//change to get participant details
                Log.d(TAG, "addproject: successful")
            else
                Log.e(TAG, "addproject: couldnt create a project", )
        }
}
}