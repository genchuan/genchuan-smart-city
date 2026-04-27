package cn.iocoder.yudao.module.studentmgmt.service.comparemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.comparemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.comparemgmt.CompareMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.comparemgmt.CompareMgmtMapper;
import com.alibaba.fastjson.JSONObject;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.COMPARE_MGMT_NOT_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 评比管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CompareMgmtServiceImpl implements CompareMgmtService {

    @Resource
    private CompareMgmtMapper compareMgmtMapper;

    @Override
    @LogRecord(type = COMPARE_TYPE, subType = COMPARE_CREATE_SUB_TYPE, bizNo = "{{#compareMgmt.id}}",
            success = COMPARE_CREATE_SUCCESS)
    public Long createCompareMgmt(CompareMgmtSaveReqVO createReqVO) {
        // 插入
        CompareMgmtDO compareMgmt = BeanUtils.toBean(createReqVO, CompareMgmtDO.class);
        compareMgmtMapper.insert(compareMgmt);

        // 记录操作日志上下文
        LogRecordContext.putVariable("compareMgmt", compareMgmt);

        // 返回
        return compareMgmt.getId();
    }

    @Override
    @LogRecord(type = COMPARE_TYPE, subType = COMPARE_UPDATE_SUB_TYPE, bizNo = "{{#updateReqVO.id}}",
            success = COMPARE_UPDATE_SUCCESS)
    public boolean updateCompareMgmt(@Valid CompareMgmtUpdateReqVO updateReqVO) {
        // 校验存在
        validateCompareMgmtExists(updateReqVO.getId());
        // 更新
        CompareMgmtDO updateObj = BeanUtils.toBean(updateReqVO, CompareMgmtDO.class);
        int i = compareMgmtMapper.updateById(updateObj);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteCompareMgmt(Long id) {
        // 校验存在
        validateCompareMgmtExists(id);
        // 删除
        compareMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteCompareMgmtListByIds(List<Long> ids) {
        // 删除
        compareMgmtMapper.deleteByIds(ids);
        }


    private CompareMgmtDO validateCompareMgmtExists(Long id) {
        CompareMgmtDO compareMgmt = compareMgmtMapper.selectById(id);
        if ( compareMgmt== null) {
            throw exception(COMPARE_MGMT_NOT_EXISTS);
        }
        return compareMgmt;
    }

    @Override
    public CompareMgmtDO getCompareMgmt(Long id) {
        return compareMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<CompareMgmtDO> getCompareMgmtPage(CompareMgmtPageReqVO pageReqVO) {
        return compareMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = COMPARE_TYPE, subType = COMPARE_SCORE_SUB_TYPE, bizNo = "{{#compare.id}}",
            success = COMPARE_SCORE_SUCCESS)
    public boolean score(CompareMgmtScoreReqVO reqVO) {
        Long[] ids = reqVO.getIds();
        int total = 0;
        for (Long id : ids) {
            // 校验存在
            CompareMgmtDO compareMgmtDO = validateCompareMgmtExists(id);
            // 更新
            CompareMgmtDO updateObj = BeanUtils.toBean(reqVO, CompareMgmtDO.class);
            // 打分完成后自动将状态修改为 “已汇总”
            updateObj.setStatus("已汇总");
            int i = compareMgmtMapper.updateById(updateObj);
            total += i;
            // 记录操作日志上下文
            LogRecordContext.putVariable("compare", compareMgmtDO);
        }
        if (total > 0) {
            return true;
        }
        return false;
    }

    @Override
    @LogRecord(type = COMPARE_TYPE, subType = COMPARE_AWARD_SUB_TYPE, bizNo = "{{#id}}",
            success = COMPARE_AWARD_SUCCESS)
    public boolean award(CompareMgmtAwardReqVO reqVO) {
        int total = 0;
        for (Long id : reqVO.getIds()) {
            // 校验存在
            CompareMgmtDO compareMgmtDO = validateCompareMgmtExists(id);
            compareMgmtDO.setAwardName(reqVO.getAwardName());
            // 更新
//            CompareMgmtDO updateObj = BeanUtils.toBean(reqVO, CompareMgmtDO.class);
            // 打分完成后自动将状态修改为 “已汇总”
            compareMgmtDO.setStatus("已汇总");
            int i = compareMgmtMapper.updateById(compareMgmtDO);
            total += i;

        }
        if (total == reqVO.getIds().length) {
            // 记录操作日志上下文
            LogRecordContext.putVariable("id", reqVO.getIds()[0]);
            return true;
        }
        return false;
    }

    @Override
    public CompareMgmtChartRespVO chart(CompareMgmtChartReqVO reqVO) {
        CompareMgmtChartRespVO vo = new CompareMgmtChartRespVO();
        String cycle = reqVO.getCycle();

        // 1. 卡片数据◆响应参数：
        //rankList (array): 班级排名列表，包含 className、totalScore、rank。
        //statusCount (object): 状态分布统计，scoringCount (打分中)、finishedCount (已汇总)。
        //cycleCount (object): 周期分布统计，weekCount (周)、monthCount (月)、termCount (学期)。
        List<JSONObject> rankList = compareMgmtMapper.selectRankList(cycle);
        vo.setRankList(rankList);
        List<JSONObject> statusJsonList = compareMgmtMapper.selectStatusCount(cycle);
        JSONObject statusCountJson = new JSONObject();
        for (JSONObject jsonObject : statusJsonList) {
            statusCountJson.putAll(jsonObject);
        }
        vo.setStatusCount(statusCountJson);

        List<JSONObject> cycleJsonList = compareMgmtMapper.selectCycleCount(cycle);
        JSONObject cycleCountJson = new JSONObject();
        for (JSONObject jsonObject : cycleJsonList) {
            cycleCountJson.putAll(jsonObject);
        }
        vo.setCycleCount(cycleCountJson);
        return vo;
    }

    @Override
    public CompareMgmtScoreRankRespVO scoreRank(CompareMgmtChartReqVO reqVO) {
        List<JSONObject> scoreRankList = compareMgmtMapper.selectScoreRank(reqVO.getCycle());
        List classList = new ArrayList();// 班级名称列表。
        List scoreList = new ArrayList();// 对应班级的得分列表。
        List rankList = new ArrayList();// 对应班级的排名列表。
        for (JSONObject jsonObject : scoreRankList) {
            classList.add(jsonObject.getString("class_name"));
            scoreList.add(jsonObject.getBigDecimal("total_score"));
            rankList.add(jsonObject.getInteger("rank_no"));
        }
        CompareMgmtScoreRankRespVO  vo = new CompareMgmtScoreRankRespVO();
        vo.setClassList(classList);
        vo.setScoreList(scoreList);
        vo.setRankList(rankList);
        return vo;
    }

}