import com.interview.exercise.ProcesadorPlantillas;
import com.interview.exercise.ProveedorMiembrosPlanilla;
import com.interview.exercise.model.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProcesadorPlantillasTest {

    @Mock
    ProveedorMiembrosPlanilla proveedorMiembrosPlanilla;

    @InjectMocks
    ProcesadorPlantillas procesadorPlantillas;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void procesarPlantillaVacia() {
        when(proveedorMiembrosPlanilla.obtenerListaEmpleados()).thenReturn(new ArrayList<>());
        float totalAPagar = procesadorPlantillas.procesarPlantilla();
        assertEquals(new Float(0), totalAPagar);
        verify(proveedorMiembrosPlanilla, times(1)).obtenerListaEmpleados();
    }

    @Test
    void procesarPlantillaActivos() {
        when(proveedorMiembrosPlanilla.obtenerListaEmpleados()).thenReturn(generarEmpleadosActivos());
        float totalAPagar = procesadorPlantillas.procesarPlantilla();
        assertEquals(new Float(27500), totalAPagar);
        verify(proveedorMiembrosPlanilla, times(1)).obtenerListaEmpleados();
    }

    @Test
    void procesarPlantillaInactivos() {
        when(proveedorMiembrosPlanilla.obtenerListaEmpleados()).thenReturn(generarEmpleadosInactivos());
        float totalAPagar = procesadorPlantillas.procesarPlantilla();
        assertEquals(new Float(0), totalAPagar);
        verify(proveedorMiembrosPlanilla, times(1)).obtenerListaEmpleados();
    }

    @Test
    void procesarPlantillaActivosInactivos() {
        when(proveedorMiembrosPlanilla.obtenerListaEmpleados()).thenReturn(generarEmpleadosActivosInactivos());
        float totalAPagar = procesadorPlantillas.procesarPlantilla();
        assertEquals(new Float(12500), totalAPagar);
        verify(proveedorMiembrosPlanilla, times(1)).obtenerListaEmpleados();
    }

    @Test
    void procesarPlantillaActivosIdInvalido() {
        when(proveedorMiembrosPlanilla.obtenerListaEmpleados()).thenReturn(generarEmpleadosIdInvalido());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> procesadorPlantillas.procesarPlantilla());
        assertTrue(exception.getMessage().contains("ID inválido"));
        verify(proveedorMiembrosPlanilla, times(1)).obtenerListaEmpleados();
    }

    @Test
    void procesarPlantillaActivosMontoInvalido() {
        when(proveedorMiembrosPlanilla.obtenerListaEmpleados()).thenReturn(generarEmpleadosMontoInvalido());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> procesadorPlantillas.procesarPlantilla());
        assertTrue(exception.getMessage().contains("Monto mensial inválido"));
        verify(proveedorMiembrosPlanilla, times(1)).obtenerListaEmpleados();
    }

    @Test
    void procesarPlantillaActivosNombreNulo() {
        when(proveedorMiembrosPlanilla.obtenerListaEmpleados()).thenReturn(generarEmpleadosNombreNulo());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> procesadorPlantillas.procesarPlantilla());
        assertTrue(exception.getMessage().contains("Nombre inválido"));
        verify(proveedorMiembrosPlanilla, times(1)).obtenerListaEmpleados();
    }

    @Test
    void procesarPlantillaActivosNombreVacio() {
        when(proveedorMiembrosPlanilla.obtenerListaEmpleados()).thenReturn(generarEmpleadosNombreVacio());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> procesadorPlantillas.procesarPlantilla());
        assertTrue(exception.getMessage().contains("Nombre inválido"));
        verify(proveedorMiembrosPlanilla, times(1)).obtenerListaEmpleados();
    }

    private List<Empleado> generarEmpleadosActivos() {
        List<Empleado> empleados = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Empleado temp = new Empleado(1 + 1, "Empleado " + (i+1), 500*(i+1),true);
            empleados.add(temp);
        }
        return empleados;
    }

    private List<Empleado> generarEmpleadosInactivos() {
        List<Empleado> empleados = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Empleado temp = new Empleado(1 + 1, "Empleado " + (i+1), 500*(i+1),false);
            empleados.add(temp);
        }
        return empleados;
    }

    private List<Empleado> generarEmpleadosActivosInactivos() {
        List<Empleado> empleados = new ArrayList<>();
        boolean activo = true;
        for (int i = 0; i < 10; i++) {
            Empleado temp = new Empleado(1 + 1, "Empleado " + (i+1), 500*(i+1),activo);
            empleados.add(temp);
            activo = !activo;
        }
        return empleados;
    }

    private List<Empleado> generarEmpleadosIdInvalido() {
        List<Empleado> empleados = new ArrayList<>();
        int random = (int) (Math.random() * 5);
        for (int i = 0; i < 5; i++) {
            Empleado temp = new Empleado(1 + 1, "Empleado " + (i+1), 500*(i+1),true);
            if(random == i)
                temp.setId(0);
            empleados.add(temp);
        }
        return empleados;
    }

    private List<Empleado> generarEmpleadosMontoInvalido() {
        List<Empleado> empleados = new ArrayList<>();
        int random = (int) (Math.random() * 5);
        for (int i = 0; i < 5; i++) {
            Empleado temp = new Empleado(1 + 1, "Empleado " + (i+1), 500*(i+1),true);
            if(random == i)
                temp.setMontoMensual(-500);
            empleados.add(temp);
        }
        return empleados;
    }

    private List<Empleado> generarEmpleadosNombreNulo() {
        List<Empleado> empleados = new ArrayList<>();
        int random = (int) (Math.random() * 5);
        for (int i = 0; i < 5; i++) {
            Empleado temp = new Empleado(1 + 1, "Empleado " + (i+1), 500*(i+1),true);
            if(random == i)
                temp.setNombre(null);
            empleados.add(temp);
        }
        return empleados;
    }

    private List<Empleado> generarEmpleadosNombreVacio() {
        List<Empleado> empleados = new ArrayList<>();
        int random = (int) (Math.random() * 5);
        for (int i = 0; i < 5; i++) {
            Empleado temp = new Empleado(1 + 1, "Empleado " + (i+1), 500*(i+1),true);
            if(random == i)
                temp.setNombre("");
            empleados.add(temp);
        }
        return empleados;
    }

}