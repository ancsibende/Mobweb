package hu.bme.aut.hf_ftc_teke.data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter


@Entity(tableName = "matchesitems")
    data class MatchesItems(
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
        @ColumnInfo(name = "fordulo") var fordulo: Int,
        @ColumnInfo(name = "nev") var nev: String,
        @ColumnInfo(name = "category") var category: Int,
        @ColumnInfo(name = "eredmeny") var eredmeny: Int
    ) {
    enum class Category {
        HAZAI,VENDEG;
        companion object {
            @JvmStatic
            @TypeConverter
            fun getByOrdinal(ordinal: Int): Category? {
                var ret: Category? = null
                for (cat in values()) {
                    if (cat.ordinal == ordinal) {
                        ret = cat
                        break
                    }
                }
                return ret
            }

            @JvmStatic
            @TypeConverter
            fun toInt(category: Category): Int {
                return category.ordinal
            }
        }
    }
}
