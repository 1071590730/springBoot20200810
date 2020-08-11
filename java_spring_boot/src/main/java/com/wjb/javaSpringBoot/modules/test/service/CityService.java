package com.wjb.javaSpringBoot.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.wjb.javaSpringBoot.modules.common.vo.SearchVo;
import com.wjb.javaSpringBoot.modules.test.entity.City;

import java.util.List;

/**
 * Created by ASUS on 2020/8/11 19:40
 */
public interface CityService {

    List<City> getCitiesByCountryId(int countryId);

    PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo);

}
