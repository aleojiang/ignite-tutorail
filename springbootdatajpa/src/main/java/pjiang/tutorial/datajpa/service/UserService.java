package pjiang.tutorial.datajpa.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pjiang.tutorial.datajpa.dto.UserDTO;
import pjiang.tutorial.datajpa.repository.UserRepository;
import pjiang.tutorial.datajpa.repository.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  13:23 2018/10/24.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UserDTO saveOrUpdate(UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        userEntity = userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDTO.class);
    }
    
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Page<UserDTO> findAllByPage(Pageable pageable) {
        Page<UserEntity> entities = userRepository.findAll(pageable);
        return entities.map(e -> modelMapper.map(e, UserDTO.class));
    }
    
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(e -> modelMapper.map(e, UserDTO.class))
                .collect(Collectors.toList());
    }
    
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Optional<UserDTO> findDistinctByFirstName(String value) {
        return userRepository.findDistinctByFirstName(value)
                .map(e -> modelMapper.map(e, UserDTO.class));
    }
    
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(e -> modelMapper.map(e, UserDTO.class));
    }
    
}
