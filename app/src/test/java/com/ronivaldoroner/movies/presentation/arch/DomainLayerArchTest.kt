package com.ronivaldoroner.movies.presentation.arch

import com.tngtech.archunit.base.DescribedPredicate
import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import kotlinx.serialization.SerialName
import org.junit.Test
import kotlinx.serialization.Serializable

class DomainLayerArchTest {

    @Test
    fun verifyFieldsModelAnnotation (){
        ArchRuleDefinition
            .fields()
            .that()
            .areDeclaredInClassesThat()
            .resideInAPackage("..domain.features..")
            .and()
            .areAnnotatedWith(Serializable::class.java)
            .should()
            .beAnnotatedWith(SerialName::class.java)
            .check(LayerCyclicityTest.importedClasses)

    }

    @Test
    fun verifyScreenName(){

    }
}