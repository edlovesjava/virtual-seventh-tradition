package org.edlovesjava.seventht;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.edlovesjava.seventht");

        noClasses()
            .that()
            .resideInAnyPackage("org.edlovesjava.seventht.service..")
            .or()
            .resideInAnyPackage("org.edlovesjava.seventht.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..org.edlovesjava.seventht.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
