package com.aimportal.DataAccess.Parameter;

import com.aimportal.DataAccess.Constant.JDBCType;

public class dbParameter<T> {
    private JDBCType _JDBCType;
    private String _ParameterName;
    private T _ParameterValue;

    public dbParameter(String Name, T Value, JDBCType Type){
        _ParameterName = Name;
        _ParameterValue = Value;
        _JDBCType = Type;
    }


    public JDBCType getJDBCType() {
        return _JDBCType;
    }
    public String getParameterName(){
        return _ParameterName;
    }
    public T getParameterVale(){
        return _ParameterValue;
    }
}
