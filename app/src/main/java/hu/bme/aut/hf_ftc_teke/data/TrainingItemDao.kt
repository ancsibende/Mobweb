package hu.bme.aut.hf_ftc_teke.data

import androidx.room.*

@Dao
interface TrainingItemDao {
    @Query("SELECT * FROM trainingitems")
    fun getAll(): List<TrainingItems>

    @Insert
    fun insert(shoppingItems: TrainingItems): Long

    @Update
    fun update(shoppingItem: TrainingItems)

    @Delete
    fun deleteItem(shoppingItem: TrainingItems)
}
