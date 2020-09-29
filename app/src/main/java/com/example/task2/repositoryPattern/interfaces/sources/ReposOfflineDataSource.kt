package mahmoud.moussa.newsapp.repos.interfaces.sources

import com.example.task2.api.model.ReposResponse

interface ReposOfflineDataSource {
    suspend fun cacheData(data: List<ReposResponse.ReposResponseItem>);
    suspend fun getReposes(): List<ReposResponse.ReposResponseItem>;
}