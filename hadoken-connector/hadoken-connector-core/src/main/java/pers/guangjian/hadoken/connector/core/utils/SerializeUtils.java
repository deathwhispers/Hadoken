package pers.guangjian.hadoken.connector.core.utils;

import com.google.common.collect.Maps;
import com.google.common.primitives.Primitives;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/10/11 15:15
 */
public class SerializeUtils {

    @SneakyThrows
    public static String readNullableUTF(ObjectInput in) {
        if (in.readBoolean()) {
            return null;
        }
        return in.readUTF();
    }

    @SneakyThrows
    public static void writeNullableUTF(String str, ObjectOutput out) {
        if (str == null) {
            out.writeBoolean(true);
            return;
        }
        out.writeBoolean(false);
        out.writeUTF(str);
    }

    @SneakyThrows
    public static void writeObject(Object obj, ObjectOutput out) {
        Type type;
        if (obj == null) {
            type = Type.NULL;
        } else {
            type = Type.of(obj);
        }
        out.writeByte(type.ordinal());
        type.write(obj, out);
    }

    @SneakyThrows
    public static Object readObject(ObjectInput input) {
        Type type = Type.all[input.readByte()];
        return type.read(input);
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static <K, T> Map<K, T> readMap(ObjectInput in,
                                           Function<Integer, Map<K, T>> mapBuilder) {
        //header
        int headerSize = in.readInt();

        Map<K, T> map = mapBuilder.apply(Math.min(8, headerSize));

        for (int i = 0; i < headerSize; i++) {
            Object key = readObject(in);
            Object value = readObject(in);
            map.put((K) key, (T) value);
        }
        return map;
    }

    @SneakyThrows
    public static void readKeyValue(ObjectInput in,
                                    BiConsumer<String, Object> consumer) {
        //header
        int headerSize = in.readInt();
        for (int i = 0; i < headerSize; i++) {
            String key = String.valueOf(readObject(in));
            consumer.accept(key, readObject(in));
        }
    }

    @SneakyThrows
    public static void writeKeyValue(Map<?, ?> map, ObjectOutput out) {
        if (map == null) {
            out.writeInt(0);
        } else {
            out.writeInt(map.size());
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                writeObject(entry.getKey(), out);
                writeObject(entry.getValue(), out);
            }
        }
    }

    @SneakyThrows
    public static <T> void writeKeyValue(Collection<T> collection,
                                         Function<T, Object> keyMapper,
                                         Function<T, Object> valueMapper,
                                         ObjectOutput out) {
        if (collection == null) {
            out.writeInt(0);
        } else {
            out.writeInt(collection.size());
            for (T t : collection) {
                Object key = keyMapper.apply(t);
                Object value = valueMapper.apply(t);
                writeObject(key, out);
                writeObject(value, out);
            }
        }
    }

