package br.com.room.model.repository

import androidx.lifecycle.LiveData
import br.com.room.model.Aluna
import br.com.room.model.data.AlunaDao

class AlunaRepository(private val alunaDao: AlunaDao) {

    val readAllData: LiveData<List<Aluna>> = alunaDao.readAllData()

    suspend fun addAluna(aluna: Aluna){
        alunaDao.addAluna(aluna)
    }

    suspend fun updateAluna(aluna: Aluna){
        alunaDao.updateAluna(aluna)
    }

    suspend fun deleteAluna(aluna: Aluna){
        alunaDao.deleteAluna(aluna)
    }

}