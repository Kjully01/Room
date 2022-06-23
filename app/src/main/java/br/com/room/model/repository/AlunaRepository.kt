package br.com.room.model.repository

import androidx.lifecycle.LiveData
import br.com.room.model.Aluna
import br.com.room.model.data.AlunaDao

class AlunaRepository(private val alunaDao: AlunaDao) {

    suspend fun addAluna(aluna: Aluna){
        alunaDao.addAluna(aluna)
    }

    suspend fun updateAluna(aluna: Aluna){
        alunaDao.updateAluna(aluna)
    }

    suspend fun deleteAluna(aluna: Aluna){
        alunaDao.deleteAluna(aluna)
    }

    fun selectAlunas(): List<Aluna>{
        return alunaDao.selectAlunas()
    }
}