package guru.springframework.sfgpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.assertj.core.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import guru.springframework.sfgpetclinic.ControllersTest;

public class IndexControllerTest implements ControllersTest{

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @DisplayName("Test proper view name is returned for index page")
    @Test
    void testIndex() {
        assertEquals("index", controller.index(), "Wrong view returned");

        assertThat(controller.index()).isEqualTo("index");
    }

    @DisplayName("test exception")
    @Test
    void testOupsHandler() {
        assertThrows(ValueNotFoundException.class, () ->controller.oopsHandler());
        /*
         * assertTrue("notimplemented".equals(controller.oopsHandler()),()->
         * " This some expensive "+
         * "messagee to build"
         * +"for my test");
         */
    }

    @Disabled("demo of timeout")
    @Test
    void testTimeout(){
        assertTimeout(Duration.ofMillis(100), () ->{
            Thread.sleep(2000);
            System.out.println("i got there");
        });
    }

    @Disabled("demo of timeout")
    @Test
    void testTimeoutPrempt(){
        assertTimeoutPreemptively( Duration.ofMillis(100), () ->{
            Thread.sleep(2000);
            System.out.println("i got there 234234234");
        });
    }

    @Test
    void testAssumptionTrue() {
        assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
    }

    @Test
    void testAssumptionTrueAssumptionIsTrue() {
        assumeTrue("GURU".equalsIgnoreCase("GURU"));
    }

    @EnabledOnOs(OS.LINUX)
    @Test
    void testMeOnLinux(){

    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testMOnWindows(){

    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testMeOnJava8(){

    }

    @EnabledOnJre(JRE.JAVA_21)
    @Test
    void testMeOnJava21(){

    }
    
    @EnabledIfEnvironmentVariable(named="USER", matches="barnard")
    @Test
    void testIfUserBarnard(){

    } 

    @EnabledIfEnvironmentVariable(named = "USER",matches="fred")
    @Test
    void testIfUserFred(){

    }
}
