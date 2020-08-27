package com.wjb.springcloud.springCloudClientTest.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.wjb.springcloud.springCloudClientTest.modules.common.vo.Result;
import com.wjb.springcloud.springCloudClientTest.modules.common.vo.SearchVo;
import com.wjb.springcloud.springCloudClientTest.modules.test.entity.City;


import java.util.List;

/**
 * Created by ASUS on 2020/8/11 19:40
 */
public interface CityService {

    List<City> getCitiesByCountryId(int countryId);

    PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo);

    PageInfo<City> getCitiesBySearchVo(SearchVo SearchVo);

    Result<City> insertCity(City city);

    Result<City> updateCity(City city);

    Result<Object> deleteCity(int cityId);
}
