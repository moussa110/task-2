package mahmoud.moussa.newsapp.repos.impl.sources

import android.util.Log
import com.example.task2.api.model.ReposResponse
import mahmoud.moussa.newsapp.repos.interfaces.NetworkHandler
import mahmoud.moussa.newsapp.repos.interfaces.sources.ReposOfflineDataSource
import mahmoud.moussa.newsapp.repos.interfaces.sources.ReposOnlineDataSource
import mahmoud.moussa.newsapp.repos.interfaces.sources.ReposRepository
import javax.inject.Inject

class ReposRepositoryImpl @Inject constructor(
    val onlineDataSource: ReposOnlineDataSource,
    val offlineDataSource: ReposOfflineDataSource,
    val networkHandler: NetworkHandler
) : ReposRepository {

    override suspend fun getReposes(): List<ReposResponse.ReposResponseItem> {
        var sourcesList: List<ReposResponse.ReposResponseItem>
        if (networkHandler.isOnline()) {
            sourcesList = onlineDataSource.getReposes()
            offlineDataSource.cacheData(sourcesList)
            Log.e("state", "1")
        } else {
            Log.e("state", "0")
            sourcesList = offlineDataSource.getReposes()
        }
        return sourcesList;
    }
}