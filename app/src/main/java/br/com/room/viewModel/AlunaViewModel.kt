package br.com.room.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.room.model.Aluna
import br.com.room.model.data.AlunaDatabase
import br.com.room.model.repository.AlunaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlunaViewModel(application: Application): AndroidViewModel(application) {

    private val repository: AlunaRepository

    init {
        val alunaDao = AlunaDatabase.getDatabase(
            application
        ).alunaDao()
        repository = AlunaRepository(alunaDao)
    }

    fun addAluna(aluna: Aluna){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAluna(aluna)
        }
    }

    fun updateAluna(aluna: Aluna){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateAluna(aluna)
        }
    }

    fun deleteAluna(aluna: Aluna){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAluna(aluna)
        }
    }

    fun selectAlunas(): List<Aluna>{
        return repository.selectAlunas()
    }
}