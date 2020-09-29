package mahmoud.moussa.newsapp.repos.interfaces.sources

import com.example.task2.api.model.ReposResponse

interface ReposOnlineDataSource {
    suspend fun getReposes(): List<ReposResponse.ReposResponseItem>;
}