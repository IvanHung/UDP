package com.aimportal.EntityGenerator;

import com.aimportal.DataAccess.Constant.*;

public class ColumnDefinition {
    private String ColumnName;
    private JDBCType Type;
    private int Length;//長度
    private boolean isPrimaryKey;
    private boolean isNotNull;//是否可為Null
    private String Description;//欄位說明
}
