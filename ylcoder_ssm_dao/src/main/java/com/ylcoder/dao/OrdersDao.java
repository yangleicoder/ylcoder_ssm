package com.ylcoder.dao;

import com.ylcdoer.domain.Orders;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface OrdersDao {

    /**
     * 一对一查询所有
     *
     * @return
     * @throws Exception
     */
    @Select("select * from orders")
    @Results(id = "o", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId",
                    one = @One(select = "com.ylcoder.dao.ProductDao.findById", fetchType = FetchType.EAGER))

    })
    List<Orders> findAll() throws Exception;

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Select("select * from orders where id=#{id}")
    @Results(id = "oa", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId",
                    one = @One(select = "com.ylcoder.dao.ProductDao.findById", fetchType = FetchType.EAGER)),
            @Result(property = "member", column = "memberId",
                    one = @One(select = "com.ylcoder.dao.MemberDao.findById", fetchType = FetchType.EAGER)),
            @Result(property = "travellers", column = "id",javaType = List.class,
                    many = @Many(select = "com.ylcoder.dao.TravellerDao.findTravellerListById",fetchType = FetchType.EAGER))
    })
    Orders findById(String id) throws Exception;
}
