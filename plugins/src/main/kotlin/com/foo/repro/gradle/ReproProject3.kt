package com.foo.repro.gradle

import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ReproProject3 : ReproProject2() {
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
                afterEvaluate {
                    afterEvaluate {
                        afterEvaluate {
                            afterEvaluate {
                                afterEvaluate {
                                    afterEvaluate {
                                        println("More lambdas")
                                        GenericTypeToMakeTheCompilatonLonger<Any, ReproProject, ReproProject2, ReproProject3>().print(
                                            Any(),
                                            ReproProject(),
                                            ReproProject2(),
                                            ReproProject3(),
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
}

class GenericTypeToMakeTheCompilatonLonger<T1, T2 : T1, T3 : T2, out T4 : T3> {
    fun print(
        arg: T1,
        arg2: T2,
        arg3: T3,
        arg4: @UnsafeVariance T4,
    ) {
        println(arg)
        println(arg2)
        println(arg3)
        println(arg4)
    }
}
