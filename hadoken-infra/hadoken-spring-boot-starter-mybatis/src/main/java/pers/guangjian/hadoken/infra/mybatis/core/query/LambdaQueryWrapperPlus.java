package pers.guangjian.hadoken.infra.mybatis.core.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * @Author: yanggj
 * @Description: 1. 拼接条件的方法，增加 xxxIfPresent 方法，用于判断值不存在的时候，不要拼接到条件中。
 * @Date: 2022/02/28 15:07
 * @Version: 1.0.0
 */
public class LambdaQueryWrapperPlus<T> extends LambdaQueryWrapper<T> {

    public LambdaQueryWrapperPlus<T> likeIfPresent(SFunction<T, ?> column, String val) {
        if (StringUtils.hasText(val)) {
            return (LambdaQueryWrapperPlus<T>) super.like(column, val);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> inIfPresent(SFunction<T, ?> column, Collection<?> values) {
        if (!CollectionUtils.isEmpty(values)) {
            return (LambdaQueryWrapperPlus<T>) super.in(column, values);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> inIfPresent(SFunction<T, ?> column, Object... values) {
        if (!ArrayUtils.isEmpty(values)) {
            return (LambdaQueryWrapperPlus<T>) super.in(column, values);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> eqIfPresent(SFunction<T, ?> column, Object val) {
        if (val != null) {
            return (LambdaQueryWrapperPlus<T>) super.eq(column, val);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> neIfPresent(SFunction<T, ?> column, Object val) {
        if (val != null) {
            return (LambdaQueryWrapperPlus<T>) super.ne(column, val);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> gtIfPresent(SFunction<T, ?> column, Object val) {
        if (val != null) {
            return (LambdaQueryWrapperPlus<T>) super.gt(column, val);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> geIfPresent(SFunction<T, ?> column, Object val) {
        if (val != null) {
            return (LambdaQueryWrapperPlus<T>) super.ge(column, val);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> ltIfPresent(SFunction<T, ?> column, Object val) {
        if (val != null) {
            return (LambdaQueryWrapperPlus<T>) super.lt(column, val);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> leIfPresent(SFunction<T, ?> column, Object val) {
        if (val != null) {
            return (LambdaQueryWrapperPlus<T>) super.le(column, val);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> betweenIfPresent(SFunction<T, ?> column, Object val1, Object val2) {
        if (val1 != null && val2 != null) {
            return (LambdaQueryWrapperPlus<T>) super.between(column, val1, val2);
        }
        if (val1 != null) {
            return (LambdaQueryWrapperPlus<T>) ge(column, val1);
        }
        if (val2 != null) {
            return (LambdaQueryWrapperPlus<T>) le(column, val2);
        }
        return this;
    }

    // ========== 重写父类方法，方便链式调用 ==========

    @Override
    public LambdaQueryWrapperPlus<T> eq(boolean condition, SFunction<T, ?> column, Object val) {
        super.eq(condition, column, val);
        return this;
    }

    @Override
    public LambdaQueryWrapperPlus<T> eq(SFunction<T, ?> column, Object val) {
        super.eq(column, val);
        return this;
    }

    @Override
    public LambdaQueryWrapperPlus<T> orderByDesc(SFunction<T, ?> column) {
        super.orderByDesc(true, column);
        return this;
    }

    @Override
    public LambdaQueryWrapperPlus<T> last(String lastSql) {
        super.last(lastSql);
        return this;
    }

    @Override
    public LambdaQueryWrapperPlus<T> in(SFunction<T, ?> column, Collection<?> coll) {
        super.in(column, coll);
        return this;
    }
}
