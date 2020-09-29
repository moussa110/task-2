package mahmoud.moussa.newsapp.repos.impl.sources

import com.example.task2.api.WebServices
import com.example.task2.api.model.ReposResponse
import mahmoud.moussa.newsapp.repos.interfaces.sources.ReposOnlineDataSource
import javax.inject.Inject

class ReposOnlineDataSourceImpl @Inject constructor(val webServices: WebServices) :
    ReposOnlineDataSource {

    override suspend fun getReposes(): List<ReposResponse.ReposResponseItem> {
        val resp = webServices.getAllRepos()
        return resp as List<ReposResponse.ReposResponseItem>
    }
}