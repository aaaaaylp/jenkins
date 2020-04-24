package com.mayikt.mapper;

import com.mayikt.entity.UserEntity;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: UserMapper
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2019/6/420:51
 */
public interface UserMapper {
    public UserEntity getUser(int id);
    public UserEntity getUser1(int id);
}
