package com.goranch.publicapis.di;

/**
 * Created by goran on 10/09/2016.
 */

public interface ComponentProvider<T> {
    T getComponent();
}
