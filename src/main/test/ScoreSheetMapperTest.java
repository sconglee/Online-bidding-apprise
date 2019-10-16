import com.avic.common.constant.BidConstant;
import com.avic.common.utils.TimeUtil;
import com.avic.mapper.ScoreSheetTemplateMapper;
import com.avic.model.ScoreSheetTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ScoreSheetMapperTest
 * @Description TODO
 * @Author xulei
 * @Date 2019/10/16/016 13:29
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class ScoreSheetMapperTest {

    @Autowired
    private ScoreSheetTemplateMapper scoreSheetTemplateMapper;

    /*
    * @Author xulei
    * @Description 新增模板
    * @Date 13:33 2019/10/16/016
    * @Param 
    * @return 
    **/
    @Test
    public void insertScoreSheetTemplate() {
        ScoreSheetTemplate scoreSheetTemplate = new ScoreSheetTemplate();
        scoreSheetTemplate.setProjectName("XX工程GS-1307 高速电视测量系统改造(二次)");
        scoreSheetTemplate.setProjectNumber("0730-196012JP0027/07");
        scoreSheetTemplate.setTotalItems("技术得分" + "," + "商务得分" + "," + "价格得分");
        scoreSheetTemplate.setSequenceNumber("1" + "," + "2" + "," + "3" + "," + "4" + "," + "5" + "," + "6" + "," + "7" + "," + "8" + "," + "9" + "," + "10" + "," + "11" + ","
                + "12" + "," + "13" + "," + "14" + "," + "15" + "," + "16" + "," + "17" + "," + "18" + "," + "19" + "," + "20" + "," + "21" + "," + "22" + "," + "23");
        scoreSheetTemplate.setScoredComName("西安" + "," + "江西");
        scoreSheetTemplate.setItemWeight("2" + "," + "12" + "," + "9" + "," + "7" + "," + "5" + "," + "2" + "," + "2" + "," + "4" + "," + "2" + "," + "2" + "," + "3" + ","
                + "4" + "," + "2" + "," + "4" + "," + "6" + "," + "3" + "," + "6" + "," + "2" + "," + "3" + "," + "1" + "," + "1" + "," + "1" + "," + "17");
        scoreSheetTemplate.setStatus(BidConstant.TEMPLATE_NO_ACTIVE);
        scoreSheetTemplate.setCreateTime(TimeUtil.getTimeByDefautFormat());
        scoreSheetTemplate.setUpdateTime(TimeUtil.getTimeByDefautFormat());

        // 先查询数据库是否已经存在，如果存在则报错
        ScoreSheetTemplate result = scoreSheetTemplateMapper.findTemplateByProjectNameAndNumber(scoreSheetTemplate);
        if (result == null || result.getRemove() == BidConstant.TEMPLATE_REMOVE) {
            // 数据库中不存在，执行insert操作
            System.out.println("insert成功：" + scoreSheetTemplateMapper.insertScoreSheetTemplate(scoreSheetTemplate));

        } else {
            System.out.println("已经存在该评标打分模板,请到修改页面进行修改相关内容！！");
        }
    }
    
    /*
    * @Author xulei
    * @Description 获取模板信息列表
    * @Date 13:33 2019/10/16/016
    * @Param []
    * @return void
    **/
    @Test
    public void findAllScoreSheetTemplate() {
        List<ScoreSheetTemplate> scoreSheetTemplateList = new ArrayList<>();

        scoreSheetTemplateList = scoreSheetTemplateMapper.findAllScoreSheetTemplate();
        for (ScoreSheetTemplate scoreSheetTemplate : scoreSheetTemplateList) {
            System.out.println("项目名称是：" + scoreSheetTemplate.getProjectName() + ";    项目编号是：" + scoreSheetTemplate.getProjectNumber()
                    + ";  项目状态是：" + BidConstant.templateStatus.get(scoreSheetTemplate.getStatus())
                    + ";   模板生成时间：" + scoreSheetTemplate.getCreateTime());
        }
    }

    /*
    * @Author xulei
    * @Description 修改模板
    * 根据项目名称 + 项目编号的组合查询条件，修改评标打分模板
    * @Date 13:36 2019/10/16/016
    * @Param [scoreSheetTemplate]
    * @return void
    **/
    @Test
    public void updateScoreSheetTemplate() {
        ScoreSheetTemplate scoreSheetTemplate = new ScoreSheetTemplate();
        scoreSheetTemplate.setProjectName("XX工程GS-1307 高速电视测量系统改造(二次)");
        scoreSheetTemplate.setProjectNumber("0730-196012JP0027/02");
        scoreSheetTemplate.setScoredComName("西安" + "," + "江西" + "," + "山东建筑");
        scoreSheetTemplate.setStatus(BidConstant.TEMPLATE_NO_ACTIVE);
        scoreSheetTemplate.setUpdateTime(TimeUtil.getTimeByDefautFormat());

        scoreSheetTemplateMapper.updateScoreSheetTemplate(scoreSheetTemplate);
    }


    /*
    * @Author xulei
    * @Description 根据项目名称和项目编号查询模板
    *   后期可以修改为根据id查询
    * @Date 13:38 2019/10/16/016
    * @Param [scoreSheetTemplate]
    * @return com.avic.model.ScoreSheetTemplate
    **/
    @Test
    public void findTemplateByProjectNameAndNumber() {

        ScoreSheetTemplate scoreSheetTemplate = new ScoreSheetTemplate();
        scoreSheetTemplate.setProjectName("XX工程GS-1307 高速电视测量系统改造(二次)");
        scoreSheetTemplate.setProjectNumber("0730-196012JP0027/04");

        ScoreSheetTemplate result = scoreSheetTemplateMapper.findTemplateByProjectNameAndNumber(scoreSheetTemplate);
        if (result != null && result.getRemove() != BidConstant.TEMPLATE_REMOVE) {
            System.out.println("根据项目名称和项目编号查询评标打分模板成功：");
            System.out.println(result.getSequenceNumber());
            System.out.println(result.toString());
        } else {
            System.out.println("根据项目名称和项目编码查询失败！！");
        }

    }

    /*
    * @Author xulei
    * @Description 使模板生效或者失效
    *   前端至少给后端传送三个参数：
    *   项目名称  项目编号  状态值
    * @Date 13:39 2019/10/16/016
    * @Param [scoreSheetTemplate]
    * @return void
    **/
    @Test
    public void enableEffectiveOrNot() {
        ScoreSheetTemplate scoreSheetTemplate = new ScoreSheetTemplate();
        scoreSheetTemplate.setProjectName("XX工程GS-1307 高速电视测量系统改造(二次)");
        scoreSheetTemplate.setProjectNumber("0730-196012JP0027/04");

        // 先去数据查询数据
        ScoreSheetTemplate result = scoreSheetTemplateMapper.findTemplateByProjectNameAndNumber(scoreSheetTemplate);
        if (result != null && result.getRemove() != BidConstant.TEMPLATE_REMOVE) {
            System.out.println("根据项目名称和项目编号查询评标打分模板成功：");
            System.out.println(result.toString());

            //查询成功，修改数据，update数据库
            if (result.getStatus() == BidConstant.TEMPLATE_NO_ACTIVE) {
                // 设置为0 生效
                result.setStatus(BidConstant.TEMPLATE_ACTIVE);
            } else {
                // 设置为1 失效
                result.setStatus(BidConstant.TEMPLATE_NO_ACTIVE);
            }
            System.out.println(result.toString());

            result.setUpdateTime(TimeUtil.getTimeByDefautFormat());
            result.setCreateTime(TimeUtil.getTimeByDefautFormat());
            System.out.println(result.toString());

            scoreSheetTemplateMapper.enableEffectiveOrNot(result);

        } else {
            System.out.println("该项目名称和项目编码对应的评分模板不存在，请确认后重试！！");
        }
    }

    /*
     * @Author xulei
     * @Description 删除模板--硬删除
     *  根据评分表模板中项目名称 + 项目编码锁定唯一模板，进行删除
     * @Date 13:37 2019/10/16/016
     * @Param [scoreSheetTemplate]
     * @return void
     **/
    @Test
    public void deleteScoreSheetTemplate() {
        ScoreSheetTemplate scoreSheetTemplate = new ScoreSheetTemplate();
        scoreSheetTemplate.setProjectName("XX工程GS-1307 高速电视测量系统改造(二次)");
        scoreSheetTemplate.setProjectNumber("0730-196012JP0027/05");

        scoreSheetTemplateMapper.deleteScoreSheetTemplate(scoreSheetTemplate);
        System.out.println("删除成功！！");
    }



}
