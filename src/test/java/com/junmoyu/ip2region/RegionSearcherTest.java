package com.junmoyu.ip2region;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * RegionSearcher unit test.
 *
 * @author 莫语
 * @since 1.0.0
 */
public class RegionSearcherTest {

    private static final String[] IP_STRING_LIST = {"36.7.136.189", "45.90.208.47", "112.32.32.244", "218.1.71.0",
            "14.23.30.96", "106.47.182.104", "104.149.18.176", "223.104.93.93", "110.250.14.24", "183.225.0.9"};

    @BeforeAll
    public static void before() {
        // ip2region.xdb 数据预热
        RegionSearcher.getRegion("127.0.0.1");
    }

    @Test
    public void testGetRegionWithStringIp() {
        for (String ip : IP_STRING_LIST) {
            long startTime = System.nanoTime();
            Region region = RegionSearcher.getRegion(ip);
            long duration = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
            System.out.printf("region: %s-%s-%s-%s, duration: %d μs}\n", region.getCountry(), region.getProvince(), region.getCity(), region.getIsp(), duration);
        }
    }

    @Test
    public void testGetRegionWithLongIp() {
        long startTime = System.nanoTime();
        Region region = RegionSearcher.getRegion(604473533L);
        long duration = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
        System.out.printf("region: %s-%s-%s-%s, duration: %d μs}\n", region.getCountry(), region.getProvince(), region.getCity(), region.getIsp(), duration);
    }
}
