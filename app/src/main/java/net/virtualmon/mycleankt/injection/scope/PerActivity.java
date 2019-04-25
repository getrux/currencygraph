package net.virtualmon.mycleankt.injection.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by AlbertM on 26/11/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {}
