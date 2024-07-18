package com.foo.repro.gradle

import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

open class ReproProject2 : ReproProject() {
    override fun apply(target: Project): Unit =
        with(target) {
            pluginManager.apply(ReproProject::class.java)
            extensions.findByType(KspExtension::class.java)?.apply {
                println(this.arguments)
                println(this.excludedSources)
                println(this.allWarningsAsErrors)
            }
            extensions.getByType(KotlinMultiplatformExtension::class.java).apply {
                println(this.targetHierarchy)
                println(this.targets.map { it.targetName + it.components })
                println(this.applyDefaultHierarchyTemplate())
            }
        }
}
