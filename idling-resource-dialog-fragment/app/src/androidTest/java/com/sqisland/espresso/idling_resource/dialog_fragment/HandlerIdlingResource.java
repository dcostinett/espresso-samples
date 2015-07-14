/* (c) Disney. All rights reserved. */
package com.sqisland.espresso.idling_resource.dialog_fragment;

import android.content.Context;
import android.support.test.espresso.IdlingResource;

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
        long elapsed = System.currentTimeMillis() - startTime;
        boolean idle = (elapsed >= waitTime);
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
