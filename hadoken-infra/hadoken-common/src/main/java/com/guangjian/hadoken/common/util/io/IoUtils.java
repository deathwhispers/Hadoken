package com.guangjian.hadoken.common.util.io;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;

import java.io.InputStream;

/**
 * @Author: yanggj
 * @Description: IO 工具类，用于 {@link IoUtil} 缺失的方法
 * @Date: 2022/02/28 15:51
 * @Version: 1.0.0
 */
public class IoUtils {

    /**
     * 从流中读取 UTF8 编码的内容
     *
     * @param in      输入流
     * @param isClose 是否关闭
     * @return 内容
     * @throws IORuntimeException IO 异常
     */
    public static String readUtf8(InputStream in, boolean isClose) throws IORuntimeException {
        return StrUtil.utf8Str(IoUtil.read(in, isClose));
    }

}