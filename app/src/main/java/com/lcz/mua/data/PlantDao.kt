package com.lcz.mua.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lcz.mua.entity.PlantEntity

/**
 * desc TODO
 * Created by lcz on 2019/6/6.
 */
@Dao
interface PlantDao {
    @Query("SELECT * FROM plants ORDER BY name")
    fun getPlants(): LiveData<List<PlantEntity>>

    @Query("SELECT * FROM plants WHERE growZoneNumber = :growZoneNumber ORDER BY name")
    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int): LiveData<List<PlantEntity>>

    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlant(plantId: String): LiveData<PlantEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(plants: List<PlantEntity>)
}