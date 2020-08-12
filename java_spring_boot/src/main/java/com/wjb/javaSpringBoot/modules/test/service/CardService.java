package com.wjb.javaSpringBoot.modules.test.service;

import com.wjb.javaSpringBoot.modules.common.vo.Result;
import com.wjb.javaSpringBoot.modules.test.entity.Card;

/**
 * Created by ASUS on 2020/8/12 20:18
 */
public interface CardService {
    Result<Card> insertCard(Card card);
}
