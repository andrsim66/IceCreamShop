package com.icecreamshop.viewer.di.compontents;

import com.icecreamshop.viewer.data.IceCreamRepository;
import com.icecreamshop.viewer.di.modules.AppModule;

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
