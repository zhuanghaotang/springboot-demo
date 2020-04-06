package com.novellatonyatt.constants;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 23:55
 * @Description:
 */
public class EnumTypeHandler<E extends Enum & BaseEnum> extends BaseTypeHandler<BaseEnum> {

    private Class<E> type;

    public EnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    /**
     * 在进行参数绑定时JAVA类型与SQL类型的转换
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    /**
     * ResultSet中SQL类型与JAVA类型的转换(通过字段名获取的字段)
     */
    @Override
    public BaseEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        return valueOf(value);
    }

    /**
     * ResultSet中SQL类型与JAVA类型的转换(通过字段索引获取的字段)
     */
    @Override
    public BaseEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        return valueOf(value);
    }

    /**
     * ResultSet中SQL类型与JAVA类型的转换(通过调用存储过程返回的字段)
     */
    @Override
    public BaseEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int value = cs.getInt(columnIndex);
        return valueOf(value);
    }

    private BaseEnum valueOf(int value) {
        for (E e : this.type.getEnumConstants()) {
            if (e.getValue() == value) {
                return e;
            }
        }
        return null;
    }

}
