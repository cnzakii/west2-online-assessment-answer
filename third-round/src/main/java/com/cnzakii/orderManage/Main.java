package com.cnzakii.orderManage;

import com.cnzakii.orderManage.dto.OrderDTO;
import com.cnzakii.orderManage.dto.ProductDTO;
import com.cnzakii.orderManage.entity.Inventory;
import com.cnzakii.orderManage.entity.Order;
import com.cnzakii.orderManage.entity.Product;
import com.cnzakii.orderManage.mapper.InventoryMapper;
import com.cnzakii.orderManage.mapper.OrderMapper;
import com.cnzakii.orderManage.mapper.ProductMapper;
import com.cnzakii.orderManage.util.MybatisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


/**
 * 主程序
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-08-05
 **/
public class Main {

    public static SqlSession sqlSession = MybatisUtils.getSession();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        mainPage();

        while (true) {
            int num = 0;
            String input = sc.nextLine();

            if (Objects.equals(input, "exit")) {
                // 如果用户输入为exit，则直接退出程序
                System.exit(0);
            } else {
                // 如果为数字
                num = Integer.parseInt(input);
            }

            switch (num) {
                case 1 -> productInformationPage();
                case 2 -> orderInformationPage();
            }
            mainPage();
        }

    }

    /**
     * 主页面程序
     */
    public static void mainPage() {
        System.out.println(
                """
                        ------------------------------------
                        |请输入对应编号执行对应操作            |
                        ------------------------------------
                        |1.操作商品信息                      |
                        ------------------------------------
                        |2.操作订单信息                      |
                        ------------------------------------
                        |exit.退出程序                       |
                        ------------------------------------
                        """);


    }

    /**
     * 操作订单信息程序
     */
    public static void orderInformationPage() {
        String s = "";
        // 当输入为home时，退出循环
        while (!Objects.equals(s, "home")) {
            System.out.println("""
                    ------------------------------------
                    |请输入对应编号执行对应操作            |
                    ------------------------------------
                    |1.添加订单信息                      |
                    ------------------------------------
                    |2.删除订单信息                      |
                    ------------------------------------
                    |3.查看订单信息                      |
                    ------------------------------------
                    |home.返回主页面                     |
                    ------------------------------------
                    |exit.退出程序                       |
                    ------------------------------------
                    """);
            String input = sc.nextLine();
            int num = 0;
            if (Objects.equals(input, "exit")) {
                // 如果用户输入为exit，则直接退出程序
                System.exit(0);
            } else if (Objects.equals(input, "home")) {
                s = input;
            } else {
                // 如果为数字
                num = Integer.parseInt(input);
            }

            switch (num) {
                case 1 -> insertOrder();
                case 2 -> deleteOrder();
                case 3 -> showOrderList();
            }
        }
    }

    /**
     * 添加订单
     */
    public static void insertOrder() {
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        InventoryMapper inventoryMapper = sqlSession.getMapper(InventoryMapper.class);

        System.out.println("请输入购买的商品编号");
        int productId = sc.nextInt();
        System.out.println("请输入购买的商品数量");
        int quantity = sc.nextInt();
        sc.nextLine();

        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setCreateTime(LocalDateTime.now());

        // 查看该商品的原始库存，如果为新则跳过；
        Inventory inventory = inventoryMapper.selectInventoryById(productId);

        // 开启事务
        try {
            // 添加订单
            orderMapper.insertOrder(order);
            // 增加库存
            if (inventory == null) {
                inventory = new Inventory();
                inventory.setId(productId);
                inventory.setQuantity(order.getQuantity());
            } else {
                inventory.setQuantity(inventory.getQuantity() + order.getQuantity());
                inventoryMapper.deleteInventoryById(inventory.getId());
            }
            inventoryMapper.insertInventory(inventory);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        }
        System.out.println("创建订单成功");
    }


    /**
     * 删除订单信息程序
     */
    public static void deleteOrder() {
        System.out.println("请输入你要删除的订单编号");
        int id = sc.nextInt();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        Order order = orderMapper.selectOrderById(id);
        if (order == null) {
            System.out.println("该订单编号无效");
        } else {
            orderMapper.deleteOrderById(id);
            sqlSession.commit();
            System.out.println("删除成功！！！");
        }
    }

    /**
     * 查看订单信息
     */
    public static void showOrderList() {
        String s = "";
        int pageNum = 1;
        // 当输入为home时，退出循环
        while (!Objects.equals(s, "back")) {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

            PageHelper.startPage(pageNum, 10);
            PageInfo<Order> pageInfo = new PageInfo<>(orderMapper.selectAll());

            System.out.println("""
                    ------------------------------------
                    |订单信息                            |
                    ------------------------------------""");
            System.out.println("|当前页数：" + pageInfo.getPageNum() + "  总页数：" + pageInfo.getPages() + "              |");
            System.out.println("------------------------------------");

            // 将Order 转换成OrderDTO

            List<OrderDTO> productDTOList = pageInfo.getList()
                    .stream()
                    .map(Main::convertOrderToDTO)
                    .toList();

            productDTOList.forEach(System.out::println);
            System.out.println("""
                    ------------------------------------
                    |请输入对应页数跳转至相应页面           |
                    ------------------------------------
                    |back.返回上一级页面                  |
                    ------------------------------------
                    |exit.退出程序                       |
                    ------------------------------------""");

            String input = sc.nextLine();

            if (Objects.equals(input, "exit")) {
                // 如果用户输入为exit，则直接退出程序
                System.exit(0);
            } else if (Objects.equals(input, "back")) {
                // 如果用户输入为home，则赋值给s，以便退出循环
                s = input;
            } else {
                // 如果为数字
                pageNum = Integer.parseInt(input);
            }
        }
    }

    /**
     * 将Order转成OrderDTO
     *
     * @param order Order对象
     * @return OrderDTO对象
     */
    public static OrderDTO convertOrderToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        Product product = productMapper.selectProductById(order.getProductId());

        orderDTO.setId(order.getId());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setCreateTime(order.getCreateTime());
        orderDTO.setProductId(order.getProductId());
        orderDTO.setProductName(product.getName());
        orderDTO.setProductPrice(product.getPrice());

        return orderDTO;
    }

    /**
     * 操作商品信息程序
     */
    public static void productInformationPage() {
        String s = "";
        // 当输入为home时，退出循环
        while (!Objects.equals(s, "home")) {
            System.out.println("""
                    ------------------------------------
                    |请输入对应编号执行对应操作             |
                    ------------------------------------
                    |1.查看商品信息及库存                  |
                    ------------------------------------
                    |2.修改商品信息                      |
                    ------------------------------------
                    |3.添加商品信息                      |
                    ------------------------------------
                    |home.返回主页面                     |
                    ------------------------------------
                    |exit.退出程序                       |
                    ------------------------------------
                    """);

            String input = sc.nextLine();
            int num = 0;
            if (Objects.equals(input, "exit")) {
                // 如果用户输入为exit，则直接退出程序
                System.exit(0);
            } else if (Objects.equals(input, "home")) {
                s = input;
            } else {
                // 如果为数字
                num = Integer.parseInt(input);
            }

            switch (num) {
                case 1 -> showProductList();
                case 2 -> updateProduct();
                case 3 -> insertProduct();
            }
        }
    }

    /**
     * 添加商品信息程序
     */
    public static void insertProduct() {
        System.out.println("请输入新商品的名称");
        String name = sc.nextLine();
        double price = 0;
        if (name == null || name.isEmpty()) {
            System.out.println("商品名称不能为空！！");
        } else {
            System.out.println("请输入新商品的价格");
            price = sc.nextDouble();
            sc.nextLine();
        }

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        productMapper.insertProduct(product);
        sqlSession.commit();
        System.out.println("添加商品信息成功");

    }

    /**
     * 修改商品信息程序
     */
    public static void updateProduct() {
        System.out.println("请输入你要修改的商品编号");
        int id = sc.nextInt();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        Product product = productMapper.selectProductById(id);
        if (product == null) {
            System.out.println("该商品编号无效！！");
        } else {
            System.out.println("请输入新的商品名称(如无需修改，则直接回车跳过)，当前商品名称：" + product.getName());
            sc.nextLine();
            String name = sc.nextLine();
            if (name != null && !name.isEmpty()) {
                product.setName(name);
            }

            System.out.println("请输入新的商品价格(如无需修改，则直接回车跳过)，当前商品价格：" + product.getPrice());
            String price = sc.nextLine();
            if (price != null && !price.isEmpty()) {
                product.setPrice(Double.parseDouble(price));
            }
        }
        productMapper.updateProductById(product);
        sqlSession.commit();
        System.out.println("修改成功！！！");
    }

    /**
     * 查看商品信息及库存页面
     */
    public static void showProductList() {
        String s = "";
        int pageNum = 1;
        // 当输入为home时，退出循环
        while (!Objects.equals(s, "back")) {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

            PageHelper.startPage(pageNum, 10);
            PageInfo<Product> pageInfo = new PageInfo<>(productMapper.selectAll());

            System.out.println("""
                    ------------------------------------
                    |商品信息及库存                       |
                    ------------------------------------""");
            System.out.println("|当前页数：" + pageInfo.getPageNum() + "  总页数：" + pageInfo.getPages() + "              |");
            System.out.println("------------------------------------");

            // 将Product 转换成ProductDTO
            List<ProductDTO> productDTOList = pageInfo.getList()
                    .stream()
                    .map(Main::convertProductToDTO)
                    .toList();

            productDTOList.forEach(System.out::println);
            System.out.println("""
                    ------------------------------------
                    |请输入对应页数跳转至相应页面           |
                    ------------------------------------
                    |back.返回上一级页面                  |
                    ------------------------------------
                    |exit.退出程序                       |
                    ------------------------------------""");

            String input = sc.nextLine();

            if (Objects.equals(input, "exit")) {
                // 如果用户输入为exit，则直接退出程序
                System.exit(0);
            } else if (Objects.equals(input, "back")) {
                // 如果用户输入为home，则赋值给s，以便退出循环
                s = input;
            } else {
                // 如果为数字
                pageNum = Integer.parseInt(input);
            }
        }
    }

    /**
     * 将Product 转换成ProductDTO
     *
     * @param product product对象
     * @return ProductDTO对象
     */
    public static ProductDTO convertProductToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());

        // 查询库存
        InventoryMapper mapper = sqlSession.getMapper(InventoryMapper.class);
        Inventory inventory = mapper.selectInventoryById(product.getId());
        if (inventory != null) {
            productDTO.setQuantity(inventory.getQuantity());
        } else {
            // 如果库存信息不存在，则添加，并初始化库存为0
            inventory = new Inventory();
            inventory.setId(product.getId());
            inventory.setQuantity(0);
            mapper.insertInventory(inventory);
            sqlSession.commit();
            productDTO.setQuantity(0);
        }
        return productDTO;
    }
}
