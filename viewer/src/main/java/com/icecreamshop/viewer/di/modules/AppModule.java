package com.icecreamshop.viewer.di.modules;

import com.google.firebase.database.FirebaseDatabase;
import com.icecreamshop.viewer.data.IceCreamRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 10/6/17.
 *
 * @author Andrii S.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    FirebaseDatabase providesFirebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    @Provides
    @Singleton
    IceCreamRepository providesIceCreamRepository(FirebaseDatabase database) {
        return new IceCreamRepository(database);
    }
}
