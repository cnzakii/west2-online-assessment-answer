package com.cnzakii.mapper;

import com.cnzakii.entity.Product;

import java.util.List;

/**
 * 商品类的mapper接口
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/
public interface ProductMapper {

    /**
     * 添加商品
     *
     * @param product 商品信息
     */
    void insertProduct(Product product);

    /**
     * 根据Id删除商品
     *
     * @param id 商品Id
     */
    void deleteProductById(Integer id);

    /**
     * 根据 id 更新 商品信息
     *
     * @param product 商品信息
     */
    void updateProductById(Product product);

    /**
     * 根据Id 查询商品信息
     *
     * @param id 商品Id
     * @return 商品信息
     */
    Product selectProductById(Integer id);

    /**
     * 查询所有的商品信息
     * @return 所有商品信息
     */
    List<Product> selectAll();

}
