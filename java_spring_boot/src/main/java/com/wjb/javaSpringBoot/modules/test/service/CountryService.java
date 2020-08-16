package com.wjb.javaSpringBoot.modules.test.service;

import com.wjb.javaSpringBoot.modules.test.entity.Country;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
 * Created by ASUS on 2020/8/11 17:12
 */
public interface CountryService {
    Country getCountryByCountryId(int countryId);

    Country getCountryByCountryName(String countryName);

    //redis
    Country mograteCountryByRedis(int countryId);
}
