package com.dm.ycm.yassitant.di.components;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * Created by ycm on 2017/5/21.
 * Description:
 * Modified by:
 */
public final class DiConfig {
    @Retention(RetentionPolicy.RUNTIME)
    @StringDef(value = {CONTEXT_SCOPE_APP, CONTEXT_SCOPE_ACTIVITY})
    public @interface ContextScope {
    }

    /**
     * 全局Context标签
     */
    public static final String CONTEXT_SCOPE_APP = "application";
    /**
     * Activity Context标签
     */
    public static final String CONTEXT_SCOPE_ACTIVITY = "activity";

    @Retention(RetentionPolicy.RUNTIME)
    @StringDef(value = {CONN_HTTP, CONN_HTTPS})
    public @interface ConnType {
    }

    /**
     * http链接
     */
    public static final String CONN_HTTP = "http";
    /**
     * https链接
     */
    public static final String CONN_HTTPS = "https";


}
