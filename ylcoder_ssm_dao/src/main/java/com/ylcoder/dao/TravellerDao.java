package com.ylcoder.dao;

import com.ylcdoer.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {
    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId=#{id})")
    public List<Traveller> findTravellerListById(String id);
}
