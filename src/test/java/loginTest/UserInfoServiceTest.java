package loginTest;

import com.tuski.springsec.model.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.tuski.springsec.repository.RoleRepository;
import com.tuski.springsec.repository.UserRepository;
import com.tuski.springsec.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserInfoServiceTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;
    private UserInfo userInfo;

    @Before
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository,
                                               mockRoleRepository,
                                               mockBCryptPasswordEncoder);
//        userInfo = UserInfo.builder()
//                .id(1)
//                .name("Gustavo")
//                .lastName("Ponce")
//                .email("test@test.com")
//                .build();
//        userInfo.setId(2);
//        userInfo.setEmail("test@test.com");
//        userInfo.setLastName("adsa");
//        userInfo.setPassword("asdad");

        Mockito.when(mockUserRepository.save(any()))
                .thenReturn(userInfo);
        Mockito.when(mockUserRepository.findByEmail(anyString()))
                .thenReturn(userInfo);
    }

    @Test
    public void testFindUserByEmail() {
        // Setup
        final String email = "test@test.com";

        // Run the test
        final UserInfo result = userServiceUnderTest.findUserByEmail(email);

        // Verify the results
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser() {
        // Setup
        final String email = "test@test.com";

        // Run the test
        //UserInfo result = userServiceUnderTest.saveUser();

        // Verify the results
        //assertEquals(email, result.getEmail());
    }
}