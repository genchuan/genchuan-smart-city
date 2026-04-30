package cn.iocoder.yudao.module.studentmgmt.service.classassign;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartCountVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.classassign.ClassAssignDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.classassign.ClassAssignMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.ClassAssignStatusEnum;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.CLASS_ASSIGN_NOT_EXISTS;

/**
 * 分班管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ClassAssignServiceImpl implements ClassAssignService {

    @Resource
    private ClassAssignMapper classAssignMapper;

    @Resource
    private StudentInfoMapper studentInfoMapper;

    @Override
    public Long createClassAssign(ClassAssignSaveReqVO createReqVO) {
        // 插入
        ClassAssignDO classAssign = BeanUtils.toBean(createReqVO, ClassAssignDO.class);
        classAssignMapper.insert(classAssign);

        // 返回
        return classAssign.getId();
    }

    @Override
    public void updateClassAssign(ClassAssignSaveReqVO updateReqVO) {
        // 校验存在
        validateClassAssignExists(updateReqVO.getId());
        // 更新
        ClassAssignDO updateObj = BeanUtils.toBean(updateReqVO, ClassAssignDO.class);
        classAssignMapper.updateById(updateObj);
    }

    @Override
    public void deleteClassAssign(Long id) {
        // 校验存在
        validateClassAssignExists(id);
        // 删除
        classAssignMapper.deleteById(id);
    }

    @Override
    public void deleteClassAssignListByIds(List<Long> ids) {
        // 删除
        classAssignMapper.deleteByIds(ids);
    }


    private ClassAssignDO validateClassAssignExists(Long id) {
        ClassAssignDO classAssignDO = classAssignMapper.selectById(id);
        if (classAssignDO == null) {
            throw exception(CLASS_ASSIGN_NOT_EXISTS);
        }
        return classAssignDO;
    }

    @Override
    public ClassAssignDO getClassAssign(Long id) {
        return classAssignMapper.selectById(id);
    }

    @Override
    public PageResult<ClassAssignDO> getClassAssignPage(ClassAssignPageReqVO pageReqVO) {
        return classAssignMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean config(ClassAssignConfigReqVO reqVO) {
        // 插入
        ClassAssignDO classAssign = BeanUtils.toBean(reqVO, ClassAssignDO.class);
        String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
        classAssign.setCreator(loginUserNickname);
        classAssign.setStatus(ClassAssignStatusEnum.UNASSIGNED.getStatus());
        int i = classAssignMapper.insert(classAssign);

        // 返回
        return i > 0;
    }

    @Override
    public Boolean assign(ClassAssignAssignReqVO reqVO) {
        int total = 0;
        Long[] ids = reqVO.getIds();
        for (Long id : ids) {
            // 校验存在
            ClassAssignDO classAssign = validateClassAssignExists(id);
            classAssign.setStatus(ClassAssignStatusEnum.ASSIGNED.getStatus());
            classAssign.setAssignTime(reqVO.getAssignTime());
            int i = classAssignMapper.updateById(classAssign);
            total += i;
        }
        // 返回
        return total > 0;
    }

    @Override
    public Boolean confirm(ClassAssignConfigReqVO reqVO) {
        int total = 0;
        Long[] ids = reqVO.getIds();
        for (Long id : ids) {
            // 校验存在
            ClassAssignDO classAssign = validateClassAssignExists(id);
            classAssign.setConfirmTime(reqVO.getConfirmTime());
            classAssign.setConfirmUser(reqVO.getConfirmUser());
            int i = classAssignMapper.updateById(classAssign);
            total += i;
        }
        // 返回
        return total > 0;
    }

    @Override
    public ClassAssignChartRespVO chart(BaseChartReqVO reqVO) {
        ClassAssignChartRespVO vo = new ClassAssignChartRespVO();

        // 1. 卡片数据
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        // 1. 卡片数据
        vo = classAssignMapper.selectTotalCount(startTime, endTime,
                ClassAssignStatusEnum.ASSIGNED.getStatus(),
                ClassAssignStatusEnum.UNASSIGNED.getStatus()
        );
        // 如果统计为空，则设置为0
        if (vo.getTotalAssignTaskCount() == null) {
            vo.setTotalAssignTaskCount(0);
        }
        if (vo.getAssignedCount() == null) {
            vo.setAssignedCount(0);
        }
        if (vo.getUnassignedCount() == null) {
            vo.setUnassignedCount(0);
        }
        if (vo.getTotalAssignedStudentCount() == null) {
            vo.setTotalAssignedStudentCount(0);
        }

        //近一周报名趋势数据，包含日期及对应报名数

        List<ChartTrendVO> sevenDayTrendCountJsonList = classAssignMapper.select7dayTrendCount();
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
        vo.setRecentWeekAssignTrend(trendList);
        return vo;
    }

    @Override
    public ClassAssignDistributionRespVo classDistribution(BaseChartReqVO reqVO) {
        ClassAssignDistributionRespVo vo = new ClassAssignDistributionRespVo();

        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }


        // 1. 卡片数据
        List<JSONObject> classStudentCount = new ArrayList<>();
        List<ChartCountVO> classStudentCountList = studentInfoMapper.selectClassStudentCount(startTime, endTime);
        for (ChartCountVO bean : classStudentCountList) {
            JSONObject json = new JSONObject();
            json.put("className", bean.getName());
            json.put("value", bean.getValue());
            classStudentCount.add(json);
        }
        vo.setClassStudentCount(classStudentCount);

        // 查询学生总人数
        Integer totalStudentCount = studentInfoMapper.selectTotalStudentCountByTime(startTime, endTime);

        // 查询各专业学生人数占比
        List<JSONObject> majorAssignRate = studentInfoMapper.selectMajorAssignRate(startTime, endTime, totalStudentCount);

        vo.setMajorAssignRate(majorAssignRate);
        return vo;
    }

}