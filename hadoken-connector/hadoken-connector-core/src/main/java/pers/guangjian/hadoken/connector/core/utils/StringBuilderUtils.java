package pers.guangjian.hadoken.connector.core.utils;

import io.netty.util.concurrent.FastThreadLocal;

import java.util.function.BiConsumer;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 14:15
 */
public class StringBuilderUtils {


    private static final FastThreadLocal<StringBuilder> cacheBuilder = new FastThreadLocal<StringBuilder>() {
        @Override
        protected StringBuilder initialValue() {
            return new StringBuilder();
        }
    };

    private static StringBuilder takeBuilder() {
        return cacheBuilder.get();
    }

    public static <T, T2, T3, T4> String buildString(T data, T2 data2, T3 data3, T4 data4) {
        StringBuilder builder = takeBuilder();
        try {
            return builder.append(data).append(data2).append(data3).append(data4).toString();
        } finally {
            builder.setLength(0);
        }
    }

    public static <T, T2, T3> String buildString(T data, T2 data2, T3 data3) {
        StringBuilder builder = takeBuilder();
        try {
            return builder.append(data).append(data2).append(data3).toString();
        } finally {
            builder.setLength(0);
        }
    }

    public static <T, T2> String buildString(T data, T2 data2) {
        StringBuilder builder = takeBuilder();
        try {
            return builder.append(data).append(data2).toString();
        } finally {
            builder.setLength(0);
        }
    }

    public static <T> String buildString(T data, BiConsumer<T, StringBuilder> builderFunction) {
        StringBuilder builder = takeBuilder();
        try {
            builderFunction.accept(data, builder);
            return builder.toString();
        } finally {
            builder.setLength(0);
        }
    }

}
