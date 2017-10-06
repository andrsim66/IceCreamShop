package com.icecreamshop.viewer.di.modules;

import com.icecreamshop.viewer.data.IceCreamRepository;
import com.icecreamshop.viewer.di.scope.ActivityScope;
import com.icecreamshop.viewer.presentation.ListIceCreamPresenter;
import com.icecreamshop.viewer.presentation.ListIceCreamView;
import com.icecreamshop.viewer.usecase.GetIceCreamListUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 10/6/17.
 *
 * @author Andrii S.
 */

@Module
public class ActivityModule {

    private ListIceCreamView view;

    public ActivityModule(ListIceCreamView view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    GetIceCreamListUseCase providesGetIceCreamListUseCase(IceCreamRepository repository) {
        return new GetIceCreamListUseCase(repository);
    }

    @Provides
    @ActivityScope
    ListIceCreamPresenter providesListIceCreamPresenter(GetIceCreamListUseCase useCase) {
        return new ListIceCreamPresenter(view, useCase);
    }
}
