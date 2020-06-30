package fr.dev.majdi.personnamvproom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Created by Majdi RABEH on 29/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
@Entity(tableName = "personne")
class Personne(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "gender") var gender: String
) {
    @Ignore
    constructor() : this(null, "", "", "")
}