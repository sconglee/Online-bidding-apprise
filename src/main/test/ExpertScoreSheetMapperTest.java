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
    public void testFindAllExpertScoreByProjectNumber() {
        List<ExpertScoreSheet> expertScoreSheetList = expertScoreSheetMapper.findAllExpertScoreByProjectNumber("0730-196012JP0027/02");
        for (ExpertScoreSheet expertScoreSheet : expertScoreSheetList) {
            System.out.println(expertScoreSheet.getCompanyName());
        }
    }

    @Test
    public void testFindScoreInfoByExpertNameAndCompanyName() {
        ExpertScoreSheet expertScoreSheet = new ExpertScoreSheet();
        expertScoreSheet.setCompanyName("西安");
        expertScoreSheet.setExpertName("lsc");
        ExpertScoreSheet expertScoreSheet1 = expertScoreSheetMapper.findScoreInfoByExpertNameAndCompanyName(expertScoreSheet);
        System.out.println(expertScoreSheet1.getPoint());
    }
}
