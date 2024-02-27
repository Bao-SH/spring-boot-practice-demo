package com.example.integratewithpostgresql;
import com.example.integratewithpostgresql.config.CacheConfiguration;
import com.example.integratewithpostgresql.domain.Sales;
import com.example.integratewithpostgresql.repository.SalesRepository;
import com.example.integratewithpostgresql.service.SalesService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Import({ CacheConfiguration.class, SalesService.class})
@ExtendWith(SpringExtension.class)
@EnableCaching
@ImportAutoConfiguration(classes = {
    CacheAutoConfiguration.class,
    RedisAutoConfiguration.class
})
class SalesServiceCachingIntegrationTest {

    private static final UUID ID = UUID.randomUUID();
    @MockBean
    private SalesRepository mocksalesRepository;

    @Autowired
    private SalesService salesService;

    @Autowired
    private CacheManager cacheManager;

    @AfterEach
    void tearDown() {
        cacheManager.getCache("salesCache").clear();
    }


    @Test
    void givenRedisCaching_whenFindItemById_thenItemReturnedFromCache() {
        Sales sale = new Sales(ID, "name", "100");
        given(mocksalesRepository.findById(ID))
            .willReturn(Optional.of(sale));


        Sales itemCacheMiss = salesService.findById(ID);
        Sales itemCacheHit = salesService.findById(ID);

        System.out.println("sale: " + sale);
        System.out.println("itemCacheMiss: " + itemCacheMiss);
        System.out.println("itemCacheHit: " + itemCacheHit);
        System.out.println("itemFromCache: " + itemFromCache());
        assertThat(itemCacheMiss).isEqualTo(sale);
        verify(mocksalesRepository, times(1)).findById(ID);
        assertThat(itemCacheHit).isEqualTo(sale);
        assertThat(itemFromCache()).isEqualTo(sale);
    }

    private Object itemFromCache() {
        return cacheManager.getCache("salesCache").get(ID).get();
    }
}

