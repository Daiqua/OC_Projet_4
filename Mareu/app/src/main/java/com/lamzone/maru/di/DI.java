package com.lamzone.maru.di;

import com.lamzone.maru.service.DummyApiService;
import com.lamzone.maru.service.MaReuApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static MaReuApiService service = new DummyApiService();

    /**
     * Get an instance on @{@link MaReuApiService}
     *
     * @return
     */
    public static MaReuApiService getApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link MaReuApiService}. Useful for tests, so we ensure the context is clean.
     *
     * @return
     */
    public static MaReuApiService getNewInstanceApiService() {
        return new DummyApiService();
    }
}
