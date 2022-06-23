package br.com.room.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.room.model.Aluna

@Database(entities = [Aluna::class], version = 1, exportSchema = true)
abstract class AlunaDatabase: RoomDatabase() {

    abstract fun alunaDao(): AlunaDao

    companion object{
        @Volatile
        private var INSTANCE: AlunaDatabase? = null

        fun getDatabase(context: Context): AlunaDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlunaDatabase::class.java,
                    "aluna_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}