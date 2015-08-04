package com.derson.pumelo.imageloader.fresco;

/**
 * Created by chengli on 15/8/4.
 */
public interface ImageDiskConfig {

    /**
     * 磁盘缓存路径名
     */
    public static final String BASE_CACHE_DIRECTORY = "mycache";

    /**
     * 磁盘缓存大小
     */
    public static final long MAX_DISK_CACHE_SIZE = 50 * 1024 * 1024;

    /**
     * 磁盘缓存大小（在较低磁盘空间下）
     */
    public static final long MAX_DISK_CACHE_SIZE_ON_LOWSPACE = 30 * 1024 * 1024;

    /**
     * 磁盘缓存大小（在很低磁盘空间下）
     */
    public static final long MAX_DISK_CACHE_SIZE_ON_VERYLOWSPACE = 10 * 1024 * 1024;
}
