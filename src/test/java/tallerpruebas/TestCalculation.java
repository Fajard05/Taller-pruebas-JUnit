package tallerpruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCalculation {

    @BeforeAll
    static void setUpBeforeClass() {
        System.out.println("Iniciando suite de pruebas para Calculation");
    }

    @AfterAll
    static void tearDownAfterClass() {
        System.out.println("Finalizando suite de pruebas para Calculation");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Preparando caso de prueba...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Caso de prueba finalizado.");
    }

    @Test
    void testFindMaxPositivos() {
        System.out.println("Test: Arreglo de números positivos");
        assertEquals(4, Calculation.findMax(new int[]{1, 2, 3, 4}));
        assertEquals(100, Calculation.findMax(new int[]{10, 50, 100, 25, 75}));
        assertEquals(1, Calculation.findMax(new int[]{1}));
    }

    @Test
    void testFindMaxNegativos() {
        System.out.println("Test: Arreglo de números negativos");
        assertEquals(-1, Calculation.findMax(new int[]{-12, -1, -3, -4, -2}));
        assertEquals(-5, Calculation.findMax(new int[]{-10, -5, -8, -7}));
        assertEquals(-100, Calculation.findMax(new int[]{-100, -200, -300}));
    }

    @Test
    void testFindMaxMixtos() {
        System.out.println("Test: Arreglo de números positivos y negativos");
        assertEquals(5, Calculation.findMax(new int[]{-3, 0, 5, -1, 2}));
        assertEquals(10, Calculation.findMax(new int[]{-10, -5, 0, 3, 10}));
        assertEquals(-1, Calculation.findMax(new int[]{-5, -3, -1, -2}));
    }
}
