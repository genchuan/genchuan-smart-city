package cn.iocoder.yudao.module.usermerchant.enums;

/**
 * 用户商户 操作日志枚举
 * 将各类的操作日志整理在这里以code的方式引用
 *
 * @author 宇佐见莲子
 */
public interface LogRecordConstants {
    // ======================= 用户管理 =======================
    String TYPE_USER_INFO = "用户信息";
    String SUB_TYPE_CREATE_USER_INFO = "创建用户信息";
    String SUCCESS_CREATE_USER_INFO = "创建用户信息 {{#userInfo.nickname}}";
    String SUB_TYPE_UPDATE_USER_INFO = "更新用户信息";
    String SUCCESS_UPDATE_USER_INFO = "更新用户信息 {{#oldUserInfo.nickname}} -> {{#newUserInfo.nickname}}，{{#_DIFF}}";
    String SUB_TYPE_UPDATE_USER_STATUS = "更新用户状态";
    String SUCCESS_UPDATE_USER_STATUS = "批量更新用户状态（共 {{#ids.size()}} 条），目标状态：{{#status}}";
    String SUB_TYPE_IMPORT_USERS = "导入用户信息";
    String SUCCESS_IMPORT_USERS = "导入用户信息，总数：{{#list.size()}}，更新支持：{{#updateSupport}}";
    // ======================= 用户车辆 =======================
    String TYPE_USER_CAR = "用户车辆";
    String SUB_TYPE_CREATE_USER_CAR = "创建用户车辆";
    String SUCCESS_CREATE_USER_CAR = "创建用户车辆，车牌号：{{#userCar.plateNo}}，所属用户ID：{{#userCar.userId}}";
    String SUB_TYPE_UPDATE_USER_CAR = "更新用户车辆";
    String SUCCESS_UPDATE_USER_CAR = "更新用户车辆，ID：{{#updateReqVO.id}}，车牌号：{{#updateReqVO.plateNo}}";
    String SUB_TYPE_IMPORT_USER_CAR = "导入用户车辆";
    String SUCCESS_IMPORT_USER_CAR = "导入用户车辆，处理记录数：{{#list.size()}}，更新支持：{{#updateSupport}}";
    String SUB_TYPE_AUDIT_USER_CAR_BIND = "审核用户车辆绑定";
    // ======================= 车牌认证 =======================
    String TYPE_PLATE_AUTH = "车牌认证";
    String SUB_TYPE_CREATE_PLATE_AUTH = "创建车牌认证";
    String SUCCESS_CREATE_PLATE_AUTH = "创建车牌认证，车牌号：{{#plateAuth.plateNo}}，用户ID：{{#plateAuth.userId}}";
    String SUB_TYPE_UPDATE_PLATE_AUTH = "更新车牌认证";
    String SUCCESS_UPDATE_PLATE_AUTH = "更新车牌认证，ID：{{#updateReqVO.id}}";
    String SUB_TYPE_DELETE_PLATE_AUTH = "删除车牌认证";
    String SUCCESS_DELETE_PLATE_AUTH = "删除车牌认证，ID：{{#id}}";
    String SUB_TYPE_DELETE_PLATE_AUTH_LIST = "批量删除车牌认证";
    String SUCCESS_DELETE_PLATE_AUTH_LIST = "批量删除车牌认证，ID列表：{{#ids}}";
    String SUB_TYPE_BATCH_AUDIT_PLATE_AUTH = "批量审核车牌认证";
    String SUCCESS_BATCH_AUDIT_PLATE_AUTH = "批量审核车牌认证，ID：{{#updateReqVO.ids}}，审核结果：{{#updateReqVO.auditResult}}，审核备注：{{#updateReqVO.auditRemark}}";
    // ======================= 商户信息 =======================
    String TYPE_MERCHANT_INFO = "商户信息";
    String SUB_TYPE_CREATE_MERCHANT_INFO = "创建商户信息";
    String SUCCESS_CREATE_MERCHANT_INFO = "创建商户信息，商户名称：{{#merchantInfo.name}}";
    String SUB_TYPE_UPDATE_MERCHANT_INFO = "更新商户信息";
    String SUCCESS_UPDATE_MERCHANT_INFO = "更新商户信息，ID：{{#updateReqVO.id}}，商户名称：{{#updateReqVO.name}}";
    String SUB_TYPE_DELETE_MERCHANT_INFO = "删除商户信息";
    String SUCCESS_DELETE_MERCHANT_INFO = "删除商户信息，ID：{{#id}}";
    String SUB_TYPE_DELETE_MERCHANT_INFO_LIST = "批量删除商户信息";
    String SUCCESS_DELETE_MERCHANT_INFO_LIST = "批量删除商户信息，ID列表：{{#ids}}";
    String SUB_TYPE_IMPORT_MERCHANT_INFO = "导入商户信息";
    String SUCCESS_IMPORT_MERCHANT_INFO = "导入商户信息，处理记录数：{{#list.size()}}，更新支持：{{#updateSupport}}";
    String SUB_TYPE_BATCH_AUDIT_MERCHANT = "批量审核商户";
    String SUCCESS_BATCH_AUDIT_MERCHANT = "#index == 1 ? '审核通过商户' : '审核驳回商户'，ID：{{#reqVO.ids}}，审核意见：{{#reqVO.auditResult}}";
    String SUB_TYPE_UPDATE_MERCHANT_STATUS = "更新商户状态";
    String SUCCESS_UPDATE_MERCHANT_STATUS = "批量更新商户状态（共 {{#ids.size()}} 条），目标状态：{{#status}}";
    // ======================= 商户对接 =======================
    String TYPE_MERCHANT_LINK = "商户对接";
    String SUB_TYPE_CREATE_MERCHANT_LINK = "创建商户对接配置";
    String SUCCESS_CREATE_MERCHANT_LINK = "创建商户对接配置，商户ID：{{#merchantLink.merchantId}}，对接类型：{{#merchantLink.linkType}}";
    String SUB_TYPE_UPDATE_MERCHANT_LINK = "更新商户对接配置";
    String SUCCESS_UPDATE_MERCHANT_LINK = "更新商户对接配置，ID：{{#updateReqVO.id}}";
    String SUB_TYPE_DELETE_MERCHANT_LINK = "删除商户对接配置";
    String SUCCESS_DELETE_MERCHANT_LINK = "删除商户对接配置，ID：{{#id}}";
    String SUB_TYPE_DELETE_MERCHANT_LINK_LIST = "批量删除商户对接配置";
    String SUCCESS_DELETE_MERCHANT_LINK_LIST = "批量删除商户对接配置，ID列表：{{#ids}}";
    String SUB_TYPE_LINK_MERCHANT = "商户对接连断";
    // ======================= 商户充值 =======================
    String TYPE_MERCHANT_RECHARGE = "商户充值";
    String SUB_TYPE_CREATE_MERCHANT_RECHARGE = "创建商户充值";
    String SUCCESS_CREATE_MERCHANT_RECHARGE = "创建商户充值，订单号：{{#recharge.orderNo}}，商户ID：{{#recharge.merchantId}}，金额：{{#recharge.amount}}";
    String SUB_TYPE_UPDATE_MERCHANT_RECHARGE = "更新商户充值";
    String SUCCESS_UPDATE_MERCHANT_RECHARGE = "更新商户充值，ID：{{#updateReqVO.id}}";
    String SUB_TYPE_DELETE_MERCHANT_RECHARGE = "删除商户充值";
    String SUCCESS_DELETE_MERCHANT_RECHARGE = "删除商户充值，ID：{{#id}}";
    String SUB_TYPE_DELETE_MERCHANT_RECHARGE_LIST = "批量删除商户充值";
    String SUCCESS_DELETE_MERCHANT_RECHARGE_LIST = "批量删除商户充值，ID列表：{{#ids}}";
    String SUB_TYPE_PAY_MERCHANT_RECHARGE = "支付商户充值";
    String SUCCESS_PAY_MERCHANT_RECHARGE = "支付商户充值，ID：{{#payReqVO.ids}}，支付渠道：{{#payReqVO.payChannel}}";
    String SUB_TYPE_CASH_MERCHANT_RECHARGE = "确认/取消商户充值";
    String SUCCESS_CASH_MERCHANT_RECHARGE = "#code == '确认' ? '确认商户充值，ID：' + #payReqVO.ids : '取消商户充值，ID：' + #payReqVO.ids";
    // ======================= 商户发券 =======================
    String TYPE_MERCHANT_SEND_COUPON = "商户发券";
    String SUB_TYPE_SEND_COUPON = "发券";
    String SUCCESS_SEND_COUPON = "#sendReqVO.execTime == null ? '立即发券' : '定时发券'，商户ID：{{#sendReqVO.merchantId}}，优惠券ID：{{#sendReqVO.couponId}}，发放数量：{{#sendReqVO.sendCount}}";
    String SUB_TYPE_EXECUTE_COUPON = "执行发券";
    String SUCCESS_EXECUTE_COUPON = "执行发券，ID列表：{{#ids}}";
    String SUB_TYPE_CANCEL_COUPON = "取消发券";
    String SUCCESS_CANCEL_COUPON = "取消发券，ID列表：{{#ids}}";
    // ======================= 集团信息 =======================
    String TYPE_GROUP_INFO = "集团信息";
    String SUB_TYPE_CREATE_GROUP_INFO = "创建集团信息";
    String SUCCESS_CREATE_GROUP_INFO = "创建集团信息，集团名称：{{#groupInfo.name}}";
    String SUB_TYPE_UPDATE_GROUP_INFO = "更新集团信息";
    String SUCCESS_UPDATE_GROUP_INFO = "更新集团信息，ID：{{#updateReqVO.id}}，集团名称：{{#updateReqVO.name}}";
    String SUB_TYPE_IMPORT_GROUP_INFO = "导入集团信息";
    String SUCCESS_IMPORT_GROUP_INFO = "导入集团信息，处理记录数：{{#list.size()}}，更新支持：{{#updateSupport}}";
    String SUB_TYPE_AUDIT_GROUP_INFO = "审核集团信息";
    String SUCCESS_AUDIT_GROUP_INFO = "批量审核集团信息，ID：{{#reqVO.ids}}，审核状态：{{#reqVO.status}}，审核意见：{{#reqVO.auditRemark}}";
    String SUB_TYPE_UPDATE_GROUP_STATUS = "更新集团状态";
    String SUCCESS_UPDATE_GROUP_STATUS = "批量更新集团状态（共 {{#ids.size()}} 条），目标状态：{{#status}}";
    // ======================= 集团车辆 =======================
    String TYPE_GROUP_CAR = "集团车辆";
    String SUB_TYPE_CREATE_GROUP_CAR = "创建集团车辆";
    String SUCCESS_CREATE_GROUP_CAR = "创建集团车辆，车牌号：{{#groupCar.plateNo}}，所属集团ID：{{#groupCar.groupId}}";
    String SUB_TYPE_UPDATE_GROUP_CAR = "更新集团车辆";
    String SUCCESS_UPDATE_GROUP_CAR = "更新集团车辆，ID：{{#updateReqVO.id}}，车牌号：{{#updateReqVO.plateNo}}";
    String SUB_TYPE_IMPORT_GROUP_CAR = "导入集团车辆";
    String SUCCESS_IMPORT_GROUP_CAR = "导入集团车辆，处理记录数：{{#list.size()}}，更新支持：{{#updateSupport}}";
    String SUB_TYPE_AUDIT_GROUP_CAR = "审核集团车辆";
    String SUCCESS_AUDIT_GROUP_CAR = "审核集团车辆，ID：{{#reqVO.ids}}，操作类型：{{#reqVO.status}}，备注：{{#reqVO.auditRemark}}";
    // ======================= 会员配置 =======================
    String TYPE_MEMBER_CONFIG = "会员配置";
    String SUB_TYPE_CREATE_MEMBER_CONFIG = "创建会员配置";
    String SUCCESS_CREATE_MEMBER_CONFIG = "创建会员配置，ID：{{#memberConfig.id}}，配置类型：{{#memberConfig.configType}}";
    String SUB_TYPE_UPDATE_MEMBER_CONFIG = "更新会员配置";
    String SUCCESS_UPDATE_MEMBER_CONFIG = "更新会员配置，ID：{{#updateReqVO.id}}，配置类型：{{#updateReqVO.configType}}";
    String SUB_TYPE_UPDATE_CONFIG_STATUS = "更新会员配置状态";
    String SUCCESS_UPDATE_CONFIG_STATUS = "批量更新会员配置状态，ID：{{#ids}}，目标状态：{{#status}}";
    // ======================= 会员用户 =======================
    String TYPE_MEMBER_USER = "会员用户";
    String SUB_TYPE_CREATE_MEMBER_USER = "创建会员用户";
    String SUCCESS_CREATE_MEMBER_USER = "创建会员用户，ID：{{#memberUser.id}}，手机号：{{#memberUser.mobile}}";
    String SUB_TYPE_UPDATE_MEMBER_USER = "更新会员用户";
    String SUCCESS_UPDATE_MEMBER_USER = "更新会员用户，ID：{{#updateReqVO.id}}";
    String SUB_TYPE_IMPORT_MEMBER_USER = "导入会员用户";
    String SUCCESS_IMPORT_MEMBER_USER = "导入会员用户，处理记录数：{{#list.size()}}，更新支持：{{#updateSupport}}";
    String SUB_TYPE_UPDATE_MEMBER_USER_STATUS = "更新会员用户状态";
    String SUCCESS_UPDATE_MEMBER_USER_STATUS = "批量更新会员用户状态，ID：{{#ids}}，目标状态：{{#status}}";
    // ======================= 会员标签 =======================
    String TYPE_MEMBER_TAG = "会员标签";
    String SUB_TYPE_CREATE_MEMBER_TAG = "创建会员标签";
    String SUCCESS_CREATE_MEMBER_TAG = "创建会员标签，ID：{{#memberTag.id}}，标签名称：{{#memberTag.name}}";
    String SUB_TYPE_UPDATE_MEMBER_TAG = "更新会员标签";
    String SUCCESS_UPDATE_MEMBER_TAG = "更新会员标签，ID：{{#updateReqVO.id}}，标签名称：{{#updateReqVO.name}}";
    String SUB_TYPE_IMPORT_MEMBER_TAG = "导入会员标签";
    String SUCCESS_IMPORT_MEMBER_TAG = "导入会员标签，处理记录数：{{#list.size()}}，更新支持：{{#updateSupport}}";
    String SUB_TYPE_UPDATE_TAG_STATUS = "更新会员标签状态";
    String SUCCESS_UPDATE_TAG_STATUS = "批量更新会员标签状态，ID：{{#ids}}，目标状态：{{#status}}";
    // ======================= 会员等级 =======================
    String TYPE_MEMBER_LEVEL = "会员等级";
    String SUB_TYPE_CREATE_MEMBER_LEVEL = "创建会员等级";
    String SUCCESS_CREATE_MEMBER_LEVEL = "创建会员等级，ID：{{#memberLevel.id}}，等级名称：{{#memberLevel.name}}";
    String SUB_TYPE_UPDATE_MEMBER_LEVEL = "更新会员等级";
    String SUCCESS_UPDATE_MEMBER_LEVEL = "更新会员等级，ID：{{#updateReqVO.id}}，等级名称：{{#updateReqVO.name}}";
    String SUB_TYPE_UPDATE_LEVEL_STATUS = "更新会员等级状态";
    String SUCCESS_UPDATE_LEVEL_STATUS = "批量更新会员等级状态，ID：{{#ids}}，目标状态：{{#status}}";
    // ======================= 会员分组 =======================
    String TYPE_MEMBER_GROUP = "会员分组";
    String SUB_TYPE_CREATE_MEMBER_GROUP = "创建会员分组";
    String SUCCESS_CREATE_MEMBER_GROUP = "创建会员分组，ID：{{#memberGroup.id}}，分组名称：{{#memberGroup.name}}";
    String SUB_TYPE_UPDATE_MEMBER_GROUP = "更新会员分组";
    String SUCCESS_UPDATE_MEMBER_GROUP = "更新会员分组，ID：{{#updateReqVO.id}}，分组名称：{{#updateReqVO.name}}";
    String SUB_TYPE_UPDATE_GROUPS_STATUS = "更新会员分组状态";
    String SUCCESS_UPDATE_GROUPS_STATUS = "批量更新会员分组状态，ID：{{#ids}}，目标状态：{{#status}}";
    // ======================= 会员积分 =======================
    String TYPE_MEMBER_POINT = "会员积分";
    String SUB_TYPE_CHECK_MEMBER_POINT = "核查会员积分记录";
    String SUCCESS_CHECK_MEMBER_POINT = "核查会员积分记录，ID：{{#reqVO.id}}，核查结果：{{#reqVO.checkResult}}";
    // ======================= 会员签到 =======================
    // ======================= 信用配置 =======================
    String TYPE_CREDIT_CONFIG = "信用配置";
    String SUB_TYPE_CREATE_CREDIT_CONFIG = "创建信用配置";
    String SUCCESS_CREATE_CREDIT_CONFIG = "创建信用配置，ID：{{#creditConfig.id}}";
    String SUB_TYPE_UPDATE_CREDIT_CONFIG = "更新信用配置";
    String SUCCESS_UPDATE_CREDIT_CONFIG = "更新信用配置，ID：{{#updateReqVO.id}}";
    String SUB_TYPE_UPDATE_CREDIT_CONFIG_STATUS = "更新信用配置状态";
    String SUCCESS_UPDATE_CREDIT_CONFIG_STATUS = "批量更新信用配置状态，ID：{{#ids}}，目标状态：{{#status}}";
    String SUB_TYPE_SAVE_CREDIT_CONFIG = "保存信用配置";
    String SUCCESS_SAVE_CREDIT_CONFIG = "#saveReqVO.id == null ? '创建信用配置' : '更新信用配置'，ID：{{#saveReqVO.id}}";
    // ======================= 用户信用 =======================
    String TYPE_USER_CREDIT = "用户信用";
    String SUB_TYPE_REMIND_USER_CREDIT = "提醒用户信用";
    String SUCCESS_REMIND_USER_CREDIT = "提醒用户信用，ID列表：{{#ids}}";
    // ======================= 周期报表 =======================
    String TYPE_CYCLE_REPORT = "周期报表";
    String SUB_TYPE_CREATE_CYCLE_REPORT = "生成周期报表";
    String SUCCESS_CREATE_CYCLE_REPORT = "生成周期报表，报表ID：{{#report.id}}，报表名称：{{#report.reportName}}，统计时段：{{#report.statTime}}";
}
