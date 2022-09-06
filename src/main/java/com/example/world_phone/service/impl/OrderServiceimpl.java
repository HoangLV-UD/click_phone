package com.example.world_phone.service.impl;

import com.example.world_phone.dto.request.order.OrderRequest;
import com.example.world_phone.dto.request.orderdetail.OrderDetailRequest;
import com.example.world_phone.dto.respone.customer.CustomerRespone;
import com.example.world_phone.dto.respone.order.OrderRespone;
import com.example.world_phone.dto.respone.order_detail.OrderDetailRespone;
import com.example.world_phone.entity.*;
import com.example.world_phone.repo.*;
import com.example.world_phone.service.IOrderService;
import com.example.world_phone.until.ConvertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 22/08/2022
 * Project_name: com.example.world_phone.service.impl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceimpl implements IOrderService {
    private final OrdersRepo ordersRepo;

    private final ConvertUtil convertUtil;

    private final CustomerRepo customerRepo;

    private final OrdersDetailRepo ordersDetailRepo;

    private final PropertyProductRepo propertyProductRepo;

    private final ProductRepo productRepo;


    @Override
    public List<OrderRespone> findAllOrder() {
        List<OrdersEntity> entities = ordersRepo.findByDeleteFlagIsFalse();
        List<OrderRespone> respones = new ArrayList<>();
        for (OrdersEntity entity: entities
             ) {
            OrderRespone orderRespone = new OrderRespone();
            orderRespone.setShipping(entity.getStatus());
            orderRespone.setTotalString(convertUtil.moneyToStringFormat(entity.getTotalMoney()));
            orderRespone.setCodeOrder(entity.getCodeOrder());
            orderRespone.setTypeOrder(String.valueOf(entity.getStatusPay()));
            orderRespone.setOrderCreate(entity.getCreateDate().toString());
            orderRespone.setCustomerName(entity.getCustomerEntity().getFullName());
            respones.add(orderRespone);
        }
        return respones;
    }

    @Override
    public OrderRespone findByOrder(String id) {
        OrdersEntity entity = ordersRepo.findByCodeOrderAndDeleteFlagIsFalse(id);
        CustomerEntity customerEntity = customerRepo.getById(entity.getCustomerEntity().getId());
        List<OrdersDetailEntity> ordersDetailEntities = ordersDetailRepo.findByDeleteFlagIsFalseAndOrdersEntity(entity);
        List<OrderDetailRespone> orderDetailRespones = new ArrayList<>();
        for (OrdersDetailEntity detail: ordersDetailEntities
             ) {
            OrderDetailRespone detailRespone = new OrderDetailRespone();
            detailRespone.setIdDetail(String.valueOf(detail.getId()));
            detailRespone.setIdProductProperty(String.valueOf(detail.getIdPropertyProduct()));
            ProductPropertyEntity propertyEntity = propertyProductRepo.getById(detail.getIdPropertyProduct());
            ProductEntity productEntity = propertyEntity.getRomEntity().getProductEntity();

            detailRespone.setQuantity(String.valueOf(detail.getQuantity()));
            detailRespone.setColorProduct(propertyEntity.getColorEntity().getValueColor());
            detailRespone.setRomProduct(propertyEntity.getRomEntity().getName());
            detailRespone.setGiaBan(detail.getPrice());
            detailRespone.setGiaBanString(convertUtil.moneyToStringFormat(detail.getPrice()));
            detailRespone.setNameProduct(productEntity.getName());
            detailRespone.setImageProduct(productEntity.getImage_key());
            detailRespone.setTonKho(String.valueOf(propertyEntity.getQuantity()));
            detailRespone.setTongTienString(convertUtil.moneyToStringFormat(detail.getPrice()* detail.getQuantity()) );
            detailRespone.setTongTien(detail.getPrice()* detail.getQuantity());
            detailRespone.setStatusProduct(propertyEntity.getQuantity() == 0 ? "0" : "1");
            orderDetailRespones.add(detailRespone);

        }
        CustomerRespone customerRespone = new CustomerRespone();
        customerRespone.setName(customerEntity.getFullName());
        customerRespone.setPhone(customerEntity.getPhoneNumber());

        OrderRespone respone = new OrderRespone();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        respone.setDeliveryDate(df.format(entity.getReceiveDate()));
        respone.setShipping(entity.getStatus());
        respone.setTotalString(convertUtil.moneyToStringFormat(entity.getTotalMoney()));
        respone.setTotal(String.valueOf(entity.getTotalMoney()));
        respone.setCodeOrder(entity.getCodeOrder());
        respone.setAddress(entity.getAddress());
        respone.setTypeOrder(String.valueOf(entity.getStatusPay()));
        respone.setOrderCreate(entity.getCreateDate().toString());
        respone.setCustomerRespone(customerRespone);
        respone.setListDetail(orderDetailRespones);
        return respone;
    }

    @Override
    public String updateOrder(OrderRequest request) {
        OrdersEntity entity = ordersRepo.findByCodeOrderAndDeleteFlagIsFalse(request.getId());
        entity.setTotalMoney(Long.valueOf(request.getTotalMoney()));
        ordersRepo.save(entity);
        for (OrderDetailRequest detail : request.getDetailRequest()
             ) {

            if(detail.getId() != null && !detail.getId().equals("")){
                String id = detail.getId().replace("orderDetail", "");
                OrdersDetailEntity detailEntity = ordersDetailRepo.findByDeleteFlagIsFalseAndId(Long.valueOf(id));
                detailEntity.setQuantity(Long.valueOf(detail.getQuantity()));
                detailEntity.setPrice(Long.valueOf(detail.getPrice()));
                ordersDetailRepo.save(detailEntity);
            }else {
                OrdersDetailEntity detailEntity = new OrdersDetailEntity();
                detailEntity.setOrdersEntity(entity);
                detailEntity.setIdPropertyProduct(Long.valueOf(detail.getProductId().replace("productDetail", "")));
                detailEntity.setQuantity(Long.valueOf(detail.getQuantity()));
                detailEntity.setPrice(Long.valueOf(detail.getPrice()));
                ordersDetailRepo.save(detailEntity);
            }
        }

        return "ok";
    }

    @Override
    public String confirmOrder(OrderRequest request) throws ParseException {
        OrdersEntity entity = ordersRepo.findByCodeOrderAndDeleteFlagIsFalse(request.getId());
        entity.setAddress(request.getRecipientAddress());
        entity.setReceiveDate(request.getDeliveryDate());
        entity.setStatus("1");
        ordersRepo.save(entity);
        return "ok";
    }
}
