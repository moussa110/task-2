package mahmoud.moussa.newsapp.repos.interfaces.sources

import com.example.task2.api.model.ReposResponse


interface ReposRepository {
    suspend fun getReposes(): List<ReposResponse.ReposResponseItem>;
}