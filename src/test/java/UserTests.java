import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import ru.hh.school.social.exceptions.EmailAlreadyBoundException;
import ru.hh.school.social.exceptions.IncorrectPasswordException;
import ru.hh.school.social.exceptions.NoEmailException;
import ru.hh.school.social.exceptions.NoIdException;
import ru.hh.school.social.impl.entities.User;
import ru.hh.school.social.impl.repositories.MemUserRepository;
import ru.hh.school.social.impl.repositories.UserRepository;
import ru.hh.school.social.impl.services.UserFacade;
import ru.hh.school.social.impl.services.UserService;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 15.01.12
 * Time: 17:56
 * To change this template use File | Settings | File Templates.
 */
public class UserTests {
    private UserService userService;
    private UserRepository users;
    private UserFacade facade;
    @Before
    public void setup(){
        users = new MemUserRepository();
        userService = new UserService(users);
        facade = new UserFacade(users);
    }

    @Test
    public void simple(){
        User user = new User("a","a");
        Assert.assertTrue(user.checkPassword("a"));
    }

    @Test 
    public void  repoTest1() throws NoEmailException {
        User user = new User("a","a");
        users.put(user);
        Assert.assertEquals(users.byEmail("a"),user);
    }

    @Test
    public void  repoTest2() throws NoIdException {
        User user = new User("a","a");
        users.put(user);
        Assert.assertEquals(users.byId(1L),user);
    }

    @Test
    public void  serviceTest1() throws EmailAlreadyBoundException, NoEmailException, IncorrectPasswordException {
        userService.registerUser("a","b","c");
        Assert.assertTrue(userService.checkPassword("a", "b"));
    }
    
    @Test
    public  void  serviceTest2() throws EmailAlreadyBoundException, NoEmailException, NoIdException {
        userService.registerUser("a","b","c");
        Assert.assertEquals(facade.getById(1L),facade.getByEmail("a"));
    }

    @Test
    public  void  serviceTest3() throws EmailAlreadyBoundException, NoEmailException, NoIdException {
        userService.registerUser("a","b","c");
        Assert.assertEquals(facade.getById(1L).getEmail(),"a");
    }



}
