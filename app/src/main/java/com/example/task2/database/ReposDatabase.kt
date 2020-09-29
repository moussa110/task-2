package mahmoud.moussa.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.task2.api.model.ReposResponse


@Database(entities = arrayOf(ReposResponse.ReposResponseItem::class), version = 1)
abstract class ReposDatabase : RoomDatabase() {

    abstract fun reposDao(): ReposDao

    companion object {

        val DB_NAME = "DB-REPOS"
        private var reposDatabase: ReposDatabase? = null

        fun getInstance(context: Context): ReposDatabase? {
            if (reposDatabase == null) {
                reposDatabase = Room.databaseBuilder(
                    context,
                    ReposDatabase::class.java, DB_NAME
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return reposDatabase
        }
    }

}