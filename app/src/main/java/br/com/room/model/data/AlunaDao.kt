package br.com.room.model.data

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.room.model.Aluna

@Dao
interface AlunaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAluna(aluna: Aluna)

    @Update
    suspend fun updateAluna(aluna: Aluna)

    @Delete
    suspend fun deleteAluna(aluna: Aluna)

    @Query("SELECT * FROM aluna_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Aluna>>

}