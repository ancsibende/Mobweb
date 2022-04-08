package hu.bme.aut.hf_ftc_teke.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TrainingItems::class], version = 1)
@TypeConverters(value = [TrainingItems.Category::class])
abstract class TrainingItemsDatabase : RoomDatabase() {
    abstract fun trainingItemDao(): TrainingItemDao

    companion object {
        fun getDatabase(applicationContext: Context): TrainingItemsDatabase {
            return Room.databaseBuilder(
                applicationContext,
                TrainingItemsDatabase::class.java,
                "training-list"
            ).build();
        }
    }
}