package com.github.chinalike.armstemplate.services

import com.github.chinalike.armstemplate.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
