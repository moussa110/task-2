package mahmoud.moussa.newsapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.task2.api.ApiManager
import com.example.task2.api.WebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import mahmoud.moussa.newsapp.database.ReposDao
import mahmoud.moussa.newsapp.database.ReposDatabase
import mahmoud.moussa.newsapp.repos.interfaces.NetworkHandler
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object module {

    @Singleton
    @Provides
    fun provideWebServices(): WebServices {
        return ApiManager().getWebServices();
    }

    @Singleton
    @Provides
    fun provideNetworkHandler(@ApplicationContext context: Context): NetworkHandler {
        return object : NetworkHandler {
            override fun isOnline(): Boolean {
                //val context=getApplication<Application>().applicationContext
                val cm =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
                return cm!!.activeNetworkInfo != null && cm!!.activeNetworkInfo!!.isConnected
            }
        }
    }

    @Singleton
    @Provides
    fun provideReposDao(@ApplicationContext context: Context): ReposDao {
        return ReposDatabase.getInstance(context)?.reposDao()!!
    }

}