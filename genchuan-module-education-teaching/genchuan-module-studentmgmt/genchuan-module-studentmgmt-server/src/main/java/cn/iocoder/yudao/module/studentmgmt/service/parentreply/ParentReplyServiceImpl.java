package cn.iocoder.yudao.module.studentmgmt.service.parentreply;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartCountVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.communicatemgmt.CommunicateMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.communicatemgmt.CommunicateMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.ParentReplyReadStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.ReadStatusEnum;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.parentreply.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.parentreply.ParentReplyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.parentreply.ParentReplyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 家长回复 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ParentReplyServiceImpl implements ParentReplyService {

    @Resource
    private ParentReplyMapper parentReplyMapper;
    @Resource
    private CommunicateMgmtMapper communicateMgmtMapper;
    @Resource
    private StudentInfoMapper studentInfoMapper;

    @Override
    public Long createParentReply(ParentReplySaveReqVO createReqVO) {
        // 插入
        ParentReplyDO parentReply = BeanUtils.toBean(createReqVO, ParentReplyDO.class);
        parentReplyMapper.insert(parentReply);

        // 返回
        return parentReply.getId();
    }

    @Override
    public void updateParentReply(ParentReplySaveReqVO updateReqVO) {
        // 校验存在
        validateParentReplyExists(updateReqVO.getId());
        // 更新
        ParentReplyDO updateObj = BeanUtils.toBean(updateReqVO, ParentReplyDO.class);
        parentReplyMapper.updateById(updateObj);
    }

    @Override
    public void deleteParentReply(Long id) {
        // 校验存在
        validateParentReplyExists(id);
        // 删除
        parentReplyMapper.deleteById(id);
    }

    @Override
    public void deleteParentReplyListByIds(List<Long> ids) {
        // 删除
        parentReplyMapper.deleteByIds(ids);
    }


    private ParentReplyDO validateParentReplyExists(Long id) {
        ParentReplyDO parentReplyDO = parentReplyMapper.selectById(id);
        if (parentReplyDO == null) {
            throw exception(PARENT_REPLY_NOT_EXISTS);
        }
        return parentReplyDO;
    }

    @Override
    public ParentReplyDO getParentReply(Long id) {
        return parentReplyMapper.selectById(id);
    }

    @Override
    public PageResult<ParentReplyDO> getParentReplyPage(ParentReplyPageReqVO pageReqVO) {
        return parentReplyMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean read(ParentReplyReadReqVO reqVO) {
        Long[] ids = reqVO.getIds();
        int total = 0;
        for (Long id : ids) {
            // 校验存在
            ParentReplyDO parentReplyDO = validateParentReplyExists(id);
            parentReplyDO.setReadStatus(ReadStatusEnum.READ.getStatus());
            // 更新
            int i = parentReplyMapper.updateById(parentReplyDO);
            total += i;
        }
        return total > 0;

    }

    @Override
    public Boolean submit(ParentReplySubmitReqVO reqVO) {
        Long communicateId = reqVO.getCommunicateId();

        CommunicateMgmtDO communicateMgmtDO = communicateMgmtMapper.selectFirstOne(CommunicateMgmtDO::getId, communicateId);
        if (communicateMgmtDO == null) {
            throw exception(COMMUNICATE_MGMT_NOT_EXISTS);
        }

        // 获取消息发布的时间
//        LocalDateTime publishTime = communicateMgmtDO.getSendTime();
        // 当前时间与发布时间的间隔
//        long minutes = publishTime.until(LocalDateTime.now(), ChronoUnit.MINUTES);

        StudentInfoDO studentInfoDO = studentInfoMapper.selectById(reqVO.getStudentId());

        ParentReplyDO parentReply = new ParentReplyDO();
        parentReply.setParentReplyTime(LocalDateTime.now());
        parentReply.setCommunicateId(communicateId);
        parentReply.setStudentId(reqVO.getStudentId());
        parentReply.setParentReplyContent(reqVO.getParentReplyContent());
        parentReply.setStudentName(studentInfoDO.getName());
        int i = parentReplyMapper.insert(parentReply);

        return i > 0;
    }

    @Override
    public Boolean reply(ParentReplyReplyReqVO reqVO) {
        Long replyId = reqVO.getId();
        ParentReplyDO parentReplyDO = validateParentReplyExists(replyId);
        parentReplyDO.setTeacherReplyContent(reqVO.getTeacherReplyContent());
        parentReplyDO.setTeacherReplyTime(LocalDateTime.now());
        parentReplyDO.setReplyStatus(ParentReplyReadStatusEnum.REPLIED.getStatus());
        int i = parentReplyMapper.updateById(parentReplyDO);
        return i > 0;
    }

    @Override
    public ParentReplyChartRespVO chart(BaseChartReqVO reqVO) {
        ParentReplyChartRespVO vo = new ParentReplyChartRespVO();

        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        ;

        // 1. 卡片数据
        vo = parentReplyMapper.selectTotalCount(startTime, endTime,
                ReadStatusEnum.READ.getStatus(),
                ParentReplyReadStatusEnum.REPLIED.getStatus());
        if (vo == null) {
            vo = new ParentReplyChartRespVO();
        }
        // 如果统计为空，则设置为0
        if (vo == null) {
            vo = new ParentReplyChartRespVO();
        }
        if (vo.getTotalReplyCount() == null) {
            vo.setTotalReplyCount(0);
        }
        if (vo.getReplyFinishRate() == null) {
            vo.setReplyFinishRate(BigDecimal.ZERO);
        }
        if (vo.getUnreadReplyCount() == null) {
            vo.setUnreadReplyCount(0);
        }

        // 每日列表
        List<ChartTrendVO> sevenDayTrendCountJsonList = parentReplyMapper.select7dayTrendCount();
        // 最近七天的日期
        List<ChartTrendVO> trendList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ChartTrendVO trendVo = new ChartTrendVO();
            String dataStr = LocalDate.now().minusDays(i).toString();
            trendVo.setDate(dataStr);
            // 取出 sevenDayTrendCountJsonList 与 dataStr 匹配的数据
            Integer count = 0;
            for (ChartTrendVO bean : sevenDayTrendCountJsonList) {
                String date = bean.getDate();
                if (date.equals(dataStr)) {
                    count = bean.getCount();
                    break;
                }
            }
            trendVo.setCount(count);
            trendList.add(trendVo);
        }
        vo.setRecentWeekReplyTrend(trendList);
        return vo;
    }

    @Override
    public ParentReplyChartIndexRespVO index(BaseChartReqVO reqVO) {
        ParentReplyChartIndexRespVO vo = new ParentReplyChartIndexRespVO();
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        ;
        // 获取每一个班级的学生的总人数
        List<ChartCountVO> classStudentCountList = studentInfoMapper.selectClassStudentCount(null, null);
        List<JSONObject> classReplyRateList = new ArrayList<>();
        for (ChartCountVO bean : classStudentCountList) {
            JSONObject json = new JSONObject();

            // 班级名称
            String className = bean.getName();
            // 每个班级的学生人数
            Integer classCount = bean.getValue();
            if (classCount == null) {
                classCount = 0;
            }
            json.put("className", className);

            // 查询该班级的回复率
            List<ChartCountVO> classReplyCountList = parentReplyMapper.selectClassReplyCount(startTime, endTime);
            if (classReplyCountList != null && classReplyCountList.size() > 0) {
                for (ChartCountVO classReplyCountBean : classReplyCountList) {
                    if (className.equals(classReplyCountBean.getName())) {
                        // 班级回复数
                        Integer classReplyCount = classReplyCountBean.getValue();
                        if (classReplyCount == null) {
                            classReplyCount = 0;
                        }
                        BigDecimal classReplyRate = new BigDecimal(classReplyCount).divide(new BigDecimal(classCount), 2, BigDecimal.ROUND_HALF_UP);
                        json.put("classReplyRate", classReplyRate);
                    } else {
                        json.put("classReplyRate", BigDecimal.ZERO);
                    }
                }
            } else {
                json.put("classReplyRate", BigDecimal.ZERO);
            }

            classReplyRateList.add(json);
        }

        vo.setClassReplyRate(classReplyRateList);
        vo.setStudentReplyCount(parentReplyMapper.selectStudentReplyCount(startTime, endTime));
        vo.setReplyTimeDistribute(parentReplyMapper.selectReplyTimeDistribute(startTime, endTime));
        return vo;
    }

}