    @AllArgsConstructor
    private enum Type {
        NULL(Void.class) {
            @Override
            public Object read(ObjectInput in) {
                return null;
            }

            @Override
            void write(Object value, ObjectOutput input) {

            }
        },
        BOOLEAN(Boolean.class) {
            @Override
            @SneakyThrows
            public Object read(ObjectInput in) {
                return in.readBoolean();
            }

            @Override
            @SneakyThrows
            void write(Object value, ObjectOutput input) {
                input.writeBoolean((Boolean) value);
            }
        },
        BYTE(Byte.class) {
            @Override
            @SneakyThrows
            Object read(ObjectInput input) {
                return input.readByte();
            }

            @Override
            @SneakyThrows
            void write(Object value, ObjectOutput input) {
                input.writeByte(((Byte) value));
            }
        },
        CHAR(Character.class) {
            @Override
            @SneakyThrows
            Object read(ObjectInput input) {
                return input.readChar();
            }

            @Override
            @SneakyThrows
            void write(Object value, ObjectOutput input) {
                input.writeChar(((Character) value));
            }
        },
        SHORT(Short.class) {
            @Override
            @SneakyThrows
            Object read(ObjectInput input) {
                return input.readShort();
            }

            @Override
            @SneakyThrows
            void write(Object value, ObjectOutput input) {
                input.writeShort(((Short) value));
            }
        },
        INT(Integer.class) {
            @Override
            @SneakyThrows
            Object read(ObjectInput input) {
                return input.readInt();
            }

            @Override
            @SneakyThrows
            void write(Object value, ObjectOutput input) {
                input.writeInt(((Integer) value));
            }
        },
        LONG(Long.class) {
            @Override
            @SneakyThrows
            Object read(ObjectInput input) {
                return input.readLong();
            }

            @Override
            @SneakyThrows
            void write(Object value, ObjectOutput input) {
                input.writeLong(((Long) value));
            }
        },
        FLOAT(Float.class) {
            @Override
            @SneakyThrows
            Object read(ObjectInput input) {
                return input.readFloat();
            }

            @Override
            @SneakyThrows
            void write(Object value, ObjectOutput input) {
                input.writeFloat(((Float) value));
            }
        },
        DOUBLE(Double.class) {
            @Override
            @SneakyThrows
            Object read(ObjectInput input) {
                return input.readDouble();
            }

            @Override
            @SneakyThrows
            void write(Object value, ObjectOutput input) {
                input.writeDouble(((Double) value));
            }
        },
        BIG_DECIMAL(BigDecimal.class) {
            private final static byte ZERO = 0x00;
            private final static byte ONE = 0x01;
            private final static byte SMALL_SCALE_0 = 0x10;
            private final static byte SMALL_SCALE_N = 0x11;

            private final static byte BIG_DECIMAL = 0x12;

            @Override
            @SneakyThrows
            Object read(ObjectInput input) {
                byte type = input.readByte();
                if (ZERO == type) {
                    return BigDecimal.ZERO;
                }
                if (ONE == type) {
                    return BigDecimal.ONE;
                }
                if (SMALL_SCALE_0 == type) {
                    return BigDecimal.valueOf(input.readLong());
                }
                if (SMALL_SCALE_N == type) {
                    int scale = input.readInt();
                    return BigDecimal.valueOf(input.readLong(), scale);
                }
                int scale = input.readInt();
                int len = input.readInt();
                byte[] bytes = new byte[len];
                input.read(bytes);
                BigInteger b = new BigInteger(bytes);
                return new BigDecimal(b, scale);
            }

            @Override
            @SneakyThrows
            void write(Object value, ObjectOutput input) {
                BigDecimal decimal = ((BigDecimal) value);

                if (BigDecimal.ZERO.equals(decimal)) {
                    input.write(ZERO);
                } else if (BigDecimal.ONE.equals(decimal)) {
                    input.write(ONE);
                } else {
                    int scale = decimal.scale();
                    BigInteger b = decimal.unscaledValue();
                    int bits = b.bitLength();
                    if (bits < 64) {
                        if (scale == 0) {
                            input.write(SMALL_SCALE_0);
                        } else {
                            input.write(SMALL_SCALE_N);
                            input.writeInt(scale);
                        }
                        input.writeLong(b.longValue());
                    } else {
                        byte[] bytes = b.toByteArray();
                        input.write(BIG_DECIMAL);
                        input.writeInt(scale);
                        input.writeInt(bytes.length);
                        input.write(bytes);
                    }
                }

            }
        },
        BIG_INTEGER(BigInteger.class) {
            private final static byte ZERO = 0x00;
            private final static byte ONE = 0x01;
            private final static byte SMALL = 0x10;

            private final static byte BIG_INTEGER = 0x12;

            @Override
            @SneakyThrows
            Object read(ObjectInput input) {
                byte type = input.readByte();
                if (ZERO == type) {
                    return BigInteger.ZERO;
                }
                if (ONE == type) {
                    return BigInteger.ONE;
                }
                if (SMALL == type) {
                    return BigInteger.valueOf(input.readLong());
                }
                int len = input.readInt();
                byte[] bytes = new byte[len];
                input.read(bytes);
                return new BigInteger(bytes);
            }

            @Override
            @SneakyThrows
            void write(Object value, ObjectOutput input) {
                BigInteger b = ((BigInteger) value);

                if (BigInteger.ZERO.equals(b)) {
                    input.write(ZERO);
                } else if (BigInteger.ONE.equals(b)) {
                    input.write(ONE);
                } else {
                    int bits = b.bitLength();
                    if (bits < 64) {
                        input.write(SMALL);
                        input.writeLong(b.longValue());
                    } else {
                        byte[] bytes = b.toByteArray();
                        input.write(BIG_INTEGER);
                        input.writeInt(bytes.length);
                        input.write(bytes);
                    }
                }

            }
        },
        STRING(String.class) {
            @Override
            @SneakyThrows
            Object read(ObjectInput input) {
                return input.readUTF();
            }

            @Override
            @SneakyThrows
            void write(Object value, ObjectOutput input) {
                input.writeUTF(String.valueOf(value));
            }
        },
        OBJECT(Object.class) {
            @Override
            @SneakyThrows
            Object read(ObjectInput input) {
                return input.readObject();
            }

            @Override
            @SneakyThrows
            void write(Object value, ObjectOutput input) {
                input.writeObject(value);
            }
        },
        ARRAY(Object[].class) {
            @Override
            @SneakyThrows
            Object read(ObjectInput input) {
                Type elementType = Type.all[input.readByte()];
                int len = input.readInt();
                Object array = Array.newInstance(elementType.javaType, len);
                for (int i = 0; i < len; i++) {
                    Array.set(array, i, SerializeUtils.readObject(input));
                }
                return array;
            }

            @Override
            @SneakyThrows
            void write(Object value, ObjectOutput input) {
                Class<?> type = value.getClass().getComponentType();
                Type elementType = Type.of(type);
                input.writeByte(elementType.ordinal());
                int len = Array.getLength(value);
                input.writeInt(len);

                for (int i = 0; i < len; i++) {
                    writeObject(Array.get(value, i), input);
                }

            }
        },
        MAP(Map.class) {
            @Override
            Object read(ObjectInput input) {
                return SerializeUtils.readMap(input, Maps::newLinkedHashMapWithExpectedSize);
            }

            @Override
            void write(Object value, ObjectOutput input) {
                writeKeyValue(((Map) value), input);
            }
        },
        COLLECTION(List.class) {
            @Override
            @SneakyThrows
            Object read(ObjectInput input) {
                int len = input.readInt();
                List<Object> list = new ArrayList<>(len);
                for (int i = 0; i < len; i++) {
                    list.add(SerializeUtils.readObject(input));
                }
                return list;
            }

            @Override
            @SneakyThrows
            void write(Object value, ObjectOutput input) {
                List<?> list = ((List<?>) value);

                int len = list.size();
                input.writeInt(len);

                for (Object o : list) {
                    writeObject(o, input);
                }
            }
        };

