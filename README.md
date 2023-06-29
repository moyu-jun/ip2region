# ip2region

> ip2region 是一个高性能的离线IP地址查询库，它能够提供 10 微秒级别的查询速度，快速准确地将IP地址转换为地理位置信息。
> 
> 本项目基于 `https://github.com/lionsoul2014/ip2region` 进行 Java 版本的进一步封装，使其更易用。

## 特点和功能

- 超高性能：采用基于内存的数据查询算法，快速响应查询请求，提供 10 微秒级别的查询速度。
- 准确度高：支持亿级别的 IP 数据段行数，具有全球范围的覆盖度和准确性。
- 简单易用：提供简洁友好的 API，方便集成和使用。
- 兼容性强：可与各种 Java 应用和框架无缝集成。
- 线程安全：使用整个 xdb 数据缓存到内存中进行查询，可以安全的用于并发。

## 使用示例

### maven 中央仓库获取依赖

```xml
<dependency>
    <groupId>com.junmoyu</groupId>
    <artifactId>ip2region</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 根据 IP 查询地区

```java
public class Example {
    public static void main(String[] args) {
        // 使用字符串类型的IP进行查询
        Region region = RegionSearcher.getRegion("36.7.136.189");
        System.out.println(region.toString());

        // 输出查询结果
        System.out.println("国家：" + region.getCountry());
        System.out.println("地区：" + region.getRegion());
        System.out.println("省份：" + region.getProvince());
        System.out.println("城市：" + region.getCity());
        System.out.println("运营商：" + region.getIsp());

        // 使用 Long 类型的IP进行查询
        Region regionByLongIp = RegionSearcher.getRegion(604473533L);
        System.out.println(regionByLongIp.toString());
    }
}
```

## 鸣谢

* ip2region: https://github.com/lionsoul2014/ip2region
