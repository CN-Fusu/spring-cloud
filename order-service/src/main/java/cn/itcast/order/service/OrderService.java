package cn.itcast.order.service;

import cn.itcast.order.clients.UserClient;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserClient userClient;
//    @Autowired
//    private RestTemplate restTemplate;
//
//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//
//        // 2.查询用户信息
//        String url = "http://userservice/user/" + order.getUserId();
//        User user = restTemplate.getForObject( url, User.class);
//        order.setUser(user);
//
//        // 4.返回
//        return order;
//    }


    public Order queryOrderById(Long id){

        Order order = orderMapper.findById(id);

        User user = userClient.findUserById(order.getUserId());

        order.setUser(user);

        return order;

    }
}
