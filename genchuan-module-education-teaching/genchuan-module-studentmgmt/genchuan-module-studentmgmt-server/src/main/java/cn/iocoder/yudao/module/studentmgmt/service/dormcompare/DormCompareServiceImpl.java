package cn.iocoder.yudao.module.studentmgmt.service.dormcompare;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo.BedMgmtBedDistributionRespVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcompare.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormcompare.DormCompareDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.dormcompare.DormCompareMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.AssessStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.BedStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.DormCompareStatusEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.DORM_COMPARE_NOT_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 宿舍评比 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DormCompareServiceImpl implements DormCompareService {

    @Resource
    private DormCompareMapper dormCompareMapper;

    @Override
    public Long createDormCompare(DormCompareSaveReqVO createReqVO) {
        // 插入
        DormCompareDO dormCompare = BeanUtils.toBean(createReqVO, DormCompareDO.class);
        dormCompareMapper.insert(dormCompare);

        // 返回
        return dormCompare.getId();
    }

    @Override
    @LogRecord(type = DORM_COMPARE_TYPE, subType = DORM_COMPARE_UPDATE_SUB_TYPE, bizNo = "{{#dormCompare.id}}",
            success = DORM_COMPARE_UPDATE_SUB_TYPE_SUCCESS)
    public void updateDormCompare(DormCompareUpdateReqVO updateReqVO) {
        // 校验存在
        DormCompareDO dormCompare = validateDormCompareExists(updateReqVO.getId());
        // 仅支持修改打分中状态的记录，已汇总记录不允许修改核心得分信息
        if (!DormCompareStatusEnum.DORM_COMPARE_STATUS_SCORING.getStatus().equals(dormCompare.getStatus())) {
            throw exception("仅支持修改打分中状态的记录，已汇总记录不允许修改核心得分信息");
        }
        // 更新
        DormCompareDO updateObj = BeanUtils.toBean(updateReqVO, DormCompareDO.class);
        dormCompareMapper.updateById(updateObj);
        // 记录操作日志上下文
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, BeanUtils.toBean(dormCompare, DormCompareSaveReqVO.class));
        LogRecordContext.putVariable("dormCompare", dormCompare);
    }

    @Override
    public void deleteDormCompare(Long id) {
        // 校验存在
        validateDormCompareExists(id);
        // 删除
        dormCompareMapper.deleteById(id);
    }

    @Override
    public void deleteDormCompareListByIds(List<Long> ids) {
        // 删除
        dormCompareMapper.deleteByIds(ids);
    }


    private DormCompareDO validateDormCompareExists(Long id) {
        DormCompareDO dormCompare = dormCompareMapper.selectById(id);
        if (dormCompare == null) {
            throw exception(DORM_COMPARE_NOT_EXISTS);
        }
        return dormCompare;
    }

    @Override
    public DormCompareDO getDormCompare(Long id) {
        return dormCompareMapper.selectById(id);
    }

    @Override
    public PageResult<DormCompareDO> getDormComparePage(DormComparePageReqVO pageReqVO) {
        return dormCompareMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = DORM_COMPARE_TYPE, subType = DORM_COMPARE_SCORE_SUB_TYPE, bizNo = "{{#dormCompare.id}}",
            success = DORM_COMPARE_SCORE_SUB_TYPE_SUCCESS)
    public boolean score(List<DormCompareScoreReqVO> reqList) {
        int total = 0;
        for (DormCompareScoreReqVO reqVO : reqList) {
            // 校验存在
            DormCompareDO dormCompareDO = validateDormCompareExists(reqVO.getId());
            // 自动校验记录是否为未打分状态，避免重复打分
            if (dormCompareDO.getScore() != null && dormCompareDO.getScore().compareTo(BigDecimal.ZERO) > 1) {
                throw exception(dormCompareDO.getDormNum() + "，已打分，请勿重复打分");
            }

            dormCompareDO.setScore(reqVO.getScore());
            // 打分人： 获取当前用户
            String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
            dormCompareDO.setScoreUser(loginUserNickname);
            // 打分状态：打分中
            dormCompareDO.setStatus(DormCompareStatusEnum.DORM_COMPARE_STATUS_SCORING.getStatus());
            int i = dormCompareMapper.updateById(dormCompareDO);
            total += i;
            // 记录操作日志上下文
            LogRecordContext.putVariable("dormCompare", dormCompareDO);
        }

        if (total == reqList.size()) {
            return true;
        }
        return false;
    }

    @Override
    @LogRecord(type = DORM_COMPARE_TYPE, subType = DORM_COMPARE_SUMMARY_SUB_TYPE, bizNo = "{{#dormCompare.id}}",
            success = DORM_COMPARE_SUMMARY_SUB_TYPE_SUCCESS)
    public boolean summary(DormCompareSummaryReqVO reqVo) {
        Long[] ids = reqVo.getIds();
        int total = 0;
        for (Long id : ids) {
            // 校验存在
            DormCompareDO dormCompareDO = validateDormCompareExists(id);
            // 汇总时间，将记录状态更新为已汇总；
            dormCompareDO.setSumTime(reqVo.getSumTime());
            dormCompareDO.setStatus(DormCompareStatusEnum.DORM_COMPARE_STATUS_SUMMARIZED.getStatus());
            dormCompareMapper.updateById(dormCompareDO);

            String cycle = dormCompareDO.getCycle();
            // 查询该周期下的所有评分是否已经全部完成打分评比
            List<DormCompareDO> dormCompareDOList = dormCompareMapper.selectList(
                    new LambdaQueryWrapper<DormCompareDO>().ge(DormCompareDO::getCycle, cycle)
                            .eq(DormCompareDO::getStatus, DormCompareStatusEnum.DORM_COMPARE_STATUS_SCORING.getStatus())
            );
            if (dormCompareDOList.size() < 1) {
                // 已经全部完成打分评比
                List<DormCompareDO> dormCompareList = dormCompareMapper.selectList(
                        new LambdaQueryWrapper<DormCompareDO>().ge(DormCompareDO::getCycle, cycle)
                                .eq(DormCompareDO::getStatus, DormCompareStatusEnum.DORM_COMPARE_STATUS_UN_SCORED.getStatus())
                );
                // 进行排名
                dormCompareList.sort(Comparator.comparing(DormCompareDO::getScore).reversed());
                // 获取排名，默认从1开始，并保存到数据库中
                for (int i = 0; i < dormCompareList.size(); i++) {
                    dormCompareList.get(i).setRankNo(i + 1);
                    dormCompareMapper.updateById(dormCompareList.get(i));
                }
            }

            // 记录操作日志上下文
            LogRecordContext.putVariable("dormCompare", dormCompareDO);

        }

        if (total > 0) {
            return true;
        }
        return false;
    }

    @Override
    @LogRecord(type = DORM_COMPARE_TYPE, subType = DORM_COMPARE_PUSH_SUB_TYPE, bizNo = "{{#dormCompare.id}}",
            success = DORM_COMPARE_PUSH_SUB_TYPE_SUCCESS)
    public boolean push(DormComparePushReqVO reqVo) {
        Long[] ids = reqVo.getIds();
        int total = 0;
        for (Long id : ids) {
            // 校验存在
            DormCompareDO dormCompareDO = validateDormCompareExists(id);
            // 自动校验记录是否已汇总，未汇总记录不允许推送
            if (!dormCompareDO.getStatus().equals(DormCompareStatusEnum.DORM_COMPARE_STATUS_SUMMARIZED.getStatus())) {
                throw exception(dormCompareDO.getDormNum() + "，未汇总，请勿推送");
            }
            // 更新记录的推送时间
            dormCompareDO.setPushTime(reqVo.getPushTime());
            int i = dormCompareMapper.updateById(dormCompareDO);

            // 记录操作日志上下文
            LogRecordContext.putVariable("dormCompare", dormCompareDO);

        }

        if (total > 0) {
            return true;
        }
        return false;
    }

    @Override
    public DormCompareChartRespVO chart(DormCompareChartReqVO reqVo) {
        DormCompareChartRespVO vo = new DormCompareChartRespVO();
        String cycle = reqVo.getCycle();
        // 1. 卡片数据
        vo = dormCompareMapper.selectTotalCompareCount(cycle);
        List<JSONObject> dormStatsList = dormCompareMapper.selectDormStats(cycle);
        vo.setDormStats(dormStatsList);

        return vo;
    }

    @Override
    public DormCompareRankRespVO scoreRank(DormCompareChartReqVO reqVo) {
        DormCompareRankRespVO vo = new DormCompareRankRespVO();
        String cycle = reqVo.getCycle();
        List<String> labels = new ArrayList<>();
        List<BigDecimal> data = new ArrayList<>();

        // 查询所有楼栋
        List<JSONObject> list = dormCompareMapper.selectTotalCompareCountTop10(cycle);
        for (JSONObject json : list) {
            String dormNum = json.getString("dormNum");
            BigDecimal score = json.getBigDecimal("score");
            labels.add(dormNum);
            data.add(score);
        }
        vo.setLabels(labels);
        vo.setData(data);
        return vo;
    }

}