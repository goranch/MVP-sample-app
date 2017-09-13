package com.goranch.publicapis.di


interface ComponentProvider<T> {
    val component: T
}