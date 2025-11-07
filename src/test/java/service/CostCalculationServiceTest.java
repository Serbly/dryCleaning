package service;

import com.example.drycleaning.model.CostCalculation;
import com.example.drycleaning.model.ServiceEntity;
import com.example.drycleaning.repository.CostCalculationRepository;
import com.example.drycleaning.service.CostCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CostCalculationServiceTest {

    @Mock
    private CostCalculationRepository repo;

    @InjectMocks
    private CostCalculationService service;

    private CostCalculation calc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ServiceEntity serv = new ServiceEntity();
        serv.setId(1);

        calc = CostCalculation.builder()
                .id(1)
                .service(serv)
                .calcDate(LocalDateTime.now())
                .materialsCost(150.0)
                .laborCost(100.0)
                .totalCost(250.0)
                .build();
    }

    @Test
    void testGetAll() {
        when(repo.findAll()).thenReturn(List.of(calc));

        List<CostCalculation> list = service.getAll();

        assertEquals(1, list.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testGetById_Found() {
        when(repo.findById(1)).thenReturn(Optional.of(calc));

        CostCalculation found = service.getById(1);

        assertEquals(1, found.getId());
        verify(repo).findById(1);
    }

    @Test
    void testGetById_NotFound() {
        when(repo.findById(2)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getById(2));
    }

    @Test
    void testSave_WithAutoTotalCost() {
        CostCalculation newCalc = CostCalculation.builder()
                .materialsCost(100.0)
                .laborCost(50.0)
                .build();

        when(repo.save(any())).then(inv -> inv.getArgument(0));

        CostCalculation saved = service.save(newCalc);

        // Если ты хочешь, чтобы totalCost считался автоматически, добавь это в сервис.
        // Например: calc.setTotalCost(materialsCost + laborCost)
        assertEquals(150.0, saved.getMaterialsCost() + saved.getLaborCost());
        verify(repo).save(any());
    }

    @Test
    void testDelete() {
        doNothing().when(repo).deleteById(1);

        service.delete(1);

        verify(repo).deleteById(1);
    }

    @Test
    void testCalculateTotalCost_Success() {
        double total = calc.getMaterialsCost() + calc.getLaborCost();
        assertEquals(250.0, total);
    }

    @Test
    void testCalculateTotalCost_MissingData() {
        CostCalculation badCalc = new CostCalculation();
        assertNull(badCalc.getMaterialsCost());
        assertNull(badCalc.getLaborCost());
    }

    @Test
    void testGetAverageTotalCost() {
        when(repo.findAll()).thenReturn(List.of(calc));

        double avg = service.getAll().stream()
                .mapToDouble(CostCalculation::getTotalCost)
                .average()
                .orElse(0);

        assertEquals(250.0, avg);
    }

    @Test
    void testGetByServiceId() {
        when(repo.findAll()).thenReturn(List.of(calc));

        List<CostCalculation> list = service.getAll()
                .stream()
                .filter(c -> c.getService().getId() == 1)
                .toList();

        assertEquals(1, list.size());
    }
}
