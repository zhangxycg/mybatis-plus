package com.zxy.mpdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxy.mpdemo.mapper.UserMapper;
import com.zxy.mpdemo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class DemoTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 1.查询所有的用户
     */
    @Test
    public void testGetAllUser() {

        List<User> users = userMapper.selectList(null);
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
    }

    /**
     * 根据条件查询
     *
     * 1.1 查询年龄大于80的
     */
    @Test
    public void testSelect1() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 80);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);

    }

    /**
     * 根据条件查询
     *
     * 1.2 查询用户名是parker并且年龄是18的
     */
    @Test
    public void testSelect2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "parker");
        wrapper.eq("age", 18);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);

    }

    /**
     * 根据条件查询
     *
     * 1.2 查询用户年龄在一个范围之间的
     */
    @Test
    public void testSelect3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 10, 20);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);

    }

    /**
     * 根据条件查询
     *
     * 1.2 根据名字模糊查询
     */
    @Test
    public void testSelect4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 指定查询的列
        wrapper.select("id", "name", "age");

        wrapper.like("name", "pa");
        // 根据年龄进行降序
        wrapper.orderByDesc("age");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);

    }

    /**
     * 2.添加用户
     */
    @Test
    public void testAddUser() {
        // 手动创建 User 对象
        User user = new User();
        user.setName("系统分析师");
        user.setAge(26);
        user.setEmail("system@qq.com");
//        user.setCreateTime(new Date());
//        user.setUpdateTime(new Date());
        int rows = userMapper.insert(user);
        System.out.println(rows);
    }

    /**
     * 3. 修改用户
     */
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(1210009368460025857L);
        user.setName("测试经理");
        int i = userMapper.updateById(user);
        System.out.println(i);

    }

    /**
     * 4.测试乐观锁
     */
    @Test
    public void testOptimisticLocker() {

        // 根据id查询用户信息
        User user = userMapper.selectById(1210021516267540481L);
        // 修改数据，调用方法实现修改
        user.setName("程序员-乐观锁");

        int i = userMapper.updateById(user);
        System.out.println(i);

    }

    /**
     * 5.根据id实现批量查询
     */
    @Test
    public void testBatchIds() {

        List<User> users = userMapper.selectBatchIds(Arrays.asList(2, 3));
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 6.简单的条件查询
     */
    @Test
    public void testConditionSelect() {

        Map<String, Object> map = new HashMap<>();
        map.put("name", "manu"); // map中的key对应的是数据库中的列名
        map.put("age", 16);
        List<User> users = userMapper.selectByMap(map);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 7.mp实现分页
     */
    @Test
    public void testPage() {

        // 创建page对象，传递两个参数：当前页和每页记录数
        Page<User> page = new Page<>(1, 3);
        // 分页查询
        userMapper.selectPage(page, null);
        // 通过page对象获取分页数据
        List<User> records = page.getRecords(); // 每页数据
        long current = page.getCurrent(); // 当前页
        long pages = page.getPages();// 总页数
        long total = page.getTotal();// 总记录数
        long size = page.getSize();// 每页记录数
        boolean previous = page.hasPrevious();// 是否有上一页
        boolean next = page.hasNext();// 是否有下一页

        System.out.println(records);
        System.out.println(current);
        System.out.println(pages);
        System.out.println(total);
        System.out.println(size);
        System.out.println(previous);
        System.out.println(next);
    }

    /**
     * 8.根据id进行删除
     */
    @Test
    public void testDeleteById() {
        int i = userMapper.deleteById(1210062670610685953L);
        System.out.println(i);

    }

}
