package pers.guangjian.hadoken.infra.mybatis.core.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * @Author: yanggj
 * @Description: 1. 拼接条件的方法，增加 xxxIfPresent 方法，用于判断值不存在的时候，不要拼接到条件中。
 * @Date: 2022/02/28 15:07
 * @Version: 1.0.0
 */
public class QueryWrapperPlus<T> extends QueryWrapper<T> {

    public QueryWrapperPlus<T> likeIfPresent(String column, String val) {
        if (StringUtils.hasText(val)) {
            return (QueryWrapperPlus<T>) super.like(column, val);
        }
        return this;
    }

    public QueryWrapperPlus<T> inIfPresent(String column, Collection<?> values) {
        if (!CollectionUtils.isEmpty(values)) {
            return (QueryWrapperPlus<T>) super.in(column, values);
        }
        return this;
    }

    public QueryWrapperPlus<T> inIfPresent(String column, Object... values) {
        if (!ArrayUtils.isEmpty(values)) {
            return (QueryWrapperPlus<T>) super.in(column, values);
        }
        return this;
    }

    public QueryWrapperPlus<T> eqIfPresent(String column, Object val) {
        if (val != null) {
            return (QueryWrapperPlus<T>) super.eq(column, val);
        }
        return this;
    }

    public QueryWrapperPlus<T> neIfPresent(String column, Object val) {
        if (val != null) {
            return (QueryWrapperPlus<T>) super.ne(column, val);
        }
        return this;
    }

    public QueryWrapperPlus<T> gtIfPresent(String column, Object val) {
        if (val != null) {
            return (QueryWrapperPlus<T>) super.gt(column, val);
        }
        return this;
    }

    public QueryWrapperPlus<T> geIfPresent(String column, Object val) {
        if (val != null) {
            return (QueryWrapperPlus<T>) super.ge(column, val);
        }
        return this;
    }

    public QueryWrapperPlus<T> ltIfPresent(String column, Object val) {
        if (val != null) {
            return (QueryWrapperPlus<T>) super.lt(column, val);
        }
        return this;
    }

    public QueryWrapperPlus<T> leIfPresent(String column, Object val) {
        if (val != null) {
            return (QueryWrapperPlus<T>) super.le(column, val);
        }
        return this;
    }

    public QueryWrapperPlus<T> betweenIfPresent(String column, Object val1, Object val2) {
        if (val1 != null && val2 != null) {
            return (QueryWrapperPlus<T>) super.between(column, val1, val2);
        }
        if (val1 != null) {
            return (QueryWrapperPlus<T>) ge(column, val1);
        }
        if (val2 != null) {
            return (QueryWrapperPlus<T>) le(column, val2);
        }
        return this;
    }

    // ========== 重写父类方法，方便链式调用 ==========

    @Override
    public QueryWrapperPlus<T> eq(boolean condition, String column, Object val) {
        super.eq(condition, column, val);
        return this;
    }

    @Override
    public QueryWrapperPlus<T> eq(String column, Object val) {
        super.eq(column, val);
        return this;
    }

    @Override
    public QueryWrapperPlus<T> orderByDesc(String column) {
        super.orderByDesc(true, column);
        return this;
    }

    @Override
    public QueryWrapperPlus<T> last(String lastSql) {
        super.last(lastSql);
        return this;
    }

    @Override
    public QueryWrapperPlus<T> in(String column, Collection<?> coll) {
        super.in(column, coll);
        return this;
    }

}
