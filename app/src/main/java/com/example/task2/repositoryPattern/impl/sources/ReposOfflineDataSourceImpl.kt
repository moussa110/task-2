package mahmoud.moussa.newsapp.repos.impl.sources

import com.example.task2.api.model.ReposResponse

import mahmoud.moussa.newsapp.database.ReposDao

import mahmoud.moussa.newsapp.repos.interfaces.sources.ReposOfflineDataSource
import javax.inject.Inject

class ReposOfflineDataSourceImpl @Inject constructor(val reposDao: ReposDao) :
    ReposOfflineDataSource {

    override suspend fun cacheData(data: List<ReposResponse.ReposResponseItem>) {
        reposDao.insertRepos(data)
    }

    override suspend fun getReposes(): List<ReposResponse.ReposResponseItem> {
        return reposDao.getAllRepos() as List<ReposResponse.ReposResponseItem>;
    }

}