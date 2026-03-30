package cn.iocoder.yudao.module.evaluate.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    ErrorCode DOCKING_NOT_EXISTS = new ErrorCode(999_999, "系统对接不存在");
    ErrorCode QUESTION_NOT_EXISTS = new ErrorCode(100_100, "题目不存在");
    ErrorCode QUESTIONNAIRE_NOT_EXISTS = new ErrorCode(100_200, "问卷不存在");
    ErrorCode OPTION_NOT_EXISTS = new ErrorCode(100_300, "选项不存在");
    ErrorCode SUBJECT_NOT_EXISTS = new ErrorCode(200_100, "评价主体不存在");
    ErrorCode SUBJECT_MEMBER_NOT_EXISTS = new ErrorCode(200_200, "评价主体成员不存在");
    ErrorCode TASK_NOT_EXISTS = new ErrorCode(200_300, "评价任务不存在");
    ErrorCode REPORT_NOT_EXISTS = new ErrorCode(200_400, "评价报告不存在");
    ErrorCode TASK_TEMPLATE_NOT_EXISTS = new ErrorCode(200_500, "评价任务模板不存在");
    ErrorCode VETO_ITEM_NOT_EXISTS = new ErrorCode(300_100, "否决项不存在");
    ErrorCode PLAN_NOT_EXISTS = new ErrorCode(400_100, "考察计划不存在");
    ErrorCode RECORD_NOT_EXISTS = new ErrorCode(400_200, "考察记录不存在");
    ErrorCode DATA_NOT_EXISTS = new ErrorCode(500_100, "上报数据不存在");
    ErrorCode DATA_ACCESS_RULE_NOT_EXISTS = new ErrorCode(500_200, "实时接入规则不存在");
    ErrorCode TIME_ACCESS_RULE_NOT_EXISTS = new ErrorCode(500_200, "实时接入规则不存在");
    ErrorCode TEMPLATE_NOT_EXISTS = new ErrorCode(600_100, "报告模板不存在");
    ErrorCode ARCHIVE_NOT_EXISTS = new ErrorCode(700_100, "结果存档不存在");
    ErrorCode AUDIT_NOT_EXISTS = new ErrorCode(700_200, "结果审核不存在");
    ErrorCode PUBLICITY_NOT_EXISTS = new ErrorCode(700_300, "结果公示不存在");
    ErrorCode PUSH_NOT_EXISTS = new ErrorCode(700_400, "结果推送不存在");
    ErrorCode STAT_REPORT_NOT_EXISTS = new ErrorCode(800_100, "统计报表不存在");
    ErrorCode REVIEW_NOT_EXISTS = new ErrorCode(900_100, "申诉复核不存在");
    ErrorCode INDEX_CATEGORY_NOT_EXISTS = new ErrorCode(101_100, "指标分类不存在");
    ErrorCode INDEX_ITEM_ALREADY_EXISTS = new ErrorCode(101_101, "同一分类下已存在同名的指标项");

    ErrorCode INDEX_ITEM_NOT_EXISTS = new ErrorCode(101_200, "指标项不存在");
    ErrorCode INDEX_SYSTEM_NOT_EXISTS = new ErrorCode(101_300, "指标体系不存在");
    ErrorCode STANDARD_ITEM_NOT_EXISTS = new ErrorCode(102_400, "标准项不存在");
    ErrorCode STANDARD_CATEGORY_NOT_EXISTS = new ErrorCode(102_300, "标准分类不存在");
    ErrorCode RULE_ITEM_NOT_EXISTS = new ErrorCode(103_100, "规则项不存在");
    ErrorCode RULE_CATEGORY_NOT_EXISTS = new ErrorCode(103_200, "规则分类不存在");
    ErrorCode RULE_CATEGORY_NAME_DUPLICATE = new ErrorCode(103_201, "同一指标体系下已存在同名的规则分类");
    ErrorCode RELATED_OBJECT_NOT_EXISTS = new ErrorCode(104_100, "关联对象不存在");
    ErrorCode OBJECT_NOT_EXISTS = new ErrorCode(105_100, "评价对象不存在");
    ErrorCode AREA_NOT_EXISTS = new ErrorCode(100_333, "区域编码不存在");
    ErrorCode STATUS_NOT_EXISTS = new ErrorCode(100_454, "状态字典不存在");
    ErrorCode OBJECT_TYPE_NOT_EXISTS = new ErrorCode(100_555, "对象类型字典不存在");
    ErrorCode USER_NOT_EXISTS = new ErrorCode(100_666, "系统用户不存在");
    ErrorCode OBJECT_NAME_DUPLICATE= new ErrorCode(100_999,"评价对象不唯一");
    ErrorCode SUBJECT_TYPE_NOT_EXISTS = new ErrorCode(995_456, "主体类型字典不存在");
    ErrorCode CYCLE_TYPE_NOT_EXISTS = new ErrorCode(123_123, "周期类型字典不存在");
    ErrorCode SCOPE_NOT_EXISTS = new ErrorCode(444_444, "范围字典不存在");
    ErrorCode COLLECT_TYPE_NOT_EXISTS = new ErrorCode(486_897, "采集方式字典不存在");
    ErrorCode TASK_STATUS_NOT_EXISTS = new ErrorCode(325_255, "任务状态字典不存在");
    ErrorCode PLATFORM_REPORT_NOT_EXISTS = new ErrorCode(453_452, "平台上报不存在");
    ErrorCode APPEAL_FEEDBACK_NOT_EXISTS = new ErrorCode(423_687, "申诉反馈不存在");
    ErrorCode AUDIT_RECORD_NOT_EXISTS = new ErrorCode(243_453, "评价结果审核不存在");
    ErrorCode PUBLIC_RECORD_NOT_EXISTS = new ErrorCode(163_536, "评价结果公示不存在");
    ErrorCode ARCHIVE_RECORD_NOT_EXISTS = new ErrorCode(462_789, "评价结果存档不存在");
    ErrorCode APPEAL_RECORD_NOT_EXISTS = new ErrorCode(435_786, "申诉复核不存在");
    ErrorCode PUSH_RECORD_NOT_EXISTS = new ErrorCode(634_543, "结果推送记录不存在");
    ErrorCode DOCKING_RECORD_NOT_EXISTS = new ErrorCode(128_975, "系统对接记录不存");
    ErrorCode REPORT_TEMPLATE_NOT_EXISTS = new ErrorCode(142_378, "报告模板不存在");
    ErrorCode RULE_TYPE_NOT_EXISTS = new ErrorCode(436_293, "规则类型字典不存在");
    ErrorCode NAME_ALREADY_EXIST = new ErrorCode(456_588,"名称已存在，不可重复");
    ErrorCode CALC_WAY_NOT_EXISTS = new ErrorCode(854_951, "计算方式字典不存在");
    ErrorCode INDEX_TYPE_NOT_EXISTS = new ErrorCode(325_547, "指标类型字典不存在");

    ErrorCode INDEX_WEIGHT_OVER_LIMIT = new ErrorCode(325_547, "指标项权重总和超过100%上限");
    ErrorCode PATROL_INSPECTION_NOT_EXISTS = new ErrorCode(325_548, "巡查巡检不存在");
    ErrorCode COMMENT_STATISTIC_NOT_EXISTS = new ErrorCode(325_549, "巡查巡检统计不存在");
    ErrorCode COMMENT_RULE_NOT_EXISTS = new ErrorCode(325_550, "评分规则不存在");
    ErrorCode RULE_DETAIL_NOT_EXISTS = new ErrorCode(325_551, "评分规则明细不存在");
    ErrorCode OBJECT_SCORE_NOT_EXISTS = new ErrorCode(325_552, "公司得分不存在");



}
