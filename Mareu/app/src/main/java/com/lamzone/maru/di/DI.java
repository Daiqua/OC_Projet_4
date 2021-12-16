package com.lamzone.maru.di;

import com.lamzone.maru.service.DummyApiService;
import com.lamzone.maru.service.MaReuApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static MaReuApiService service = new DummyApiService();


    public static MaReuApiService getApiService() {
        return service;
    }

    public static MaReuApiService getNewInstanceApiService() {
        service = new DummyApiService();
        return service;
    }
}
