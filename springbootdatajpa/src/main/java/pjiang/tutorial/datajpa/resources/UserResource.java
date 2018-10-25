package pjiang.tutorial.datajpa.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pjiang.tutorial.datajpa.dto.UserDTO;
import pjiang.tutorial.datajpa.service.UserService;

import java.util.List;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  14:04 2018/10/24.
 */
@RestController
@RequestMapping("/users")
public class UserResource {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Long saveUser(UserDTO userDTO) {
        UserDTO userDTO1 = userService.saveOrUpdate(userDTO);
        return userDTO1.getId();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDTO getUserById(@PathVariable("id") Long id) {
        return userService.findById(id).orElse(null);
    }
    
    @RequestMapping(value = "page/u", method = RequestMethod.GET)
    public Page<UserDTO> getAllUsersWithPage(
            @RequestParam("page") int page, @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userService.findAllByPage(pageRequest);
    }
    @RequestMapping(value = "list/u", method = RequestMethod.GET)
    public List<UserDTO> getAllUsersWithPage() {
        return userService.findAll();
    }

    
}
