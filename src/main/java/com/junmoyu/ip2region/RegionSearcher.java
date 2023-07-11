package com.junmoyu.ip2region;

import org.lionsoul.ip2region.xdb.Searcher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * IP to Region Searcher.
 *
 * @author 莫语
 * @since 1.0.0
 */
public final class RegionSearcher {

    private static final Searcher SEARCHER;

    /*
     * Initialize the Searcher object.
     */
    static {
        long startTime = System.nanoTime();
        try (InputStream inputStream = RegionSearcher.class.getClassLoader().getResourceAsStream("ip2region.xdb")) {
            if (inputStream == null) {
                throw new IOException("ip2region.xdb loading failed.");
            }
            byte[] buffer = new byte[8192];
            int bytesRead;
            try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
                SEARCHER = Searcher.newWithBuffer(output.toByteArray());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long cost = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
        System.out.printf("ip2region.xdb loading completed, took: %d μs\n", cost);
    }

    /**
     * Retrieves the geographic region corresponding to the given IP address.
     *
     * @param ip The IP address in long format.
     * @return The geographic region associated with the IP address.
     * @throws RuntimeException if an exception occurs during the region lookup process.
     */
    public static Region getRegion(long ip) {
        try {
            return convert(SEARCHER.search(ip));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the geographic region corresponding to the given IP address.
     *
     * @param ip The IP address in String format.
     * @return The geographic region associated with the IP address.
     * @throws RuntimeException if an exception occurs during the region lookup process.
     */
    public static Region getRegion(String ip) {
        try {
            return convert(SEARCHER.search(ip));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts a region string into a Region object.
     *
     * @param regionStr The region string to be converted.
     * @return A Region object representing the converted region string, or null if the input is invalid or empty.
     */
    private static Region convert(String regionStr) {
        if (regionStr == null || regionStr.length() == 0) {
            return null;
        }
        String[] regionSplit = regionStr.split("\\|");
        if (regionSplit.length != 5) {
            return null;
        }
        Region region = new Region();
        region.setCountry("0".equals(regionSplit[0]) ? null : regionSplit[0]);
        region.setRegion("0".equals(regionSplit[1]) ? null : regionSplit[1]);
        region.setProvince("0".equals(regionSplit[2]) ? null : regionSplit[2]);
        region.setCity("0".equals(regionSplit[3]) ? null : regionSplit[3]);
        region.setIsp("0".equals(regionSplit[4]) ? null : regionSplit[4]);
        return region;
    }
}