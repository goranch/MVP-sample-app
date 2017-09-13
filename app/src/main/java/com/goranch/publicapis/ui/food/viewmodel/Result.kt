package com.goranch.publicapis.ui.food.viewmodel

data class Result<out T>(
        val data: T?,
        val error: Throwable?
)
