import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.hh.school.social.exceptions.NoIdException;
import ru.hh.school.social.impl.repositories.MemSessionRepository;
import ru.hh.school.social.impl.repositories.SessionRepository;
import ru.hh.school.social.impl.services.SessionService;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 15.01.12
 * Time: 17:56
 * To change this template use File | Settings | File Templates.
 */
public class SessionTests {
    SessionRepository sessions;
    SessionService service;

    @Before
    public  void setup(){
        sessions = new MemSessionRepository();
        service = new SessionService(sessions);
    }

    @Test
    public void setSessionTest1() throws NoIdException {
        service.setAuth(2L);
        Assert.assertEquals(service.getSessionUser("1"),sessions.byId(1L).getUserId());
    }

    @Test
    public void setSessionTest2(){
        Assert.assertEquals(service.setAuth(1L).getUserId(),service.getSessionUser("1"));
    }
    
    @Test
    public void checkTest1(){
        service.setAuth(2L);
        Assert.assertTrue(service.isAuth("1"));
    }

    @Test
    public void checkTest2(){
        service.setAuth(2L);
        Assert.assertFalse(service.isAuth("2"));
    }


    @Test
    public void unsetTest(){
        service.setAuth(2L);
        service.unsetAuth("1");
        Assert.assertEquals(null,service.getSessionUser("1"));
    }
}
