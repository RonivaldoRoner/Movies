package com.ronivaldoroner.movies.presentation.arch

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices
import org.junit.Test

class LayerCyclicityTest {

    @Test
    fun verifyCyclicity() {
        slices().matching(PACKAGE).should().beFreeOfCycles()
    }

    @Test
    fun verifyLayerAccess() {
        layeredArchitecture()
            .consideringAllDependencies()
            .layer("Presentation").definedBy("..presentation..")
            .layer("Domain").definedBy("..domain..")
            .layer("Data").definedBy("..data..")
            .layer("Remote").definedBy("..remote..")
            .layer("Local").definedBy("..local..")
            .whereLayer("Presentation").mayNotBeAccessedByAnyLayer()
            .whereLayer("Data").mayOnlyBeAccessedByLayers("Presentation")
            .whereLayer("Remote").mayOnlyBeAccessedByLayers("Data")
            .whereLayer("Local").mayOnlyBeAccessedByLayers("Data")
            .whereLayer("Domain")
            .mayOnlyBeAccessedByLayers("Presentation", "Data", "Remote", "Local")
            .check(importedClasses)
    }

    companion object {
        const val PACKAGE = "com.ronivaldoroner.movies"
        val importedClasses = ClassFileImporter().importPackages(PACKAGE)
    }
}