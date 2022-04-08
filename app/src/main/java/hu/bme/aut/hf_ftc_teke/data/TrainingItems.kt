package hu.bme.aut.hf_ftc_teke.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "trainingitems")
data class TrainingItems(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "datum") var datum: String,
    @ColumnInfo(name = "helyszin") var helyszin: Category,
    @ColumnInfo(name = "eredmeny") var eredmeny: Int,
    @ColumnInfo(name = "megjegyzes") var megjegyzes: String
) {
    enum class Category {
        FRADI,GYOR,PAPA;
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
