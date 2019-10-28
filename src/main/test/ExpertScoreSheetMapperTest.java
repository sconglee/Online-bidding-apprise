import com.avic.common.utils.TimeUtil;
import com.avic.mapper.ExpertScoreSheetMapper;
import com.avic.model.ExpertScoreSheet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author sconglee
 * @date 2019/10/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class ExpertScoreSheetMapperTest {

    @Autowired
    private ExpertScoreSheetMapper expertScoreSheetMapper;


    @Test
    public void testFindScoreInfoByExpertNameAndCompanyName() {
        ExpertScoreSheet expertScoreSheet = new ExpertScoreSheet();
        expertScoreSheet.setCompanyName("西安");
        expertScoreSheet.setExpertName("lsc");
        ExpertScoreSheet expertScoreSheet1 = expertScoreSheetMapper.findScoreInfoByExpertNameAndCompanyName(expertScoreSheet);
        System.out.println(expertScoreSheet1.getPoint());
    }

    @Test
    public void testInsertExpertScoreSheet() {
        ExpertScoreSheet expertScoreSheet = new ExpertScoreSheet();
        expertScoreSheet.setProjectName("XX工程GS-1307 高速电视测量系统改造(二次)");
        expertScoreSheet.setProjectNumber("0730-196012JP0027/02");
        expertScoreSheet.setCompanyName("西安");
        expertScoreSheet.setExpertName("xl");
        expertScoreSheet.setPoint("1,2,3,4,5,6,7");
        expertScoreSheet.setCreateTime(TimeUtil.getTimeByDefautFormat());
        expertScoreSheet.setUpdateTime(TimeUtil.getTimeByDefautFormat());

        Integer result = expertScoreSheetMapper.updateExpertScoreSheet(expertScoreSheet);
        if (result > 0) {
            System.out.println(expertScoreSheet.getPoint());
        } else {
            System.out.println("失败");
        }
    }






}
