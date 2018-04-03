package com.sinosoft.stukpisys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.stukpisys.entity.Education;
import com.sinosoft.stukpisys.entity.ScoreValue;
import com.sinosoft.stukpisys.entity.UserInfo;
import com.sinosoft.stukpisys.servsce.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: stukpisys
 * @description: 筛选控制
 * @author: ZRTZRT
 * @create: 2018-03-28 21:16
 **/
@RestController
@RequestMapping(value = "/sift", produces = "application/json;charset=UTF-8")
public class siftController {
    @Autowired
    HRService hrService;

    /***
     * 筛选用户
     * @param HRName 面试官
     * @param job   应聘岗位
     * @param school    学校
     * @param Education 学历
     * @param major 专业
     * @param sex   性别
     * @param isFired   淘汰
     * @param isNew 特殊
     * @param hasErr    严重不符合项
     * @param is211 211
     * @return 成绩和评价
     * 评价字段：
     * 主动性、主动性、灵活度、责任心与沉稳度、展示评价、第一阶段观察与评价、第二阶段观察与评价、第三阶段观察与评价、
     * 责任心、主动性、抗压性、团队意识、学习能力、沟通、严重不符合项、中途退出-淘汰实习生、淘汰阶段、淘汰原因、中途进入-特殊实习生
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    @GetMapping(value ="/judge" )
    public String siftUserJudge(String HRName,String job,String school,String Education,String major,boolean sex,boolean isFired,boolean isNew,boolean hasErr,boolean is211)  {


       List<ScoreValue> list=hrService.getJudgeByParam(HRName,job,school,Education,major, sex,isFired,isNew, hasErr, is211);


     //   System.out.println(list.get(0).getUserId());
       return JSON.toJSONString(list);
       // return "success";
    }

    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    @GetMapping(value ="/score")
    public String siftUserScore(String HRName,String job,String school,String Education,String major,boolean sex,boolean isFired,boolean isNew,boolean hasErr,boolean is211) {

        List<ScoreValue> list = hrService.getUserScoreParam(HRName, job, school, Education, major, sex, isFired, isNew, hasErr, is211);
        return JSON.toJSONString(list);
        // return "success";
    }

    /***
     * 筛选两个或三个阶段排名倒五的
     * @param stage 阶段数
     * @return  评价
     */
    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/judgeInBack")
    public String getJudgeInBack(int stage)
    {

        List<List<Object>> lll=new ArrayList<>();
        List<Long> l2=new ArrayList<>();

        if(stage==2) {
            l2=hrService.getScoreInbackByStage2(2);
            if (l2.size() != 0) {
                for (int i = 0; i < l2.size(); i++) {

                    lll.addAll(hrService.getJudgeInback(l2.get(i)));

                }
            }
        }else if(stage==3){
            l2=hrService.getScoreInbackByStage(3);
            if (l2.size() != 0) {
                for (int i = 0; i < l2.size(); i++) {

                    lll.addAll(hrService.getJudgeInback(l2.get(i)));

                }
            }

        }
        //todo
        return JSON.toJSONString(lll);
    }

    @PreAuthorize("hasAnyRole('MG','ADMIN')")
    @GetMapping(value ="/scoreInBack")
    public String getScoreInBack(int stages)
    {
        List<List<Object>> ll=new ArrayList<>();
        List<Long> l1=new ArrayList<>();
        if(stages==3){
        l1=hrService.getScoreInbackByStage(stages);
        if(l1.size()!=0) {
            for (int i = 0; i < l1.size(); i++) {

                ll.add( hrService.getScoreById(l1.get(i)));
            }
        }else{
            System.out.println("无");
        }

        }else if(stages==2) {
            l1=hrService.getScoreInbackByStage2(stages);
            if(l1.size()!=0) {
                for (int i = 0; i < l1.size(); i++) {

                    ll.add( hrService.getScoreById(l1.get(i)));
                }
            }else{
                System.out.println("无");
            }
        }
        return JSON.toJSONString(ll);
    }

    /***
     * 获取筛选条件
     * @return
     * [{'HR':[]},{'job':[]},{'school':[]},{'Education':[]},{'major':[]}]
     */
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    @GetMapping(value ="/getSiftTerms")
    public String getSiftTerms()
    {
        List<UserInfo> listHr=hrService.gethrName();
        List<UserInfo>  listJob=hrService.getJob();
        List<Education> listSchoolName=hrService.getschoolName();
        List<Education> listHighestEducate=hrService.getHighestEducate();
        List<Education> listMajor=hrService.getMajor();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hr",listHr);
        jsonObject.put("job",listJob);
        jsonObject.put("school_name",listSchoolName);
        jsonObject.put("highest_educate",listHighestEducate);
        jsonObject.put("major",listMajor);

        return jsonObject.toJSONString();
    }
    //测试用的接口
    @PreAuthorize("hasAnyRole('HR','MG','ADMIN')")
    @GetMapping(value ="/getScoreById")
    public String getScoreById(int id)
    {
        List<Object> list=hrService.getScoreById(id);
        //todo
        return JSON.toJSONString(list);
    }

}
