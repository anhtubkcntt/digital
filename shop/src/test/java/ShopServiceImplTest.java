import com.digital.shop.exception.BusinessException;
import com.digital.shop.model.dto.request.ShopCreateRequest;
import com.digital.shop.model.dto.response.ShopResponse;
import com.digital.shop.model.entity.ShopEntity;
import com.digital.shop.repository.ShopRepo;
import com.digital.shop.service.impl.ShopServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ShopServiceImplTest {

    @InjectMocks
    private ShopServiceImpl shopService;

    @Mock
    private ShopRepo shopRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateShop() {
        // Create a sample ShopCreateRequest
        ShopCreateRequest request = new ShopCreateRequest();
        request.setShopName("Test Shop");

        // Create a mock ShopEntity for saving
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setShopName("Test Shop");

        // Mock repository behavior
        when(shopRepo.save(any(ShopEntity.class))).thenReturn(shopEntity);

        // Perform the createShop operation
        ShopResponse response = shopService.createShop(request);

        // Verify that the response is not null
        assertNotNull(response);
        // You can add more assertions based on your expected behavior

        // Verify that the save method was called with the correct arguments
        verify(shopRepo, times(1)).save(any(ShopEntity.class));
    }

    @Test
    public void testFindAllShop() {
        // Create a list of mock ShopEntity objects
        List<ShopEntity> shopEntityList = new ArrayList<>();
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setShopName("Test Shop");
        shopEntityList.add(shopEntity);
        shopEntityList.add(shopEntity);

        // Mock repository behavior to return the list
        when(shopRepo.findAll()).thenReturn(shopEntityList);

        // Perform the findAllShop operation
        List<ShopResponse> shopResponses = shopService.findAllShop();

        // Verify that the response list is not null and has the expected size
        assertNotNull(shopResponses);
        assertEquals(2, shopResponses.size()); // Assuming two entities were returned

        // Verify that the findAll method was called
        verify(shopRepo, times(1)).findAll();
    }

    @Test
    public void testFindAllShopNoShopsFound() {
        // Mock repository behavior to return an empty list
        when(shopRepo.findAll()).thenReturn(new ArrayList<>());

        // Perform the findAllShop operation and expect a BusinessException
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            shopService.findAllShop();
        });

        // Verify that the BusinessException contains the expected message and HTTP status
        assertEquals("could not find any shop", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());

        // Verify that the findAll method was called
        verify(shopRepo, times(1)).findAll();
    }
}
