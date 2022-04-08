package hu.bme.aut.hf_ftc_teke.data

import androidx.room.*

@Dao
interface MatchesItemDao {
    @Query("SELECT * FROM matchesitems")
    fun getAll(): List<MatchesItems>

    @Query("SELECT * FROM matchesitems WHERE fordulo=:fordulo AND category=:category")
    fun getFordulo(fordulo: Int,category:Int): List<MatchesItems>

    @Query("SELECT * FROM matchesitems WHERE category=:category AND nev=:nev")
    fun getPicked(category: Int, nev:String): List<MatchesItems>

    @Query("SELECT eredmeny FROM matchesitems WHERE category=:category AND nev=:nev")
    fun getAtlag(category: Int, nev:String): List<Int>

    @Insert
    fun insert(matchesItems: MatchesItems): Long

    @Update
    fun update(matchesItems: MatchesItems)

    @Delete
    fun deleteItem(matchesItems: MatchesItems)
}