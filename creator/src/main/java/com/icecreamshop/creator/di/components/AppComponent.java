package com.icecreamshop.creator.di.components;

import com.icecreamshop.creator.data.IceCreamRepository;
import com.icecreamshop.creator.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created on 10/6/17.
 *
 * @author Andrii S.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    IceCreamRepository repository();
}
