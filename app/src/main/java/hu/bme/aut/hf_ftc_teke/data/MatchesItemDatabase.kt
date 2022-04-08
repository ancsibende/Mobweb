package hu.bme.aut.hf_ftc_teke.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MatchesItems::class], version = 1)
@TypeConverters(value = [MatchesItems.Category::class])
abstract class MatchesItemDatabase:RoomDatabase() {
    abstract fun matchesItemDao(): MatchesItemDao

    companion object {
        fun getDatabase(applicationContext: Context): MatchesItemDatabase {
            return Room.databaseBuilder(
                applicationContext,
                MatchesItemDatabase::class.java,
                "matches-list"
            ).createFromAsset("database/matchesitems.db").build();
        }
    }
}