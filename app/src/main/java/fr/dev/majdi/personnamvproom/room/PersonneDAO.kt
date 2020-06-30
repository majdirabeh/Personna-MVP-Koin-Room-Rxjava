package fr.dev.majdi.personnamvproom.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import fr.dev.majdi.personnamvproom.model.Personne
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Majdi RABEH on 29/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
@Dao
interface PersonneDAO {
    @Query("SELECT * FROM personne")
    fun getAll(): Flowable<MutableList<Personne>>

    @Query("SELECT * FROM personne WHERE name LIKE :name")
    fun getByName(name: String): Flowable<MutableList<Personne>>

    @Query("SELECT * FROM personne WHERE email LIKE :email")
    fun getByEmail(email: String): Single<Personne>

    @Insert(onConflict = REPLACE)
    fun insert(personne: Personne)

    @Query("DELETE from personne WHERE id=:id")
    fun deleteById(id: Long)

    @Query("SELECT * FROM personne WHERE id=:id")
    fun getById(id: Long): Single<Personne>

    @Update()
    fun update(personne: Personne)
}