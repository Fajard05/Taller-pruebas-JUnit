package tallerpruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class EmployeeTest {

    private Employee workerUSD;
    private Employee supervisorUSD;
    private Employee managerUSD;
    private Employee workerEUR;
    private float rmu;

    @BeforeEach
    void setUp() {
        rmu = 386.0f;
        workerUSD = new Employee(500.0f, "USD", 0.0f, EmployeeType.Worker);
        supervisorUSD = new Employee(800.0f, "USD", 100.0f, EmployeeType.Supervisor);
        managerUSD = new Employee(1200.0f, "USD", 200.0f, EmployeeType.Manager);
        workerEUR = new Employee(500.0f, "EUR", 0.0f, EmployeeType.Worker);
    }

    @Test
    void testWorkerSinBono() {
        float resultado = workerUSD.cs();
        int mes = LocalDate.now().getMonthValue();
        float esperado;
        if (mes % 2 == 0) {
            esperado = 500.0f;
        } else {
            esperado = 500.0f + rmu / 12 * 2;
        }
        assertEquals(esperado, resultado, 0.01f);
    }

    @Test
    void testSupervisorConBono() {
        float resultado = supervisorUSD.cs();
        int mes = LocalDate.now().getMonthValue();
        float salarioBase = 800.0f + (100.0f * 0.35f);
        float esperado;
        if (mes % 2 == 0) {
            esperado = salarioBase;
        } else {
            esperado = salarioBase + rmu / 12 * 2;
        }
        assertEquals(esperado, resultado, 0.01f);
    }

    @Test
    void testManagerConBono() {
        float resultado = managerUSD.cs();
        int mes = LocalDate.now().getMonthValue();
        float salarioBase = 1200.0f + (200.0f * 0.7f);
        float esperado;
        if (mes % 2 == 0) {
            esperado = salarioBase;
        } else {
            esperado = salarioBase + rmu / 12 * 2;
        }
        assertEquals(esperado, resultado, 0.01f);
    }

    @Test
    void testMonedaNoUSDResta5Porciento() {
        float resultadoEUR = workerEUR.cs();
        int mes = LocalDate.now().getMonthValue();
        float salarioBase = 500.0f * 0.95f;
        float esperado;
        if (mes % 2 == 0) {
            esperado = salarioBase;
        } else {
            esperado = salarioBase + rmu / 12 * 2;
        }
        assertEquals(esperado, resultadoEUR, 0.01f);
    }

    @Test
    void testMonedaUSDSinDescuento() {
        float resultadoUSD = workerUSD.cs();
        int mes = LocalDate.now().getMonthValue();
        float esperado;
        if (mes % 2 == 0) {
            esperado = 500.0f;
        } else {
            esperado = 500.0f + rmu / 12 * 2;
        }
        assertEquals(esperado, resultadoUSD, 0.01f);
    }

    @Test
    void testDecimoMesPar() {
        int mes = LocalDate.now().getMonthValue();
        Assumptions.assumeTrue(mes % 2 == 0, "Se ejecuta solo en meses pares");
        float resultado = workerUSD.cs();
        assertEquals(500.0f, resultado, 0.01f);
    }

    @Test
    void testDecimoMesImpar() {
        int mes = LocalDate.now().getMonthValue();
        Assumptions.assumeTrue(mes % 2 != 0, "Se ejecuta solo en meses impares");
        float resultado = workerUSD.cs();
        float esperado = 500.0f + rmu / 12 * 2;
        assertEquals(esperado, resultado, 0.01f);
    }

    @Test
    void testBonusWorker() {
        float resultado = workerUSD.CalculateYearBonus();
        assertEquals(rmu, resultado, 0.01f);
    }

    @Test
    void testBonusSupervisor() {
        float resultado = supervisorUSD.CalculateYearBonus();
        float esperado = 800.0f + rmu * 0.5f;
        assertEquals(esperado, resultado, 0.01f);
    }

    @Test
    void testBonusManager() {
        float resultado = managerUSD.CalculateYearBonus();
        float esperado = 1200.0f + rmu * 1.0f;
        assertEquals(esperado, resultado, 0.01f);
    }

    @Test
    void testBonusMonedaNoUSD() {
        float resultado = workerEUR.CalculateYearBonus();
        assertEquals(rmu, resultado, 0.01f);
    }

    @Test
    void testEmployeeConstructor() {
        assertNotNull(workerUSD);
        assertNotNull(supervisorUSD);
        assertNotNull(managerUSD);
        assertNotNull(workerEUR);
    }

    @Test
    void testDiferenciaEntreTipos() {
        float csWorker = workerUSD.cs();
        float csSupervisor = supervisorUSD.cs();
        float csManager = managerUSD.cs();
        assertNotEquals(csWorker, csSupervisor, 0.01f);
        assertNotEquals(csSupervisor, csManager, 0.01f);
    }

    @Test
    void testCSRetornaValorPositivo() {
        assertNotEquals(0.0f, workerUSD.cs(), 0.01f);
        assertNotEquals(0.0f, supervisorUSD.cs(), 0.01f);
        assertNotEquals(0.0f, managerUSD.cs(), 0.01f);
    }
}
