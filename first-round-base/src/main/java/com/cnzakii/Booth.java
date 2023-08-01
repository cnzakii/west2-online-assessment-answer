package com.cnzakii;

/**
 * 任务4-设计一个西瓜摊类
 *
 * @author Zaki
 * @version 1.0
 * @since 2023-07-31
 **/
public class Booth {

    /**
     * 摊号
     */
    private long id;

    /**
     * 摊主姓名
     */
    private String name;

    /**
     * 在售西瓜数
     */
    private int tota;

    /**
     * 是否休摊整改
     */
    private boolean isClosed;


    public Booth(long id, String name, int tota, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.tota = tota;
        this.isClosed = isClosed;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTota() {
        return tota;
    }

    public void setTota(int tota) {
        this.tota = tota;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    @Override
    public String toString() {
        return "西瓜摊信息如下：" +
                "\nid：\t" + id +
                "\n姓名：\t'" + name +
                "\n在售西瓜数：\t" + tota +
                "\n是否休摊整改：\t" + isClosed;
    }

    /**
     * 向目标摊位卖家 购买指定数量的西瓜
     *
     * @param merchant 目标摊位卖家
     * @param num      指定数量的西瓜
     */
    public static void purchase(Booth merchant, int num) {
        if (num <= 0) {
            System.out.println("购买的西瓜数必须为正数");
            return;
        }
        if (merchant.isClosed) {
            System.out.println("商家处于休摊整改状态");
            return;
        }
        if (num > merchant.tota) {
            System.out.println("购买西瓜数不能大于在售西瓜数");
            return;
        }

        merchant.tota = merchant.tota - num;
        System.out.println("购买成功");
    }

    /**
     * 该摊位进货西瓜方法
     *
     * @param num 西瓜数量
     */
    public void restock(int num) {
        if (num <= 0) {
            System.out.println("进货西瓜数必须为正数");
            return;
        }
        if (num > 200) {
            System.out.println("单次进货量不能超过200");
            return;
        }

        this.tota = this.tota + num;
    }

    /**
     * 让 booths 中所有未被休业整改的摊位歇业(将 false 变为 true)
     *
     * @param booths 需要歇业的摊位数组
     */
    public static void closeBooths(Booth[] booths) {
        for (Booth booth : booths) {
            if (booth.isClosed) {
                System.out.println("该摊位已在休业整改");
                System.out.println(booth);
            }
            booth.setClosed(true);
        }
    }
}
