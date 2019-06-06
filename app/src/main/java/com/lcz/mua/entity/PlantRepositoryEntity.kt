package com.lcz.mua.entity

import com.lcz.mua.data.PlantDao

/**
 * desc TODO
 * Created by lcz on 2019/6/6.
 */
class PlantRepositoryEntity private constructor(private val plantDao: PlantDao) {
    fun getPlants() = plantDao.getPlants()

    fun getPlant(plantId: String) = plantDao.getPlant(plantId)

    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int) =
            plantDao.getPlantsWithGrowZoneNumber(growZoneNumber)

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: PlantRepositoryEntity? = null

        fun getInstance(plantDao: PlantDao) =
                instance ?: synchronized(this) {
                    instance ?: PlantRepositoryEntity(plantDao).also { instance = it }
                }
    }
}