        private final Class<?> javaType;

        abstract Object read(ObjectInput input);

        abstract void write(Object value, ObjectOutput input);

        final static Type[] all = values();

        private static final Map<Class<?>, Type> cache = new ConcurrentReferenceHashMap<>();

        public static Type of(Object javaType) {
            if (javaType instanceof String) {
                return STRING;
            }
            if (javaType instanceof Boolean) {
                return BOOLEAN;
            }
            if (javaType instanceof Integer) {
                return INT;
            }
            if (javaType instanceof Long) {
                return LONG;
            }
            if (javaType instanceof Double) {
                return DOUBLE;
            }
            if (javaType instanceof Float) {
                return FLOAT;
            }
            if (javaType instanceof Byte) {
                return BYTE;
            }
            if (javaType instanceof Short) {
                return SHORT;
            }
            if (javaType instanceof Character) {
                return CHAR;
            }
            if (javaType instanceof BigDecimal) {
                return BIG_DECIMAL;
            }
            if (javaType instanceof BigInteger) {
                return BIG_INTEGER;
            }
            if (javaType instanceof Map) {
                return MAP;
            }
            if (javaType instanceof List) {
                return COLLECTION;
            }
            return of(javaType.getClass());
        }

        public static Type of(Class<?> javaType) {
            return cache.computeIfAbsent(javaType, t -> {
                if (t.isPrimitive()) {
                    t = Primitives.wrap(t);
                }
                for (Type type : all) {
                    if (type.javaType == t || type.javaType.isAssignableFrom(t)) {
                        return type;
                    }
                }
                if (t.isArray() && !t.getComponentType().isPrimitive()) {
                    return ARRAY;
                }
                return OBJECT;
            });
        }

    }

}
