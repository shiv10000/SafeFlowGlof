package com.example.safeflow.Data

import com.example.safeflow.R


data class LakeData(
    val id: Int,
    val name: String,
    val distance: String,
    val riskStatus: String,
    val type: String,
    val imageRes: Int
)


object LakeDataProvider {
    fun getLakeData(): List<LakeData> {
        return listOf(
            LakeData(
                id = 1,
                name = "Hindon River",
                distance = "15km west",
                riskStatus = "Safe",
                type = "River",
                imageRes = R.drawable.lake1
            )

        )
    }
}