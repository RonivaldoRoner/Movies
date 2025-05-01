package com.ronivaldoroner.presentation.arch

import com.tngtech.archunit.base.DescribedPredicate
import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption.*
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import org.junit.Test

class LayerCyclicityTest {

    @Test
    fun verifyCyclicity(){

            layeredArchitecture()
                .consideringAllDependencies()
                .layer("Presentation").definedBy("..$packege.presentation..")
                .layer("Domain").definedBy("..$packege.domain..")
                .layer("Data").definedBy("..$packege.data..")
                .layer("Remote").definedBy("..$packege.remote..")
                .layer("Local").definedBy("..$packege.local..")
                .whereLayer("Presentation").mayNotBeAccessedByAnyLayer()
                .whereLayer("Data").mayOnlyBeAccessedByLayers("Presentation")
                .whereLayer("Remote").mayOnlyBeAccessedByLayers("Data")
                .whereLayer("Local").mayOnlyBeAccessedByLayers("Data")
                .check(importedClasses)
    }

    companion object {
        private val packege = "com.ronivaldoroner.movies"

        private val importedClasses = ClassFileImporter()
            .withImportOption(DoNotIncludeJars())
            .withImportOption(DoNotIncludeTests())
            .importPackages(packege)

        private val originDefault = object : DescribedPredicate<JavaClass>(
            "from base package"
        ) {
            override fun test(t: JavaClass?): Boolean {
                return javaClass.packageName.contains(packege)
            }
        }

        private val targetIgnoreOfApplication = object : DescribedPredicate<JavaClass>(
            "from EnvironmentVariable"
        ) {
            override fun test(t: JavaClass?): Boolean {
                return javaClass.simpleName == "EnvironmentVariable"
            }
        }
    }
}