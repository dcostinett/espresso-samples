/* (c) Disney. All rights reserved. */
package com.sqisland.espresso.idling_resource.dialog_fragment;

import android.hardware.input.InputManager;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentManager;

import javax.net.ssl.ManagerFactoryParameters;

/**
 * @author costd037 on 7/28/15.
 */
public class DialogFragmentIdlingResource implements IdlingResource {
    private ResourceCallback callback;
    private FragmentManager fragmentManager;
    private String tag;


    public DialogFragmentIdlingResource(FragmentManager fragmentManager, String tag) {
        this.fragmentManager = fragmentManager;
        this.tag = tag;
    }

    @Override
    public String getName() {
        return DialogFragmentIdlingResource.class.getSimpleName();
    }

    @Override
    public boolean isIdleNow() {
        boolean idle = (fragmentManager.findFragmentByTag(tag)) == null;
        if (idle) {
            callback.onTransitionToIdle();
        }
        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {

    }
}
