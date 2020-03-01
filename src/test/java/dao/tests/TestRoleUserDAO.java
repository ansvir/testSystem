package dao.tests;

import dao.RoleUserDAO;
import org.apache.log4j.Logger;
import org.junit.Test;
import entity.RoleUser;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class TestRoleUserDAO {

    private final static Logger log = Logger.getLogger(TestRoleUserDAO.class);

    @Test
    public void testFindByIds() {
        RoleUserDAO roleUserDAO = new RoleUserDAO();
        List<RoleUser> rolesUsers = roleUserDAO.findAll();
        assertNotNull("List is null", rolesUsers);
        assertFalse("List is empty", rolesUsers.isEmpty());
        int roleUserSize = rolesUsers.size();
        Random random = new Random();
        int randomRoleUserSet = random.nextInt(roleUserSize);
        Long randomRoleId = roleUserDAO.findAll().get(randomRoleUserSet).getRoleId();
        Long randomUserId = roleUserDAO.findAll().get(randomRoleUserSet).getUserId();
        RoleUser roleUser = roleUserDAO.findByIds(randomRoleId, randomUserId);
        assertNotNull("Set is null", roleUser);
        log.info(roleUser);
    }
}
