package com.dm.ycm.yassitant.di.components.annotation;

import android.support.annotation.NonNull;


import com.dm.ycm.yassitant.di.components.DiConfig;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by ycm on 2017/5/21.
 * Description:
 * Modified by:
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Scoped {
    @NonNull @DiConfig.ContextScope String value() default DiConfig.CONTEXT_SCOPE_APP;
}
