import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.hh.school.social.exceptions.NoIdException;
import ru.hh.school.social.impl.entities.Recommendation;
import ru.hh.school.social.impl.entities.User;
import ru.hh.school.social.impl.entities.UserInfo;
import ru.hh.school.social.impl.services.RecommendationsDAO;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 15.01.12
 * Time: 17:56
 * To change this template use File | Settings | File Templates.
 */
public class RecommendationTests {
    private RecommendationsDAO recommendationsDao;
    private UserInfo first;
    private UserInfo second;
    private UserInfo third;
    
    @Before
    public void setup() {
        this.recommendationsDao = new RecommendationsDAO();
        User user = new User("","");
        user.setId(1L);
        first = new UserInfo(user);
        user.setId(2L);
        second = new UserInfo(user);
        user.setId(3L);
        third = new UserInfo(user);
    }

    @Test
    public void simpleAddTest1() throws NoIdException {
        Recommendation rec = new Recommendation();
        rec.setSelf(false);
        rec.setSender(first);
        rec.setWriter(second);
        rec.setSubject(third);
        recommendationsDao.putQuery(rec);
        Assert.assertEquals(rec, recommendationsDao.byId(1L));
    }

    @Test
    public void simplePutTest1(){
        Recommendation rec = new Recommendation();
        rec.setSelf(false);
        rec.setSender(first);
        rec.setWriter(second);
        rec.setSubject(third);
        recommendationsDao.putQuery(rec);
        Assert.assertEquals(rec, recommendationsDao.getQueries(2L).iterator().next());
    }

    @Test
    public void simpleAnswerTest1(){
        Recommendation rec = new Recommendation();
        rec.setSelf(false);
        rec.setSender(first);
        rec.setWriter(second);
        rec.setSubject(third);
        recommendationsDao.putQuery(rec);
        recommendationsDao.processAnswer(1L,"blablabla");
        Assert.assertEquals(rec, recommendationsDao.getInbox(1L).iterator().next());
    }

    @Test
    public void answerTest2(){
        Recommendation rec = new Recommendation();
        rec.setSelf(false);
        rec.setSender(first);
        rec.setWriter(second);
        rec.setSubject(third);
        recommendationsDao.putQuery(rec);
        recommendationsDao.processAnswer(1L,"blablabla");
        Assert.assertEquals("blablabla", ((Recommendation)recommendationsDao.getInbox(1L).iterator().next()).getMessage());
    }

    @Test
    public void answerTest3(){
        Recommendation rec = new Recommendation();
        rec.setSelf(true);
        rec.setSender(first);
        rec.setWriter(second);
        rec.setSubject(third);
        recommendationsDao.putQuery(rec);
        recommendationsDao.processAnswer(1L,"blablabla");
        Assert.assertEquals("blablabla", ((Recommendation)recommendationsDao.getSelfRecommendations(1L).iterator().next()).getMessage());
    }

    @Test
    public void accessTest1(){
        Recommendation rec = new Recommendation();
        rec.setSelf(false);
        rec.setSender(first);
        rec.setWriter(second);
        rec.setSubject(third);
        recommendationsDao.putQuery(rec);
        recommendationsDao.processAnswer(1L,"blablabla");
        Assert.assertTrue(recommendationsDao.checkAccessRights(1L,1L,"inbox"));
    }

    @Test
    public void accessTest2(){
        Recommendation rec = new Recommendation();
        rec.setSelf(true);
        rec.setSender(first);
        rec.setWriter(second);
        rec.setSubject(third);
        recommendationsDao.putQuery(rec);
        recommendationsDao.processAnswer(1L,"blablabla");
        Assert.assertTrue(recommendationsDao.checkAccessRights(1L,1L,"recommendations"));
    }

    @Test
    public void accessTest3(){
        Recommendation rec = new Recommendation();
        rec.setSelf(false);
        rec.setSender(first);
        rec.setWriter(second);
        rec.setSubject(third);
        recommendationsDao.putQuery(rec);
        recommendationsDao.processAnswer(1L,"blablabla");
        Assert.assertFalse(recommendationsDao.checkAccessRights(3L,1L,"inbox"));
    }

    @Test
    public void accessTest4(){
        Recommendation rec = new Recommendation();
        rec.setSelf(true);
        rec.setSender(first);
        rec.setWriter(second);
        rec.setSubject(third);
        recommendationsDao.putQuery(rec);
        recommendationsDao.processAnswer(1L,"blablabla");
        Assert.assertFalse(recommendationsDao.checkAccessRights(3L,1L,"recommendations"));
    }


}
