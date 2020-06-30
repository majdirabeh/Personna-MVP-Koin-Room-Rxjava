package fr.dev.majdi.personnamvproom.room

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.dev.majdi.personnamvproom.model.Personne

/**
 * Created by Majdi RABEH on 29/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
@Database(entities = [(Personne::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personneDao(): PersonneDAO
}