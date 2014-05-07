package org.msgpack.core;

import org.msgpack.core.MessagePack.Code;

/**
 * Created on 2014/05/04.
 */
public enum MessageFormat {

    FIXINT(ValueType.INTEGER),
    FIXMAP(ValueType.MAP),
    FIXARRAY(ValueType.ARRAY),
    FIXSTR(ValueType.STRING),

    NIL(ValueType.NIL),
    UNKNOWN(ValueType.UNKNOWN),
    FALSE(ValueType.BOOLEAN),
    TRUE(ValueType.BOOLEAN),

    BIN8(ValueType.BINARY),
    BIN16(ValueType.BINARY),
    BIN32(ValueType.BINARY),

    EXT8(ValueType.EXTENDED),
    EXT16(ValueType.EXTENDED),
    EXT32(ValueType.EXTENDED),

    FLOAT32(ValueType.FLOAT),
    FLOAT64(ValueType.FLOAT),

    UINT8(ValueType.INTEGER),
    UINT16(ValueType.INTEGER),
    UINT32(ValueType.INTEGER),
    UINT64(ValueType.INTEGER),

    INT8(ValueType.INTEGER),
    INT16(ValueType.INTEGER),
    INT32(ValueType.INTEGER),
    INT64(ValueType.INTEGER),


    FIXEXT1(ValueType.EXTENDED),
    FIXEXT2(ValueType.EXTENDED),
    FIXEXT4(ValueType.EXTENDED),
    FIXEXT8(ValueType.EXTENDED),
    FIXEXT16(ValueType.EXTENDED),

    STR8(ValueType.STRING),
    STR16(ValueType.STRING),
    STR32(ValueType.STRING),

    ARRAY16(ValueType.ARRAY),
    ARRAY32(ValueType.ARRAY),

    MAP16(ValueType.MAP),
    MAP32(ValueType.MAP),

    NEGFIXINT(ValueType.INTEGER)
    ;

    private final ValueType valueType;

    private MessageFormat(ValueType valueType) {
        this.valueType = valueType;
    }

    public ValueType getValueType() {
        return valueType;
    }

    private final static MessageFormat[] formatTable = MessageFormat.values();
    private final static byte[] table = new byte[256];

    static {
        for(int b = 0; b <= 0xFF; ++b) {
            table[b] = (byte) toMessageFormat((byte) b).ordinal();
        }
    }

    public static MessageFormat lookUp(final byte b) {
        return formatTable[table[b & 0xFF]];
    }


    static MessageFormat toMessageFormat(final byte b) {
        if ((b & Code.POSFIXINT_MASK) == 0) {
            return FIXINT;
        }
        if ((b & Code.NEGFIXINT_PREFIX) == Code.NEGFIXINT_PREFIX) {
            return NEGFIXINT;
        }
        if ((b & 0xe0) == Code.FIXSTR_PREFIX) {
            return FIXSTR;
        }
        if ((b & 0xf0) == Code.FIXARRAY_PREFIX) {
            return FIXARRAY;
        }
        if ((b & 0xf0) == Code.FIXMAP_PREFIX) {
            return FIXMAP;
        }
        switch (b & 0xff) {
            case Code.NIL:
                return NIL;
            case Code.FALSE:
                return FALSE;
            case Code.TRUE:
                return TRUE;
            case Code.BIN8:
                return BIN8;
            case Code.BIN16:
                return BIN16;
            case Code.BIN32:
                return BIN32;
            case Code.EXT8:
                return EXT8;
            case Code.EXT16:
                return EXT16;
            case Code.EXT32:
                return EXT32;
            case Code.FLOAT32:
                return FLOAT32;
            case Code.FLOAT64:
                return FLOAT64;
            case Code.UINT8:
                return UINT8;
            case Code.UINT16:
                return UINT16;
            case Code.UINT32:
                return UINT32;
            case Code.UINT64:
                return UINT64;
            case Code.INT8:
                return INT8;
            case Code.INT16:
                return INT16;
            case Code.INT32:
                return INT32;
            case Code.INT64:
                return INT64;
            case Code.FIXEXT1:
                return FIXEXT1;
            case Code.FIXEXT2:
                return FIXEXT2;
            case Code.FIXEXT4:
                return FIXEXT4;
            case Code.FIXEXT8:
                return FIXEXT8;
            case Code.FIXEXT16:
                return FIXEXT16;
            case Code.STR8:
                return STR8;
            case Code.STR16:
                return STR16;
            case Code.STR32:
                return STR32;
            case Code.ARRAY16:
                return ARRAY16;
            case Code.ARRAY32:
                return ARRAY32;
            case Code.MAP16:
                return MAP16;
            case Code.MAP32:
                return MAP32;
            default:
                return UNKNOWN;
        }
    }

}
