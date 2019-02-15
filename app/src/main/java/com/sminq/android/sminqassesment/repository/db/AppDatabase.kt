package com.sminq.android.sminqassesment.repository.db
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.sminq.android.sminqassesment.repository.api.model.Restaurant

/** Room database builder class
 * Returns single instance of App database
 */
@Database(entities = [Restaurant::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
    companion object {
        private val DATABASE_NAME = "restaurantDB"
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, AppDatabase.DATABASE_NAME
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}