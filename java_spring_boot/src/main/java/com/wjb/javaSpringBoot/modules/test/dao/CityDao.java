package com.wjb.javaSpringBoot.modules.test.dao;

import com.wjb.javaSpringBoot.modules.common.vo.SearchVo;
import com.wjb.javaSpringBoot.modules.test.entity.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ASUS on 2020/8/11 17:10
 */

@Repository
@Mapper
public interface CityDao {

    @Select("select * from m_city where country_id = #{countryId}")
    List<City> getCitiesByCountryId(int countryId);

    List<City> getCitiesByCountrySearchVo(SearchVo SearchVo);

}