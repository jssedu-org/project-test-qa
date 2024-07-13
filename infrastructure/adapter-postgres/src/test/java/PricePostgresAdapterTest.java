import com.usdev.domain.model.*;
import com.usdev.infrastructure.postgres.adapter.PricePostgresAdapter;
import com.usdev.infrastructure.postgres.entity.PriceEntity;
import com.usdev.infrastructure.postgres.mapper.PriceDboMapper;
import com.usdev.infrastructure.postgres.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PricePostgresAdapterTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceDboMapper priceMapper;

    @InjectMocks
    private PricePostgresAdapter pricePostgresAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_returnsListOfPrices() {
        PriceEntity priceDbo = new PriceEntity();
        Price price = createPrice();
        when(priceRepository.findAll()).thenReturn(List.of(priceDbo));
        when(priceMapper.toDomain(priceDbo)).thenReturn(price);

        List<Price> result = pricePostgresAdapter.getAll();

        assertEquals(1, result.size());
        assertEquals(price, result.get(0));
    }

    @Test
    void getAll_returnsEmptyList_whenNoPricesExist() {
        when(priceRepository.findAll()).thenReturn(Collections.emptyList());

        List<Price> result = pricePostgresAdapter.getAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void findPrices_returnsListOfPrices() {
        LocalDateTime appDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 2L;
        PriceEntity priceDbo = new PriceEntity();
        Price price = createPrice();
        when(priceRepository.findPrices(appDate, productId, brandId, Sort.by("priority").descending())).thenReturn(List.of(priceDbo));
        when(priceMapper.toDomain(priceDbo)).thenReturn(price);

        List<Price> result = pricePostgresAdapter.findPrices(appDate, productId, brandId);

        assertEquals(1, result.size());
        assertEquals(price, result.get(0));
    }

    @Test
    void findPrices_returnsEmptyList_whenNoPricesExist() {
        LocalDateTime appDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 2L;
        when(priceRepository.findPrices(appDate, productId, brandId, Sort.by("priority").descending())).thenReturn(Collections.emptyList());

        List<Price> result = pricePostgresAdapter.findPrices(appDate, productId, brandId);

        assertTrue(result.isEmpty());
    }

    @Test
    void getAll_invokesRepositoryFindAll() {
        pricePostgresAdapter.getAll();

        verify(priceRepository, times(1)).findAll();
    }

    @Test
    void findPrices_invokesRepositoryFindPrices() {
        LocalDateTime appDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 2L;

        pricePostgresAdapter.findPrices(appDate, productId, brandId);

        verify(priceRepository, times(1)).findPrices(any(LocalDateTime.class), anyLong(), anyLong(), any(Sort.class));
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