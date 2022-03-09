package pers.guangjian.hadoken.common.util.object;

import pers.guangjian.hadoken.common.entity.PageParam;

/**
 * @author yanggj
 *  {@link PageParam} 工具类
 * @date 2022/02/28 16:08
 * @version 1.0.0
 */
public class PageUtils {

    public static int getStart(PageParam pageParam) {
        return (pageParam.getPageNo() - 1) * pageParam.getPageSize();
    }

}
