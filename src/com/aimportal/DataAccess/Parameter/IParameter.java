package com.aimportal.DataAccess.Parameter;

import java.sql.SQLException;

/**
 * Created by IvanHung on 2017/4/19.
 */
public interface IParameter<T> {
    enum types{
        Integer,Float;
    }
    void setParameter(String parameterName,T parameterValue) throws SQLException;
}
