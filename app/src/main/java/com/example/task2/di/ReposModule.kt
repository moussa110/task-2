package mahmoud.moussa.newsapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import mahmoud.moussa.newsapp.repos.impl.sources.ReposOfflineDataSourceImpl
import mahmoud.moussa.newsapp.repos.impl.sources.ReposOnlineDataSourceImpl
import mahmoud.moussa.newsapp.repos.impl.sources.ReposRepositoryImpl
import mahmoud.moussa.newsapp.repos.interfaces.sources.ReposOfflineDataSource
import mahmoud.moussa.newsapp.repos.interfaces.sources.ReposOnlineDataSource
import mahmoud.moussa.newsapp.repos.interfaces.sources.ReposRepository

@Module
@InstallIn(ActivityComponent::class)
abstract class ReposModule {
    @Binds
    abstract fun bindReposRepository(
        repos: ReposRepositoryImpl
    ): ReposRepository;

    @Binds
    abstract fun bindOnlineReposRepository(
        repos: ReposOnlineDataSourceImpl
    ): ReposOnlineDataSource;

    @Binds
    abstract fun bindOfflineReposRepository(
        repos: ReposOfflineDataSourceImpl
    ): ReposOfflineDataSource;

}