package com.guangjian.hadoken.infra.mybatis.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.guangjian.hadoken.common.entity.PageParam;
import com.guangjian.hadoken.common.entity.PageResult;
import com.guangjian.hadoken.infra.mybatis.core.util.MyBatisUtils;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @Author: yanggj
 * @Description: 对 BaseMapper 进行拓展，提供更多的能力
 * @Date: 2022/02/28 15:04
 * @Version: 1.0.0
 */
public interface BaseMapperPlus<T> extends BaseMapper<T> {
    default PageResult<T> selectPage(PageParam pageParam, @Param("ew") Wrapper<T> queryWrapper) {
        // MyBatis Plus 查询
        IPage<T> mpPage = MyBatisUtils.buildPage(pageParam);
        selectPage(mpPage, queryWrapper);
        // 转换返回
        return new PageResult<>(mpPage.getRecords(), mpPage.getTotal());
    }

    default T selectOne(String field, Object value) {
        return selectOne(new QueryWrapper<T>().eq(field, value));
    }

    default T selectOne(SFunction<T, ?> field, Object value) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default T selectOne(String field1, Object value1, String field2, Object value2) {
        return selectOne(new QueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }

    default T selectOne(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }

    default Integer selectCount(String field, Object value) {
        return selectCount(new QueryWrapper<T>().eq(field, value)).intValue();
    }

    default Integer selectCount(SFunction<T, ?> field, Object value) {
        return selectCount(new LambdaQueryWrapper<T>().eq(field, value)).intValue();
    }

    default List<T> selectList() {
        return selectList(new QueryWrapper<>());
    }

    default List<T> selectList(String field, Object value) {
        return selectList(new QueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList(SFunction<T, ?> field, Object value) {
        return selectList(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList(String field, Collection<?> values) {
        return selectList(new QueryWrapper<T>().in(field, values));
    }

    default List<T> selectList(SFunction<T, ?> field, Collection<?> values) {
        return selectList(new LambdaQueryWrapper<T>().in(field, values));
    }

    default void insertBatch(Collection<T> entities) {
        // TODO 芋艿：修改成支持批量的
        entities.forEach(this::insert);
    }

}
