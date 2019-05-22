package com.deer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deer.model.User;

import java.util.Set;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Mr_Deer
 * @since 2019-05-17
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名 查询所有的权限表达式
     *
     * @param username 用户名
     * @return 结果集
     */
    Set<String> getPermissionExpressionsByUsername(String username);
}
