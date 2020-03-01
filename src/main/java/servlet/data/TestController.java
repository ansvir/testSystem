package servlet.data;

import java.util.List;

import dao.TestDAO;
import entity.Test;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/data")
public class TestController {

    private final static Logger log = Logger.getLogger(TestController.class);

    @Autowired
    private TestDAO testDAO;

    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    public List<Test> getAll() {
        log.debug("Enter getAll()");
        return testDAO.findAll();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Test getById(@RequestParam("id") Long testId) {
        log.debug("Enter getById()");
        return testDAO.findById(testId);
    }
}