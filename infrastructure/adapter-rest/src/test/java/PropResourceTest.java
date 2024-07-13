import com.usdev.application.ports.api.PricesAPIService;
import com.usdev.domain.model.*;
import com.usdev.infrastructure.rest.controller.PropResource;
import com.usdev.infrastructure.rest.dto.PriceResponseDto;
import com.usdev.infrastructure.rest.mapper.PriceDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class PropResourceTest {

    @Mock
    private PricesAPIService apiService;

    @Mock
    private PriceDtoMapper propMapper;

    @InjectMocks
    private PropResource propResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPrices_returnsPriceResponseDto_whenPriceExists() {
        LocalDateTime appDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 2L;
        Price price = createPrice();
        PriceResponseDto priceResponseDto = new PriceResponseDto();
        when(apiService.getPrice(appDate, productId, brandId)).thenReturn(Optional.of(price));
        when(propMapper.toDto(price)).thenReturn(priceResponseDto);

        ResponseEntity<PriceResponseDto> response = propResource.getPrices(appDate, productId, brandId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(priceResponseDto, response.getBody());
    }

    @Test
    void getPrices_returnsNoContent_whenNoPriceExists() {
        LocalDateTime appDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 2L;
        when(apiService.getPrice(appDate, productId, brandId)).thenReturn(Optional.empty());

        ResponseEntity<PriceResponseDto> response = propResource.getPrices(appDate, productId, brandId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void findAll_returnsListOfPriceResponseDto_whenPricesExist() {
        Price price1 = createPrice();
        Price price2 = createPrice();
        PriceResponseDto priceResponseDto1 = new PriceResponseDto();
        PriceResponseDto priceResponseDto2 = new PriceResponseDto();
        when(apiService.getAllPrices()).thenReturn(List.of(price1, price2));
        when(propMapper.toDto(price1)).thenReturn(priceResponseDto1);
        when(propMapper.toDto(price2)).thenReturn(priceResponseDto2);

        ResponseEntity<List<PriceResponseDto>> response = propResource.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(priceResponseDto1, priceResponseDto2), response.getBody());
    }

    @Test
    void findAll_returnsEmptyList_whenNoPricesExist() {
        when(apiService.getAllPrices()).thenReturn(Collections.emptyList());

        ResponseEntity<List<PriceResponseDto>> response = propResource.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    private Price createPrice() {
        return Price.builder()
                .priceId(PriceId.builder().id(1L).build())
                .brandId(BrandId.builder().id(2L).build())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .priceList(PriceList.builder().id(5L).build())
                .productId(ProductId.builder().id(4L).build())
                .priority(Priority.builder().id(1).build())
                .price(6.0)
                .cur(Currency.builder().cur("test").build())
                .build();
    }
}