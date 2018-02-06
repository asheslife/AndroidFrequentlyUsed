package com.asheslife.framework.model;

import com.asheslife.framework.core.BaseActivity;

/**
 * Created by asheslife on 2016/3/27.
 *
 */
public class Module {
    public String moduleName;
    public String moduleIcon;
    public Class<? extends BaseActivity> moduleTargetClass;

    public Module(String moduleName, Class<? extends BaseActivity> moduleTargetClass) {
        this.moduleName = moduleName;
        this.moduleTargetClass = moduleTargetClass;
    }
}
