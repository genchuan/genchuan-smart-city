package cn.iocoder.yudao.module.studentmgmt.service.promotemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.promotemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.promotemgmt.PromoteMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.promotemgmt.PromoteMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.PromoteStatusEnum;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.PROMOTE_MGMT_NOT_EXISTS;

/**
 * 宣传管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PromoteMgmtServiceImpl implements PromoteMgmtService {

    @Resource
    private PromoteMgmtMapper promoteMgmtMapper;

    @Override
    public Long createPromoteMgmt(PromoteMgmtSaveReqVO createReqVO) {
        // 插入
        PromoteMgmtDO promoteMgmt = BeanUtils.toBean(createReqVO, PromoteMgmtDO.class);
        promoteMgmtMapper.insert(promoteMgmt);

        // 返回
        return promoteMgmt.getId();
    }

    @Override
    public void updatePromoteMgmt(PromoteMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validatePromoteMgmtExists(updateReqVO.getId());
        // 更新
        PromoteMgmtDO updateObj = BeanUtils.toBean(updateReqVO, PromoteMgmtDO.class);
        promoteMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deletePromoteMgmt(Long id) {
        // 校验存在
        validatePromoteMgmtExists(id);
        // 删除
        promoteMgmtMapper.deleteById(id);
    }

    @Override
        public void deletePromoteMgmtListByIds(List<Long> ids) {
        // 删除
        promoteMgmtMapper.deleteByIds(ids);
        }


    private PromoteMgmtDO validatePromoteMgmtExists(Long id) {
        PromoteMgmtDO promoteMgmtDO = promoteMgmtMapper.selectById(id);
        if (promoteMgmtDO == null) {
            throw exception(PROMOTE_MGMT_NOT_EXISTS);
        }
        return promoteMgmtDO;
    }

    @Override
    public PromoteMgmtDO getPromoteMgmt(Long id) {
        return promoteMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<PromoteMgmtDO> getPromoteMgmtPage(PromoteMgmtPageReqVO pageReqVO) {
        return promoteMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean execute(PromoteMgmtExecuteReqVO reqVO) {
        int total = 0;
        Long[] ids = reqVO.getIds();
        for (Long id : ids) {
            // 校验存在
            PromoteMgmtDO promoteMgmt = validatePromoteMgmtExists(id);
            //标记宣传任务为已执行，录入宣传人数、意向学生数等执行结果，记录执行人及执行时间
            promoteMgmt.setStatus(PromoteStatusEnum.EXECUTED.getStatus());
            promoteMgmt.setPromoteNum(reqVO.getPromoteNum());
            promoteMgmt.setIntentNum(reqVO.getIntentNum());
            String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
            promoteMgmt.setExecuteUser(loginUserNickname);
            promoteMgmt.setExecuteTime(LocalDateTime.now());

            // 更新
            int i = promoteMgmtMapper.updateById(promoteMgmt);

            total += i;

        }
        return total > 0;
    }

    @Override
    public PromoteMgmtChartRespVO chart(PromoteMgmtChartReqVO reqVO) {
        PromoteMgmtChartRespVO vo = new PromoteMgmtChartRespVO();

        Integer year = reqVO.getYear();
        if (year == null) {
            year = LocalDateTime.now().getYear();
        }
        // 1. 卡片数据
        vo = promoteMgmtMapper.selectTotalCount(year);
        // 如果统计为空，则设置为0
        if (vo.getWaitExecuteCount() == null) {
            vo.setWaitExecuteCount(0);
        }
        if (vo.getTotalCount() == null) {
            vo.setTotalCount(0);
        }
        if (vo.getFinishedCount() == null) {
            vo.setFinishedCount(0);
        }
        if (vo.getTotalPromoteNum() == null) {
            vo.setTotalPromoteNum(0);
        }
        if (vo.getTotalIntentNum() == null) {
            vo.setTotalIntentNum(0);
        }

        // 每日列表
        List<JSONObject> dateCountList = promoteMgmtMapper.selectDateCountList(year);
        List<String> dateList = new ArrayList<>();
        List<Integer> dailyPromoteList = new ArrayList<>();
        List<Integer> dailyIntentList = new ArrayList<>();
        for (JSONObject jsonObject : dateCountList) {
            dateList.add(jsonObject.getString("date"));
            dailyPromoteList.add(jsonObject.getInteger("promote_num"));
            dailyIntentList.add(jsonObject.getInteger("intent_num"));

        }
        vo.setDateList(dateList);
        vo.setDailyPromoteList(dailyPromoteList);
        vo.setDailyIntentList(dailyIntentList);
        return vo;
    }

    @Override
    public PromoteCountReqVO promoteCount(PromoteMgmtChartReqVO reqVO) {
        PromoteCountReqVO vo = new PromoteCountReqVO();

        Integer year = reqVO.getYear();
        if (year == null) {
            year = LocalDateTime.now().getYear();
        }
        List<String> dateList = new ArrayList<>();
        List<Integer> dailyPromoteList = new ArrayList<>();
        List<Integer> dailyIntentList = new ArrayList<>();
        List<JSONObject> list = promoteMgmtMapper.selectPromoteCount(year);
        for (JSONObject jsonObject : list) {

            dateList.add(jsonObject.getString("site"));
            dailyPromoteList.add(jsonObject.getInteger("promoteNum"));
            dailyIntentList.add(jsonObject.getInteger("intentNum"));
        }
        vo.setSiteList(dateList);
        vo.setPromoteNumList(dailyPromoteList);
        vo.setIntentNumList(dailyIntentList);
        return vo;
    }

}