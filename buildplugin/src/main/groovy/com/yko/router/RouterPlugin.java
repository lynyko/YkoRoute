package com.yko.router;


import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Created by yko on 2017/9/25.
 */

public class RouterPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("========================");
        System.out.println("hello gradle plugin!");
        System.out.println("========================");
    }
}
