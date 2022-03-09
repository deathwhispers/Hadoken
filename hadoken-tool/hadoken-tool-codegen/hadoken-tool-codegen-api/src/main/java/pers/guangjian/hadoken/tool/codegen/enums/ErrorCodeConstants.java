package pers.guangjian.hadoken.tool.codegen.enums;

import pers.guangjian.hadoken.common.exception.ErrorCode;

/**
 * @author yanggj
 *  错误码枚举
 * @date 2022/03/02 16:01
 * @version 1.0.0
 */
public interface ErrorCodeConstants {

    // ========== 代码生成器 1003001000 ==========
    ErrorCode CODEGEN_TABLE_EXISTS = new ErrorCode(1003001000, "表定义已经存在");
    ErrorCode CODEGEN_IMPORT_TABLE_NULL = new ErrorCode(1003001001, "导入的表不存在");
    ErrorCode CODEGEN_IMPORT_COLUMNS_NULL = new ErrorCode(1003001002, "导入的字段不存在");
    ErrorCode CODEGEN_PARSE_SQL_ERROR = new ErrorCode(1003001003, "解析 SQL 失败，请检查");
    ErrorCode CODEGEN_TABLE_NOT_EXISTS = new ErrorCode(1003001004, "表定义不存在");
    ErrorCode CODEGEN_COLUMN_NOT_EXISTS = new ErrorCode(1003001005, "字段义不存在");
    ErrorCode CODEGEN_SYNC_COLUMNS_NULL = new ErrorCode(1003001006, "同步的字段不存在");
    ErrorCode CODEGEN_SYNC_NONE_CHANGE = new ErrorCode(1003001007, "同步失败，不存在改变");

    ErrorCode CODEGEN_PACKAGE_FAILED = new ErrorCode(1003001010, "打包失败");
    ErrorCode CODEGEN_CONFIG_GEN_FIRST = new ErrorCode(1003001011, "请先配置生成器");
    ErrorCode CODEGEN_GENERATE_FAILED = new ErrorCode(1003001012, "生成失败，请手动处理已生成的文件");

}
