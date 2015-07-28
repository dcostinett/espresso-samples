/* (c) Disney. All rights reserved. */
package com.sqisland.espresso.idling_resource.dialog_fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;


/**
 * @author costd037 on 7/14/15.
 */
public class HandlerIdlingResource implements IdlingResource {
    private Context context;
    private ResourceCallback resourceCallback;
    private long waitTime;
    private long startTime;

    public HandlerIdlingResource(Context context) {
        this.context = context;

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();

    }

    public HandlerIdlingResource(long waitTime) {
        this.startTime = System.currentTimeMillis();
        this.waitTime = waitTime;
    }

    @Override
    public String getName() {
        return HandlerIdlingResource.class.getSimpleName();
    }

    @Override
    public boolean isIdleNow() {
        boolean idle = false;
        long elapsed = System.currentTimeMillis() - startTime;

        idle = (elapsed >= waitTime);
        if (idle) {
            resourceCallback.onTransitionToIdle();
        }
        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }
}
