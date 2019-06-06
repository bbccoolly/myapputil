package com.lcz.mua.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.lcz.mua.entity.PlantEntity
import com.lcz.mua.entity.PlantRepositoryEntity

/**
 * desc TODO
 * Created by lcz on 2019/6/6.
 */
class PlantListViewModel internal constructor(plantRepositoryEntity: PlantRepositoryEntity) : ViewModel() {
    private val growZoneNumber = MutableLiveData<Int>().apply { value = NO_GROW_ZONE }
    val plants: LiveData<List<PlantEntity>> = growZoneNumber.switchMap {
        if (it == NO_GROW_ZONE) {
            plantRepositoryEntity.getPlants()
        } else {
            plantRepositoryEntity.getPlantsWithGrowZoneNumber(it)
        }
    }

    fun setGrowZoneNumber(num: Int) {
        growZoneNumber.value = num
    }

    fun clearGrowZoneNumber() {
        growZoneNumber.value = NO_GROW_ZONE
    }

    fun isFiltered() = growZoneNumber.value != NO_GROW_ZONE

    companion object {
        private const val NO_GROW_ZONE = -1
    }
}