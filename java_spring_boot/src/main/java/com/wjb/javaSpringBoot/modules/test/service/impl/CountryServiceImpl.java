package com.wjb.javaSpringBoot.modules.test.service.impl;

import com.wjb.javaSpringBoot.modules.test.dao.CountryDao;
import com.wjb.javaSpringBoot.modules.test.entity.Country;
import com.wjb.javaSpringBoot.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ASUS on 2020/8/11 17:14
 */
@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;

    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }
}
