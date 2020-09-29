package mahmoud.moussa.newsapp.database

import androidx.room.*
import com.example.task2.api.model.ReposResponse

@Dao
interface ReposDao {
    @Insert
    fun insertRepos(reposItem: List<ReposResponse.ReposResponseItem>)

    @Query("DELETE FROM reposresponseitem")
    fun deleteRepos()

    @Query("select * from ReposResponseItem")
    fun getAllRepos(): MutableList<ReposResponse.ReposResponseItem?>

}